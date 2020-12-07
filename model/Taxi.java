package model;

public class Taxi extends PublicCar {

    private String driverName;
    private int rating;
    private boolean isAvailable;

    public Taxi(int wheelNumber, double speed, int price, String brand, String model, String color, String made,
                int year, int mileage, String plateNumber, String driverName, int rating,
                boolean isAvailable) {
        super(wheelNumber, speed, price, brand, model, color, made, year, mileage, plateNumber);
        this.driverName = driverName;
        this.rating = rating;
        this.isAvailable = isAvailable;
    }

    public Taxi(){
        super(4, 60, 100, "Volvo V70", "", "yellow",
                "2000", 2001, 30, "03AA1201");
        this.driverName = "";
        this.rating = 5;
        this.isAvailable = true;
    }

    public void call(String fromAddress, String toAddress) {
        isAvailable = false;
    }

    public void finishRide() {
        isAvailable = true;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public void horn() {
            System.out.println("The taxi horn sound is usually a honk.");
    }
}
