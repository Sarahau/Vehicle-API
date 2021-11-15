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

    /**
     * POST method that creates a new vehicle with the provided details in the body of the API request.
     * Sample API request:
     *
     *
     * @param newVehicle
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/addVehicle", method = RequestMethod.POST)
    public Vehicle addVehicle(@RequestBody Vehicle newVehicle) throws IOException {
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
            return constructVehicle(vehicle);
        } else {
            return null; //TODO out of bounds id
        }
    }

    @RequestMapping(value = "/updateVehicle", method = RequestMethod.PUT)
    public Vehicle updateVehicle(@RequestBody Vehicle newVehicle)throws IOException{
        return newVehicle;
    }

    @RequestMapping(value = "/deleteVehicle/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteVehicle(@PathVariable("id") int id) throws IOException{
        ResponseEntity responseEntity;
        Scanner scanner = new Scanner(new File(fileName));
        List<String> stringList = new ArrayList<>();

        while (scanner.hasNextLine()) {
           String line = scanner.nextLine();
           stringList.add(line);
       }

       if (id > stringList.size()) {
           responseEntity = new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
       } else {
           stringList.set(id, "");

           File file = new File(fileName);
           FileWriter outputFile = new FileWriter(file, false);
           for (int i = 0; i < stringList.size(); i++) {
               outputFile.write(stringList.get(i) + "\n");
           }
           outputFile.close();
           responseEntity = new ResponseEntity(HttpStatus.OK);
       }
        return responseEntity;
    }

    @RequestMapping(value = "/getLatestVehicles", method = RequestMethod.GET)
    public List<Vehicle> getLatestVehicles() throws IOException{
        List<Vehicle> latestVehicles = new ArrayList<>();
        int latestVehicleCounter = 0;
        // I hope this gives you a headache Sarah
        for(int i = vehicleStringList.size()-1; i > 0 || latestVehicleCounter < 10; i--, latestVehicleCounter++){
            latestVehicles.add(constructVehicle(vehicleStringList.get(i)));
        }
        return latestVehicles;
    }

    private Vehicle constructVehicle(String vehicleString){
        Scanner scanner = new Scanner(vehicleString);
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
    }
}