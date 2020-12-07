package model;

public class PersonalCar extends Car {

    char type; //sport kam family

    public PersonalCar(int wheelNumber, double speed, int price, String brand, String model, String color,
                       String made, int year, int mileage, String plateNumber, char type) {
        super(wheelNumber, speed, price, brand, model, color, made, year, mileage, plateNumber);
        this.type = type;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    @Override
    public void horn() {
        System.out.println("The personal car horn sound is usually a honk.");
    }
}
