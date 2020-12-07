package model;

public abstract class Vehicle {

    private int wheelNumber;
    private double speed;
    private int price;
    private String brand;

    public Vehicle(int wheelNumber, double speed, int price, String brand) {
        this.wheelNumber = wheelNumber;
        this.speed = speed;
        this.price = price;
        this.brand = brand;
    }

    public abstract void horn();

    /*
    //Overloading

    public void pushBrake(int decrease){
        speed -= decrease;
    }

    public void addSpeed(int increase) {
        speed += increase;
    }

    int xCoord, yCoord;

    public void addSpeed(){
        xCoord++;
    }
     */

    public int getWheelNumber() {
        return wheelNumber;
    }

    public void setWheelNumber(int wheelNumber) {
        this.wheelNumber = wheelNumber;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
