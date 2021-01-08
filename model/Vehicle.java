package model;

public abstract class Vehicle {

    /**
     * The Vehicle has:
     *     wheelNumber - is a byte which can have 2, 3 or 4 values only
     *     speed - maximal speed per hour (1000 km/h) (any not negative value and not 0)
     *     price - price of the vehicle (any not negative value and not 0)
     *     brand - the brand of the vehicle (any not empty string)
     *     plateNumber - the car plateNumber (should have 5-8 characters in the string)
     */

    private int wheelNumber = 4;
    private double speed = 50.5;
    private int price = 230000;
    private String brand = "new";
    private String plateNumber = "11QA1232";

    public Vehicle(int wheelNumber, double speed, int price, String brand, String plateNumber) {
        setWheelNumber(wheelNumber);
        setSpeed(speed);
        setPrice(price);
        setBrand(brand);
        setPlateNumber(plateNumber);
    }

    public Vehicle(String data) {
        String[] s = data.split(",");
        this.wheelNumber = Integer.parseInt(s[0]);
        this.speed = Double.parseDouble(s[1]);
        this.price = Integer.parseInt(s[2]);
        this.brand = s[3];
        this.plateNumber = s[4];
    }

    public int getWheelNumber() {
        return wheelNumber;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        if(speed > 0)
            this.speed = speed;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        if(price > 0)
            this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        if(brand.length() > 0)
            this.brand = brand;
    }

    /**
     * The wheelNumber will be 2 or 3 for the bicycle and 4 for the car, thus it is an abstract
     * function and we will override it.
     * @param wheelNumber
     */

    public void setWheelNumber(int wheelNumber){
            this.wheelNumber = wheelNumber;
    }

    /**
     * The horn sounds differently for the car and bicycle, that is why it is an abstract function
     * and we will override it.
     */

    public abstract void horn();

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        if(plateNumber.length() >= 5 && plateNumber.length() <= 8)
            this.plateNumber = plateNumber;
    }

    public void printInfo(){
        System.out.println("Wheel number: " + getWheelNumber());
        System.out.println("Speed: " + getSpeed());
        System.out.println("Price: " + getPrice());
        System.out.println("Brand: " + getBrand());
        System.out.println("PlateNumber: " + getPlateNumber());
    }

    @Override
    public String toString() {
        return wheelNumber + "," + speed + "," + price + "," + brand + "," + plateNumber;
    }
}
