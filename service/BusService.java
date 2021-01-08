package service;

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

    /**
     * In the bus.txt file, the bus info is saved and that PATH will be used in a few places in the
     * BusService class.
     */

    public static final String PATH = "C:\\Users\\ACER\\IdeaProjects\\Homework8\\src\\bus.txt";

    /**
     * convert function converts the List<String> into Buses.
     * @param data
     * @return
     */

    private static List<Bus> convert(List<String> data) {
        List<Bus> bus = new ArrayList<>();
        for (String x : data) {
            bus.add(new Bus(x));
        }
        return bus;
    }

    /**
     * The bus info is added by the admin.
     * @param index
     * @return
     */

    public Bus createBus(int index) {

        Scanner scanner = new Scanner(System.in);
        int wheelNumber, price = 0;
        double speed = 0, routeTime = 0;
        String brand = "", plateNumber = "", route = "";
        boolean isWorking = false;

        System.out.println("Enter wheelNumber:");
        wheelNumber = scanner.nextInt();
        String str = wheelNumber + ",";

        try {
            if (index == 0) {
                Files.write(Paths.get(PATH), str.getBytes());
            }
            else{
                write(PATH,str);
            }
        }
        catch (Exception exception) {
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
            write(PATH, str);   while (true) {
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
                System.out.println("Enter whether the bus is currently working - true or false.");
                isWorking = scanner.nextBoolean();
                if(String.valueOf(isWorking).equals("true") || String.valueOf(isWorking).equals("false"))
                    break;
            }

            str = isWorking + ",";
            write(PATH,str);

            while (true) {
                System.out.println("Enter route - any not empty string.");
                route = scanner.next();
                if(route.length() > 0)
                    break;
            }

            str = route + ",";
            write(PATH,str);

            while (true) {
                System.out.println("Enter route time - any not negative value and not 0.");
                routeTime = scanner.nextDouble();
                if(routeTime > 0)
                    break;
            }

            str =  Double.toString(routeTime);
            write(PATH,str + "\n");
        } catch (Exception exception) {
            System.out.println("An error occurred.");
            exception.printStackTrace();
            System.exit(0);
        }

        return new Bus(wheelNumber, speed, price, brand, plateNumber, isWorking, route, routeTime);
    }

    /**
     * If there is no bus.txt file, then the user gets the message that there are no bus available.
     * Otherwise, the info from the file is read and bus are created with that info.
     *
     * @return
     */
    public List<Bus> fillBusInfo() {

        String busFileName = "bus.txt";
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
        }
        return bus;
    }

    /**
     * The admin chooses the bus number, but as the numbers start from 0, the index is one less. If there is no
     * file with buses, then the message will appear. Otherwise, the chosen bus will be deleted and the temp
     * file won't contain that bus and then the temp file will be renamed.
     * @param busNumber
     * @return
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

    /**
     * The admin can choose which bus they need. The admin chooses the bus number, but as the numbers start
     * from 0, the index is one less. If the file doesn't exist then the message will appear.
     * Otherwise, the bus the admin chose, will be returned.
     * @param busNumber
     * @return
     */

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
     * Provides the working buses with route Komitas.
     * @param bus
     */

    public void printWorkingBusesWithRouteKomitas(List<Bus> bus){
        int count = 0;

        for(Bus b:bus){
            if(b != null && b.isWorking() && b.getRoute().equals("Komitas")){
                b.printInfo();
                count++;
            }
        }

        if(count == 0){
            System.out.println("There are no working buses with route Komitas.");
        }
    }

    /*
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
    */
}