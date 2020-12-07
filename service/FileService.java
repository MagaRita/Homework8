package service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileService {

    public static String[] read(String path) throws Exception {
        return Files.readAllLines(Paths.get(path)).toArray(new String[0]);
    }

    public static void write(String path, String str) throws Exception {
        Files.write(Paths.get(path), str.getBytes(), StandardOpenOption.APPEND);
    }

    public static boolean createFile(String fileName){
        try {
            File file = new File("C:\\Users\\ACER\\IdeaProjects\\Homework8\\src\\" + fileName);
            if (file.createNewFile()) {
                System.out.println("The following file has been created to add bicycles: " + file.getName());
            } else {
                System.out.println("File already exists.");
                return false;
            }

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            System.exit(0);
        }
        return true;
    }
}
