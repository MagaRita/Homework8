package service;

import comparator.bicycle.PriceComparator;
import model.Bicycle;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static service.FileService.*;

public class BicycleService {

    /**
     * In the bicycle.txt file, the bicycle info is saved and that PATH will be used in a few places in the
     * BicycleService class.
     */

    public static final String PATH = "C:\\Users\\ACER\\IdeaProjects\\Homework8\\src\\bicycle.txt";

    /**
     * convert function converts the List<String> into Bicycles.
     * @param data
     * @return
     */

    private static List<Bicycle> convert(List<String> data) {
        List<Bicycle> bicycles = new ArrayList<>();
        for (String x : data) {
            bicycles.add(new Bicycle(x));
        }
        return bicycles;
    }

    /**
     * The bicycle info is added by the admin.
     * @param index
     * @return
     */

    public Bicycle createBicycle(int index) {

        Scanner scanner = new Scanner(System.in);
        int wheelNumber;

        while (true) {
            System.out.println("Enter wheelNumber - the number can be 2 or 3");
            wheelNumber = scanner.nextInt();
            if (wheelNumber == 2 || wheelNumber == 3) {
                break;
            }
        }
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

        int price = 0, cadence = 0;
        double speed = 0;
        String gear = "", brand = "", plateNumber = "";

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
                System.out.println("Enter cadence - any not negative value and not 0.");
                cadence = scanner.nextInt();
                if(cadence > 0)
                    break;
            }

            str = cadence + ",";
            write(PATH, str);

            while (true) {
                System.out.println("Enter gear - there can be high and low gear.");
                gear = scanner.next();
                if(gear.equals("low") || gear.equals("high"))
                    break;
            }

            write(PATH, gear + "\n");
        } catch (Exception exception) {
            System.out.println("An error occurred.");
            exception.printStackTrace();
            System.exit(0);
        }

        return new Bicycle(wheelNumber, speed, price, brand, plateNumber, cadence, gear);
    }

    /**
     * If there is no bicycle.txt file, then the user gets the message that there are no bicycles available.
     * Otherwise, the info from the file is read and bicycles are created with that info.
     * @return
     */
    public List<Bicycle> fillBicycleInfo() {

        String bicycleFileName = "bicycle.txt";
        List<Bicycle> bicycles = null;

        if (createFile(bicycleFileName)) {
            System.out.println("There are no bicycle's available at the moment.");
            return null;
        } else {
            try {
                bicycles = convert(FileService.read(PATH));
            } catch (Exception e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
        return bicycles;
    }

    /**
     * The admin chooses the bicycle number, but as the numbers start from 0, the index is one less. If there is no
     * file with bicycles, then the message will appear. Otherwise, the chosen bicycle will be deleted and the temp
     * file won't contain that bicycle and then the temp file will be renamed.
     * @param bicycleNumber
     * @return
     */

    public List<Bicycle> removeBicycle(int bicycleNumber) {

        int index = bicycleNumber - 1;
        List<Bicycle> bicycles = null;
        if (createFile("bicycle.txt")) {
            System.out.println("There are no bicycle's available at the moment." +
                    "\nPlease choose one of the other options or return to the Admin Main Menu.");
            return null;
        } else {
            try {
                bicycles = convert(FileService.read(PATH));

                for (int i = 0; i < bicycles.size(); i++) {

                    if (i == index) {
                        bicycles.remove(i);
                    }
                }
                File file = new File(PATH);
                File temp = File.createTempFile("file", ".txt", file.getParentFile());
                for (Bicycle b : bicycles) {
                    write(String.valueOf(temp), b.toString());
                    write(String.valueOf(temp), "\n");
                }
                file.delete();
                temp.renameTo(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return  bicycles;
    }

    /**
     * The admin can choose which bicycle they need. The admin chooses the bicycle number, but as the numbers start
     * from 0, the index is one less. If the file doesn't exist then the message will appear.
     * Otherwise, the bicycle the admin chose, will be returned.
     * @param bicycleNumber
     * @return
     */

    public Bicycle chooseBicycle(int bicycleNumber){

        int index = bicycleNumber-1;
        if (createFile("bicycle.txt")) {
            System.out.println("There are no bicycle's available at the moment." +
                    "\nPlease choose one of the other options or return to the Admin Main Menu.");
        } else {
            try {
                List<Bicycle> bicycles = convert(FileService.read(PATH));
                for (int i = 0;i<bicycles.size();i++){
                    if(i == index){
                        return bicycles.get(i);
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
     * The bicycles are ordered by price in the descending order.
     * @param bicycles
     */

    public void orderByPriceDesc(List<Bicycle> bicycles) {
        Collections.sort(bicycles, new PriceComparator().reversed());
    }

    /**
     * printAllAlchemyBrandBicyclePrices function checks if the bicycles are not null, then it prints the Alchemy Brand
     * bicycle prices.
     * @param bicycles
     */

    public void printAllAlchemyBrandBicyclePrices(List<Bicycle> bicycles) {
        int count = 0;
        for (Bicycle b : bicycles) {
            if (b != null && b.getBrand().equals("Alchemy")) {
                System.out.println("The price is: " + b.getPrice());
                count++;
            }
        }

        if (count == 0) {
            System.out.println("There are no Alchemy Brand bicycles.");
        }
    }

    /**
     * prints the bicycles which have high gear.
     * @param bicycles
     */

    public void printBicyclesWithHighGear(List<Bicycle> bicycles) {
        int count = 0;
        for (Bicycle b : bicycles) {
            if (b != null && b.getGear().equals("high")) {
                b.printInfo();
                System.out.println("*********");
                count++;
            }
        }
        if (count == 0) {
            System.out.println("There are no bicycles with high gear.");
        }
    }

    /**
     * maxCadence function returns the bicycles which have maximum cadence. Bicycle class is Comparable and the
     * compareTo function shows that it's comparing the cadences.
     * @param bicycles
     * @return
     */

    public Bicycle maxCadence(List<Bicycle> bicycles) {
      /*
        if(bicycles.get(0) == null) {
            return null;
        }
        Bicycle max = bicycles.get(0);
        for(int i = 1; i < bicycles.size(); i++){
            Bicycle b = bicycles.get(i);
            if(b != null && max.getCadence() <= b.getCadence()){
                max = b;
            }
        }
        return max;
       */
        //   try{
        return Collections.max(bicycles);
        //    } catch (NullPointerException e){
        //       return ;
        //    }
    }

    /**
     * The bicycles are ordered by price in the ascending order.
     * @param bicycles
     */

    public void orderByPriceAsc(List<Bicycle> bicycles) {

        Collections.sort(bicycles, new PriceComparator());

        /*

        Bicycle temp;
        for (int i = 0; i < bicycles.size(); i++){
            for (int j = 1; j < (bicycles.size() - i); j++) {
                Bicycle b1 = bicycles.get(j - 1), b2 = bicycles.get(j);
                if (b1.getPrice() > b2.getPrice()) {
                    temp = b1;
                    b1 = b2;
                    b2 = temp;
                }
            }
        }

        for (Bicycle b:bicycles){
            System.out.println("*********");
            b.printInfo();
        }

         */
    }
}