package com.example.project;

/**
 * A class of cars. Extends the Vehicle class, and includes additional information (if the car
 * is a convertible or not).
 * The printVehicle method also includes the convertible information, as well as the
 * rest of the car's information.
 */
public class Car extends Vehicle {

    private boolean isConvertible;

    /**
     * Public default constructor
     * @param make
     * @param model
     * @param modelYear
     * @param fwd
     * @param price
     * @param mpg
     * @param isConvertible
     */
    public Car(String make, String model, int modelYear, boolean fwd, int price, int mpg, boolean isConvertible) {
        super(make, model, modelYear, fwd, price, mpg);
        this.isConvertible = isConvertible;
    }

    /**
     * Checks if the car is a convertible or not
     * @return true if the car is a convertible, false if it is not
     */
    public boolean isConvertible() {
        return isConvertible;
    }

    /**
     * Sets if the car is a convertible or not
     * @param convertible true if the car is a car, false if it is not
     */
    public void setConvertible(boolean convertible) {
        isConvertible = convertible;
    }

    /**
     * prints the information of the car
     */
    @Override
    public void printVehicle() {
        super.printVehicle();
        if (isConvertible) {
            System.out.println("convertible");
        } else {
            System.out.println("is not a convertible");
        }
    }
}
