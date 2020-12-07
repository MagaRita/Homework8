package main;

import model.*;
import service.BicycleService;
import service.BusService;
import service.PersonalCarService;
import service.TaxiService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("Firstly, let's check if the files for bicycle, personal car, bus and taxi exist.\n" +
                "If they don't exist, we will write ourselves.\n");
        BicycleService bicycleService = new BicycleService();
        Bicycle[] bicycles = bicycleService.writeOrReadInfoInBicycleObject();
        PersonalCarService personalCarService = new PersonalCarService();
        PersonalCar[] personalCars = personalCarService.writeOrReadInfoInPersonalCarObject();
        BusService busService = new BusService();
        Bus[] bus = busService.writeOrReadInfoInBusObject();
        TaxiService taxiService = new TaxiService();
        Taxi[] taxi = taxiService.writeOrReadInfoInTaxiObject();

        if(bicycles.length == 0 || personalCars.length == 0 || bus.length == 0 ||
                taxi.length == 0){
            System.out.println("Something is missing in the bicycle, personal car, bus or taxi info.\nPlease provide all " +
                    "the info and run the program again.");
            System.exit(0);
        }

        System.out.println("\nLet's firstly check what the taxi horn sounds like.");
        //Polymorphism
        PublicCar pObj = new Taxi();
        //pObj.horn();
        pObj = new Bus();
        //pObj.horn();

        function(pObj);
        function(new Taxi());

        while(true) {

            int command1 = mainMenuOptions();
            if (command1 == 1) {
                boolean loop1 = true;
                while (loop1) {
                    loop1 = bicycleMenuOptions(bicycles);
                }
            } else if (command1 == 2) {
                boolean loop1 = true;
                while (loop1) {
                   int[] arr = carMenuOptions();
                   int command2 = arr[0];
                   loop1 = arr[1] != 0;

                    if (command2 == 1) {
                        boolean loop2 = true;
                        while (loop2) {
                            loop2= personalCarMenuOptions(personalCars);
                        }
                    } else if (command2 == 2) {
                        boolean loop2 = true;
                        while (loop2) {
                            int[] array = publicCarMenuOptions();
                            int command3 = array[0];
                            loop2 = (array[1] == 0)?false:true;

                            if (command3 == 1) {
                                boolean loop3 = true;
                                while (loop3) {
                                    loop3 = busMenuOptions(bus);
                                }
                            } else if(command3 == 2) {
                                boolean loop3 = true;
                                while (loop3) {
                                    loop3 = taxiMenuOptions(taxi);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private static void function(PublicCar p){
        if(p instanceof Taxi){
            Taxi tObj = (Taxi) p;

            tObj.horn();
        }
    }

    private static int mainMenuOptions(){
        //menu
        Scanner ss = new Scanner(System.in);
        System.out.println();
        System.out.println("----Menu1----");
        System.out.println("Choose the Vehicle.");
        System.out.println("1. Type 1 for the Bicycle.");
        System.out.println("2. Type 2 for the Car.");
        System.out.println("3. Type any other number to Exit");

        int command1 = ss.nextInt();
        switch (command1) {
            case 1:
                System.out.println("You have chosen the Bicycle.");
                break;
            case 2:
                System.out.println("You have chosen the Car.");
                break;
            default:
                System.out.println("The program has exited.");
                System.exit(0);
        }

        return command1;
    }

    private static boolean bicycleMenuOptions(Bicycle[] bicycles){

        boolean loop1 = true;
        BicycleService bicycleService = new BicycleService();

        System.out.println();
        System.out.println("----Bicycle Menu----");
        System.out.println("Choose one of the options below: ");
        System.out.println("1. Type 1 for printing all the bicycles information.");
        System.out.println("2. Type 2 to know all the alchemy brand bicycle prices.");
        System.out.println("3. Type 3 to get all the bicycle with gear more than 52.");
        System.out.println("4. Type 4 to get the minimal cadence bicycle.");
        System.out.println("5. Type 5 to order the bicycles by price and to know the bicycle horn type.");
        System.out.println("6. Type any other number to redirect to Menu1.");
        System.out.println("-----------------------");

        Scanner ss = new Scanner(System.in);
        int command2 = ss.nextInt();

        switch (command2) {
            case 1:
                System.out.println("You have chosen 1: all the bicycles info.");
                for (int i = 0; i < bicycles.length; i++) {
                    if(bicycles[i] != null) {
                        bicycleService.printInfoOfOneBicycle(bicycles[i]);
                        System.out.println("-----------------------");
                    }
                }
                System.out.println();
                break;
            case 2:
                System.out.println("You have chosen 2: the alchemy brand bicycle prices are: ");
                bicycleService.printAllAlchemyBrandBicyclePrices(bicycles);
                System.out.println();
                break;
            case 3:
                System.out.println("You have chosen 3: the bicycles with gear more than 52. ");
                bicycleService.printBicyclesWithGearMoreThan52(bicycles);
                System.out.println();
                break;
            case 4:
                System.out.println("You have chosen 4: the minimal cadence bicycle.");
                bicycleService.printInfoOfOneBicycle(bicycleService.minimalCadence(bicycles));
                System.out.println();
                break;
            case 5:
                System.out.println("You have chosen 5: the bicycles are ordered by price.");
                bicycleService.orderByPrice(bicycles);
                System.out.println();
                if (bicycles[0] != null) {
                    bicycles[0].horn();
                }
                System.out.println();
                break;
            default:
                System.out.println("You didn't choose any of the numbers above, " +
                        "so you will be redirected to Menu1.");
                System.out.println();
                loop1 = false;
        }
        return  loop1;
    }

    private static int[] carMenuOptions(){
        boolean loop1 = true;
        int[] arr = new int[2];

        System.out.println();
        System.out.println("----Menu2----");
        System.out.println("Choose the Car.");
        System.out.println("1. Type 1 for the Personal Car.");
        System.out.println("2. Type 2 for the Public Car.");
        System.out.println("3. Type any other number to go to Menu 1");

        Scanner ss = new Scanner(System.in);
        int command2 = ss.nextInt();
        switch (command2) {
            case 1:
                System.out.println("You have chosen the Personal Car.");
                break;
            case 2:
                System.out.println("You have chosen the Public Car.");
                break;
            default:
                System.out.println("You didn't choose the numbers between 1 or 2, " +
                        "so you will be redirected to Menu1.");
                System.out.println();
                loop1 = false;
        }
        arr[0] = command2;
        arr[1] = (loop1 == false)? 0:1;
        return arr;
    }

    private static boolean personalCarMenuOptions(PersonalCar[] personalCars){
        boolean loop2 = true;
        PersonalCarService personalCarService = new PersonalCarService();

        System.out.println();
        System.out.println("----Personal Car Menu----");
        System.out.println("Choose one of the options below: ");
        System.out.println("1. Type 1 for printing all the personal car information.");
        System.out.println("2. Type 2 to order the personal cars by price and to know the personal car horn type.");
        System.out.println("3. Type 3 to print the sport cars colors.");
        System.out.println("4. Type 4 to print the personal cars which have a price less than 1 million and year more" +
                "than 2018.");
        System.out.println("5. Type any other number to redirect to Menu 2.");
        System.out.println("-----------------------");

        Scanner ss = new Scanner(System.in);
        int command3 = ss.nextInt();

        switch (command3) {
            case 1:
                System.out.println("You have chosen 1: all the personal car info.");
                for (int i = 0; i < personalCars.length; i++) {
                    if(personalCars[i] != null){
                        personalCarService.printInfoOfOnePersonalCar(personalCars[i]);
                        System.out.println("-----------------------");
                    }
                }
                System.out.println();
                break;
            case 2:
                System.out.println("You have chosen 2: the personal cars are ordered by price.");
                personalCarService.orderByPrice(personalCars);
                System.out.println();
                if (personalCars[0] != null) {
                    personalCars[0].horn();
                }
                System.out.println();
                break;
            case 3:
                System.out.println("You have chosen 3: the sport cars colors are as follows.");
                personalCarService.printSportCarsColor(personalCars);
                break;
            case 4:
                System.out.println("You have chosen 4: the personal cars which have price less than 1 million and " +
                        "year more than 2018.");
                personalCarService.printPriceLessThan1MillionAndYearMoreThan2018PersonalCars(personalCars);
                break;
            default:
                System.out.println("You didn't choose any of the numbers above, " +
                        "so you will be redirected to Menu1.");
                System.out.println();
                loop2 = false;
        }
        return loop2;
    }

    private static int[] publicCarMenuOptions(){
        boolean loop2 = true;
        int[] array = new int[2];

        System.out.println();
        System.out.println("----Public Car Menu----");
        System.out.println("Choose one of the options below: ");
        System.out.println("1. Type 1 for Bus.");
        System.out.println("2. Type 2 for Taxi.");
        System.out.println("3. Type any other number to redirect to Menu 2.");
        System.out.println("-----------------------");

        Scanner ss = new Scanner(System.in);
        int command3 = ss.nextInt();

        switch (command3) {
            case 1:
                System.out.println("You have chosen 1: the bus.");
                System.out.println();
                break;
            case 2:
                System.out.println("You have chosen 2: the taxi.");
                System.out.println();
                break;
            default:
                System.out.println("You didn't choose the numbers between 1 or 2, " +
                        "so you will be redirected to Menu 2.");
                System.out.println();
                loop2 = false;
        }
        array[0] = command3;
        array[1] = (loop2 == false)? 0:1;
        return array;
    }

    private static boolean busMenuOptions(Bus[] bus){
        boolean loop3 = true;
        BusService busService = new BusService();

        System.out.println();
        System.out.println("----BUS Menu----");
        System.out.println("Choose one of the options below: ");
        System.out.println("1. Type 1 for printing all the bus information.");
        System.out.println("2. Type 2 to print all the working buses with route Komitas.");
        System.out.println("3. Type 3 to print the newer bus info.");
        System.out.println("4. Type any other number to redirect to Menu 2.");
        System.out.println("-----------------------");

        Scanner ss = new Scanner(System.in);
        int command4 = ss.nextInt();

        switch (command4) {
            case 1:
                System.out.println("You have chosen 1: all the bus info.");
                for (int i = 0; i < bus.length; i++) {
                    if(bus[i] != null) {
                        busService.printInfoOfOneBus(bus[i]);
                        System.out.println("-----------------------");
                    }
                }
                System.out.println();
                break;
            case 2:
                System.out.println("You have chosen 2: all the working buses with route Komitas.");
                busService.printWorkingBusesWithRouteKomitas(bus);
                System.out.println();
                if (bus[0] != null) {
                    bus[0].horn();
                }
                System.out.println();
                break;
            case 3:
                System.out.println("You have chosen 3: the newer bus info.");
                busService.printNewerBusInfo(bus);
                break;
            default:
                System.out.println("You didn't choose any of the numbers above, " +
                        "so you will be redirected to Menu1.");
                System.out.println();
                loop3 = false;
        }
        return loop3;
    }

    private static boolean taxiMenuOptions(Taxi[] taxi){

        boolean loop3 = true;
        TaxiService taxiService = new TaxiService();

        System.out.println();
        System.out.println("----TAXI Menu----");
        System.out.println("Choose one of the options below: ");
        System.out.println("1. Type 1 for printing all the taxi information.");
        System.out.println("2. Type 2 to find a taxi and to know the taxi horn type.");
        System.out.println("3. Type any other number to redirect to Menu 2.");
        System.out.println("-----------------------");

        Scanner ss = new Scanner(System.in);
        int command4 = ss.nextInt();

        switch (command4) {
            case 1:
                System.out.println("You have chosen 1: all the bus info.");
                for (int i = 0; i < taxi.length; i++) {
                    if(taxi[i] != null) {
                        taxiService.printInfoOfOneTaxi(taxi[i]);
                        System.out.println("-----------------------");
                    }
                }
                System.out.println();
                break;
            case 2:
                System.out.println("You have chosen 2: we will find a taxi for you.");
                if (taxiService.findTaxi(taxi, "Komitas 1", "Tashir poxoc", 100)) {
                    System.out.println("We found a taxi for you.");
                } else {
                    System.out.println("Sorry, no taxi's available.");
                }
                System.out.println();
                if (taxi[0] != null) {
                    taxi[0].horn();
                }
                System.out.println();
                break;
            default:
                System.out.println("You didn't choose any of the numbers above, " +
                        "so you will be redirected to Menu1.");
                System.out.println();
                loop3 = false;
        }
        return loop3;
    }
}