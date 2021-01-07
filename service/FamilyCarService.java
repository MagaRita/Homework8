package service;

import model.Bicycle;
import model.Bus;
import model.FamilyCar;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static service.FileService.createFile;
import static service.FileService.write;

public class FamilyCarService {

    public static final String PATH = "C:\\Users\\ACER\\IdeaProjects\\Homework8\\src\\familyCar.txt";

    private static List<FamilyCar> convert(List<String> data) {
        List<FamilyCar> familyCars = new ArrayList<>();
        for (String x : data) {
            familyCars.add(new FamilyCar(x));
        }
        return familyCars;
    }

    public FamilyCar createFamilyCar(int index) {

        Scanner scanner = new Scanner(System.in);
        int wheelNumber, price = 0, cadence = 0, gear = 0, year = 0,made = 0, mileage = 0;
        double speed = 0;
        char type = 0;
        String brand = "", model = "", color = "", plateNumber = "";

        System.out.println("Enter wheelNumber:");
        wheelNumber = scanner.nextInt();
        String str = wheelNumber + ",";

        String path = "C:\\Users\\ACER\\IdeaProjects\\Homework8\\src\\familyCar.txt";
        try {
            if (index == 0) {

                Files.write(Paths.get(path), str.getBytes());
            } else {
                write(path, str);
            }
        } catch (Exception exception) {
            System.out.println("An error occurred.");
            exception.printStackTrace();
            System.exit(0);
        }

        try {
            System.out.println("Enter speed:");
            speed = scanner.nextDouble();
            str = speed + ",";
            write(path, str);
            System.out.println("Enter price:");
            price = scanner.nextInt();
            str = price + ",";
            write(path, str);
            System.out.println("Enter brand:");
            brand = scanner.next();
            str = brand + ",";
            write(path, str);
            System.out.println("Enter plateNumber:");
            plateNumber = scanner.next();
            str = plateNumber + ",";
            write(path, str);
            System.out.println("Enter model:");
            model = scanner.next();
            str = model + ",";
            write(path, str);
            System.out.println("Enter color:");
            color = scanner.next();
            str = color + ",";
            write(path, str);
            System.out.println("Enter made:");
            made = scanner.nextInt();
            str = made + ",";
            write(path, str);
            System.out.println("Enter year:");
            year = scanner.nextInt();
            str = year + ",";
            write(path, str);
            System.out.println("Enter mileage:");
            mileage = scanner.nextInt();
            str = mileage + ",";
            write(path, str);;
            System.out.println("Enter type:");
            type = scanner.next().charAt(0);
            str = String.valueOf(type);
            write(path, str + "\n");

            System.out.println("Successfully wrote in the file.");
        } catch (Exception exception) {
            System.out.println("An error occurred.");
            exception.printStackTrace();
            System.exit(0);
        }

        return new FamilyCar(wheelNumber, speed, price, brand, plateNumber, model, color, made, year, mileage, type);
    }

    public FamilyCar[] writeOrReadInfoInFamilyCarObject() {

        String familyCarFileName = "familyCar.txt";
        FamilyCar[] familyCars;

        if (createFile(familyCarFileName)) {

            System.out.println("-----------------------");
            System.out.print("Type how many family cars should be created: ");
            Scanner s = new Scanner(System.in);
            int count = s.nextInt();
            System.out.println("We will create " + count + " different family cars.");
            System.out.println("Let's enter the information for each of them: ");
            familyCars = new FamilyCar[count];

            for (int i = 0; i < count; i++) {
                familyCars[i] = createFamilyCar(i);
            }
            System.out.println();
        } else {
            String path = "C:\\Users\\ACER\\IdeaProjects\\Homework8\\src\\familyCar.txt";
            List<String> read = null;
            try {
                read = FileService.read(path);
            } catch (Exception e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

            familyCars = new FamilyCar[read.size()];


            fillFamilyCars(familyCars, read);

            System.out.println("-----------------------");
        }
        return familyCars;
    }

    private void fillFamilyCars(FamilyCar[] familyCars, List<String> row) {

        int defaultFamilyCarsMembers = 11;
        int index = 0;
        for (int i = 0; i < row.size(); i++) {
            String[] member = row.get(i).split(",");
            if (member.length == defaultFamilyCarsMembers) {
                familyCars[index++] = new FamilyCar(Integer.parseInt(member[0]), Double.parseDouble(member[1]),
                        Integer.parseInt(member[2]), member[3], member[4], member[5],  member[6],
                        Integer.parseInt(member[7]), Integer.parseInt(member[8]), Integer.parseInt(member[9]), member[10].charAt(0));
            } else {
                System.out.println("Row " + i + " does not have all the family car information.");
            }
        }
    }

    public void orderByPrice(List<FamilyCar> familyCars) {

        FamilyCar temp;
        for (int i = 0; i < familyCars.size(); i++) {
            for (int j = 1; j < (familyCars.size() - i); j++) {
                FamilyCar f1= familyCars.get(j-1), f2 = familyCars.get(j);
                if (f1.getPrice() > f2.getPrice()) {
                    temp = f1;
                    f1 = f2;
                    f2 = temp;
                }
            }
        }

        for (FamilyCar p : familyCars) {
            System.out.println("*********");
            p.printInfo();
        }
    }

    public void printSportCarsColor(List<FamilyCar> familyCars) {
        int count = 0;
        for (FamilyCar f: familyCars) {
            if (f != null && f.getType() == 's') {
                System.out.println("There is a sport car which has color: " + f.getColor());
                count++;
            }
        }
        if(count == 0){
            System.out.println("There are no sport cars.");
        }
    }

    public void printPriceLessThan1MillionAndYearMoreThan2018FamilyCars(List<FamilyCar> familyCars) {
        int count = 0;

        for (FamilyCar p: familyCars) {
            if (p != null && p.getPrice() < 1_000_000 && p.getYear() > 2018) {
                p.printInfo();
                count++;
            }
        }

        /*
        for (int i = 0; i < familyCars.length; i++) {
            if (familyCars[i] != null && familyCars[i].getPrice() < 1_000_000 && familyCars[i].getYear() > 2018) {
                printFamilyCarInfo(personalCars[i]);
                count++;
            }
        }

         */
        if(count == 0){
            System.out.println("There is no family car which has a price less than 1 million " +
                    "and the year is more than 2018.");
        }
    }

    public List<FamilyCar> removeFamilyCar(int familyCarNumber) {

        int index = familyCarNumber - 1;
        List<FamilyCar> familyCars = null;
        if (createFile("familyCar.txt")) {
            System.out.println("There are no family cars available at the moment." +
                    "\nPlease choose one of the other options or return to the Admin Main Menu.");
            return null;
        } else {
            try {
                familyCars = convert(FileService.read(PATH));

                for (int i = 0; i < familyCars.size(); i++) {

                    if (i == index) {
                        familyCars.remove(i);
                    }
                }
                File file = new File("C:\\Users\\ACER\\IdeaProjects\\Homework8\\src\\familyCar.txt");
                File temp = File.createTempFile("file3", ".txt", file.getParentFile());
                for (FamilyCar f : familyCars) {
                    write(String.valueOf(temp), f.toString());
                    write(String.valueOf(temp), "\n");
                }
                file.delete();
                temp.renameTo(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return  familyCars;
    }

    public FamilyCar chooseFamilyCar(int familyCarNumber){

        int index = familyCarNumber-1;
        if (createFile("familyCar.txt")) {
            System.out.println("There are no family cars available at the moment." +
                    "\nPlease choose one of the other options or return to the Admin Main Menu.");
        } else {
            try {
                List<FamilyCar> familyCars = convert(FileService.read(PATH));
                for (int i = 0;i<familyCars.size();i++){
                    if(i == index){
                        return familyCars.get(i);
                    }
                }
            } catch (Exception e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * If there is no bicycle.txt file, then the user gets the message that there are no bicycles available.
     * Otherwise, the info from the file is read and bicycles are created with that info.
     *
     * @return
     */
    public List<FamilyCar> fillFamilyCarInfo() {

        String familyCarFileName = "familyCar.txt";
        List<FamilyCar> familyCars = null;

        if (createFile(familyCarFileName)) {
            System.out.println("There are no family cars available at the moment." +
                    "\nPlease choose one of the other options or return to the Main Menu.");
            return null;
        } else {
            try {
                familyCars = convert(FileService.read(PATH));
            } catch (Exception e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

            System.out.println("-----------------------");
        }
        return familyCars;
    }
}