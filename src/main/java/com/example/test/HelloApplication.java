package com.example.test;

import com.example.test.domain.BaseEntity;
import com.example.test.domain.Difficulty;
import com.example.test.domain.Question;
import com.example.test.repo.GenericRepository;
import com.example.test.repo.Repository;
import com.example.test.service.IService;
import com.example.test.util.EntityManage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.UUID;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            EntityManage.setInstance(new IService<Question>(new GenericRepository<>("jdbc:sqlite:src/main/java/database.db", Question.class)));
//            EntityManage.getInstance().getService().add(new Question(EntityManage.getInstance().getService().getId(),"","","","","", Difficulty.HIGH.toString()));
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.show();
        } catch (Exception exception){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Look, you dumdum!");
            alert.setContentText(exception.toString());

            alert.showAndWait();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}