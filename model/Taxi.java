package model;

public class Taxi extends Car {

    /**
     * The Taxi has:
     *     driverName - the driver name (any not empty string)
     *     rating - rating is between 1-5
     *     isAvailable - is the taxi available or not
     * And inherits Car which inherits the Vehicle:  model, color, made, year, mileage,
     * plateNumber, wheelNumber, speed, price and brand.
     */


    /**
     * The taxi standard price is set to 500.
     */

    public static final int STANDARD_PRICE_TAXI = 500;

    private String driverName;
    private byte rating;
    private boolean isAvailable;

    public Taxi(String data) {
        super(data.substring(0, data.indexOf(',', 1 + data.indexOf(',', 1 +
                                data.indexOf(',', 1 + data.indexOf(',', 1 +
                                data.indexOf(',', 1 + data.indexOf(',', 1 +
                                data.indexOf(',', 1 + data.indexOf(',', 1 +
                                data.indexOf(',', 1 + data.indexOf(','))))))))))));

        String[] s = data.replace(data.substring(0, data.indexOf(',', 1 + data.indexOf(',', 1 +
                                       data.indexOf(',', 1 + data.indexOf(',', 1 +
                                       data.indexOf(',', 1 + data.indexOf(',', 1 +
                                       data.indexOf(',', 1 + data.indexOf(',', 1 +
                                       data.indexOf(',', 1 + data.indexOf(','))))))))))),"")
                                      .split(",");

        setDriverName(s[1]);
        setRating(Byte.parseByte(s[2]));
        setAvailable(Boolean.parseBoolean(s[3]));
    }

    public Taxi(int wheelNumber, double speed, int price, String brand, String plateNumber,
                String model, String color, int made, int year, int mileage, String driverName,
                byte rating, boolean isAvailable) {
        super(wheelNumber, speed, price, brand, plateNumber, model, color, made, year, mileage);
        setDriverName(driverName);
        setRating(rating);
        this.isAvailable = isAvailable;
    }

    /*
    public Taxi(){
        super(4, 60, 100, "Volvo V70", "03AA1201","", "yellow",
                2000, 2001, 30);
        this.driverName = "";
        this.rating = 5;
        this.isAvailable = true;
    }

     */

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        if(driverName.length() > 0)
            this.driverName = driverName;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(byte rating) {
        if (rating >= 1 && rating <= 5)
            this.rating = rating;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    /**
     * The horn sound is different for the taxi.
     */

    @Override
    public void horn() {
            System.out.println("The taxi horn sound is usually a honk.");
    }

    /**
     * The user can call a taxi and the status will change to unavailable.
     * @param fromAddress
     * @param toAddress
     */

    public void call(String fromAddress, String toAddress) {
        isAvailable = false;
    }

    /**
     * When the ride is finished, the status will change to available.
     */

    public void finishRide() {
        isAvailable = true;
    }

    /**
     *  The taxi info is printed.
     */

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Route: " + getDriverName());
        System.out.println("Route time: " + getRating());
        System.out.println("The taxi is available: " + (isAvailable()? "Yes":"No"));
    }
}
