package com.example.test.repo;

import com.example.test.domain.BaseEntity;

import java.io.*;
import java.util.ArrayList;
import java.util.UUID;

public class BinRepository<T extends BaseEntity> extends Repository<T> {

    private Repository<T> data;
    private final String file;


    public BinRepository(String file) throws Exception {
        this.file = file;
        this.data = new Repository<T>();
        loadFile();
    }

    @Override
    public int size() {
        return this.data.size();
    }

    @Override
    public T get(int poz) {
        return this.data.get(poz);
    }

    @Override
    public T getById(Integer id) {
        return this.data.getById(id);
    }

    @Override
    public ArrayList<T> getData() {
        return this.data.getData();
    }

    @Override
    public void add(T entity) throws Exception {
        data.add(entity);
        saveFile();
    }
    @Override
    public void update(T upEntity) throws Exception {
        data.update(upEntity);
        saveFile();
    }

    @Override
    public void delete(T entity) throws Exception {
        data.delete(entity);
        saveFile();
    }

    public void loadFile() throws Exception {
        try (ObjectInputStream oos = new ObjectInputStream(new FileInputStream(file))) {
            var a = (ArrayList<T>) oos.readObject();
            this.data.setArray(a);
        } catch (IOException e) {
        } catch (Exception e) {}
    }


    public void saveFile() throws Exception {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(this.data.getData());
        } catch (IOException e) {
        }
    }

}
