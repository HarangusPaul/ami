package com.example.test.util;

import com.example.test.domain.BaseEntity;
import com.example.test.domain.Question;
import com.example.test.service.IService;
import javafx.scene.control.Alert;

public class EntityManage<T extends BaseEntity> {
    private static EntityManage instance;

    private IService<T> service;


    public IService<T> getService() {
        return service;
    }

    private void setService(IService<T> service) {
        this.service = service;
    }

    private EntityManage(IService<T> service) {
        setService(service);
    }

    public static void setInstance(IService<Question> service) {
        EntityManage.instance = new EntityManage(service);
    }

    public static EntityManage getInstance() {
        return instance;
    }

    public void printError(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error Dialog");
        alert.setHeaderText("Look, you dumdum!");
        alert.setContentText(msg);

        alert.showAndWait();
    }
}
