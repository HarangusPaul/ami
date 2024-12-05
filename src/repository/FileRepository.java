package repository;

import domain.Block;
import domain.Building;
import domain.House;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileRepository<T extends Building> extends Repository<T> {

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
    public T getById(String id) {
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
//            saveFile();
    }

    public void loadFile() {
        try (Scanner myReader = new Scanner(new File(file))) {
            while(myReader.hasNextLine()){
                var line = myReader.nextLine();
                if(file.substring(file.lastIndexOf('/')+1).equals("blocuri"))
                {
                    var b = line.strip().split(";");
                    if(Integer.parseInt(b[1]) == 0 || Integer.parseInt(b[1]) < Integer.parseInt(b[2])){
                        throw new Exception("Nu poti avea asa ceva!Verifica fisierul");
                    }
                    this.data.add((T) new Block(line.strip().split(";")));
                }
                if(file.substring(file.lastIndexOf('/')+1).equals("case"))
                    this.data.add((T) new House(line.strip().split(";")));
            }
        } catch (FileNotFoundException ignored) {
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void saveFile(){
        try (FileWriter myWriter = new FileWriter(file);) {
            for (var ent : this.data.data) {
//                myWriter.write(ent.getForDB());
            }
        } catch (Exception e) {
        }
    }

}
