package model;

public class FamilyCar extends Car {

    /**
     * The Personal Car has:
     *     type -
     * And inherits the Car which inherits the Vehicle: model, color, made, year, mileage, plateNumber,
     * wheelNumber, speed, price and brand.
     */

    char type;

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
        if (type == 's' || type == 'f')
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
}
