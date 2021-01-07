package model;

public class SportsCar extends Car{


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
