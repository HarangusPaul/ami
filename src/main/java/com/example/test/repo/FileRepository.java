package com.example.test.repo;



import com.example.test.domain.BaseEntity;
import com.example.test.util.TransformerToArrayBasedOnString;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class FileRepository<T extends BaseEntity> extends Repository<T> {

    private Repository<T> data;
    private final String file;

    public FileRepository(String file) throws IOException, ClassNotFoundException {
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

    public void loadFile() {
        try (Scanner myReader = new Scanner(new File(file))) {
            if (!myReader.hasNextLine())
                return;
            var a = myReader.nextLine();
            TransformerToArrayBasedOnString<T> transformerToArrayBasedOnString = new TransformerToArrayBasedOnString<>();
            this.data.data = transformerToArrayBasedOnString.doTransform(a.substring(a.indexOf("[")+1,a.lastIndexOf("]")));
        } catch (FileNotFoundException ignored) {
        }
    }


    public void saveFile(){
        try (FileWriter myWriter = new FileWriter(file);) {
            myWriter.write("[");
            int count = 0;
            for (var ent : this.data.data) {
                myWriter.write(ent.getForDb());
                count++;
                if(this.data.data.size() > count){
                    myWriter.write(",");
                }
            }
            myWriter.write("]");
        } catch (Exception e) {
        }
    }

}
