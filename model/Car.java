package model;

public abstract class Car extends Vehicle {

    /**
     * The Car has:
     *     model - the model of the car (any not empty string)
     *     color - the color of the car (any not empty string)
     *     made - the car was made in (any number in between 1900 and 2020)
     *     year - the years the car was used since (greater or equal to the year the car was made)
     *     mileage - the mileage of the car (any not negative value and not 0)
     * And inherits the Vehicle: wheelNumber, speed, price, brand and plateNumber.
     */

    private String model = "BMW";
    private String color = "blue";
    private int made = 1990;
    private int year = 2000;
    private int mileage = 23;

    public Car(String data) {
        super(data.substring(0, data.indexOf(',', 1 + data.indexOf(',', 1 +
                                data.indexOf(',', 1 + data.indexOf(',', 1 +
                                data.indexOf(',')))))));

        String[] s = data.replace(data.substring(0, data.indexOf(',', 1 + data.indexOf(',', 1 +
                                                    data.indexOf(',', 1 + data.indexOf(',', 1 +
                                                    data.indexOf(','))))) + 1), "")
                                                    .split(",");

        setModel(s[0]);
        setColor(s[1]);
        setMade(Integer.parseInt(s[2]));
        setYear(Integer.parseInt(s[3]));
        setMileage(Integer.parseInt(s[4]));
    }

    public Car(int wheelNumber, double speed, int price, String brand, String plateNumber, String model, String color, int made,
               int year, int mileage) {
        super(wheelNumber, speed, price, brand, plateNumber);
        setModel(model);
        setColor(color);
        setMade(made);
        setYear(year);
        setMileage(mileage);
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        if(model.length() > 0)
            this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        if(color.length() > 0)
            this.color = color;
    }

    public int getMade() {
        return made;
    }

    public void setMade(int made) {
        if (made > 1900 && made < 2020)
        this.made = made;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        if(year >= made)
            this.year = year;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        if(mileage > 0)
            this.mileage = mileage;
    }

    /**
     * The wheelNumber can be 4 for cars.
     * @param wheelNumber
     */

    @Override
    public void setWheelNumber(int wheelNumber) {
        if(wheelNumber == 4){
            super.setWheelNumber(wheelNumber);
        }
    }

    /**
     * The Car info is printed.
     */

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Model: " + getModel());
        System.out.println("Color: " + getColor());
        System.out.println("Made: " + getMade());
        System.out.println("Year: " + getYear());
        System.out.println("Mileage: " + getMileage());
    }

    @Override
    public String toString() {
        return super.toString() + "," + model + "," + color + "," + made + "," + year + "," + mileage;
    }
}