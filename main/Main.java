package main;

import model.*;
import service.*;

import java.util.*;

public class Main {

    /**
     * After the admin deletes one of the bicycles, family cars, sports cars, buses or taxi's, the new info will be
     * written in these variables.
     */

    static List<Bicycle> newBicycles = null;
    static List<FamilyCar> newFamilyCar = null;
    static List<SportsCar> newSportsCar = null;
    static List<Bus> newBus = null;
    static List<Taxi> newTaxi = null;

    public static void main(String[] args) {

        /**
         * Firstly, let's check whether the files exist and fill the bicycle, family car, sports car, bus
         * and taxi info.
         */

        BicycleService bicycleService = new BicycleService();
        List<Bicycle> bicycles = bicycleService.fillBicycleInfo();
        FamilyCarService familyCarService = new FamilyCarService();
        List<FamilyCar> familyCars = familyCarService.fillFamilyCarInfo();
        SportsCarService sportsCarService = new SportsCarService();
        List<SportsCar> sportsCars = sportsCarService.fillSportsCarInfo();
        BusService busService = new BusService();
        List<Bus> bus = busService.fillBusInfo();
        TaxiService taxiService = new TaxiService();
        List<Taxi> taxi = taxiService.fillTaxiInfo();

        /**
         * The user should either login or register in order to see the information.
         * There is a possibility to login as an admin or a user.
         */

        boolean loop = true;
        UserService userService = new UserService();
        boolean isAdmin = false;

        while (loop) {

            /**
             * User types whether they would like to login (l) or register (r). If 'l','L' or 'r','R' are not typed,
             * the user will be asked to type again, until the correct info will be typed.
             */

            System.out.println("Would you like to Login or Register?\nPlease type 'l' or 'r'.");
            Scanner scanner = new Scanner(System.in);
            String str = scanner.next();
            char c = str.charAt(0);

            switch (c) {
                case 'l':
                case 'L':
                    System.out.println("You have chosen to Login.");

                    /**
                     * In the fillLoginInfo function, the user types the username and password to login
                     * and the  function returns those credentials.
                     * The first element in the array is the username and the second one is the password.
                     */

                    String[] loginInfo = userService.fillLoginInfo();

                    /**
                     * The login info should be validated whether it's correct or not.
                     * The username and password is compared with the database.txt file user info.
                     * If the credentials are correct then they can login successfully as a user or an admin.
                     * Otherwise, the user will get the message "Wrong username or password." and will need
                     * to choose whether to login or register again.
                     */

                    if (userService.loginAdminValidation(loginInfo[0], loginInfo[1])) {
                        isAdmin = true;
                        loop = false;
                    } else if (userService.loginValidation(loginInfo[0], loginInfo[1])) {
                        loop = false;
                    }
                    break;
                case 'r':
                case 'R':
                    System.out.println("You have chosen to Register.");

                    /**
                     * The user types the full name, username, email and password to register
                     * and the fillRegistrationInfo function returns the typed values.
                     * The first element in the array is the full name, then the username and email and then password.
                     */

                    String[] registrationInfo = userService.fillRegistrationInfo();

                    /**
                     * The registration info should be validated whether there already exists a person with those
                     * credentials or not before registering that user.
                     * The validation is done by checking whether the username or email already exist in the
                     * database.txt file or not.
                     *  If they don't exist and if they aren't the same as the admin credentials,
                     *  then the user will be registered and get the "You have registered successfully." message.
                     *  Otherwise, if the credentials exist or are the same as the admin credentials,
                     *  the user will get the message "You are already logged in. Please Login." and will
                     *  need to choose whether to login or register again.
                     */

                    if (userService.newUser(registrationInfo[0], registrationInfo[1],
                            registrationInfo[2], registrationInfo[3])) {
                        System.out.print("You have registered successfully. ");
                        loop = false;
                    }
                    break;
                default:

                    /**
                     * When 'l','L' or 'r','R' are not typed, the  user will be asked to type again,
                     * until the correct info will be typed. The while loop will repeat until a correct info will be
                     * typed.
                     */

                    System.out.println("Incorrect information typed. Please try again.");
            }
        }

        /**
         * The user is registered or logged in and the date and time is also mentioned when the user is logged in.
         */

        Date date = new Date();
        System.out.println(date.toLocaleString());
        //yyyy-MM-dd hh-mm-ss
        //SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh-mm-ss a");
        //System.out.println(sdf.format(date));

        /**
         * Now the user can choose from the menu.
         * If the user is logged in as an admin, the Admin Main Menu will open where the admin can see the user list
         * and can add/remove vehicles from the database (in our case files) or make updates.
         * Otherwise, if the user is logged in, then the Main Menu will open where the registered user can choose
         * whether they would like to buy a vehicle or use the services offered.
         */

        if (isAdmin) {

            /**
             * This is the case when the admin is logged in.
             */

            while (true) {

                /**
                 * The Admin Main Menu opens. If the admin doesn't type 1 or 2, the program will end and the admin
                 * will be logged out.
                 */

                int command1 = adminMainMenuOptions();
                if (command1 == 1) {

                    /**
                     * The admin chose to see the full list of users.
                     */

                    List<User> userList = userService.fillUserInfo();
                    for (User x : userList) {
                        System.out.println(x.toString()); //same as writing just x
                    }
                } else if (command1 == 2) {

                    /**
                     * The admin chose to add/remove or update a vehicle info. To do so, the admin enters the Vehicle
                     * Main Menu.
                     */

                    boolean loops = true;
                    while (loops) {
                        int[] array = vehicleMenuOptions();
                        //command2 shows what command the admin typed.
                        int command2 = array[0];
                        //Shows whether to go to Admin Main Menu or stay in the Vehicle Main Menu.
                        loops = (array[1] == 0) ? false : true;

                        switch (command2) {
                            case 1:
                                /**
                                 * The admin chose to make arrangements in the bicycle. The admin is redirected to the
                                 * Bicycle Menu.
                                 */
                                if (bicycles != null) {
                                    boolean loop1 = true;
                                    while (loop1) {
                                        loop1 = changeBicycleMenuOptions(bicycles);
                                    }
                                }
                                break;
                            case 2:
                                /**
                                 * The admin chose to make arrangements in the family car.
                                 */
                                if (familyCars != null) {
                                    boolean loop1 = true;
                                    while (loop1) {
                                        loop1 = changeFamilyCarMenu(familyCars);
                                    }
                                }
                                break;
                            case 3:
                                /**
                                 * The admin chose to make arrangements in the sports car.
                                 */
                                if (sportsCars != null) {
                                    boolean loop1 = true;
                                    while (loop1) {
                                        loop1 = changeSportsCarMenu(sportsCars);
                                    }
                                }
                                break;
                            case 4:
                                /**
                                 * The admin chose to make arrangements in the bus.
                                 */
                                if (bus != null) {
                                    boolean loop1 = true;
                                    while (loop1) {
                                        loop1 = changeBusMenu(bus);
                                    }
                                }
                                break;
                            case 5:
                                /**
                                 * The admin chose to make arrangements in the taxi.
                                 */
                                if (taxi != null) {
                                    boolean loop1 = true;
                                    while (loop1) {
                                        loop1 = changeTaxiMenu(taxi);
                                    }
                                }
                        }
                    }
                }
            }
        } else {

            /**
             * Now, the user is logged in.
             */
            while (true) {

                /**
                 * The user is provided with the Main Menu Options, whether to buy a vehicle or use the services.
                 * If none of those are chosen, the user will be logged out and the program will end.
                 */
                int command1 = mainMenuOptions();
                if (command1 == 1) {

                    /**
                     * As the user chose to buy a vehicle, Menu2 appears, where the user can choose to buy a
                     * bicycle or a car.
                     */
                    boolean loops = true;
                    while (loops) {

                        /**
                         * The user is provided with the Buying a Vehicle Menu, whether they would like to buy a
                         * bicycle or a car. If none of those are chosen, the user will be redirected to the Main Menu.
                         */

                        int[] array = buyingMenuOptions();
                        //The user chose either to buy a bicycle or a car.
                        int command2 = array[0];
                        //Shows whether to go to Main Menu or stay in the Buying a Vehicle Menu.
                        loops = (array[1] == 0) ? false : true;

                        if (command2 == 1 && bicycles != null) {

                            /**
                             * When the user chooses the bicycle, they will be redirected to the Bicycle Menu
                             * where they can see different options to choose from. If none of those are chosen,
                             * the user will be redirected to the Buying a Vehicle Menu.
                             */

                            boolean loop1 = true;
                            while (loop1) {
                                loop1 = bicycleMenuOptions(bicycles);
                            }
                        } else if (command2 == 2) {

                            /**
                             * When the user chooses the car, they will be redirected to the Car Menu
                             * where they can choose the family car or the sports car. If none of those are chosen,
                             * the user will be redirected to the Buying a Vehicle Menu.
                             */

                            boolean loop1 = true;
                            while (loop1) {
                                int[] arr = carMenuOptions();
                                //This shows whether the user chose the family car or the sports car.
                                int command3 = arr[0];
                                //This shows whether the user wants to redirect to the Buying a Vehicle Menu or not.
                                loop1 = (arr[1] == 0) ? false : true;
                                if (command3 == 1) {

                                    /**
                                     * When the user chooses the family car, they will be redirected to the Family
                                     * Car Menu where they can choose from the options provided.
                                     * If none of those options are chosen, the user will be redirected to the
                                     * Car Menu.
                                     */

                                    boolean loop2 = true;
                                    while (loop2) {
                                        loop2 = familyCarMenuOptions(familyCars);
                                    }
                                } else if (command3 == 2) {

                                    /**
                                     * When the user chooses the sports car, they will be redirected to the Sports
                                     * Car Menu where they can choose from the options provided.
                                     * If none of those options are chosen, the user will be redirected to the
                                     * Car Menu.
                                     */

                                    boolean loop2 = true;
                                    while (loop2) {
                                        loop2 = sportsCarMenuOptions(sportsCars);
                                    }
                                }
                            }
                        }
                    }
                } else if (command1 == 2) {

                    /**
                     * The user chose to use the services offered. Thus, they will see the Public Transportation Menu
                     * from which they can choose the bus or the taxi. If none of those options are chosen, the user
                     * will be redirected to the Main Menu.
                     */

                    boolean loop1 = true;
                    while (loop1) {
                        int[] array = publicTransportationMenuOptions();
                        int command2 = array[0];
                        loop1 = (array[1] == 0) ? false : true;

                        if (command2 == 1) {

                            /**
                             * When the user chooses the bus, the bus options are provided.  If none of those options
                             * are chosen, the user will be redirected to the Public Transportation Menu.
                             */

                            boolean loop2 = true;
                            while (loop2) {
                                loop2 = busMenuOptions(bus);
                            }
                        } else if (command2 == 2) {

                            /**
                             * When the user chooses the taxi, the taxi options are provided. If none of those options
                             * are chosen, the user will be redirected to the Public Transportation Menu.
                             */

                            boolean loop2 = true;
                            while (loop2) {
                                loop2 = taxiMenuOptions(taxi);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * The admin can type 1 to see the full list of registered users, 2 to add/remove or update a vehicle info
     * or any other integer to exit the menu and log out immediately.
     * @return
     */

    private static int adminMainMenuOptions(){
        Scanner ss = new Scanner(System.in);
        System.out.println();
        System.out.println("----Admin Main Menu----");
        System.out.println("As an admin, you can view all the user list or add/remove vehicles.");
        System.out.println("1. Type 1 to view the registered users list.");
        System.out.println("2. Type 2 to add/remove or update a vehicle info.");
        System.out.println("3. Type any other number to Exit the admin menu and log out.");

        int command = ss.nextInt();
        switch (command) {
            case 1:
                System.out.println("You have chosen to view the registered users list.");
                break;
            case 2:
                System.out.println("You have chosen to  add/remove or update a vehicle info.");
                break;
            default:
                System.out.println("You have exited the Admin Main Menu and you are logged out.");
                //The program will end.
                System.exit(0);
        }
        return command;
    }

    /**
     * The admin can choose which options they are interested in from the Vehicle Main Menu (1-5) and when typing smth
     * else, the admin will be redirected to the Admin Main Menu. vehicleMenuOptions returns the command the admin
     * typed and a boolean showing whether smth else was typed instead of 1-5 or not.
     * @return
     */

    private static int[] vehicleMenuOptions(){
        boolean loop = true;
        int[] arr = new int[2];

        Scanner ss = new Scanner(System.in);
        System.out.println();
        System.out.println("----Vehicle Main Menu----");
        System.out.println("As an admin, where would you like to add/remove or make changes in a vehicle properties?");
        System.out.println("1. Type 1 for bicycle.");
        System.out.println("2. Type 2 for family car.");
        System.out.println("3. Type 3 for sports car.");
        System.out.println("4. Type 4 for bus.");
        System.out.println("5. Type 5 for taxi.");
        System.out.println("6. Type any other number to redirect to Admin Main Menu.");

        int command = ss.nextInt();
        switch (command) {
            case 1:
                System.out.println("You have chosen the bicycle.");
                break;
            case 2:
                System.out.println("You have chosen the family car.");
                break;
            case 3:
                System.out.println("You have chosen the sports car.");
            case 4:
                System.out.println("You have chosen the bus.");
            case 5:
                System.out.println("You have chosen the taxi.");
            default:
                System.out.println("ype any other number to redirect to the Admin Main Menu.");
                loop = false;
        }

        arr[0] = command;
        arr[1] = (loop == false)? 0:1;
        return arr;
    }

    /**
     * The admin can choose from the options below and if none of them are chosen, the admin will be redirected to the
     * Vehicle Main Menu.
     * @param bicycles
     * @return
     */

    private static boolean changeBicycleMenuOptions(List<Bicycle> bicycles){

        boolean loop = true;
        BicycleService bicycleService = new BicycleService();

        System.out.println();
        System.out.println("----Bicycle Menu----");
        System.out.println("Choose one of the options below: ");
        System.out.println("1. Type 1 to print all the bicycle's information.");
        System.out.println("2. Type 2 to remove a bicycle.");
        System.out.println("3. Type 3 to add a new bicycle.");
        System.out.println("4. Type 4 to update the bicycle properties.");
        System.out.println("5. Type any other number to redirect to Vehicle Main Menu.");
        System.out.println("-----------------------");

        Scanner ss = new Scanner(System.in);
        int command = ss.nextInt();
        Scanner scanner = new Scanner(System.in);

        /**
         * This is the case when one of the bicycles is deleted, then the new bicycle info should be assigned to the
         * current bicycle.
         */

        if(newBicycles != null)
            bicycles = newBicycles;

        switch (command) {
            case 1:
                System.out.println("You have chosen 1: here are all the bicycles info.");
                for (Bicycle b:bicycles) {
                    if(b != null) {
                        System.out.println(b);
                    }
                }
                System.out.println();
                break;
            case 2:

                /**
                 * removeBicycle function removes one of the bicycle's from the file.
                 */

                System.out.println("You have chosen 2: you are able to remove one of the bicycles " +
                        "which is not available any longer.");
                System.out.print("Type the bicycle which is no longer available and should be removed: ");
                int bicycleNumber = scanner.nextInt();
                if(bicycleNumber >=1 && bicycleNumber <= bicycles.size()) {
                    newBicycles = bicycleService.removeBicycle(bicycleNumber);
                }
                else
                    System.out.println("There is no bicycle with the number you provided." +
                            "\nYou will be redirected to the Bicycle Menu. Choose to remove a bicycle again and " +
                            "type a correct value.");
                System.out.println();
                break;
            case 3:

                /**
                 * addBicycle function adds a new bicycle to the file (the admin bought new ones).
                 */
                System.out.println("You have chosen 3: you can add a new bicycle:");

                if(bicycles == null)
                    bicycles.add(bicycleService.createBicycle(0));
                else
                    bicycles.add(bicycleService.createBicycle(3));
                System.out.println();
                break;
            case 4:

                /**
                 * Here the admin can update the bicycle info. Choose which bicycle should be updated and
                 * which info specifically.
                 */
                
                System.out.println("You have chosen 4: you can update the bicycle info:");
                System.out.println("Choose which bicycle info you would like to change: ");
                bicycleNumber = scanner.nextInt();
                if(bicycleNumber >=1 && bicycleNumber <= bicycles.size()){
                    Bicycle b = bicycleService.chooseBicycle(bicycleNumber);
                    if(b != null) {
                        System.out.println("Type one of the numbers below which indicate " +
                                "which property you would like to change: " +
                                "1 (wheelNumber), 2 (speed), 3 (price), 4(brand), 5(plateNumber)," +
                                " 6(cadence) or 7(gear)?");
                        Scanner sc = new Scanner(System.in);
                        int value = sc.nextInt();

                        switch (value) {
                            case 1:
                                System.out.println("Change the wheelNumber to: ");
                                b.setWheelNumber(sc.nextInt());
                                break;
                            case 2:
                                System.out.println("Change the speed to: ");
                                b.setSpeed(sc.nextDouble());
                                break;
                            case 3:
                                System.out.println("Change the price to: ");
                                b.setPrice(sc.nextInt());
                                break;
                            case 4:
                                System.out.println("Change the brand to: ");
                                b.setBrand(sc.next());
                                break;
                            case 5:
                                System.out.println("Change the plateNumber to: ");
                                b.setPlateNumber(sc.next());
                                break;
                            case 6:
                                System.out.println("Change the cadence to: ");
                                b.setCadence(sc.nextInt());
                                break;
                            case 7:
                                System.out.println("Change the gear to: ");
                                b.setGear(sc.next());
                                break;
                            default:
                                System.out.println("Wrong value typed, so you will be redirected to the Bicycle Menu." +
                                        "\nYou can choose to update the bicycle values again and type correct values.");
                        }
                    }
                }
                else
                /**
                 * If the admin typed an incorrect bicycle number from the list, then they will get this message.
                 */
                    System.out.println("There is no bicycle with the number you provided." +
                            "\nYou will be redirected to the Bicycle Menu. Choose to remove a bicycle again and " +
                            "type a correct value.");
                System.out.println();
                break;
            default:

                /**
                 * When none of the above options are chosen, the user will be redirected to Vehicle Main Menu.
                 */

                System.out.println("You didn't choose any of the numbers above, " +
                        "so you will be redirected to Vehicle Main Menu.");
                System.out.println();
                loop = false;
        }
        return  loop;
    }

    /**
     * The admin can choose from the options below and if none of them are chosen, the admin will be redirected to the
     * Vehicle Main Menu.
     * @return
     */

    private static boolean changeFamilyCarMenu(List<FamilyCar> familyCars){

        boolean loop = true;
        FamilyCarService familyCarService = new FamilyCarService();

        System.out.println();
        System.out.println("----Family Car Menu----");
        System.out.println("Choose one of the options below: ");
        System.out.println("1. Type 1 to print all the family cars information.");
        System.out.println("2. Type 2 to remove a family car.");
        System.out.println("3. Type 3 to add a new family car.");
        System.out.println("4. Type 4 to update the family car properties.");
        System.out.println("5. Type any other number to redirect to Vehicle Main Menu.");
        System.out.println("-----------------------");

        Scanner ss = new Scanner(System.in);
        int command = ss.nextInt();
        Scanner scanner = new Scanner(System.in);

        /**
         * This is the case when one of the family cars is deleted, then the new family car info should be assigned
         * to the current family car.
         */

        if(newFamilyCar != null)
            familyCars = newFamilyCar;

        switch (command) {
            case 1:
                System.out.println("You have chosen 1: here is all the family car info.");
                for (FamilyCar f:familyCars) {
                    if(f != null) {
                        System.out.println(f);
                    }
                }
                System.out.println();
                break;
            case 2:

                /**
                 * removeFamilyCar function removes one of the family cars from the file.
                 */

                System.out.println("You have chosen 2: you are able to remove one of the family cars " +
                        "which is not available any longer.");
                System.out.print("Type the family car which is no longer available and should be removed: ");
                int familyCarNumber = scanner.nextInt();
                if(familyCarNumber >=1 && familyCarNumber <= familyCars.size()) {
                    newFamilyCar = familyCarService.removeFamilyCar(familyCarNumber);
                }
                else
                    System.out.println("There is no family car with the number you provided." +
                            "\nYou will be redirected to the Family Car Menu. Choose to remove a family car again and "
                            + "type a correct value.");
                System.out.println();
                break;
            case 3:

                /**
                 * addFamilyCar function adds a new family car to the file (the admin bought new ones).
                 */

                System.out.println("You have chosen 3: you can add a new family car:");

                if(familyCars == null)
                    familyCars.add(familyCarService.createFamilyCar(0));
                else
                    familyCars.add(familyCarService.createFamilyCar(3));
                System.out.println();
                break;
            case 4:

                /**
                 * Here the admin can update the family car info. Choose which family car should be updated and
                 * which info specifically.
                 */

                System.out.println("You have chosen 4: you can update the family car info:");
                System.out.println("Choose which family car info you would like to change: ");
                familyCarNumber = scanner.nextInt();
                if(familyCarNumber >=1 && familyCarNumber <= familyCars.size()){
                    FamilyCar f = familyCarService.chooseFamilyCar(familyCarNumber);
                    if(f != null) {
                        System.out.println("Type one of the numbers below which indicate " +
                                "which property you would like to change: " +
                                "1 (wheelNumber), 2 (speed), 3 (price), 4(brand), 5(plateNumber)," +
                                " 6(model), 7(color), 8(made), 9(year), 10(mileage), 11(type)?");
                        Scanner sc = new Scanner(System.in);
                        int value = sc.nextInt();

                        switch (value) {
                            case 1:
                                System.out.println("Change the wheelNumber to: ");
                                f.setWheelNumber(sc.nextInt());
                                break;
                            case 2:
                                System.out.println("Change the speed to: ");
                                f.setSpeed(sc.nextDouble());
                                break;
                            case 3:
                                System.out.println("Change the price to: ");
                                f.setPrice(sc.nextInt());
                                break;
                            case 4:
                                System.out.println("Change the brand to: ");
                                f.setBrand(sc.next());
                                break;
                            case 5:
                                System.out.println("Change the plateNumber to: ");
                                f.setPlateNumber(sc.next());
                                break;
                            case 6:
                                System.out.println("Change the model to: ");
                                f.setModel(sc.next());
                                break;
                            case 7:
                                System.out.println("Change the color to: ");
                                f.setColor(sc.next());
                                break;
                            case 8:
                                System.out.println("Change the made to: ");
                                f.setMade(sc.nextInt());
                                break;
                            case 9:
                                System.out.println("Change the year to: ");
                                f.setYear(sc.nextInt());
                                break;
                            case 10:
                                System.out.println("Change the mileage to: ");
                                f.setMileage(sc.nextInt());
                                break;
                            case 11:
                                System.out.println("Change the type to: ");
                                f.setType(sc.next().charAt(0));
                                break;
                            default:
                                System.out.println("Wrong value typed, so you will be redirected to the" +
                                        " Family Car Menu." + "\nYou can choose to update the family car values again" +
                                        " and" + "type correct values.");
                        }
                    }
                }
                else

                /**
                 * If the admin typed an incorrect family car number from the list, then they will get this message.
                 */
                    System.out.println("There is no family cars with the number you provided." +
                            "\nYou will be redirected to the Family Car Menu. Choose to remove a family car again and " +
                            "type a correct value.");
                System.out.println();
                break;
            default:

                /**
                 * When none of the above options are chosen, the user will be redirected to Vehicle Main Menu.
                 */

                System.out.println("You didn't choose any of the numbers above, " +
                        "so you will be redirected to Vehicle Main Menu.");
                System.out.println();
                loop = false;
        }
        return  loop;
    }

    /**
     * The admin can choose from the options below and if none of them are chosen, the admin will be redirected to the
     * Vehicle Main Menu.
     * @return
     */

    private static boolean changeSportsCarMenu(List<SportsCar> sportsCars){

        boolean loop = true;
        SportsCarService sportsCarService = new SportsCarService();

        System.out.println();
        System.out.println("----Sports Car Menu----");
        System.out.println("Choose one of the options below: ");
        System.out.println("1. Type 1 to print all the sports cars information.");
        System.out.println("2. Type 2 to remove a sports car.");
        System.out.println("3. Type 3 to add a new sports car.");
        System.out.println("4. Type 4 to update the sports car properties.");
        System.out.println("5. Type any other number to redirect to Vehicle Main Menu.");
        System.out.println("-----------------------");

        Scanner ss = new Scanner(System.in);
        int command = ss.nextInt();
        Scanner scanner = new Scanner(System.in);

        /**
         * This is the case when one of the sports cars is deleted, then the new sports car info should be assigned
         * to the current sports car.
         */

        if(newSportsCar != null)
            sportsCars = newSportsCar;

        switch (command) {
            case 1:
                System.out.println("You have chosen 1: here is all the sports car info.");
                for (SportsCar s:sportsCars) {
                    if(s != null) {
                        System.out.println(s);
                    }
                }
                System.out.println();
                break;
            case 2:

                /**
                 * removeSportsCar function removes one of the sports cars from the file.
                 */

                System.out.println("You have chosen 2: you are able to remove one of the sports cars " +
                        "which is not available any longer.");
                System.out.print("Type the sports car which is no longer available and should be removed: ");
                int sportsCarNumber = scanner.nextInt();
                if(sportsCarNumber >=1 && sportsCarNumber <= sportsCars.size()) {
                    newSportsCar = sportsCarService.removeSportsCar(sportsCarNumber);
                }
                else
                    System.out.println("There is no sports car with the number you provided." +
                            "\nYou will be redirected to the Sports Car Menu. Choose to remove a sports car again and "
                            + "type a correct value.");
                System.out.println();
                break;
            case 3:

                /**
                 * addSportsCar function adds a new sports car to the file (the admin bought new ones).
                 */
                System.out.println("You have chosen 3: you can add a new sports car:");

                if(sportsCars == null)
                    sportsCars.add(sportsCarService.createSportsCar(0));
                else
                    sportsCars.add(sportsCarService.createSportsCar(3));
                System.out.println();
                break;
            case 4:

                /**
                 * Here the admin can update the sports car info. Choose which sports car should be updated and
                 * which info specifically.
                 */

                System.out.println("You have chosen 4: you can update the sports car info:");
                System.out.println("Choose which sports car info you would like to change: ");
                sportsCarNumber = scanner.nextInt();
                if(sportsCarNumber >=1 && sportsCarNumber <= sportsCars.size()){
                    SportsCar s = sportsCarService.chooseSportsCar(sportsCarNumber);
                    if(s != null) {
                        System.out.println("Type one of the numbers below which indicate " +
                                "which property you would like to change: " +
                                "1 (wheelNumber), 2 (speed), 3 (price), 4(brand), 5(plateNumber)," +
                                " 6(model), 7(color), 8(made), 9(year), 10(mileage)?");
                        Scanner sc = new Scanner(System.in);
                        int value = sc.nextInt();

                        switch (value) {
                            case 1:
                                System.out.println("Change the wheelNumber to: ");
                                s.setWheelNumber(sc.nextInt());
                                break;
                            case 2:
                                System.out.println("Change the speed to: ");
                                s.setSpeed(sc.nextDouble());
                                break;
                            case 3:
                                System.out.println("Change the price to: ");
                                s.setPrice(sc.nextInt());
                                break;
                            case 4:
                                System.out.println("Change the brand to: ");
                                s.setBrand(sc.next());
                                break;
                            case 5:
                                System.out.println("Change the plateNumber to: ");
                                s.setPlateNumber(sc.next());
                                break;
                            case 6:
                                System.out.println("Change the model to: ");
                                s.setModel(sc.next());
                                break;
                            case 7:
                                System.out.println("Change the color to: ");
                                s.setColor(sc.next());
                                break;
                            case 8:
                                System.out.println("Change the made to: ");
                                s.setMade(sc.nextInt());
                                break;
                            case 9:
                                System.out.println("Change the year to: ");
                                s.setYear(sc.nextInt());
                                break;
                            case 10:
                                System.out.println("Change the mileage to: ");
                                s.setMileage(sc.nextInt());
                                break;
                            default:
                                System.out.println("Wrong value typed, so you will be redirected to the" +
                                        " Sports Car Menu." + "\nYou can choose to update the sports car values again" +
                                        " and type correct values.");
                        }
                    }
                }
                else
                /**
                 * If the admin typed an incorrect sports car number from the list, then they will get this message.
                 */
                    System.out.println("There is no sports cars with the number you provided." +
                            "\nYou will be redirected to the Sports Car Menu. Choose to remove a sports car again and " +
                            "type a correct value.");
                System.out.println();
                break;
            default:

                /**
                 * When none of the above options are chosen, the user will be redirected to Vehicle Main Menu.
                 */

                System.out.println("You didn't choose any of the numbers above, " +
                        "so you will be redirected to Vehicle Main Menu.");
                System.out.println();
                loop = false;
        }
        return  loop;
    }

    /**
     * The admin can choose from the options below and if none of them are chosen, the admin will be redirected to the
     * Vehicle Main Menu.
     * @return
     */

    private static boolean changeBusMenu(List<Bus> bus){

        boolean loop = true;
        BusService busService = new BusService();

        System.out.println();
        System.out.println("----Bus Menu----");
        System.out.println("Choose one of the options below: ");
        System.out.println("1. Type 1 to print all the bus information.");
        System.out.println("2. Type 2 to remove a bus.");
        System.out.println("3. Type 3 to add a new bus.");
        System.out.println("4. Type 4 to update the bus properties.");
        System.out.println("5. Type any other number to redirect to Vehicle Main Menu.");
        System.out.println("-----------------------");

        Scanner ss = new Scanner(System.in);
        int command = ss.nextInt();
        Scanner scanner = new Scanner(System.in);

        /**
         * This is the case when one of the buses is deleted, then the new bus info should be assigned to the
         * current bus.
         */

        if(newBus != null)
            bus = newBus;

        switch (command) {
            case 1:

                System.out.println("You have chosen 1: here is all the bus info.");
                for (Bus b:bus) {
                    if(b != null) {
                        System.out.println(b);
                    }
                }
                System.out.println();
                break;
            case 2:

                /**
                 * removeBus function removes one of the bus from the file.
                 */

                System.out.println("You have chosen 2: you are able to remove one of the bus " +
                        "which is not available any longer.");
                System.out.print("Type the bus which is no longer available and should be removed: ");
                int busNumber = scanner.nextInt();
                if(busNumber >=1 && busNumber <= bus.size()) {
                    newBus = busService.removeBus(busNumber);
                }
                else
                    System.out.println("There is no buses with the number you provided." +
                            "\nYou will be redirected to the Bus Menu. Choose to remove a bus again and "
                            + "type a correct value.");
                System.out.println();
                break;
            case 3:

                /**
                 * addBus function adds a new bus to the file (the admin bought new ones).
                 */

                System.out.println("You have chosen 3: you can add a new bus:");

                if(bus == null)
                    bus.add(busService.createBus(0));
                else
                    bus.add(busService.createBus(3));
                System.out.println();
                break;
            case 4:

                /**
                 * Here the admin can update the bus info. Choose which bus should be updated and
                 * which info specifically.
                 */

                System.out.println("You have chosen 4: you can update the bus info:");
                System.out.println("Choose which bus info you would like to change: ");
                busNumber = scanner.nextInt();
                if(busNumber >=1 && busNumber <= bus.size()){
                    Bus b = busService.chooseBus(busNumber);
                    if(b != null) {
                        System.out.println("Type one of the numbers below which indicate " +
                                "which property you would like to change: " +
                                "1 (wheelNumber), 2 (speed), 3 (price), 4(brand), 5(plateNumber)," +
                                " 6(isWorking), 7(route), 8(routeTime)?");
                        Scanner sc = new Scanner(System.in);
                        int value = sc.nextInt();

                        switch (value) {
                            case 1:
                                System.out.println("Change the wheelNumber to: ");
                                b.setWheelNumber(sc.nextInt());
                                break;
                            case 2:
                                System.out.println("Change the speed to: ");
                                b.setSpeed(sc.nextDouble());
                                break;
                            case 3:
                                System.out.println("Change the price to: ");
                                b.setPrice(sc.nextInt());
                                break;
                            case 4:
                                System.out.println("Change the brand to: ");
                                b.setBrand(sc.next());
                                break;
                            case 5:
                                System.out.println("Change the plateNumber to: ");
                                b.setPlateNumber(sc.next());
                                break;
                            case 6:
                                System.out.println("Change the isWorking status to: ");
                                b.setWorking(sc.nextBoolean());
                                break;
                            case 7:
                                System.out.println("Change the route to: ");
                                b.setRoute(sc.next());
                                break;
                            case 8:
                                System.out.println("Change the routeTime to: ");
                                b.setRouteTime(sc.nextDouble());
                                break;
                            default:
                                System.out.println("Wrong value typed, so you will be redirected to the" +
                                        " Bus Menu." + "\nYou can choose to update the bus values again" +
                                        " and type correct values.");
                        }
                    }
                }
                else
                /**
                 * If the admin typed an incorrect bus number from the list, then they will get this message.
                 */
                    System.out.println("There is no bus with the number you provided." +
                            "\nYou will be redirected to the Bus Menu. Choose to remove a bus again and " +
                            "type a correct value.");
                System.out.println();
                break;
            default:

                /**
                 * When none of the above options are chosen, the user will be redirected to Vehicle Main Menu.
                 */

                System.out.println("You didn't choose any of the numbers above, " +
                        "so you will be redirected to Vehicle Main Menu.");
                System.out.println();
                loop = false;
        }
        return  loop;
    }

    /**
     * The admin can choose from the options below and if none of them are chosen, the admin will be redirected to the
     * Vehicle Main Menu.
     * @return
     */

    private static boolean changeTaxiMenu(List<Taxi> taxi){

        boolean loop = true;
        TaxiService taxiService = new TaxiService();

        System.out.println();
        System.out.println("----Taxi Menu----");
        System.out.println("Choose one of the options below: ");
        System.out.println("1. Type 1 to print all the taxi information.");
        System.out.println("2. Type 2 to remove a taxi.");
        System.out.println("3. Type 3 to add a new taxi.");
        System.out.println("4. Type 4 to update the taxi properties.");
        System.out.println("5. Type any other number to redirect to Vehicle Main Menu.");
        System.out.println("-----------------------");

        Scanner ss = new Scanner(System.in);
        int command = ss.nextInt();
        Scanner scanner = new Scanner(System.in);

        /**
         * This is the case when one of the taxi's is deleted, then the new taxi info should be assigned to the
         * current taxi.
         */

        if(newTaxi != null)
            taxi = newTaxi;

        switch (command) {
            case 1:

                System.out.println("You have chosen 1: here is all the taxi info.");
                for (Taxi t:taxi) {
                    if(t != null) {
                        System.out.println(t);
                    }
                }
                System.out.println();
                break;
            case 2:

                /**
                 * removeTaxi function removes one of the taxi from the file.
                 */

                System.out.println("You have chosen 2: you are able to remove one of the taxi " +
                        "which is not available any longer.");
                System.out.print("Type the taxi which is no longer available and should be removed: ");
                int taxiNumber = scanner.nextInt();
                if(taxiNumber >=1 && taxiNumber <= taxi.size()) {
                    newTaxi = taxiService.removeTaxi(taxiNumber);
                }
                else
                    System.out.println("There is no taxi's with the number you provided." +
                            "\nYou will be redirected to the Taxi Menu. Choose to remove a taxi again and "
                            + "type a correct value.");
                System.out.println();
                break;
            case 3:

                /**
                 * addTaxi function adds a new taxi to the file (the admin bought new ones).
                 */
                System.out.println("You have chosen 3: you can add a new taxi:");

                if(taxi == null)
                    taxi.add(taxiService.createTaxi(0));
                else
                    taxi.add(taxiService.createTaxi(3));
                System.out.println();
                break;
            case 4:

                /**
                 * Here the admin can update the taxi info. Choose which taxi should be updated and
                 * which info specifically.
                 */
                System.out.println("You have chosen 4: you can update the taxi info:");
                System.out.println("Choose which taxi info you would like to change: ");
                taxiNumber = scanner.nextInt();
                if(taxiNumber >=1 && taxiNumber <= taxi.size()){
                    Taxi t = taxiService.chooseTaxi(taxiNumber);
                    if(t != null) {
                        System.out.println("Type one of the numbers below which indicate " +
                                "which property you would like to change: " +
                                "1 (wheelNumber), 2 (speed), 3 (price), 4(brand), 5(plateNumber)," +
                                " 6(model), 7(color), 8(made), 9(year), 10(mileage), 11(driverName), 12(rating) or " +
                                "13(isAvailable)?");
                        Scanner sc = new Scanner(System.in);
                        int value = sc.nextInt();

                        switch (value) {
                            case 1:
                                System.out.println("Change the wheelNumber to: ");
                                t.setWheelNumber(sc.nextInt());
                                break;
                            case 2:
                                System.out.println("Change the speed to: ");
                                t.setSpeed(sc.nextDouble());
                                break;
                            case 3:
                                System.out.println("Change the price to: ");
                                t.setPrice(sc.nextInt());
                                break;
                            case 4:
                                System.out.println("Change the brand to: ");
                                t.setBrand(sc.next());
                                break;
                            case 5:
                                System.out.println("Change the plateNumber to: ");
                                t.setPlateNumber(sc.next());
                                break;
                            case 6:
                                System.out.println("Change the model to: ");
                                t.setModel(sc.next());
                                break;
                            case 7:
                                System.out.println("Change the color to: ");
                                t.setColor(sc.next());
                                break;
                            case 8:
                                System.out.println("Change the made to: ");
                                t.setMade(sc.nextInt());
                                break;
                            case 9:
                                System.out.println("Change the year to: ");
                                t.setYear(sc.nextInt());
                                break;
                            case 10:
                                System.out.println("Change the mileage to: ");
                                t.setMileage(sc.nextInt());
                                break;
                            case 11:
                                System.out.println("Change the driver name to: ");
                                t.setDriverName(sc.next());
                                break;
                            case 12:
                                System.out.println("Change the rating to: ");
                                t.setRating(sc.nextByte());
                                break;
                            case 13:
                                System.out.println("Change the isAvailable status to: ");
                                t.setAvailable(sc.nextBoolean());
                                break;
                            default:
                                System.out.println("Wrong value typed, so you will be redirected to the" +
                                        " Taxi Menu." + "\nYou can choose to update the taxi values again" +
                                        " and type correct values.");
                        }
                    }
                }
                else
                /**
                 * If the admin typed an incorrect taxi number from the list, then they will get this message.
                 */
                    System.out.println("There is no taxi with the number you provided." +
                            "\nYou will be redirected to the Taxi Menu. Choose to remove a taxi again and " +
                            "type a correct value.");
                System.out.println();
                break;
            default:

                /**
                 * When none of the above options are chosen, the user will be redirected to Vehicle Main Menu.
                 */

                System.out.println("You didn't choose any of the numbers above, " +
                        "so you will be redirected to Vehicle Main Menu.");
                System.out.println();
                loop = false;
        }
        return  loop;
    }

    /**
     * The user can type 1 to buy a vehicle, 2 to choose one of the services or any other integer to exit the menu and
     * log out immediately.
     * @return
     */

    private static int mainMenuOptions(){
        Scanner ss = new Scanner(System.in);
        System.out.println();
        System.out.println("----Main Menu----");
        System.out.println("Would you like to buy a vehicle or use the services that are offered?");
        System.out.println("1. Type 1 for buying a vehicle.");
        System.out.println("2. Type 2 to know about the public transportation services.");
        System.out.println("3. Type any other number to Exit the menu and log out.");

        int command = ss.nextInt();
        switch (command) {
            case 1:
                System.out.println("You have chosen to buy vehicles.");
                break;
            case 2:
                System.out.println("You have chosen to see the public transportation services.");
                break;
            default:
                System.out.println("You have exited the menu and you are logged out.");
                //The program will end.
                System.exit(0);
        }

        return command;
    }

    /**
     * The user can type 1 to buy a bicycle, 2 to buy a car or any other integer to go to the Main Menu.
     * @return
     */

    private static int[] buyingMenuOptions(){

        boolean loop = true;
        int[] arr = new int[2];

        Scanner ss = new Scanner(System.in);
        System.out.println();
        System.out.println("----Buying a Vehicle Menu----");
        System.out.println("Would you like to buy a bicycle or a car?");
        System.out.println("1. Type 1 for the bicycle.");
        System.out.println("2. Type 2 for the car.");
        System.out.println("3. Type any other number to redirect to the Main Menu.");

        int command = ss.nextInt();
        switch (command) {
            case 1:
                System.out.println("You have chosen to buy a bicycle.");
                break;
            case 2:
                System.out.println("You have chosen to buy a car.");
                break;
            default:
                System.out.println("You didn't choose any of the numbers above, " +
                "so you will be redirected to the Main Menu.\n");
                loop = false;
        }

        /**
         * The typed command should be returned, thus if 1 or 2 is chosen, it will be in arr[0] and if any other number
         * is chosen, then arr[1] will redirect to the Main Menu.
         */

        arr[0] = command;
        arr[1] = (loop == false)? 0:1;
        return arr;
    }

    /**
     * The bicycleMenuOptions function provides the Bicycle Menu for the user to choose from. If none of the options
     * are chosen, the user will be redirected to the Buying a Vehicle Menu.
     * @param bicycles
     * @return
     */

    private static boolean bicycleMenuOptions(List<Bicycle> bicycles){

        boolean loop = true;
        BicycleService bicycleService = new BicycleService();

        System.out.println();
        System.out.println("----Bicycle Menu----");
        System.out.println("Choose one of the options below: ");
        System.out.println("1. Type 1 for printing all the bicycle's information.");
        System.out.println("2. Type 2 to know all the alchemy brand bicycle prices.");
        System.out.println("3. Type 3 to get all the bicycle's with high gear.");
        System.out.println("4. Type 4 to get the maximum cadence bicycle.");
        System.out.println("5. Type 5 to order the bicycles in ascending order by price " +
                "and to know the bicycle horn type.");
        System.out.println("6. Type 6 to order the bicycles  in descending order by price.");
        System.out.println("7. Type any other number to redirect to Buying a Vehicle Menu.");
        System.out.println("-----------------------");

        Scanner ss = new Scanner(System.in);
        int command = ss.nextInt();

        switch (command) {
            case 1:

                /**
                 * printBicycleInfo function prints the bicycle info one bicycle at a time.
                 */

                System.out.println("You have chosen 1: here are all the bicycles info.");
                for (Bicycle b:bicycles) {
                    if(b != null) {
                        b.printInfo();
                        System.out.println("-----------------------");
                    }
                }
                System.out.println();
                break;
            case 2:

                /**
                 * printAllAlchemyBrandBicyclePrices function prints all the bicycle prices which have Alchemy brand.
                 */

                System.out.println("You have chosen 2: the alchemy brand bicycle prices are: ");
                bicycleService.printAllAlchemyBrandBicyclePrices(bicycles);
                System.out.println();
                break;
            case 3:

                /**
                 * printBicyclesWithHighGear function will print all the bicycles which have high gear.
                 */
                System.out.println("You have chosen 3: the bicycles with high gear are:");
                bicycleService.printBicyclesWithHighGear(bicycles);
                System.out.println();
                break;
            case 4:

                /**
                 * maxCadence function finds the bicycle which has the maximum cadence and printBicycleInfo function
                 * prints that bicycle full info.
                 */

                System.out.println("You have chosen 4: the maximum cadence bicycle.");
                if (bicycles != null)
                    bicycleService.maxCadence(bicycles).printInfo();
                System.out.println();
                break;
            case 5:

                /**
                 * orderByPriceAsc function orders the bicycles by price in the ascending order
                 * and then we check what the bicycle horn sound is.
                 */

                System.out.println("You have chosen 5: the bicycles are in ascending ordered by price.");
                if (bicycles.get(0) != null) {
                    bicycles.get(0).horn();
                }
                System.out.println();
                bicycleService.orderByPriceAsc(bicycles);
                for (Bicycle b:bicycles)
                    System.out.println(b);
                System.out.println();

                break;
            case 6:

                /**
                 * orderByPrice function orders the bicycles by price in the descending order.
                 */

                System.out.println("You have chosen 6: the bicycles are in descending ordered by price.");
                if(bicycles != null) {
                    bicycleService.orderByPriceDesc(bicycles);
                    for (Bicycle b : bicycles)
                        System.out.println(b);
                }
                System.out.println();
                break;
            default:

                /**
                 * When none of the above options are chosen, the user will be redirected to Buying a Vehicle Menu.
                 */
                System.out.println("You didn't choose any of the numbers above, " +
                        "so you will be redirected to Buying a Vehicle Menu.");
                System.out.println();
                loop = false;
        }
        return  loop;
    }

    /**
     * The carMenuOptions function provides the Car Menu where the user can choose the Family Car or the Sport Car.
     * If none of the options are chosen, the user will be redirected to the Buying a Vehicle Menu.
     * @return
     */

    private static int[] carMenuOptions(){
        boolean loop1 = true;
        int[] arr = new int[2];

        System.out.println();
        System.out.println("----Car Menu----");
        System.out.println("Choose the Car.");
        System.out.println("1. Type 1 for the Family Car.");
        System.out.println("2. Type 2 for the Sports Car.");
        System.out.println("3. Type any other number to go to Buying a Vehicle Menu");

        Scanner ss = new Scanner(System.in);
        int command2 = ss.nextInt();
        switch (command2) {
            case 1:
                System.out.println("You have chosen the Family Car.");
                break;
            case 2:
                System.out.println("You have chosen the Sports Car.");
                break;
            default:
                System.out.println("You didn't choose the numbers above, " +
                        "so you will be redirected to Buying a Vehicle Menu.");
                System.out.println();
                loop1 = false;
        }

        arr[0] = command2;
        arr[1] = (loop1 == false)? 0:1;
        return arr;
    }

    /**
     * The familyCarMenuOptions function provides the Family Car Menu for the user to choose from. If none of the
     * options are chosen, the user will be redirected to the Car Menu.
     * @param familyCars
     * @return
     */

    private static boolean familyCarMenuOptions(List<FamilyCar> familyCars){
        boolean loop = true;
        FamilyCarService familyCarService = new FamilyCarService();

        System.out.println();
        System.out.println("----Family Car Menu----");
        System.out.println("Choose one of the options below: ");
        System.out.println("1. Type 1 for printing all the family car information.");
        System.out.println("2. Type 2 to order the family cars by price and to know the family car horn type.");
        System.out.println("3. Type 3 to print the family cars colors.");
        System.out.println("4. Type 4 to print the family cars which have a price less than 1 million and year more" +
                "than 2018.");
        System.out.println("5. Type any other number to redirect to the Car Menu.");
        System.out.println("-----------------------");

        Scanner ss = new Scanner(System.in);
        int command = ss.nextInt();

        switch (command) {
            case 1:
                System.out.println("You have chosen 1: all the family car info.");
                for (FamilyCar f:familyCars) {
                    if(f != null){
                        f.printInfo();
                        System.out.println("-----------------------");
                    }
                }
                System.out.println();
                break;
            case 2:

                /**
                 * The family car is ordered by price.
                 */
                System.out.println("You have chosen 2: the family cars are ordered by price.");
                familyCarService.orderByPrice(familyCars);
                System.out.println();
                if (familyCars.get(0) != null) {
                    familyCars.get(0).horn();
                }
                System.out.println();
                break;
            case 3:
                /**
                 * Provides all the family car colors.
                 */
                System.out.println("You have chosen 3: the family car colors are as follows.");
                familyCarService.printFamilyCarColor(familyCars);
                break;
            case 4:
                /**
                 * Provides the family cars which have price less than 1 million and year more than 2018.
                 */
                System.out.println("You have chosen 4: the family cars which have price less than 1 million and " +
                        "year more than 2018.");
                familyCarService.printPriceLessThan1MillionAndYearMoreThan2018FamilyCars(familyCars);
                break;
            default:
                /**
                 * When none of the above are chosen, the user will be redirected to the Car Menu.
                 */
                System.out.println("You didn't choose any of the numbers above, " +
                        "so you will be redirected to the Car Menu.");
                System.out.println();
                loop = false;
        }
        return loop;
    }

    /**
     * The sportsCarMenuOptions function provides the Sports Car Menu for the user to choose from. If none of the
     * options are chosen, the user will be redirected to the Car Menu.
     * @param sportsCars
     * @return
     */

    private static boolean sportsCarMenuOptions(List<SportsCar> sportsCars){
        boolean loop = true;
        SportsCarService sportsCarService = new SportsCarService();

        System.out.println();
        System.out.println("----Sports Car Menu----");
        System.out.println("Choose one of the options below: ");
        System.out.println("1. Type 1 for printing all the sports car information.");
        System.out.println("2. Type 2 to order the sports cars by price and to know the sports car horn type.");
        System.out.println("3. Type 3 to print the sports cars colors.");
        System.out.println("4. Type 4 to print the sports cars which have a price less than 1 million and year more" +
                "than 2018.");
        System.out.println("5. Type any other number to redirect to the Car Menu.");
        System.out.println("-----------------------");

        Scanner ss = new Scanner(System.in);
        int command = ss.nextInt();

        switch (command) {
            case 1:

                /**
                 * Provides the full list of the sports cars.
                 */

                System.out.println("You have chosen 1: all the sports car info.");
                for (SportsCar s:sportsCars) {
                    if(s != null){
                        s.printInfo();
                        System.out.println("-----------------------");
                    }
                }
                System.out.println();
                break;
            case 2:

                /**
                 * The sports cars are ordered by price.
                 */

                System.out.println("You have chosen 2: the sports cars are ordered by price.");
                sportsCarService.orderByPrice(sportsCars);
                System.out.println();
                if (sportsCars.get(0) != null) {
                    sportsCars.get(0).horn();
                }
                System.out.println();
                break;
            case 3:

                /**
                 * The sports car colors are provided.
                 */

                System.out.println("You have chosen 3: the sports car colors are as follows.");
                sportsCarService.printSportsCarColor(sportsCars);
                break;
            case 4:

                /**
                 * Provides the sports cars which have price less than 1 million and year more than 2018.
                 */

                System.out.println("You have chosen 4: the sports cars which have price less than 1 million and " +
                        "year more than 2018.");
                sportsCarService.printPriceLessThan1MillionAndYearMoreThan2018SportsCars(sportsCars);
                break;
            default:
                System.out.println("You didn't choose any of the numbers above, " +
                        "so you will be redirected to the Car Menu.");
                System.out.println();
                loop = false;
        }
        return loop;
    }

    /**
     * The publicTransportationMenuOptions function provides the Public Transportation Menu where the user can
     * choose bus or taxi options. If none of the options are chosen, the user will be redirected to the Main Menu.
     * @return
     */

    private static int[] publicTransportationMenuOptions(){
        boolean loop = true;
        int[] array = new int[2];

        System.out.println();
        System.out.println("----Public Transportation Menu----");
        System.out.println("Choose one of the options below: ");
        System.out.println("1. Type 1 for Bus.");
        System.out.println("2. Type 2 for Taxi.");
        System.out.println("3. Type any other number to redirect to the Main Menu.");
        System.out.println("-----------------------");

        Scanner ss = new Scanner(System.in);
        int command3 = ss.nextInt();

        switch (command3) {
            case 1:

                /**
                 * The user chose the bus.
                 */

                System.out.println("You have chosen 1: the bus.");
                System.out.println();
                break;
            case 2:

                /**
                 * The user chose the taxi.
                 */

                System.out.println("You have chosen 2: the taxi.");
                System.out.println();
                break;
            default:

                /**
                 * The user didn't choose the above options and thus will be redirected to the Main Menu.
                 */

                System.out.println("You didn't choose the numbers above, " +
                        "so you will be redirected to the Main Menu.");
                System.out.println();
                loop = false;
        }
        array[0] = command3;
        array[1] = (loop == false)? 0:1;
        return array;
    }

    /**
     * The busMenuOptions function provides the Bus Menu where the user can choose one of the options provided.
     * If none of the options are chosen, the user will be redirected to the Public Transportation Menu.
     * @return
     */

    private static boolean busMenuOptions(List<Bus> bus){
        boolean loop3 = true;
        BusService busService = new BusService();

        System.out.println();
        System.out.println("----BUS Menu----");
        System.out.println("Choose one of the options below: ");
        System.out.println("1. Type 1 for printing all the bus information.");
        System.out.println("2. Type 2 to print all the working buses with route Komitas.");
        System.out.println("3. Type any other number to redirect to Public Transportation Menu.");
        System.out.println("-----------------------");

        Scanner ss = new Scanner(System.in);
        int command4 = ss.nextInt();

        switch (command4) {
            case 1:

                /**
                 * Provides all the bus info.
                 */

                System.out.println("You have chosen 1: all the bus info.");
                for (Bus b:bus) {
                    if(b != null) {
                        b.printInfo();
                        System.out.println("-----------------------");
                    }
                }
                System.out.println();
                break;
            case 2:

                /**
                 * Provides all the working buses with route Komitas.
                 */

                System.out.println("You have chosen 2: all the working buses with route Komitas.");
                busService.printWorkingBusesWithRouteKomitas(bus);
                System.out.println();
                if (bus.get(0) != null) {
                    bus.get(0).horn();
                }
                System.out.println();
                break;
            default:

                /**
                 * The user will be redirected to the Public Transportation Menu.
                 */

                System.out.println("You didn't choose any of the numbers above, " +
                        "so you will be redirected to Public Transportation Menu.");
                System.out.println();
                loop3 = false;
        }
        return loop3;
    }

    /**
     * The taxiMenuOptions function provides the Taxi Menu where the user can choose one of the options provided.
     * If none of the options are chosen, the user will be redirected to the Public Transportation Menu.
     * @param taxi
     * @return
     */
    private static boolean taxiMenuOptions(List<Taxi> taxi){

        boolean loop3 = true;
        TaxiService taxiService = new TaxiService();

        System.out.println();
        System.out.println("----TAXI Menu----");
        System.out.println("Choose one of the options below: ");
        System.out.println("1. Type 1 for printing all the taxi information.");
        System.out.println("2. Type 2 to find a taxi and to know the taxi horn type.");
        System.out.println("3. Type any other number to redirect to Public Transportation Menu.");
        System.out.println("-----------------------");

        Scanner ss = new Scanner(System.in);
        int command4 = ss.nextInt();

        switch (command4) {
            case 1:

                /**
                 * Provides the bus info.
                 */

                System.out.println("You have chosen 1: all the bus info.");
                for (Taxi t : taxi) {
                    if(t != null) {
                        t.printInfo();
                        System.out.println("-----------------------");
                    }
                }
                System.out.println();
                break;
            case 2:

                /**
                 * Finds taxi for the user.
                 */

                System.out.println("You have chosen 2: we will find a taxi for you.");
                if (taxiService.findTaxi(taxi, "Komitas 1", "Tashir poxoc", 100)) {
                    System.out.println("We found a taxi for you.");
                } else {
                    System.out.println("Sorry, no taxi's available.");
                }
                System.out.println();
                if (taxi.get(0) != null) {
                    taxi.get(0).horn();
                }
                System.out.println();
                break;
            default:
                System.out.println("You didn't choose any of the numbers above, " +
                        "so you will be redirected to Public Transportation Menu.");
                System.out.println();
                loop3 = false;
        }
        return loop3;
    }

      /*
        Vehicle vehicle = new Vehicle(2, 50, 1000, "Arch", "11GH1231") {
            @Override
            public void horn() {
                System.out.println("The horn is a honk");
            }
        };

        System.out.println(vehicle);

        Bicycle bicycle2 = new Bicycle(2, 50, 1000, "Arch", "11GH1231", 100,
        "high");
        System.out.println(bicycle2);

         */

             /*

        System.out.println("Now, let's check if the files for bicycle, personal car, bus and taxi exist.\n" +
                "If they don't exist, we will write ourselves.\n");


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

    }

    private static void function(PublicCar p){
        if(p instanceof Taxi){
            Taxi tObj = (Taxi) p;

            tObj.horn();
        }

         */
}