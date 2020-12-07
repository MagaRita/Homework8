package service;

import model.PersonalCar;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import static service.FileService.createFile;
import static service.FileService.write;

public class PersonalCarService {

    private PersonalCar createPersonalCar(int index) {

        Scanner scanner = new Scanner(System.in);
        int wheelNumber, price = 0, cadence = 0, gear = 0, year = 0, mileage = 0;
        double speed = 0;
        char type = 0;
        String brand = "", model = "", color = "", made = "", plateNumber = "";

        System.out.println("Enter wheelNumber:");
        wheelNumber = scanner.nextInt();
        String str = wheelNumber + ",";

        String path = "C:\\Users\\ACER\\IdeaProjects\\Homework8\\src\\personalCar.txt";
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
            System.out.println("Enter model:");
            model = scanner.next();
            str = model + ",";
            write(path, str);
            System.out.println("Enter color:");
            color = scanner.next();
            str = color + ",";
            write(path, str);
            System.out.println("Enter made:");
            made = scanner.next();
            str = made + ",";
            write(path, str);
            System.out.println("Enter year:");
            year = scanner.nextInt();
            str = year + ",";
            write(path, str);
            System.out.println("Enter mileage:");
            mileage = scanner.nextInt();
            str = mileage + ",";
            write(path, str);
            System.out.println("Enter plateNumber:");
            plateNumber = scanner.next();
            str = plateNumber + ",";
            write(path, str);
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

        return new PersonalCar(wheelNumber, speed, price, brand, model, color, made, year, mileage, plateNumber, type);
    }

    public PersonalCar[] writeOrReadInfoInPersonalCarObject() {

        String personalCarFileName = "personalCar.txt";
        PersonalCar[] personalCars;

        if (createFile(personalCarFileName)) {

            System.out.println("-----------------------");
            System.out.print("Type how many personal cars should be created: ");
            Scanner s = new Scanner(System.in);
            int count = s.nextInt();
            System.out.println("We will create " + count + " different personal cars.");
            System.out.println("Let's enter the information for each of them: ");
            personalCars = new PersonalCar[count];

            for (int i = 0; i < count; i++) {
                personalCars[i] = createPersonalCar(i);
            }
            System.out.println();
        } else {
            String path = "C:\\Users\\ACER\\IdeaProjects\\Homework8\\src\\personalCar.txt";
            String[] read = null;
            try {
                read = FileService.read(path);
            } catch (Exception e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

            personalCars = new PersonalCar[read.length];

            //  String str = Arrays.toString(read).substring(1, Arrays.toString(read).length()-1);

            fillPersonalCars(personalCars, read);

            System.out.println("-----------------------");
        }
        return personalCars;
    }

    private void fillPersonalCars(PersonalCar[] personalCars, String[] row) {

        int defaultPersonalCarsMembers = 11;
        int index = 0;
        for (int i = 0; i < row.length; i++) {
            String[] member = row[i].split(",");
            if (member.length == defaultPersonalCarsMembers) {
                personalCars[index++] = new PersonalCar(Integer.parseInt(member[0]), Double.parseDouble(member[1]),
                        Integer.parseInt(member[2]), member[3], member[4], member[5], member[6],
                        Integer.parseInt(member[7]), Integer.parseInt(member[8]), member[9], member[10].charAt(0));
            } else {
                System.out.println("Row " + i + " does not have all the personal car information.");
            }
        }
    }

    public void printInfoOfOnePersonalCar(PersonalCar personalCar) {
        if(personalCar != null) {
            System.out.println("Wheel number: " + personalCar.getWheelNumber());
            System.out.println("Speed: " + personalCar.getSpeed());
            System.out.println("Price: " + personalCar.getPrice());
            System.out.println("Brand: " + personalCar.getBrand());
            System.out.println("Model: " + personalCar.getModel());
            System.out.println("Color: " + personalCar.getColor());
            System.out.println("Made: " + personalCar.getMade());
            System.out.println("Year: " + personalCar.getYear());
            System.out.println("Mileage: " + personalCar.getMileage());
            System.out.println("Plate Number: " + personalCar.getPlateNumber());
            System.out.println("Type: " + personalCar.getType());
        }
    }

    public void orderByPrice(PersonalCar[] personalCars) {

        PersonalCar temp;
        for (int i = 0; i < personalCars.length; i++) {
            for (int j = 1; j < (personalCars.length - i); j++) {
                if (personalCars[j - 1].getPrice() > personalCars[j].getPrice()) {
                    temp = personalCars[j - 1];
                    personalCars[j - 1] = personalCars[j];
                    personalCars[j] = temp;
                }
            }
        }

        for (PersonalCar p : personalCars) {
            System.out.println("*********");
            printInfoOfOnePersonalCar(p);
        }
    }

    public void printSportCarsColor(PersonalCar[] personalCars) {
        int count = 0;
        for (int i = 0; i < personalCars.length; i++) {
            if (personalCars[i] != null && personalCars[i].getType() == 's') {
                System.out.println("There is a sport car which has color: " + personalCars[i].getColor());
                count++;
            }
        }
        if(count == 0){
            System.out.println("There are no sport cars.");
        }
    }

    public void printPriceLessThan1MillionAndYearMoreThan2018PersonalCars(PersonalCar[] personalCars) {
        int count = 0;
        for (int i = 0; i < personalCars.length; i++) {
            if (personalCars[i] != null && personalCars[i].getPrice() < 1_000_000 && personalCars[i].getYear() > 2018) {
                printInfoOfOnePersonalCar(personalCars[i]);
                count++;
            }
        }
        if(count == 0){
            System.out.println("There is no personal car which has a price less than 1 million " +
                    "and the year is more than 2018.");
        }
    }
}