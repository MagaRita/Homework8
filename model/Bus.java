package model;

public class Bus extends PublicCar {

    private boolean isWorking; // Is the bus driver driving the bus or is the bus driver having a break.
    private String route;
    private double routeTime;

    public Bus(int wheelNumber, double speed, int price, String brand, String model, String color, String made,
               int year, int mileage, String plateNumber, boolean isWorking, String route,
               double routeTime) {
        super(wheelNumber, speed, price, brand, model, color, made, year, mileage, plateNumber);
        this.isWorking = isWorking;
        this.route = route;
        this.routeTime = routeTime;
    }

    public Bus(){
        super(4, 60, 100, "Krystal Shuttle Bus", "Double-decker", "white",
                "2000", 2001, 30, "00AA1101");
        this.isWorking = true;
        this.route = "Komitas";
        this.routeTime = 2;
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
        this.route = route;
    }

    public double getRouteTime() {
        return routeTime;
    }

    public void setRouteTime(double routeTime) {
        this.routeTime = routeTime;
    }

    @Override
    public void horn() {
        System.out.println("The car horn sound is usually a honk.");
    }
}