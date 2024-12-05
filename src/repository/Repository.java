package repository;

import domain.Building;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Repository<T extends Building> implements Iterable<T>, IRepository<T>,Serializable {
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

    public T getById(String id) throws NoSuchElementException {
        for (T entity : data) {
            Building entity1 = (Building) entity;
//            if (entity1.getAutor().equals(id)) {
//                return entity;
//            }
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
