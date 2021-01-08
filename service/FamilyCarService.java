package service;

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

    /**
     * In the familyCar.txt file, the family car info is saved and that PATH will be used in a few places in the
     * FamilyCarService class.
     */

    public static final String PATH = "C:\\Users\\ACER\\IdeaProjects\\Homework8\\src\\familyCar.txt";

    /**
     * convert function converts the List<String> into Family Cars.
     * @param data
     * @return
     */

    private static List<FamilyCar> convert(List<String> data) {
        List<FamilyCar> familyCars = new ArrayList<>();
        for (String x : data) {
            familyCars.add(new FamilyCar(x));
        }
        return familyCars;
    }

    /**
     * The family car info is added by the admin.
     * @param index
     * @return
     */

    public FamilyCar createFamilyCar(int index) {

        Scanner scanner = new Scanner(System.in);
        int wheelNumber, price = 0, cadence = 0, gear = 0, year = 0,made = 0, mileage = 0;
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

            str = mileage + ",";
            write(PATH, str);


            while (true) {
                System.out.println("Enter type - can be a small car or a big one.");
                type = scanner.next().charAt(0);
                if (type == 's' || type == 'b')
                    break;
            }

            str = String.valueOf(type);
            write(PATH, str + "\n");
        } catch (Exception exception) {
            System.out.println("An error occurred.");
            exception.printStackTrace();
            System.exit(0);
        }

        return new FamilyCar(wheelNumber, speed, price, brand, plateNumber, model, color, made, year, mileage, type);
    }

    /**
     * If there is no familyCar.txt file, then the user gets the message that there are no family cars available.
     * Otherwise, the info from the file is read and family cars are created with that info.
     *
     * @return
     */
    public List<FamilyCar> fillFamilyCarInfo() {

        String familyCarFileName = "familyCar.txt";
        List<FamilyCar> familyCars = null;

        if (createFile(familyCarFileName)) {
            System.out.println("There are no family cars available at the moment.");
            return null;
        } else {
            try {
                familyCars = convert(FileService.read(PATH));
            } catch (Exception e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
        return familyCars;
    }

    /**
     * The admin chooses the family car number, but as the numbers start from 0, the index is one less. If there is no
     * file with family cars, then the message will appear. Otherwise, the chosen family car will be deleted and
     * the temp file won't contain that family car and then the temp file will be renamed.
     * @param familyCarNumber
     * @return
     */

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

    /**
     * The admin can choose which family car they need. The admin chooses the family car number, but as the numbers
     * start from 0, the index is one less. If the file doesn't exist then the message will appear.
     * Otherwise, the family car the admin chose, will be returned.
     * @param familyCarNumber
     * @return
     */

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
     * The family cars are ordered by price.
     * @param familyCars
     */

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

    /**
     * The family car colors are provided.
     * @param familyCars
     */

    public void printFamilyCarColor(List<FamilyCar> familyCars) {
        int count = 0;
        for (FamilyCar f: familyCars) {
            if (f != null) {
                System.out.println("There is a family car which has color: " + f.getColor());
                count++;
            }
        }
        if(count == 0){
            System.out.println("There are no family cars.");
        }
    }

    /**
     * The family cars with price less than 1 million and year more than 2018 are provided.
     * @param familyCars
     */

    public void printPriceLessThan1MillionAndYearMoreThan2018FamilyCars(List<FamilyCar> familyCars) {
        int count = 0;

        for (FamilyCar p: familyCars) {
            if (p != null && p.getPrice() < 1_000_000 && p.getYear() > 2018) {
                p.printInfo();
                count++;
            }
        }

        if(count == 0){
            System.out.println("There is no family car which has a price less than 1 million " +
                    "and the year is more than 2018.");
        }
    }

/*
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

    */
}