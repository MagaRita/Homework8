package service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class FileService {

    /**
     * The info from the file is read.
     * @param path
     * @return
     * @throws IOException
     */
    static List<String> read(String path) throws IOException {
        return Files.readAllLines(Paths.get(path));
    }

    /**
     * The info is written in the file.
     * @param path
     * @param text
     * @throws IOException
     */
    public static void write(String path, String text) throws IOException {
        Files.write(Paths.get(path), text.getBytes(), StandardOpenOption.APPEND);
    }

    /**
     * The function returns false if the file already exists.
     * If the file doesn't exist, then it creates that file and returns true.
     * @param fileName
     * @return
     */

    public static boolean createFile(String fileName){
        try {
            File file = new File("C:\\Users\\ACER\\IdeaProjects\\Homework8\\src\\" + fileName);
            if (file.createNewFile()) {
                // This is to check if it works fine.
                 System.out.println("The following file has been created: " + file.getName());
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
