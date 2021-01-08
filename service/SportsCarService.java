package service;

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

    /**
     * In the sportsCar.txt file, the sports car info is saved and that PATH will be used in a few places in the
     * SportsCarService class.
     */

    public static final String PATH = "C:\\Users\\ACER\\IdeaProjects\\Homework8\\src\\sportsCar.txt";

    /**
     * convert function converts the List<String> into Sports Cars.
     * @param data
     * @return
     */

    private static List<SportsCar> convert(List<String> data) {
        List<SportsCar> sportsCars = new ArrayList<>();
        for (String x : data) {
            sportsCars.add(new SportsCar(x));
        }
        return sportsCars;
    }

    /**
     * The sports car info is added by the admin.
     * @param index
     * @return
     */

    public SportsCar createSportsCar(int index) {

        Scanner scanner = new Scanner(System.in);
        int wheelNumber, price = 0, year = 0,made = 0, mileage = 0;
        double speed = 0;
        char type = 0;
        String brand = "", model = "", color = "", plateNumber = "";

        System.out.println("Enter wheelNumber:");
        wheelNumber = scanner.nextInt();
        String str = wheelNumber + ",";

        try {
            if (index == 0) {

                Files.write(Paths.get(PATH), str.getBytes());
            } else {
                write(PATH, str);
            }
        } catch (Exception exception) {
            System.out.println("An error occurred.");
            exception.printStackTrace();
            System.exit(0);
        }

        try {
            while (true) {
                System.out.println("Enter speed - not a negative number and not 0.");
                speed = scanner.nextDouble();
                if (speed > 0) {
                    break;
                }
            }
            str = speed + ",";
            write(PATH, str);

            while (true) {
                System.out.println("Enter price - not a negative number and not 0.");
                price = scanner.nextInt();
                if (price > 0) {
                    break;
                }
            }

            str = price + ",";
            write(PATH, str);

            while (true) {
                System.out.println("Enter brand - any not empty string.");
                brand = scanner.next();
                if (brand.length() > 0) {
                    break;
                }
            }

            str = brand + ",";
            write(PATH, str);

            while (true) {
                System.out.println("Enter plate number - should have 5-8 characters in the string.");
                plateNumber = scanner.next();
                if(plateNumber.length() >= 5 && plateNumber.length() <= 8)
                    break;
            }

            str = plateNumber + ",";
            write(PATH, str);

            while (true) {
                System.out.println("Enter model - any not empty string.");
                model = scanner.next();
                if(model.length() > 0)
                    break;
            }

            str = model + ",";
            write(PATH, str);

            while (true) {
                System.out.println("Enter color - any not empty string.");
                color = scanner.next();
                if(color.length() > 0)
                    break;
            }

            str = color + ",";
            write(PATH, str);

            while (true) {
                System.out.println("Enter made - any number in between 1900 and 2020.");
                made = scanner.nextInt();
                if (made > 1900 && made < 2020)
                    break;
            }

            str = made + ",";
            write(PATH, str);

            while (true) {
                System.out.println("Enter year - greater or equal to the year the car was made.");
                year = scanner.nextInt();
                if(year >= made)
                    break;
            }

            str = year + ",";
            write(PATH, str);

            while (true) {
                System.out.println("Enter mileage - any not negative value and not 0.");
                mileage = scanner.nextInt();
                if(mileage > 0)
                    break;
            }

            str = mileage + "\n";
            write(PATH, str);

        } catch (Exception exception) {
            System.out.println("An error occurred.");
            exception.printStackTrace();
            System.exit(0);
        }
        return new SportsCar(wheelNumber, speed, price, brand, plateNumber, model, color, made, year, mileage);
    }

    /**
     * If there is no sportsCar.txt file, then the user gets the message that there are no sports cars available.
     * Otherwise, the info from the file is read and sports cars are created with that info.
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
        }
        return sportsCars;
    }

    /**
     * The admin chooses the sports car number, but as the numbers start from 0, the index is one less. If there is no
     * file with sports cars, then the message will appear. Otherwise, the chosen sports car will be deleted and
     * the temp file won't contain that sports car and then the temp file will be renamed.
     * @param sportsCarNumber
     * @return
     */

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

    /**
     * The admin can choose which sports car they need. The admin chooses the sports car number, but as the numbers
     * start from 0, the index is one less. If the file doesn't exist then the message will appear.
     * Otherwise, the sports car the admin chose, will be returned.
     * @param sportsCarNumber
     * @return
     */

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
     * The sports cars are ordered by price.
     * @param sportsCars
     */

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
            p.printInfo();
        }
    }

    /**
     * Prints the sports cars which have a price less than 1 million and year more than 2018.
     * @param sportsCars
     */

    public void printPriceLessThan1MillionAndYearMoreThan2018SportsCars(List<SportsCar> sportsCars) {
        int count = 0;

        for (SportsCar p: sportsCars) {
            if (p != null && p.getPrice() < 1_000_000 && p.getYear() > 2018) {
                p.printInfo();
                count++;
            }
        }

        if(count == 0){
            System.out.println("There is no Sports car which has a price less than 1 million " +
                    "and the year is more than 2018.");
        }
    }

    /**
     * Prints the sports cars color.
     * @param sportsCars
     */

    public void printSportsCarColor(List<SportsCar> sportsCars) {
        int count = 0;
        for (SportsCar s: sportsCars) {
            if (s != null) {
                System.out.println("There is a sports car which has color: " + s.getColor());
                count++;
            }
        }
        if(count == 0){
            System.out.println("There are no sports cars.");
        }
    }
    
    /*
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

     */
}

