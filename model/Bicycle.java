package model;

public class Bicycle extends Vehicle implements Comparable<Bicycle>{

    /**
     *  The Bicycle has:
     *      cadence - cadence is the rate at which a cyclist pedals.
     *                  It's the number of pedal revolutions per minute (any not negative value and not 0)
     *      gear - let's assume there can be high gear and low gear
     * And inherits the Vehicle: wheelNumber, speed, price, brand and plateNumber.
     */

    private int cadence = 100;
    private String gear = "high";


    public Bicycle(int wheelNumber, double speed, int price, String brand, String plateNumber,
                   int cadence, String gear) {
        super(wheelNumber, speed, price, brand, plateNumber);
        setCadence(cadence);
        setGear(gear);
    }

    public Bicycle(String data) {
        super(data.substring(0, data.indexOf(',', 1 + data.indexOf(',', 1 +
                                data.indexOf(',', 1 + data.indexOf(',', 1 +
                                        data.indexOf(',')))))));

        String[] s = data.replace(data.substring(0, data.indexOf(',', 1 +
                                                    data.indexOf(',', 1 +
                                                    data.indexOf(',', 1 +
                                                    data.indexOf(',', 1 +
                                                    data.indexOf(','))))) + 1), "")
                                                    .split(",");
        setCadence(Integer.parseInt(s[0]));
        setGear(s[1]);
    }

    public int getCadence() {
        return cadence;
    }

    public void setCadence(int cadence) {
        if(cadence > 0)
            this.cadence = cadence;
    }

    public String getGear() {
        return gear;
    }

    public void setGear(String gear) {
        if(gear.equals("low") || gear.equals("high"))
            this.gear = gear;
    }

    /**
     * The wheelNumber can be 2 or 3 for bicycles.
     */

    @Override
    public void setWheelNumber(int wheelNumber) {
        if(wheelNumber == 2 || wheelNumber == 3){
            super.setWheelNumber(wheelNumber);
        }
    }

    /**
     * The horn sound is different for the bicycle.
     */

    @Override
    public void horn() {
        System.out.println("The bicycle horn is usually a bell.");
    }

    /**
     * The bicycle info is printed.
     */

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Cadence: " + getCadence());
        System.out.printf("Gear: %s gear\n", getGear());
    }

    @Override
    public String toString() {
        return super.toString() + "," + cadence + "," + gear;
    }

    /**
     * Bicycle class is Comparable and compareTo function compares the cadences.
     * @param o
     * @return
     */

    @Override
    public int compareTo(Bicycle o) {
        return this.cadence - o.cadence;
    }
}
