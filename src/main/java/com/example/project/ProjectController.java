package com.example.project;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@RestController
public class ProjectController {
    // local txt file
    String fileName;
    List<String> vehicleStringList;

    @PostConstruct
    public void initializeData() throws FileNotFoundException {
        fileName = "./vehicles.txt";

        vehicleStringList = new ArrayList<>();
        Scanner scanner = new Scanner(new File(fileName));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            vehicleStringList.add(line);
        }
    }

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
        vehicleStringList.add(newVehicle.vehicleToString());

        return newVehicle;
    }

    @RequestMapping(value = "/getVehicle/{id}", method = RequestMethod.GET)
    public Vehicle getVehicle(@PathVariable("id") int id)throws IOException {
        if (id < vehicleStringList.size()) {
            String vehicle = vehicleStringList.get(id);
            if (vehicle.equals("")) {
                return null; //TODO exception (vehicle was deleted)
            }
            Scanner scanner = new Scanner(vehicle);
            String make = scanner.next();
            String vehicleDetails = scanner.next();
            String[] details = vehicleDetails.split(",");
            // Sample Output:
            // Ford Focus,1850,100,TRUE
            String model = details[0];
            int modelYear = Integer.parseInt(details[1]);
            int price = Integer.parseInt(details[2]);
            int mpg = 0; // not in txt file??
            boolean fwd;
            fwd = (details.length == 4); //fwd is the 4th detail if true, false if missing
            return new Vehicle(make, model, modelYear, fwd, price, mpg);
        } else {
            return null; //TODO out of bounds id
        }
    }

    @RequestMapping(value = "/updateVehicle", method = RequestMethod.PUT)
    public Vehicle updateVehicle(@RequestBody Vehicle newVehicle)throws IOException{
        return newVehicle;
    }

    @RequestMapping(value = "/deleteVehicle{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteVehicle(@PathVariable("id") int id) throws IOException{

    }
//
    @RequestMapping(value = "/getLatestVehicles", method = RequestMethod.GET)
    public List<Vehicle> getLatestVehicles() throws IOException{
        Scanner fileScanner = new Scanner(new File(fileName));
        List<Vehicle> latestVehicles = new ArrayList<>();

        while (fileScanner.hasNextLine()){
            if(latestVehicles.size() > 10){
                latestVehicles.remove(0);
            }
            String make = fileScanner.next();

            String vehicleDetails = fileScanner.next();
            String[] details = vehicleDetails.split(",");
            // Sample Output:
            // Ford Focus,1850,100,TRUE
            String model = details[0];
            int modelYear = Integer.parseInt(details[1]);
            int price = Integer.parseInt(details[2]);
            int mpg = 0; // not in txt file??
            boolean fwd;
            fwd = (details.length == 4); //fwd is the 4th detail if true, false if missing
            Vehicle vehicleLine = new Vehicle(make, model, modelYear, fwd, price, mpg);
            latestVehicles.add(vehicleLine);
        }
        return latestVehicles;
    }

}