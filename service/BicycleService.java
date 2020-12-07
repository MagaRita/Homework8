package service;

import model.Bicycle;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import static service.FileService.createFile;
import static service.FileService.write;

public class BicycleService {

    private Bicycle createBicycle(int index) {

        Scanner scanner = new Scanner(System.in);
        int wheelNumber, price = 0, cadence = 0, gear = 0;
        double speed = 0;
        String brand = "";

        while (true) {
            System.out.println("Enter wheelNumber - the number can be 2 or 3");
            wheelNumber = scanner.nextInt();
            if (wheelNumber == 2 || wheelNumber == 3) {
                break;
            }
        }
        String str = wheelNumber + ",";

        String path = "C:\\Users\\ACER\\IdeaProjects\\Homework8\\src\\bicycle.txt";
        try{
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
            System.out.println("Enter cadence:");
            cadence = scanner.nextInt();
            str = Integer.toString(cadence) + ",";
            write(path,str);
            System.out.println("Enter gear:");
            gear = scanner.nextInt();
            str =  Integer.toString(gear);
            write(path,str + "\n");
            System.out.println("Successfully wrote in the file.");
        } catch (Exception exception) {
            System.out.println("An error occurred.");
            exception.printStackTrace();
            System.exit(0);
        }

        return new Bicycle(wheelNumber, speed, price, brand, cadence, gear);
    }

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
           String[] read = null;
           try {
               read = FileService.read(path);
           } catch (Exception e) {
               System.out.println("An error occurred.");
               e.printStackTrace();
           }

        //   String str = Arrays.toString(read).substring(1, Arrays.toString(read).length()-1);

           bicycles = new Bicycle[read.length];
           fillBicycle(bicycles,read);

           System.out.println("-----------------------");
       }
       return bicycles;
   }

    private void fillBicycle(Bicycle[] bicycles, String[] row){

        int defaultBicycleMembers = 6;
        int index = 0;
        for(int i=0;i<row.length;i++){
            String[] member = row[i].split(",");
            if(member.length == defaultBicycleMembers){
                bicycles[index++] = new Bicycle(Integer.parseInt(member[0]),Double.parseDouble(member[1]),
                        Integer.parseInt(member[2]), member[3], Integer.parseInt(member[4]), Integer.parseInt(member[5]));
            } else {
                System.out.println("Row " + i + " does not have all the bicycle information.");
            }
        }
    }

    public void printInfoOfOneBicycle(Bicycle bicycle){
        if (bicycle != null) {
            System.out.println("Wheel number: " + bicycle.getWheelNumber());
            System.out.println("Speed: " + bicycle.getSpeed());
            System.out.println("Price: " + bicycle.getPrice());
            System.out.println("Brand: " + bicycle.getBrand());
            System.out.println("Cadence: " + bicycle.getCadence());
            System.out.println("Gear: " + bicycle.getGear());
        }
    }

    public void printAllAlchemyBrandBicyclePrices(Bicycle[] bicycles) {
        int count = 0;
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

    public void printBicyclesWithGearMoreThan52(Bicycle[] bicycles) {
        int count = 0;
        for (Bicycle b:bicycles){
            if(b != null && b.getGear() > 52){
                printInfoOfOneBicycle(b);
                System.out.println("*********");
                count++;
            }
        }
        if(count == 0) {
            System.out.println("There are no bicycles with gear more than 52.");
        }
    }

    public Bicycle minimalCadence(Bicycle[] bicycles) {
        if(bicycles[0]== null) { ;
            return null;
        }
        Bicycle min = bicycles[0];
        for (int i = 1; i < bicycles.length; i++) {
            if (bicycles[i]!= null &&  bicycles[i].getCadence() <= min.getCadence()) {
                min = bicycles[i];
            }
        }
        return min;
    }

    public void orderByPrice(Bicycle[] bicycles) {
        Bicycle temp;
        for (int i = 0; i < bicycles.length; i++){
            for (int j = 1; j < (bicycles.length - i); j++) {
                if (bicycles[j - 1].getPrice() > bicycles[j].getPrice()) {
                    temp = bicycles[j - 1];
                    bicycles[j - 1] = bicycles[j];
                    bicycles[j] = temp;
                }
            }
        }

        for (Bicycle b:bicycles){
            System.out.println("*********");
            printInfoOfOneBicycle(b);
        }
    }
}