package com.example.test.controllers;

import com.example.test.domain.QDTO;
import com.example.test.domain.Question;
import com.example.test.util.EntityManage;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ListController implements Initializable {
    public ListView listOfObjectsInView;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            List<Question> elements = EntityManage.getInstance().getService().returnAll();
            elements = elements.stream().sorted(Comparator.comparing(Question::getDificulty).thenComparing(Comparator.comparing(Question::getId))).collect(Collectors.toList());
            var elementsList = elements.stream().map(Object::toString).collect(Collectors.joining(",")).toString().split(",");
            listOfObjectsInView.getItems().addAll(elementsList);
        } catch (Exception exception){
            EntityManage.getInstance().printError(exception.getMessage());
        }
    }
}
