package com.example.test.repo;


import com.example.test.domain.BaseEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.UUID;

public class Repository<T extends BaseEntity> implements Iterable<T>, IRepository<T>,Serializable {
    protected ArrayList<T> data;

    public Repository() {
        this.data = new ArrayList<T>();
    }

    public void add(T entity) throws Exception {
        data.add(entity);
    }

    public int size(){ return this.data.size();}

    public T get(int poz) {
        return this.data.get(poz);
    }

    public T getById(Integer id) throws NoSuchElementException {
        for (T entity : data) {
            BaseEntity entity1 = (BaseEntity) entity;
            if (entity1.getId().equals(id)) {
                return entity;
            }
        }throw new NoSuchElementException();
    }


    public ArrayList<T> getData() {
        return data;
    }

    public void update(T upEntity) throws Exception {
        this.data.set(this.data.indexOf(upEntity),upEntity);
    }

    public void delete(T entity) throws Exception {
        this.data.remove(entity);
    }

    @Override
    public Iterator<T> iterator() {
        return this.data.iterator();
    }

    public void setArray(ArrayList<T> a) {
        this.data = a;
    }

}
