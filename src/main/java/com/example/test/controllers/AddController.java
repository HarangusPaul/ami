package com.example.test.controllers;

import com.example.test.domain.Question;
import com.example.test.util.EntityManage;
import com.example.test.util.dataTypes.StringFunctions;
import com.example.test.util.dataTypes.Validator;
import javafx.scene.control.TextField;

import java.util.Comparator;
import java.util.List;

public class AddController {


    public TextField q;
    public TextField rasps;
    public TextField crasp;
    public TextField punctaj;

    public void handleAdd() {
        try {
            var raspunsuri = rasps.getText().split(",");
            if (!StringFunctions.emptyStrings(q.getText(), raspunsuri[0], raspunsuri[1], raspunsuri[2],crasp.getText(),punctaj.getText() )){
                var a = new Question(EntityManage.getInstance().getService().getId(), q.getText(), raspunsuri[0], raspunsuri[1], raspunsuri[2], crasp.getText(), punctaj.getText());
                if(Validator.isActivity(a)){
                    EntityManage.getInstance().getService().add(a);
                    return;
                }
                throw new Exception("Invalid Question!");
            }

        } catch (Exception exception) {
            EntityManage.getInstance().printError(exception.getMessage());
        }
    }
}
