package com.example.test.repo;



import com.example.test.domain.BaseEntity;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class GenericRepository<T extends BaseEntity> implements IRepository<T> {

    private final String DATABASE_URL;
    private final Class<T> classDefinition;

    private final Class<BaseEntity> baseEntityClass = BaseEntity.class;

    public GenericRepository(String database_url, Class<T> classDefinition) {
        DATABASE_URL = database_url;
        this.classDefinition = classDefinition;
    }

    private Field[] getFieldList() throws NoSuchFieldException {
        var listOfFields = Arrays.stream(classDefinition.getDeclaredFields()).toList();
        var firstField = baseEntityClass.getDeclaredField("id");
        List<Field> finalList = new ArrayList<>();
        finalList.add(firstField);
        finalList.addAll(listOfFields);

        return finalList.toArray(new Field[0]);
    }

    private void createTable() {
        String tableName = classDefinition.getSimpleName().toLowerCase();
        String columns = Arrays.stream(classDefinition.getDeclaredFields())
                .map(field -> field.getName() + " TEXT")
                .collect(Collectors.joining(", "));
        columns = "id," + columns;
        String query = String.format("CREATE TABLE IF NOT EXISTS %s (%s);", tableName, columns);

        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(T entity) {
        createTable();

        String tableName = classDefinition.getSimpleName().toLowerCase();
        String columnNames = Arrays.stream(classDefinition.getDeclaredFields())
                .map(Field::getName)
                .collect(Collectors.joining(", "));
        columnNames = "id," + columnNames;
        String placeholders = Arrays.stream(classDefinition.getDeclaredFields())
                .map(field -> "?")
                .collect(Collectors.joining(", "));

        String insertQuery = String.format("INSERT INTO %s (%s) VALUES (?,%s);", tableName, columnNames, placeholders);

        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            int parameterIndex = 1;
            for (Field field : this.getFieldList()) {
                field.setAccessible(true);
                Object value = field.get(entity);
                preparedStatement.setObject(parameterIndex++, value);
            }

            preparedStatement.executeUpdate();
        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int size() {
        return this.getAll().size();
    }

    @Override
    public T get(int poz) {
        return this.getAll().get(poz);
    }

    @Override
    public T getById(Integer id) {
        var a = this.getAll().stream().filter(n -> n.getId().toString().equals(id.toString())).toList();
        return a.get(0);
    }

    @Override
    public ArrayList<T> getData() {
        return (ArrayList<T>) this.getAll();
    }

    public void update(T entity) {
        createTable();

        String tableName = classDefinition.getSimpleName().toLowerCase();
        String updateColumns = Arrays.stream(classDefinition.getDeclaredFields())
                .map(field -> field.getName() + " = ?")
                .collect(Collectors.joining(", "));
        String updateQuery = String.format("UPDATE %s SET %s WHERE id = ?", tableName, updateColumns);

        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

            int parameterIndex = 1;
            for (Field field : classDefinition.getDeclaredFields()) {
                field.setAccessible(true);
                Object value = field.get(entity);
                preparedStatement.setObject(parameterIndex++, value);
            }

            Field idField = baseEntityClass.getDeclaredField("id");
            idField.setAccessible(true);
            preparedStatement.setObject(parameterIndex, idField.get(entity));

            preparedStatement.executeUpdate();
        } catch (SQLException | IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(T entity) throws Exception {
        createTable();

        String tableName = classDefinition.getSimpleName().toLowerCase();
        String deleteQuery = String.format("DELETE FROM %s WHERE id = ?", tableName);

        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {

            preparedStatement.setString(1, entity.getId().toString());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<T> getAll() {
        createTable();

        List<T> entities = new ArrayList<>();
        String tableName = classDefinition.getSimpleName().toLowerCase();
        String selectQuery = String.format("SELECT * FROM %s;", tableName);

        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (resultSet.next()) {
                T entity = classDefinition.getDeclaredConstructor().newInstance();

                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    if (Objects.equals(columnName, "id")) {
                        entity.setId(resultSet.getObject(i).toString());
                        continue;
                    }
                    Field field = getFieldByName(classDefinition, columnName);
                    if (field != null) {
                        field.setAccessible(true);
                        Object value = resultSet.getObject(i);
                        setField(entity, field, value);
                    }
                }
                entities.add(entity);
            }
        } catch (SQLException | IllegalAccessException | InstantiationException | NoSuchMethodException |
                 InvocationTargetException e) {
            e.printStackTrace();
        }
        return entities;
    }

    private Field getFieldByName(Class<?> clazz, String name) {
        try {
            return clazz.getDeclaredField(name);
        } catch (NoSuchFieldException e) {
//            try {
//                return baseEntityClass.getDeclaredField(name);
//            } catch (NoSuchFieldException ex) {
            return null;
//            }
        }
    }

    private void setField(Object entity, Field field, Object value) throws IllegalAccessException {
        field.setAccessible(true);
        Class<?> fieldType = field.getType();
        if (fieldType == int.class || fieldType == Integer.class) {
            field.set(entity, value != null ? Integer.parseInt(value.toString()) : null);
        } else if (fieldType == double.class || fieldType == Double.class) {
            field.set(entity, value != null ? ((Number) value).doubleValue() : null);
        } else if (fieldType == LocalDate.class) {
            // Assuming value is a String representing a date in ISO_LOCAL_DATE format
            field.set(entity, value != null ? LocalDate.parse((String) value) : null);
        }
//        else if (Pacient.class.isAssignableFrom(fieldType)) {
//            // If the field is of type Pacient, assume value is a String and create a new Pacient
//            field.set(entity, value != null ? createPacientFromDbString((String) value) : null);}
        else {
            field.set(entity, value);
        }
    }

//    private Pacient createPacientFromDbString(String dbString) {
//        String[] parts = dbString
//                .replaceAll("[()]", "") // Remove parentheses
//                .split("[= ]+");
//
//        UUID id = UUID.fromString(parts[7]);
//        String firstName = parts[1];
//        String lastName = parts[3];
//        int age = Integer.parseInt(parts[5]);
//
//        return new Pacient(id, firstName, lastName, age);
//    }
}