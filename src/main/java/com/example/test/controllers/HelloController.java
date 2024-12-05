package com.example.test.controllers;

import com.example.test.util.FileLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class HelloController {
    @FXML
    private BorderPane borderPane;

    @FXML
    protected void handlePacientButton() {
        Pane p = new FileLoader().getView("listPage");
        borderPane.setCenter(p);
    }

    @FXML
    protected void handleAppointmentButton() {
        Pane p = new FileLoader().getView("addPage");
        borderPane.setCenter(p);
    }

    @FXML
    protected void handleRaportButton() {
        Pane p = new FileLoader().getView("filterPage");
        borderPane.setCenter(p);
    }

}