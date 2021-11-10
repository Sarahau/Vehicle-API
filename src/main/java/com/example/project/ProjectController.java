package com.example.project;

import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@RestController
public class ProjectController {
    // local txt file
    String fileName = "./vehicles.txt";

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

//    @RequestMapping(value = "/getVehicle/{id}", method = RequestMethod.GET)
//    public Vehicle getVehicle(@PathVariable("id") int id)throws IOException {
//        return
//    }
//
//    @RequestBody(value = "/updateVehicle", method = RequestMethod.PUT)
//    public Vehicle updateVehicle(@RequestBody Vehicle newVehicle)throws IOException{
//        return newVehicle;
//    }

}