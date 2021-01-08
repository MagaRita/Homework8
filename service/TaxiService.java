package service;

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

    /**
     * In the taxi.txt file, the taxi info is saved and that PATH will be used in a few places in the
     * TaxiService class.
     */

    public static final String PATH = "C:\\Users\\ACER\\IdeaProjects\\Homework8\\src\\taxi.txt";

    /**
     * convert function converts the List<String> into Taxi's.
     * @param data
     * @return
     */

    private static List<Taxi> convert(List<String> data) {
        List<Taxi> taxis = new ArrayList<>();
        for (String x : data) {
            taxis.add(new Taxi(x));
        }
        return taxis;
    }

    /**
     * The taxi info is added by the admin.
     * @param index
     * @return
     */

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
                System.out.println("Enter driver name - any not empty string.");
                driverName = scanner.next();
                if(driverName.length() > 0)
                    break;
            }

            str = driverName + ",";
            write(PATH,str);

            while (true) {
                System.out.println("Enter rating - rating is between 1-5.");
                rating = scanner.nextByte();
                if (rating >= 1 && rating <= 5)
                    break;
            }

            str =  rating + ",";
            write(PATH,str);

            while (true) {
                System.out.println("Enter whether the bus is currently available:");
                isAvailable = scanner.nextBoolean();
                if (String.valueOf(isAvailable).equals("true") || String.valueOf(isAvailable).equals("false"))
                    break;
            }

            str = Boolean.toString(isAvailable);
            write(PATH,str + "\n");
        } catch (Exception exception) {
            System.out.println("An error occurred.");
            exception.printStackTrace();
            System.exit(0);
        }

        return new Taxi(wheelNumber, speed, price, brand,plateNumber, model, color, made, year, mileage,
                driverName, (byte) rating,  isAvailable);
    }

    /**
     * If there is no taxi.txt file, then the user gets the message that there are no taxi's available.
     * Otherwise, the info from the file is read and taxi's are created with that info.
     *
     * @return
     */
    public List<Taxi> fillTaxiInfo() {

        String taxiFileName = "taxi.txt";
        List<Taxi> taxi = null;

        if (createFile(taxiFileName)) {
            System.out.println("There are no taxi's available at the moment.");
            return null;
        } else {
            try {
                taxi = convert(FileService.read(PATH));
            } catch (Exception e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
        return taxi;
    }

    /**
     * The admin chooses the taxi number, but as the numbers start from 0, the index is one less. If there is no
     * file with taxi's, then the message will appear. Otherwise, the chosen taxi will be deleted and
     * the temp file won't contain that taxi and then the temp file will be renamed.
     * @param taxiNumber
     * @return
     */

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

    /**
     * The admin can choose which taxi they need. The admin chooses the taxi number, but as the numbers
     * start from 0, the index is one less. If the file doesn't exist then the message will appear.
     * Otherwise, the taxi the admin chose, will be returned.
     * @param taxiNumber
     * @return
     */

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
     * The user can find the taxi they are looking for.
     * @param taxi
     * @param addressFrom
     * @param addressTo
     * @param standardPrice
     * @return
     */

    public boolean findTaxi(List<Taxi> taxi, String addressFrom, String addressTo, int standardPrice){

        for (Taxi t:taxi){
            if(t!= null && t.isAvailable() && standardPrice == Taxi.STANDARD_PRICE_TAXI){
                t.call(addressFrom, addressTo);
                return true;
            }
        }

        System.out.println("No taxi's found.");
        return false;
    }
}