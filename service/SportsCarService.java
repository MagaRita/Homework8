package service;

import model.Bicycle;
import model.Bus;
import model.SportsCar;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static service.FileService.createFile;
import static service.FileService.write;

public class SportsCarService {

    public static final String PATH = "C:\\Users\\ACER\\IdeaProjects\\Homework8\\src\\sportsCar.txt";

    private static List<SportsCar> convert(List<String> data) {
        List<SportsCar> sportsCars = new ArrayList<>();
        for (String x : data) {
            sportsCars.add(new SportsCar(x));
        }
        return sportsCars;
    }

    public SportsCar createSportsCar(int index) {

        Scanner scanner = new Scanner(System.in);
        int wheelNumber, price = 0, year = 0,made = 0, mileage = 0;
        double speed = 0;
        char type = 0;
        String brand = "", model = "", color = "", plateNumber = "";

        System.out.println("Enter wheelNumber:");
        wheelNumber = scanner.nextInt();
        String str = wheelNumber + ",";

        String path = "C:\\Users\\ACER\\IdeaProjects\\Homework8\\src\\sportsCar.txt";
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
            str = mileage + "\n";
            write(path, str);

            System.out.println("Successfully wrote in the file.");
        } catch (Exception exception) {
            System.out.println("An error occurred.");
            exception.printStackTrace();
            System.exit(0);
        }
        return new SportsCar(wheelNumber, speed, price, brand, plateNumber, model, color, made, year, mileage);
    }

    public SportsCar[] writeOrReadInfoInSportsCarObject() {

        String sportsCarFileName = "SportsCar.txt";
        SportsCar[] sportsCars;

        if (createFile(sportsCarFileName)) {

            System.out.println("-----------------------");
            System.out.print("Type how many Sports cars should be created: ");
            Scanner s = new Scanner(System.in);
            int count = s.nextInt();
            System.out.println("We will create " + count + " different Sports cars.");
            System.out.println("Let's enter the information for each of them: ");
            sportsCars = new SportsCar[count];

            for (int i = 0; i < count; i++) {
                sportsCars[i] = createSportsCar(i);
            }
            System.out.println();
        } else {
            String path = "C:\\Users\\ACER\\IdeaProjects\\Homework8\\src\\sportsCar.txt";
            List<String> read = null;
            try {
                read = FileService.read(path);
            } catch (Exception e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

            sportsCars = new SportsCar[read.size()];


            fillSportsCars(sportsCars, read);

            System.out.println("-----------------------");
        }
        return sportsCars;
    }

    private void fillSportsCars(SportsCar[] sportsCars, List<String> row) {

        int defaultSportsCarsMembers = 10;
        int index = 0;
        for (int i = 0; i < row.size(); i++) {
            String[] member = row.get(i).split(",");
            if (member.length == defaultSportsCarsMembers) {
                sportsCars[index++] = new SportsCar(Integer.parseInt(member[0]), Double.parseDouble(member[1]),
                        Integer.parseInt(member[2]), member[3], member[4], member[5],  member[6],
                        Integer.parseInt(member[7]), Integer.parseInt(member[8]), Integer.parseInt(member[9]));
            } else {
                System.out.println("Row " + i + " does not have all the sports car information.");
            }
        }
    }

    /**
     * The Sports Car info is printed in the printSportsCarInfo function.
     * @param sportsCars
     */

    public void printSportsCarInfo(SportsCar sportsCars) {
        if(sportsCars != null) {
            System.out.println("Wheel number: " + sportsCars.getWheelNumber());
            System.out.println("Speed: " + sportsCars.getSpeed());
            System.out.println("Price: " + sportsCars.getPrice());
            System.out.println("Brand: " + sportsCars.getBrand());
            System.out.println("Plate Number: " + sportsCars.getPlateNumber());
            System.out.println("Model: " + sportsCars.getModel());
            System.out.println("Color: " + sportsCars.getColor());
            System.out.println("Made: " + sportsCars.getMade());
            System.out.println("Year: " + sportsCars.getYear());
            System.out.println("Mileage: " + sportsCars.getMileage());
        }
    }

    public void orderByPrice(List<SportsCar> sportsCars) {

        SportsCar temp;
        for (int i = 0; i < sportsCars.size(); i++) {
            for (int j = 1; j < (sportsCars.size() - i); j++) {
                SportsCar s1 = sportsCars.get(j-1),s2 = sportsCars.get(j);
                if (s1.getPrice() > s2.getPrice()) {
                    temp = s1;
                    s1 = s2;
                    s2 = temp;
                }
            }
        }

        for (SportsCar p : sportsCars) {
            System.out.println("*********");
            printSportsCarInfo(p);
        }
    }

    /*
    public void printSportCarsColor(SportsCar[] sportsCars) {
        int count = 0;
        for (int i = 0; i < sportsCars.length; i++) {
            if (sportsCars[i] != null && sportsCars[i].getType() == 's') {
                System.out.println("There is a sport car which has color: " + sportsCars[i].getColor());
                count++;
            }
        }
        if(count == 0){
            System.out.println("There are no sport cars.");
        }
    }

     */

    public void printPriceLessThan1MillionAndYearMoreThan2018SportsCars(List<SportsCar> sportsCars) {
        int count = 0;

        for (SportsCar p: sportsCars) {
            if (p != null && p.getPrice() < 1_000_000 && p.getYear() > 2018) {
                printSportsCarInfo(p);
                count++;
            }
        }

        /*
        for (int i = 0; i < sportsCars.length; i++) {
            if (sportsCars[i] != null && sportsCars[i].getPrice() < 1_000_000 && sportsCars[i].getYear() > 2018) {
                printSportsCarInfo(personalCars[i]);
                count++;
            }
        }

         */
        if(count == 0){
            System.out.println("There is no Sports car which has a price less than 1 million " +
                    "and the year is more than 2018.");
        }
    }

    public List<SportsCar> removeSportsCar(int sportsCarNumber) {

        int index = sportsCarNumber - 1;
        List<SportsCar> sportsCars = null;
        if (createFile("sportsCar.txt")) {
            System.out.println("There are no sports cars available at the moment." +
                    "\nPlease choose one of the other options or return to the Admin Main Menu.");
            return null;
        } else {
            try {
                sportsCars = convert(FileService.read(PATH));

                for (int i = 0; i < sportsCars.size(); i++) {

                    if (i == index) {
                        sportsCars.remove(i);
                    }
                }
                File file = new File("C:\\Users\\ACER\\IdeaProjects\\Homework8\\src\\sportsCar.txt");
                File temp = File.createTempFile("file3", ".txt", file.getParentFile());
                for (SportsCar s : sportsCars) {
                    write(String.valueOf(temp), s.toString());
                    write(String.valueOf(temp), "\n");
                }
                file.delete();
                temp.renameTo(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return  sportsCars;
    }

    public SportsCar chooseSportsCar(int sportsCarNumber){

        int index = sportsCarNumber-1;
        if (createFile("sportsCar.txt")) {
            System.out.println("There are no bicycle's available at the moment." +
                    "\nPlease choose one of the other options or return to the Admin Main Menu.");
        } else {
            try {
                List<SportsCar> sportsCars = convert(FileService.read(PATH));
                for (int i = 0;i<sportsCars.size();i++){
                    if(i == index){
                        return sportsCars.get(i);
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
    public List<SportsCar> fillSportsCarInfo() {

        String sportsCarFileName = "sportsCar.txt";
        List<SportsCar> sportsCars = null;

        if (createFile(sportsCarFileName)) {
            System.out.println("There are no sports cars available at the moment." +
                    "\nPlease choose one of the other options or return to the Main Menu.");
            return null;
        } else {
            try {
                sportsCars = convert(FileService.read(PATH));
            } catch (Exception e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

            System.out.println("-----------------------");
        }
        return sportsCars;
    }
}

