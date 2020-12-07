package service;

import model.Bus;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import static service.FileService.createFile;
import static service.FileService.write;

public class BusService {

    private Bus createBus(int index) {

        Scanner scanner = new Scanner(System.in);
        int wheelNumber, price = 0, year = 0, mileage = 0;
        double speed = 0, routeTime = 0;
        String brand = "", model = "", color = "", made = "", plateNumber = "", route = "";
        boolean isWorking = false;

        System.out.println("Enter wheelNumber:");
        wheelNumber = scanner.nextInt();
        String str = wheelNumber + ",";

        String path = "C:\\Users\\ACER\\IdeaProjects\\Homework8\\src\\bus.txt";
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
            str = speed + ",";
            write(path,str);
            System.out.println("Enter price:");
            price = scanner.nextInt();
            str = price + ",";
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
            System.out.println("Enter whether the bus is currently working:");
            isWorking = scanner.nextBoolean();
            str = isWorking + ",";
            write(path,str);
            System.out.println("Enter route:");
            route = scanner.next();
            str = route + ",";
            write(path,str);
            System.out.println("Enter route time:");
            routeTime = scanner.nextDouble();
            str =  Double.toString(routeTime);
            write(path,str + "\n");
            System.out.println("Successfully wrote in the file.");
        } catch (Exception exception) {
            System.out.println("An error occurred.");
            exception.printStackTrace();
            System.exit(0);
        }

        return new Bus(wheelNumber, speed, price, brand, model, color, made, year, mileage, plateNumber, isWorking,
                route, routeTime);
    }

    public Bus[] writeOrReadInfoInBusObject(){

        String busFileName = "bus.txt";
        Bus[] bus;

        if(createFile(busFileName)) {

            System.out.println("-----------------------");
            System.out.print("Type how many buses should be created: ");
            Scanner s = new Scanner(System.in);
            int count = s.nextInt();
            System.out.println("We will create " + count + " different buses.");
            System.out.println("Let's enter the information for each of them: ");
            bus = new Bus[count];

            for (int i = 0; i < count; i++) {
                bus[i] = createBus(i);
            }
            System.out.println();
        } else {
            String path = "C:\\Users\\ACER\\IdeaProjects\\Homework8\\src\\bus.txt";
            String[] read = null;
            try {
                read = FileService.read(path);
            } catch (Exception e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

           // String str = Arrays.toString(read).substring(1, Arrays.toString(read).length()-1);

            bus = new Bus[read.length];
            fillBus(bus,read);

            System.out.println("-----------------------");
        }
        return bus;
    }

    private void fillBus(Bus[] bus, String[] row){

        int defaultBusMembers = 13;
        int index = 0;
        for(int i=0;i<row.length;i++){
            String[] member = row[i].split(",");
            if(member.length == defaultBusMembers){
                bus[index++] = new Bus(Integer.parseInt(member[0]),Double.parseDouble(member[1]),
                        Integer.parseInt(member[2]), member[3], member[4], member[5], member[6],
                        Integer.parseInt(member[7]), Integer.parseInt(member[8]), member[9],
                        Boolean.parseBoolean(member[10]), member[11], Double.parseDouble(member[12]));
            } else {
                System.out.println("Row " + i + " does not have all the bus information.");
            }
        }
    }

    public void printInfoOfOneBus(Bus bus){
        if(bus != null) {
            System.out.println("Wheel number: " + bus.getWheelNumber());
            System.out.println("Speed: " + bus.getSpeed());
            System.out.println("Price: " + bus.getPrice());
            System.out.println("Brand: " + bus.getBrand());
            System.out.println("Model: " + bus.getModel());
            System.out.println("Color: " + bus.getColor());
            System.out.println("Made: " + bus.getMade());
            System.out.println("Year: " + bus.getYear());
            System.out.println("Mileage: " + bus.getMileage());
            System.out.println("Plate number: " + bus.getPlateNumber());
            System.out.println("The bus is working: " + bus.isWorking());
            System.out.println("Route: " + bus.getRoute());
            System.out.println("Route time: " + bus.getRouteTime());
        }
    }

    public void printWorkingBusesWithRouteKomitas(Bus[] bus){
        int count = 0;
        for(int i=0;i<bus.length;i++){
            if(bus[i] != null && bus[i].isWorking() && bus[i].getRoute().equals("Komitas")){
                printInfoOfOneBus(bus[i]);
                count++;
            }
        }
        if(count == 0){
            System.out.println("There are no working busses with route Komitas.");
        }
    }

    public void printNewerBusInfo(Bus[] bus) {
        if(bus[0]== null) {
            return;
        }
        Bus newerBus = bus[0];

        for(int i=1;i<bus.length;i++){
            if(bus[i].getYear() > newerBus.getYear()){
                newerBus = bus[i];
            }
        }
        printInfoOfOneBus(newerBus);
    }
}