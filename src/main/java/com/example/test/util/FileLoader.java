package com.example.test.util;


import com.example.test.HelloApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;


public class FileLoader {
    private Pane view;

    public Pane getView(String fileName) {

        try {
            URL file = HelloApplication.class.getResource( fileName + ".fxml");
            if(file == null)
                throw new FileNotFoundException();
            view = new FXMLLoader().load(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return view;
    }
}
