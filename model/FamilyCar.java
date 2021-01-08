package model;

public class FamilyCar extends Car {

    /**
     * The Family Car has:
     * type - can be a small car or a big one
     * And inherits the Car which inherits the Vehicle: model, color, made, year, mileage, plateNumber,
     * wheelNumber, speed, price and brand.
     */

    char type = 's';

    public FamilyCar(String data) {

        super(data.substring(0, data.indexOf(',', 1 +
                                data.indexOf(',', 1 +
                                data.indexOf(',', 1 +
                                data.indexOf(',', 1 +
                                data.indexOf(',', 1 +
                                data.indexOf(',', 1 +
                                data.indexOf(',', 1 +
                                data.indexOf(',', 1 +
                                data.indexOf(',', 1 +
                                data.indexOf(','))))))))))));

        setType(data.substring(data.lastIndexOf(',') + 1).trim().charAt(0));
    }

    public FamilyCar(int wheelNumber, double speed, int price, String brand, String plateNumber, String model, String color,
                                  int made, int year, int mileage, char type) {
        super(wheelNumber, speed, price, brand, plateNumber, model, color, made, year, mileage);
        setType(type);
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        if (type == 's' || type == 'b')
            this.type = type;
    }

    /**
     * The horn sound is different for the personal car.
     */

    @Override
    public void horn() {
        System.out.println("The family car horn sound is usually a honk.");
    }

    /**
     * The Family Car info is printed.
     */

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Type: " + getType());
    }

    @Override
    public String toString() {
        return String.valueOf(type);
    }
}
