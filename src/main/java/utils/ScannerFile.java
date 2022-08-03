package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ScannerFile {

    public static void main(String[]args) throws FileNotFoundException {

    }
    public static String getPassword() throws FileNotFoundException {
        File file = new File("/Users/scret/SenderPassword.txt");
        Scanner scan = new Scanner(file);
        String passwd = null;
        while(scan.hasNextLine()) passwd = scan.nextLine();
        return passwd;
    }
}
