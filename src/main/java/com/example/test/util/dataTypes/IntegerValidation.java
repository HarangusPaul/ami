package com.example.test.util.dataTypes;

public class IntegerValidation {
    public static void valid(String... nrs) throws Exception {
        try {
            for (var nr:nrs){
                Integer.parseInt(nr);
            }
        }catch (Exception exception) {
            throw new Exception("Not a number!");
        }

    }
}
