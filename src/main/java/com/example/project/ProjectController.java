package com.example.project;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

@RestController
public class ProjectController {
    // local txt file
    String fileName = "./vehicles.txt";
    String storageFile = "./storage.txt";

    @RequestMapping(value = "/addVehicle", method = RequestMethod.POST)
    public Vehicle addVehicle(@RequestBody Vehicle newVehicle) throws IOException {
//        hello how is it going?
        File file = new File(fileName);
        FileWriter outputFile = new FileWriter(file, true);
        outputFile.write(newVehicle.getMake() + " " + newVehicle.getModel() + "," + newVehicle.getModelYear() + "," + newVehicle.getPrice() + ",");
        if (newVehicle.isFwd()) {
            outputFile.write("TRUE");
        }
        outputFile.write("\n");
        outputFile.close();

        return newVehicle;
    }

    @RequestMapping(value = "/getVehicle/{id}", method = RequestMethod.GET)
    public Vehicle getVehicle(@PathVariable("id") int id)throws IOException {
        Scanner scanner = new Scanner(fileName);
        for (int i = 0; i < id; i++) {
            scanner.nextLine();
        }
        Scanner lineScanner = new Scanner(scanner.next());

        String make = lineScanner.next();

        String vehicleDetails = lineScanner.next();
        String[] details = vehicleDetails.split(",");

        //Ford Focus,1850,100,
        String model = details[0];
        int modelYear = Integer.parseInt(details[1]);
        int price = Integer.parseInt(details[2]);
        int mpg = 0; // not in txt file??
        boolean fwd;
        fwd = (details.length == 4); //fwd is the 4th detail if true, false if missing
        return new Vehicle(make, model, modelYear, fwd, price, mpg);

    }

    @RequestMapping(value = "/updateVehicle", method = RequestMethod.PUT)
    public Vehicle updateVehicle(@RequestBody Vehicle newVehicle)throws IOException{
        return newVehicle;
    }

    @RequestMapping(value = "/deleteVehicle{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteVehicle(@PathVariable("id") int id) throws IOException{

    }

    @RequestMapping(value = "/getLatestVehicles", method = RequestMethod.GET)
    public List<Vehicle> getLatestVehicles() throws IOException{

    }

}