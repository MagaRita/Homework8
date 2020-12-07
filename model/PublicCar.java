package model;

public abstract class PublicCar extends Car{

    public static final int STANDARD_PRICE = 100;

    public PublicCar(int wheelNumber, double speed, int price, String brand, String model, String color, String made,
                     int year, int mileage, String plateNumber) {
        super(wheelNumber, speed, price, brand, model, color, made, year, mileage, plateNumber);
    }

    public PublicCar(){
        super(4, 60, 100, "Volvo V70", "", "yellow",
                "2000", 2001, 30, "03AA1201");
    }
}
