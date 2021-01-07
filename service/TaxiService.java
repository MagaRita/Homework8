package service;

import model.Bicycle;
import model.Taxi;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static service.FileService.createFile;
import static service.FileService.write;

public class TaxiService {

    public static final String PATH = "C:\\Users\\ACER\\IdeaProjects\\Homework8\\src\\taxi.txt";

    private static List<Taxi> convert(List<String> data) {
        List<Taxi> taxis = new ArrayList<>();
        for (String x : data) {
            taxis.add(new Taxi(x));
        }
        return taxis;
    }

    public boolean findTaxi(List<Taxi> taxi, String addressFrom, String addressTo, int standardPrice){

        for (Taxi t:taxi){
            if(t!= null && t.isAvailable() && standardPrice == Taxi.STANDARD_PRICE_TAXI){
                t.call(addressFrom, addressTo);
                return true;
            }
        }

        /*
        for(int i=0;i<taxi.length;i++){
            if(taxi[i] != null && taxi[i].isAvailable() && standardPrice == PublicCar.STANDARD_PRICE){
            taxi[i].call(addressFrom, addressTo);
            return true;
            }
        }

         */
        System.out.println("No taxi's found.");
        return false;
    }

    public Taxi createTaxi(int index) {

        Scanner scanner = new Scanner(System.in);
        int wheelNumber, price = 0, rating = 0, year = 0, made = 0, mileage = 0;
        double speed = 0;
        String brand = "", model = "", color = "", driverName = "", plateNumber = "";
        boolean isAvailable = false;

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
            System.out.println("Enter speed:");
            speed = scanner.nextDouble();
            str = speed + ",";
            write(PATH,str);
            System.out.println("Enter price:");
            price = scanner.nextInt();
            str = price + ",";
            write(PATH,str);
            System.out.println("Enter brand:");
            brand = scanner.next();
            str = brand + ",";
            write(PATH,str);
            System.out.println("Enter plate number:");
            plateNumber = scanner.next();
            str = plateNumber + ",";
            write(PATH,str);
            System.out.println("Enter model:");
            model = scanner.next();
            str = model + ",";
            write(PATH,str);
            System.out.println("Enter color:");
            color = scanner.next();
            str = color + ",";
            write(PATH,str);
            System.out.println("Enter made:");
            made = scanner.nextInt();
            str = made + ",";
            write(PATH,str);
            System.out.println("Enter year:");
            year = scanner.nextInt();
            str = year + ",";
            write(PATH,str);
            System.out.println("Enter mileage:");
            mileage = scanner.nextInt();
            str = mileage + ",";
            write(PATH,str);
            System.out.println("Enter driver name:");
            driverName = scanner.next();
            str = driverName + ",";
            write(PATH,str);
            System.out.println("Enter rating:");
            rating = scanner.nextByte();
            str =  rating + ",";
            write(PATH,str);
            System.out.println("Enter whether the bus is currently available:");
            isAvailable = scanner.nextBoolean();
            str = Boolean.toString(isAvailable);
            write(PATH,str + "\n");
            System.out.println("Successfully wrote in the file.");
        } catch (Exception exception) {
            System.out.println("An error occurred.");
            exception.printStackTrace();
            System.exit(0);
        }

        return new Taxi(wheelNumber, speed, price, brand,plateNumber, model, color, made, year, mileage,
               driverName, (byte) rating,  isAvailable);
    }

    /*
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
            List<String> read = null;
            try {
                read = FileService.read(path);
            } catch (Exception e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

        //    String str = Arrays.toString(read).substring(1, Arrays.toString(read).length()-1);
            taxis = new Taxi[read.size()];
            fillTaxi(taxis,read);

            System.out.println("-----------------------");
        }
        return taxis;
    }

     */
    private void fillTaxi(Taxi[] taxis, List<String> row){

        int defaultTaxiMembers = 13;
        int index = 0;
        for(int i=0;i<row.size();i++){
            String[] member = row.get(i).split(",");
            if(member.length == defaultTaxiMembers){
                taxis[index++] = new Taxi(Integer.parseInt(member[0]),Double.parseDouble(member[1]),
                        Integer.parseInt(member[2]), member[3], member[4], member[5],  member[6],
                        Integer.parseInt(member[7]), Integer.parseInt(member[8]), Integer.parseInt(member[9]),
                        member[10], Byte.parseByte(member[11]), Boolean.parseBoolean(member[12]));
            } else {
                System.out.println("Row " + i + " does not have all the taxi information.");
            }
        }
    }

    public List<Taxi> removeTaxi(int taxiNumber) {

        int index = taxiNumber - 1;
        List<Taxi> taxis = null;
        if (createFile("taxi.txt")) {
            System.out.println("There are no taxi's available at the moment." +
                    "\nPlease choose one of the other options or return to the Admin Main Menu.");
            return null;
        } else {
            try {
                taxis = convert(FileService.read(PATH));

                for (int i = 0; i < taxis.size(); i++) {

                    if (i == index) {
                        taxis.remove(i);
                    }
                }
                File file = new File("C:\\Users\\ACER\\IdeaProjects\\Homework8\\src\\taxi.txt");
                File temp = File.createTempFile("file4", ".txt", file.getParentFile());
                for (Taxi t : taxis) {
                    write(String.valueOf(temp), t.toString());
                    write(String.valueOf(temp), "\n");
                }
                file.delete();
                temp.renameTo(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return  taxis;
    }

    public Taxi chooseTaxi(int taxiNumber){

        int index = taxiNumber-1;
        if (createFile("taxi.txt")) {
            System.out.println("There are no taxi's available at the moment." +
                    "\nPlease choose one of the other options or return to the Admin Main Menu.");
        } else {
            try {
                List<Taxi> taxis = convert(FileService.read(PATH));
                for (int i = 0;i<taxis.size();i++){
                    if(i == index){
                        return taxis.get(i);
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
    public List<Taxi> fillTaxiInfo() {

        String taxiFileName = "taxi.txt";
        List<Taxi> taxi = null;

        if (createFile(taxiFileName)) {
            System.out.println("There are no taxi's available at the moment." +
                    "\nPlease choose one of the other options or return to the Main Menu.");
            return null;
        } else {
            try {
                taxi = convert(FileService.read(PATH));
            } catch (Exception e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

            System.out.println("-----------------------");
        }
        return taxi;
    }
}