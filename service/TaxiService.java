package service;

import model.Taxi;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import static service.FileService.createFile;
import static service.FileService.write;

public class TaxiService {

    public boolean findTaxi(Taxi[] taxi, String addressFrom, String addressTo, int standardPrice){
        for(int i=0;i<taxi.length;i++){
            if(taxi[i].isAvailable() && standardPrice == taxi[i].STANDARD_PRICE){
            taxi[i].call(addressFrom, addressTo);
            return true;
            }
        }
        return false;
    }

    private Taxi createTaxi(int index) {

        Scanner scanner = new Scanner(System.in);
        int wheelNumber, price = 0, rating = 0, year = 0, mileage = 0;
        double speed = 0;
        String brand = "", model = "", color = "", made = "", driverName = "", plateNumber = "";
        boolean isAvailable = false;

        while (true) {
            System.out.println("Enter wheelNumber - the number can be 2 or 3");
            wheelNumber = scanner.nextInt();
            if (wheelNumber == 2 || wheelNumber == 3) {
                break;
            }
        }
        String str = wheelNumber + ",";

        String path = "C:\\Users\\ACER\\IdeaProjects\\Homework8\\src\\taxi.txt";
        try {
            if (index == 0) {
                Files.write(Paths.get(path), str.getBytes());
            }
            else{
                write(path,str);
            }
        }
        catch (Exception exception) {
            System.out.println("An error occurred.");
            exception.printStackTrace();
            System.exit(0);
        }

        try {
            System.out.println("Enter speed:");
            speed = scanner.nextDouble();
            str = Double.toString(speed) + ",";
            write(path,str);
            System.out.println("Enter price:");
            price = scanner.nextInt();
            str = Integer.toString(price) + ",";
            write(path,str);
            System.out.println("Enter brand:");
            brand = scanner.next();
            str = brand + ",";
            write(path,str);
            System.out.println("Enter model:");
            model = scanner.next();
            str = model + ",";
            write(path,str);
            System.out.println("Enter color:");
            color = scanner.next();
            str = color + ",";
            write(path,str);
            System.out.println("Enter made:");
            made = scanner.next();
            str = made + ",";
            write(path,str);
            System.out.println("Enter year:");
            year = scanner.nextInt();
            str = year + ",";
            write(path,str);
            System.out.println("Enter mileage:");
            mileage = scanner.nextInt();
            str = mileage + ",";
            write(path,str);
            System.out.println("Enter plate number:");
            plateNumber = scanner.next();
            str = plateNumber + ",";
            write(path,str);
            System.out.println("Enter driver name:");
            driverName = scanner.next();
            str = driverName + ",";
            write(path,str);
            System.out.println("Enter rating:");
            rating = scanner.nextInt();
            str =  rating + ",";
            write(path,str);
            System.out.println("Enter whether the bus is currently available:");
            isAvailable = scanner.nextBoolean();
            str = Boolean.toString(isAvailable);
            write(path,str + "\n");
            System.out.println("Successfully wrote in the file.");
        } catch (Exception exception) {
            System.out.println("An error occurred.");
            exception.printStackTrace();
            System.exit(0);
        }

        return new Taxi(wheelNumber, speed, price, brand, model, color, made, year, mileage, plateNumber,
               driverName, rating,  isAvailable);
    }

    public Taxi[] writeOrReadInfoInTaxiObject(){

        String taxiFileName = "taxi.txt";
        Taxi[] taxis;

        if(createFile(taxiFileName)) {

            System.out.println("-----------------------");
            System.out.print("Type how many taxi's should be created: ");
            Scanner s = new Scanner(System.in);
            int count = s.nextInt();
            System.out.println("We will create " + count + " different taxi's.");
            System.out.println("Let's enter the information for each of them: ");
            taxis = new Taxi[count];

            for (int i = 0; i < count; i++) {
                taxis[i] = createTaxi(i);
            }
            System.out.println();
        } else {
            String path = "C:\\Users\\ACER\\IdeaProjects\\Homework8\\src\\taxi.txt";
            String[] read = null;
            try {
                read = FileService.read(path);
            } catch (Exception e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

        //    String str = Arrays.toString(read).substring(1, Arrays.toString(read).length()-1);
            taxis = new Taxi[read.length];
            fillTaxi(taxis,read);

            System.out.println("-----------------------");
        }
        return taxis;
    }

    private void fillTaxi(Taxi[] taxis, String[] row){

        int defaultTaxiMembers = 13;
        int index = 0;
        for(int i=0;i<row.length;i++){
            String[] member = row[i].split(",");
            if(member.length == defaultTaxiMembers){
                taxis[index++] = new Taxi(Integer.parseInt(member[0]),Double.parseDouble(member[1]),
                        Integer.parseInt(member[2]), member[3], member[4], member[5], member[6],
                        Integer.parseInt(member[7]), Integer.parseInt(member[8]), member[9],
                        member[10], Integer.parseInt(member[11]), Boolean.parseBoolean(member[12]));
            } else {
                System.out.println("Row " + i + " does not have all the taxi information.");
            }
        }
    }

    public void printInfoOfOneTaxi(Taxi taxi){
        System.out.println("Wheel number: " + taxi.getWheelNumber());
        System.out.println("Speed: " + taxi.getSpeed());
        System.out.println("Price: " + taxi.getPrice());
        System.out.println("Brand: " + taxi.getBrand());
        System.out.println("Model: " + taxi.getModel());
        System.out.println("Color: " + taxi.getColor());
        System.out.println("Made: " + taxi.getMade());
        System.out.println("Year: " + taxi.getYear());
        System.out.println("Mileage: " + taxi.getMileage());
        System.out.println("Plate number: " + taxi.getPlateNumber());
        System.out.println("Route: " + taxi.getDriverName());
        System.out.println("Route time: " + taxi.getRating());
        System.out.println("The taxi is available: " + taxi.isAvailable());
    }
}