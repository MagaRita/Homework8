package model;

public class Bus extends Vehicle {

    /**
     * The Bus has:
     *     isWorking - Is the bus driver driving the bus or is the bus driver having a break.
     *     route - the route for the bus (any not empty string)
     *     routeTime - the time the route takes (any not negative value and not 0)
     * And inherits Vehicle:  wheelNumber, speed, price, brand and plateNumber.
     */


    /**
     * The bus standard price is set to 100.
     */

    public static final int STANDARD_PRICE_BUS = 100;

    private boolean isWorking;
    private String route;
    private double routeTime;

    public Bus(int wheelNumber, double speed, int price, String brand, String plateNumber, boolean isWorking, String route,
               double routeTime) {
        super(wheelNumber, speed, price, brand, plateNumber);
        this.isWorking = isWorking;
        setRoute(route);
        setRouteTime(routeTime);
    }

    public Bus(){
        super(4, 60, 100, "Krystal Shuttle Bus", "11OB1283");
        this.isWorking = true;
        this.route = "Komitas";
        this.routeTime = 2;
    }

    public Bus(String data) {
        super(data.substring(0, data.indexOf(',', 1 + data.indexOf(',', 1 +
                data.indexOf(',', 1 + data.indexOf(',', 1 + data.indexOf(',')))))));

        String[] s = data.replace(data.substring(0, data.indexOf(',', 1 +
                data.indexOf(',', 1 + data.indexOf(',', 1 +
                        data.indexOf(',', 1 + data.indexOf(','))))) + 1), "")
                .split(",");

        setWorking(Boolean.parseBoolean(s[0]));
        setRoute(s[1]);
        setRouteTime(Double.parseDouble(s[2]));
    }
    public boolean isWorking() {
        return isWorking;
    }

    public void setWorking(boolean working) {
        isWorking = working;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        if(route.length() > 0)
            this.route = route;
    }

    public double getRouteTime() {
        return routeTime;
    }

    public void setRouteTime(double routeTime) {
        if(routeTime > 0)
            this.routeTime = routeTime;
    }

    /**
     * The horn sound is different for the bus.
     */

    @Override
    public void horn() {
        System.out.println("The car horn sound is usually a honk.");
    }

    /**
     * The bus info is printed.
     */

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("The bus is working: " + (isWorking()?"Yes":"No"));
        System.out.println("Route: " + getRoute());
        System.out.println("Route time: " + getRouteTime());
    }
}