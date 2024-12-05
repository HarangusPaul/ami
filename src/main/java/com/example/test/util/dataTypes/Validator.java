package com.example.test.util.dataTypes;

import com.example.test.domain.Difficulty;
import com.example.test.domain.Question;

public class Validator {
    public static boolean isActivity(Question a) {
        boolean ok = false;
        var b = a.getDificulty();
        if(b.equals(Difficulty.HIGH.getDisplayName()) || b.equals(Difficulty.MEDIUM.getDisplayName())||b.equals(Difficulty.LOW.getDisplayName())) {
            ok = true;
            if(!a.getRaspunsCorect().equals( a.getRaspuns1()) && !a.getRaspunsCorect().equals( a.getRaspuns2())&& !a.getRaspunsCorect().equals( a.getRaspuns3()))
                ok = false;
        }
        return ok;
    }
}
