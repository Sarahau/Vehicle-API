package com.example.project;

/**
 * A class of trucks. Extends the Vehicle class, and includes additional information
 * (if the truck has a side step and what the tow capacity is).
 * The printVehicle method also includes the additional information, as well as the
 * rest of the truck's information.
 */
public class Truck extends Vehicle {
    private boolean hasStep;
    private int towCap;

    /**
     * Public default constructor
     * @param make
     * @param model
     * @param modelYear
     * @param fwd
     * @param price
     * @param mpg
     * @param hasStep
     * @param towCap
     */
    public Truck(String make, String model, int modelYear, boolean fwd, int price, int mpg, boolean hasStep, int towCap) {
        super(make, model, modelYear, fwd, price, mpg);
        this.hasStep = hasStep;
        this.towCap = towCap;
    }

    /**
     * Checks if the truck has a side step or not
     * @return true if the truck has a side step, false if it does not
     */
    public boolean hasStep() {
        return hasStep;
    }

    /**
     * Sets if the truck has a side step or not
     * @param hasStep true if the truck has a side step, false if it does not
     */
    public void setHasStep(boolean hasStep) {
        this.hasStep = hasStep;
    }

    /**
     * Gets the tow capacity of the truck
     * @return the tow capacity of the truck
     */
    public int getTowCap() {
        return towCap;
    }

    /**
     * Sets the tow capacity of the truck
     * @param towCap the tow capacity
     */
    public void setTowCap(int towCap) {
        this.towCap = towCap;
    }

    /**
     * prints the information of the truck
     */
    @Override
    public void printVehicle() {
        super.printVehicle();
        if (hasStep) {
            System.out.println("has a side step");
        } else {
            System.out.println("no side step");
        }
        System.out.println("tow up to " + towCap + " tons");
    }
}
