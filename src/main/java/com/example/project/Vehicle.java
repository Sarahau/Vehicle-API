package com.example.project;

import java.io.Serializable;
import java.util.Scanner;

/**
 * A class of vehicles, containing information such as the make, model, model year,
 * if the vehicle is a four wheel drive or not, the retail price, and the miles per gallon.
 * Contains getter and setter methods, as well as a printVehicle method that prints all
 * of the information in a formatted block.
 */
public class Vehicle implements Serializable {

    private String make;
    private String model;
    private int modelYear;
    private boolean fwd;
    private int price;
    private int mpg;

    /**
     * Public default constructor
     * @param make
     * @param model
     * @param modelYear
     * @param fwd
     * @param price
     * @param mpg
     */
    public Vehicle(String make, String model, int modelYear, boolean fwd, int price, int mpg) {
        this.make = make;
        this.model = model;
        this.modelYear = modelYear;
        this.fwd = fwd;
        this.price = price;
        this.mpg = mpg;
    }

    /**
     * Gets the make of the vehicle
     * @return the make
     */
    public String getMake() {
        return make;
    }

    /**
     * Sets the make of the vehicle
     * @param make the make
     */
    public void setMake(String make) {
        this.make = make;
    }

    /**
     * Gets the model of the vehicle
     * @return the model
     */
    public String getModel() {
        return model;
    }

    /**
     * Sets the model of the vehicle
     * @param model the model
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Gets the model year of the vehicle
     * @return the model year
     */
    public int getModelYear() {
        return modelYear;
    }

    /**
     * Sets the model year of the vehicle
     * @param modelYear the model year
     */
    public void setModelYear(int modelYear) {
        this.modelYear = modelYear;
    }

    /**
     * Checks if the vehicle is a four wheel drive or not
     * @return true if the vehicle is a four wheel drive, false if it is not
     */
    public boolean isFwd() {
        return fwd;
    }

    /**
     * Sets if the vehicle is a four wheel drive or not
     * @param fwd true if the vehicle is a four wheel drive, false if it is not
     */
    public void setFwd(boolean fwd) {
        this.fwd = fwd;
    }

    /**
     * Gets the retail price of the vehicle
     * @return the retail price
     */
    public int getPrice() {
        return price;
    }

    /**
     * Sets the retail price of the vehicle
     * @param price the retail price of the vehicle
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Gets the number of miles per gallon of the vehicle
     * @return the number of miles per gallon
     */
    public int getMpg() {
        return mpg;
    }

    /**
     * Sets the number of miles per gallon of the vehicle
     * @param mpg the number of miles per gallon
     */
    public void setMpg(int mpg) {
        this.mpg = mpg;
    }

    /**
     * prints the information of the vehicle
     */
    public void printVehicle() {
        System.out.println(modelYear + " " + make + " " + model);
        if (isFwd()) {
            System.out.println("4WD");
        } else {
            System.out.println("not a 4WD");
        }
        System.out.println("$" + price);
        System.out.println(mpg + "MPG");
    }

    /**
     * Returns all information as string. Used for debugging.
     */
    public String vehicleToString(){
        String vehicleInformation;
        if(!this.fwd)
            vehicleInformation = this.make + " " + this.model + "," + this.modelYear + "," + this.price;
        else
            vehicleInformation = this.make + " " + this.model + "," + this.modelYear + "," + this.price + "," + "TRUE";

        return vehicleInformation;
    }
}
