package model;

public class Bicycle extends Vehicle {

    public int cadence;
    public int gear;

    public Bicycle(int wheelNumber, double speed, int price, String brand, int cadence, int gear) {
        super(wheelNumber, speed, price, brand);
        this.cadence = cadence;
        this.gear = gear;
    }

    @Override
    public void setWheelNumber(int wheelNumber) {
        if(wheelNumber == 2 || wheelNumber == 3){
            setWheelNumber(wheelNumber);
        }
    }

    @Override
    public void horn() {
        System.out.println("The bicycle horn is usually a bell.");
    }

    public int getCadence() {
        return cadence;
    }

    public void setCadence(int cadence) {
        this.cadence = cadence;
    }

    public int getGear() {
        return gear;
    }

    public void setGear(int gear) {
        this.gear = gear;
    }
}
