package com.example.project;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.SerializationUtils;
import java.io.*;
import java.net.URL;
import java.util.Scanner;

/**
 * Dealer class. Creates an inventory based on data from a web txt file.
 * Also provides serialization and deserialization methods to save the inventory
 * as a .ser file and then re-create the inventory from that same file
 */
public class Dealer implements Serializable {
    String link = "https://raw.githubusercontent.com/kyungsooim/TestData/master/data/SeptemberInventory.txt";
    URL url;
    Scanner scanner;
    Inventory inventory;

    /**
     * Default constructor. Initializes the url and inventory, and sets up a scanner
     * to read data from the web.
     *
     * @throws IOException
     */
    public Dealer() throws IOException {
        url = new URL(link);
        scanner = new Scanner(url.openStream());
        inventory = new Inventory();
    }

    /**
     * Updates the dealer's inventory according to the data provided
     * by the scanner. Assumes that the data provided is stored as
     * "Make" followed by a sequence of specifications separated by commas.
     */
    public void updateInventory() {
        while (scanner.hasNextLine()) {
            Scanner vehicleScanner = new Scanner(scanner.nextLine());
            String make = vehicleScanner.next();

            String vehicleDetails = vehicleScanner.next();
            String[] details = vehicleDetails.split(",");

            String model = details[0];
            int modelYear = Integer.parseInt(details[1]);
            int price = Integer.parseInt(details[2]);
            int mpg = 0; // not in txt file??
            boolean fwd;
            fwd = (details.length == 4); //fwd is the 4th detail if true, false if missing
            inventory.add(new Vehicle(make, model, modelYear, fwd, price, mpg));
        }
    }

    /**
     * Creates and saves the dealer as a serialized object in a .ser file
     *
     * @throws IOException
     */
    public void serialize() throws IOException {
        try {
            FileOutputStream fileOut = new FileOutputStream("./tmp/dealer.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(inventory);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in tmp/dealer.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    /**
     * Deserializes a dealer from a .ser file.
     *
     * @return the deserialized Inventory
     * @throws IOException
     */
    public Inventory deserialize() throws IOException {
        File file = FileUtils.getFile("./tmp/dealer.ser");

        byte[] dataToDeserialize = null;
        dataToDeserialize = FileUtils.readFileToByteArray(file);

        Inventory deserializedInventory = SerializationUtils.deserialize(dataToDeserialize);

        return deserializedInventory;
    }

    /**
     * Prints the dealer's inventory
     */
    public void printDealerInventory() {
        inventory.printInventory();
    }

    /**
     * Generates a txt report of the vehicles within the given Inventory
     * Creates a new txt file if there is no existing file, or else
     * appends the inventory's vehicles to the existing report file
     * @param inv the Inventory of vehicles to add to the report
     * @throws IOException if the output file does not exist
     */
    public void generateReport(Inventory inv) throws IOException {
        File file = new File("./tmp/report.txt");
        FileWriter outputFile = new FileWriter(file, file.exists());
        for (int i = 0; i < inv.size(); i++) {
            Vehicle vehicle = inv.getVehicle(i);
            outputFile.write(vehicle.getMake() + " " + vehicle.getModel() + "," + vehicle.getModelYear() + "," + vehicle.getPrice() + ",");
            if (vehicle.isFwd()) {
                outputFile.write("TRUE");
            }
            outputFile.write("\n");
        }
        outputFile.close();
    }
}
