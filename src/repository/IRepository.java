package repository;

import domain.Building;

import java.util.ArrayList;

public interface IRepository<T extends Building> {
    void add(T entity) throws Exception;

    int size();

    T get(int poz);

    T getById(String id);

    ArrayList<T> getData();

    void update(T upEntity) throws Exception;

    void delete(T entity) throws Exception;
}
