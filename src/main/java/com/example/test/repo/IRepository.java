package com.example.test.repo;

import com.example.test.domain.BaseEntity;

import java.util.ArrayList;
import java.util.UUID;

public interface IRepository<T extends BaseEntity> {
    void add(T entity) throws Exception;

    int size();

    T get(int poz);

    T getById(Integer id);

    ArrayList<T> getData();

    void update(T upEntity) throws Exception;

    void delete(T entity) throws Exception;
}
