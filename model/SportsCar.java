package model;

public class SportsCar extends Car{

    /**
     * The Sports Car extends from the car but doesn't have any variables.
     * @param wheelNumber
     * @param speed
     * @param price
     * @param brand
     * @param plateNumber
     * @param model
     * @param color
     * @param made
     * @param year
     * @param mileage
     */

    public SportsCar(int wheelNumber, double speed, int price, String brand, String plateNumber,
                                String model, String color, int made, int year, int mileage) {
        super(wheelNumber, speed, price, brand, plateNumber, model, color, made, year, mileage);
    }

    public SportsCar(String data) {
        super(data);
    }

    @Override
    public void horn() {
        System.out.println("The sports cars don't have horns.");
    }
}
