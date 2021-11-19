package com.example.project;

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

    public List<String> getVehicleStringList() {
        return vehicleStringList;
    }

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
     *{
     * 	"make": "Ford",
     * 	"model": "Focus",
     * 	"modelYear": 1850,
     * 	"fwd": false,
     * 	"price": 100,
     * 	"mpg": 1
     *}
     *
     * @param newVehicle
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/addVehicle", method = RequestMethod.POST)
    public Vehicle addVehicle(@RequestBody Vehicle newVehicle) throws IOException {
        vehicleStringList.add(newVehicle.vehicleToString());
        updateData();

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

    @RequestMapping(value = "/updateVehicle/{id}", method = RequestMethod.PUT)
    public Vehicle updateVehicle(@PathVariable("id") int id, @RequestBody Vehicle newVehicle)throws IOException{
        if (id < vehicleStringList.size()) {
            String vehicle = vehicleStringList.get(id);
            if (vehicle.equals("")) {
                return null; //TODO exception (vehicle was deleted)
            }
            vehicleStringList.set(id, newVehicle.vehicleToString());
            updateData();
            return newVehicle;
        } else {
            return null; //TODO out of bounds id
        }
    }

    @RequestMapping(value = "/deleteVehicle/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteVehicle(@PathVariable("id") int id) throws IOException{
        ResponseEntity responseEntity;
        Scanner scanner = new Scanner(new File(fileName));

        if (id > vehicleStringList.size()) {
            responseEntity = new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        } else {
            vehicleStringList.set(id, "");
            updateData();
            responseEntity = new ResponseEntity(HttpStatus.OK);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/getLatestVehicles", method = RequestMethod.GET)
    public List<Vehicle> getLatestVehicles() throws IOException{
        List<Vehicle> latestVehicles = new ArrayList<>();
        File file = new File("./latestVehicleReport.txt");
        FileWriter outputFile = new FileWriter(file, false);

        for(int i = vehicleStringList.size()-1, latestVehicleCounter = 0; i > 0 && latestVehicleCounter < 10; i--, latestVehicleCounter++){
            if (!vehicleStringList.get(i).equals("")) {
                latestVehicles.add(constructVehicle(vehicleStringList.get(i)));
                outputFile.write(vehicleStringList.get(i) + "\n");
                System.out.println(vehicleStringList.get(i));
            }
        }
        outputFile.close();
        return latestVehicles;
    }

    private void updateData() throws IOException{
        File file = new File(fileName);
        FileWriter outputFile = new FileWriter(file, false);
        for (int i = 0; i < vehicleStringList.size(); i++) {
            outputFile.write(vehicleStringList.get(i) + "\n");
        }
        outputFile.close();
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