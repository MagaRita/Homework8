package model;

public abstract class Car extends Vehicle {

    private String model;
    private String color;
    private String made;
    private int year;
    private int mileage;
    private String plateNumber;

    public Car(int wheelNumber, double speed, int price, String brand, String model, String color, String made,
               int year, int mileage, String plateNumber) {
        super(wheelNumber, speed, price, brand);
        this.model = model;
        this.color = color;
        this.made = made;
        if (year < 1900) {
            throw new IllegalArgumentException();
        }
        this.year = year;
        this.mileage = mileage;
        this.plateNumber = plateNumber;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMade() {
        return made;
    }

    public void setMade(String made) {
        this.made = made;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        if (year < 1900) {
            throw new IllegalArgumentException();
        }
        this.year = year;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }
}