package com.example.project;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@Component
public class MyTasks {

    RestTemplate restTemplate = new RestTemplate();
    String[] makerList = {"Audi", "BMW", "Chevrolet", "Dodge", "Ferrari", "GMC", "Honda", "Infiniti", "Jeep", "Kia", "Lotus", "McLaren", "Nissan", "Porsche", "Rolls-Royce", "Subaru", "Tesla", "Volkswagen"};
    String[] modelList = {"S4", "M3", "Camero", "Caliber", "Portofino", "Yukon", "Accord", "Q50", "Wrangler", "Sorento", "Evora", "P1", "GT-R", "Cayenne", "Ghost", "WRX", "Model S", "Beetle"};
    Random random = new Random();

    @Scheduled(fixedRate = 2000)
    public void addVehicle() {
        String url = "http://localhost:8080/addVehicle";
        String make = makerList[random.nextInt(makerList.length)];
        String model = modelList[random.nextInt(modelList.length)];
        int year = random.nextInt(30 - 1) + 1986;
        int price = random.nextInt(30000 - 1) + 15000;
        boolean fwd = (random.nextInt(100) % 2 == 0);

        String newVehicle = "{\n" +
                "    \"make\": \"" + make + "\",\n" +
                "    \"model\": \"" + model +
                "    \"modelYear\": " + year + ",\n" +
                "    \"fwd\": " + fwd + ",\n" +
                "    \"price\": " + price + ",\n" +
                "    \"mpg\": 20\n" +
                "}";

        Vehicle vehicle = restTemplate.postForObject(url, newVehicle, Vehicle.class);
        System.out.println(vehicle.vehicleToString());
        System.out.println(newVehicle);
    }

    @Scheduled(fixedRate = 2000)
    public void deleteVehicle() {
        String url = "http://localhost:8080/addVehicle";

        int id = random.nextInt(makerList.length); //TODO change to be the arraylist length I think?
        restTemplate.delete(url, j);
    }

    @Scheduled()
    public void updateVehicle() {

    }

    @Scheduled(cron = "00****")
    public void latestVehicleReport() {

    }
}
