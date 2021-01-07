package service;

import comparator.bicycle.PriceComparator;
import model.Bicycle;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static service.FileService.*;

public class BicycleService {

    public static final String PATH = "C:\\Users\\ACER\\IdeaProjects\\Homework8\\src\\bicycle.txt";

    private static List<Bicycle> convert(List<String> data) {
        List<Bicycle> bicycles = new ArrayList<>();
        for (String x : data) {
            bicycles.add(new Bicycle(x));
        }
        return bicycles;
    }

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
            System.out.println("Enter speed:");
            speed = scanner.nextDouble();
            str = speed + ",";
            write(PATH, str);
            System.out.println("Enter price:");
            price = scanner.nextInt();
            str = price + ",";
            write(PATH, str);
            System.out.println("Enter brand:");
            brand = scanner.next();
            str = brand + ",";
            write(PATH, str);
            System.out.println("Enter plate number:");
            plateNumber = scanner.next();
            str = plateNumber + ",";
            write(PATH, str);
            System.out.println("Enter cadence:");
            cadence = scanner.nextInt();
            str = cadence + ",";
            write(PATH, str);
            System.out.println("Enter gear:");
            gear = scanner.next();
            write(PATH, gear + "\n");
            System.out.println("Successfully wrote in the file.");
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
     *
     * @return
     */
    public List<Bicycle> fillBicycleInfo() {

        String bicycleFileName = "bicycle.txt";
        List<Bicycle> bicycles = null;

        if (createFile(bicycleFileName)) {
            System.out.println("There are no bicycle's available at the moment." +
                    "\nPlease choose one of the other options or return to the Main Menu.");
            return null;
        } else {
            try {
                bicycles = convert(FileService.read(PATH));
            } catch (Exception e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

            System.out.println("-----------------------");
        }
        return bicycles;
    }

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

    public void orderByPriceAsc(List<Bicycle> bicycles) {

        Collections.sort(bicycles, new PriceComparator());
       /* StringJoiner sj = new StringJoiner(" ");
        for(Bicycle b:bicycles){
            sj.add(sj.toString());
        }
        return sj.toString();

        */

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


    public void orderByPriceDesc(List<Bicycle> bicycles) {
        Collections.sort(bicycles, new PriceComparator().reversed());
      /*  StringJoiner sj = new StringJoiner(" ");
        for(Bicycle b:bicycles){
            sj.add(sj.toString());
        }
        return sj.toString();
       */
    }

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
                File file = new File("C:\\Users\\ACER\\IdeaProjects\\Homework8\\src\\bicycle.txt");
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

                /*
                for (int i = 0; i<bicycles.size();i++){

                    if(i == index){
                        bicycles.remove(i);
                        if (file.delete()) {
                            System.out.println("Deleted the file: " + file.getName());
                        } else {
                            System.out.println("Failed to delete the file.");
                        }
                        for (Bicycle b:bicycles) {
                            write(String.valueOf(temp), bicycles.toString());
                            System.out.println();
                        }
                        temp.renameTo(file);
                    }
                }
            } catch (Exception e) {
                System.out.println("An error occurred.");
                e.printStackTrace();



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
}














































    /*
   public Bicycle[] writeOrReadInfoInBicycleObject(){

       String bicycleFileName = "bicycle.txt";
       Bicycle[] bicycles;

       if(createFile(bicycleFileName)) {

           System.out.println("-----------------------");
           System.out.print("Type how many bicycles should be created: ");
           Scanner s = new Scanner(System.in);
           int count = s.nextInt();
           System.out.println("We will create " + count + " different bicycles.");
           System.out.println("Let's enter the information for each of them: ");
           bicycles = new Bicycle[count];

           for (int i = 0; i < count; i++) {
               bicycles[i] = createBicycle(i);
           }
           System.out.println();
       } else {
           String path = "C:\\Users\\ACER\\IdeaProjects\\Homework8\\src\\bicycle.txt";
           List<String> read = null;
           try {
               read = FileService.read(path);
           } catch (Exception e) {
               System.out.println("An error occurred.");
               e.printStackTrace();
           }

           bicycles = new Bicycle[read.size()];
           fillBicycle(bicycles,read);

           System.out.println("-----------------------");
       }
       return bicycles;
   }
     */

    /**
     * The file info is split into rows and then each part is separated using the comma and filled into the bicycle.
     */

    /*
    private void fillIntoBicycle(List<Bicycle> bicycles, List<String>row){

        int defaultBicycleMembers = 7;
        int index = 0;
        for(int i=0;i<row.size();i++){
            List<String> member = Arrays.asList(row.get(i).split(","));
            if(member.size() == defaultBicycleMembers){
                Bicycle b = bicycles.get( index++);
                b = new Bicycle(Integer.parseInt(member.get(0)),
                        Double.parseDouble(member.get(1)), Integer.parseInt(member.get(2)), member.get(3), member.get(4),
                        Integer.parseInt(member.get(5)), member.get(6));

                //bicycles.get(index++) = new List<Bicycle>(AInteger.parseInt(member.get(0)),
               //         Double.parseDouble(member.get(1)), Integer.parseInt(member.get(2)), member.get(3), member.get(4),
               //         Integer.parseInt(member.get(5)), member.get(6));
            }
            else {
                // If there is one row that has a missing info for the bicycle, then I print it for myself.
                //No eed for the user to see this.
                System.out.println("Row " + i + " does not have all the bicycle information.");
            }
        }
    }


     */
        /*
        for(int i=0;i<bicycles.length;i++){
            if(bicycles[i] != null && bicycles[i].getBrand().equals("Alchemy")){
                System.out.println("The price is: " + bicycles[i].getPrice());
                count++;
            }
        }
        if(count == 0){
            System.out.println("There are no Alchemy Brand bicycles.");
        }
    }
         */