package service;

import model.Bicycle;
import model.Bus;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static service.FileService.createFile;
import static service.FileService.write;

public class BusService {

    public static final String PATH = "C:\\Users\\ACER\\IdeaProjects\\Homework8\\src\\bus.txt";

    private static List<Bus> convert(List<String> data) {
        List<Bus> bus = new ArrayList<>();
        for (String x : data) {
            bus.add(new Bus(x));
        }
        return bus;
    }

    public Bus createBus(int index) {

        Scanner scanner = new Scanner(System.in);
        int wheelNumber, price = 0;
        double speed = 0, routeTime = 0;
        String brand = "", plateNumber = "", route = "";
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

        return new Bus(wheelNumber, speed, price, brand, plateNumber, isWorking,
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
            List<String> read = null;
            try {
                read = FileService.read(path);
            } catch (Exception e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

           // String str = Arrays.toString(read).substring(1, Arrays.toString(read).length()-1);

            bus = new Bus[read.size()];
            fillBus(bus,read);

            System.out.println("-----------------------");
        }
        return bus;
    }

    private void fillBus(Bus[] bus, List<String> row){

        int defaultBusMembers = 8;
        int index = 0;
        for(int i=0;i<row.size();i++){
            String[] member = row.get(i).split(",");
            if(member.length == defaultBusMembers){
                bus[index++] = new Bus(Integer.parseInt(member[0]),Double.parseDouble(member[1]),
                        Integer.parseInt(member[2]), member[3], member[4], Boolean.parseBoolean(member[5]),
                        member[6], Double.parseDouble(member[7]));
            } else {
                System.out.println("Row " + i + " does not have all the bus information.");
            }
        }
    }

    public void printWorkingBusesWithRouteKomitas(List<Bus> bus){
        int count = 0;

        for(Bus b:bus){
            if(b != null && b.isWorking() && b.getRoute().equals("Komitas")){
                b.printInfo();
                count++;
            }
        }
        /*
        for(int i=0;i<bus.length;i++){
            if(bus[i] != null && bus[i].isWorking() && bus[i].getRoute().equals("Komitas")){
                printBusInfo(bus[i]);
                count++;
            }
        }

         */
        if(count == 0){
            System.out.println("There are no working busses with route Komitas.");
        }
    }

    /*

    public void printBus(Bus[] bus) {
        if(bus[0]== null) {
            return;
        }
        Bus newerBus = bus[0];

        for(int i=1;i<bus.length;i++){
            if(bus[i].getYear() > newerBus.getYear()){
                newerBus = bus[i];
            }
        }
        printBusInfo(newerBus);
    }

     */

    public List<Bus> removeBus(int busNumber) {

        int index = busNumber - 1;
        List<Bus> bus = null;
        if (createFile("bus.txt")) {
            System.out.println("There are no buses available at the moment." +
                    "\nPlease choose one of the other options or return to the Admin Main Menu.");
            return null;
        } else {
            try {
                bus = convert(FileService.read(PATH));

                for (int i = 0; i < bus.size(); i++) {

                    if (i == index) {
                        bus.remove(i);
                    }
                }
                File file = new File("C:\\Users\\ACER\\IdeaProjects\\Homework8\\src\\bus.txt");
                File temp = File.createTempFile("file2", ".txt", file.getParentFile());
                for (Bus b : bus) {
                    write(String.valueOf(temp), b.toString());
                    write(String.valueOf(temp), "\n");
                }
                file.delete();
                temp.renameTo(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return  bus;
    }

    public Bus chooseBus(int busNumber){

        int index = busNumber-1;
        if (createFile("bus.txt")) {
            System.out.println("There are no buses available at the moment." +
                    "\nPlease choose one of the other options or return to the Admin Main Menu.");
        } else {
            try {
                List<Bus> bus = convert(FileService.read(PATH));
                for (int i = 0;i<bus.size();i++){
                    if(i == index){
                        return bus.get(i);
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
     * If there is no bus.txt file, then the user gets the message that there are no bus available.
     * Otherwise, the info from the file is read and bus are created with that info.
     *
     * @return
     */
    public List<Bus> fillBusInfo() {

        String busFileName = "bicycle.txt";
        List<Bus> bus = null;

        if (createFile(busFileName)) {
            System.out.println("There are no buses available at the moment." +
                    "\nPlease choose one of the other options or return to the Main Menu.");
            return null;
        } else {
            try {
                bus = convert(FileService.read(PATH));
            } catch (Exception e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

            System.out.println("-----------------------");
        }
        return bus;
    }
}