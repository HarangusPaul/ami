package com.example.test.util.dataTypes;

public class StringFunctions {
    public static boolean containsIgnoreCase(String mainStr, String subStr) {
        if (mainStr == null || subStr == null || mainStr.isEmpty() || subStr.isEmpty()) {
            return false;
        }
        return mainStr.toLowerCase().contains(subStr.toLowerCase());
    }

    public static boolean emptyStrings(String... p) {
        for(var st:p){
            if (st.equals("")) { return true;}
        }
        return false;
    }


}
