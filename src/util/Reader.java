package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Reader {
    public static String read_ln(String message) throws IOException {
        System.out.print(message!=null?message:"Enter:");
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        return reader.readLine();
    }

}
