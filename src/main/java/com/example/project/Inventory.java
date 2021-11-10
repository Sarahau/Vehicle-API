package com.example.project;/*
 * Sarah Au
 * EGR327 Software Construction
 * Homework 1
 * 9/26/2021
 * Worked with: Jake Speyer (for JUnit tests)
 */

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.SerializationUtils;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Inventory holds objects of type Vehicle (or any subclass that extends Vehicle).
 * It has methods to add or remove vehicles to the inventory, as well as other
 * methods to return the cheapest or priciest vehicle and print the average cost of all
 * the vehicles within the inventory.
 */
public class Inventory implements Serializable {

    private List<Vehicle> vehicleList;

    /**
     * Default constructor that initializes the list
     */
    public Inventory() {
        vehicleList = new ArrayList<>();
    }

    /**
     * Adds a new vehicle to the inventory
     * @param vehicle Vehicle to add
     */
    public void add(Vehicle vehicle) {
        vehicleList.add(vehicle);
    }

    /**
     * Removes a vehicle from the inventory
     * @param vehicle Vehicle to remove
     */
    public void remove(Vehicle vehicle) {
        if (vehicleList.contains(vehicle)) {
            vehicleList.remove(vehicle);
        } else {
            System.out.print("The vehicle you're trying to remove is not in the inventory");
        }
    }

    /**
     * Checks the whole inventory for the cheapest vehicle based on the prices
     * @return Vehicle with the lowest price
     */
    public Vehicle findCheapestVehicle() {
        if (vehicleList.isEmpty()) {
            System.out.print("The inventory is empty");
            return null;
        }

        Vehicle cheapest = vehicleList.get(0);

        for (Vehicle vehicle : vehicleList) {
            if (vehicle.getPrice() < cheapest.getPrice()) {
                cheapest = vehicle;
            }
        }
        return cheapest;
    }

    /**
     * Checks the whole inventory for the most expensive vehicle based on the prices
     * @return Vehicle with the highest price
     */
    public Vehicle findMostExpensiveVehicle() {
        if (vehicleList.isEmpty()) {
            System.out.print("The inventory is empty");
            return null;
        }

        Vehicle priciest = vehicleList.get(0);

        for (Vehicle vehicle : vehicleList) {
            if (vehicle.getPrice() > priciest.getPrice()) {
                priciest = vehicle;
            }
        }

        return priciest;
    }

    /**
     * Prints the average price of all the vehicles in the inventory
     */
    public void printAveragePriceOfAllVehicles() {
        if (vehicleList.isEmpty()) {
            System.out.print("The inventory is empty");
            return;
        }

        int averagePrice;

        if (vehicleList.size() > 1) {
            int total = 0;

            for (Vehicle vehicle : vehicleList) {
                total += vehicle.getPrice();
            }

            averagePrice = total / vehicleList.size();
        } else {
            averagePrice = vehicleList.get(0).getPrice();
        }

        System.out.print("The average price of the vehicles in the inventory is $" + averagePrice);
    }

    /**
     * Checks if the inventory is empty or not (the size of the array is 0 if it is empty)
     * @return true if the inventory is empty, false if it is not
     */
    public boolean isEmpty() {
        return vehicleList.size() == 0;
    }

    /**
     * Checks if the inventory contains a vehicle or not
     * @param vehicle Vehicle to check for
     * @return true if the inventory contains the vehicle, false if it does not
     */
    public boolean containsVehicle(Vehicle vehicle) {
        return vehicleList.contains(vehicle);
    }

    public void printInventory() {
        for (Vehicle vehicle : vehicleList) {
            vehicle.printVehicle();
            System.out.println();
        }
    }

    public Vehicle getVehicle(int index) {
        return vehicleList.get(index);
    }

    public int size() {
        return vehicleList.size();
    }
}
