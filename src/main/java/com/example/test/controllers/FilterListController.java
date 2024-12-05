package com.example.test.controllers;

import com.example.test.domain.Difficulty;
import com.example.test.domain.Question;
import com.example.test.util.EntityManage;
import com.example.test.util.dataTypes.SavingFunctions;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class FilterListController implements Initializable {
    public ListView listOfObjectsInView;
    public TextField NumarIntrebari;
    public TextField Min;
    public TextField Max;

    private ObservableList<Question> yourObservableList = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            setYourObservableList();
        } catch (Exception exception){
            EntityManage.getInstance().printError(exception.getMessage());
        }
    }

    public void handleGen() {
        try {

            var nr = Integer.parseInt(NumarIntrebari.getText());
            if(nr >  EntityManage.getInstance().getService().returnAll().size())
                throw new Exception("Nu ai destule intrebari");
            var dif = Min.getText();
            boolean ok = false;

            if(dif.equals(Difficulty.HIGH.getDisplayName()) || dif.equals(Difficulty.MEDIUM.getDisplayName())
                    ||dif.equals(Difficulty.LOW.getDisplayName())) {
                ok = true;
            }

            if(!ok){
                throw new Exception("Ceva introdus gresit!");
            }

            Thread newtThread = new Thread(new Runnable() {
                List<Question> e;

                @Override
                public void run() {
                    try {
                        e  = EntityManage.getInstance().getService().questionListF(EntityManage.getInstance().getService()
                                .returnAll(),nr,dif);
                    } catch (Exception ex) {
                        EntityManage.getInstance().printError(ex.getMessage());
                    }
                    Platform.runLater(() -> modifyList(e));
                }
            });
            newtThread.start();


        } catch (Exception exception){
            EntityManage.getInstance().printError(exception.getMessage());
        }
    }

        private void initListView(List<Question> piesas) {
            SavingFunctions.saveArrayListToFile(piesas,"src/main/java/com/example/test/question");
        var elementsList = piesas.stream().sorted(Comparator.comparing(Question::getId)).map(Object::toString).collect(Collectors.joining(",")).split(",");
        listOfObjectsInView.getItems().addAll(elementsList);
    }


    public void setYourObservableList() {
        // Initialize your ObservableList
        // Add a listener to your ObservableList
        yourObservableList.addListener((ListChangeListener<Question>) change -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    // Elements were added, handle the change
                    handleListChange();
                } else if (change.wasRemoved()) {
                    // Elements were removed, handle the change
//                    handleListChange();
                }
                // Other types of changes can be handled similarly
            }
        });
    }

    public void handleListChange() {
        // Implement the logic to handle the change in the ObservableList
        initListView(yourObservableList.stream().toList());
        yourObservableList.clear();
    }

    public void modifyList(List<Question> piese) {
        // Example: Modifying the ObservableList to trigger the listener
        yourObservableList.addAll(piese);
    }
}
