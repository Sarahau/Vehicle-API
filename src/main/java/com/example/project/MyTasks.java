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

    @Scheduled(fixedRate = 4000)
    public void addVehicle() {
        String url = "http://localhost:8080/addVehicle";
        String make = makerList[random.nextInt(makerList.length)];
        String model = modelList[random.nextInt(modelList.length)];
        int year = random.nextInt(30 - 1) + 1986;
        int price = random.nextInt(30000 - 1) + 15000;
        boolean fwd = (random.nextInt(100) % 2 == 0);

        Vehicle newVehicle = new Vehicle(make, model, year, fwd, price, 20);
        restTemplate.postForObject(url, newVehicle, Vehicle.class);
    }

    @Scheduled(fixedRate = 10000)
    public void deleteVehicle() {
        String url = "http://localhost:8080/deleteVehicle/";

        int id = random.nextInt(100);
        restTemplate.delete(url + id);
    }

    @Scheduled(fixedRate = 7000)
    public void updateVehicle() {
        String url = "http://localhost:8080/updateVehicle/";

        String make = makerList[random.nextInt(makerList.length)];
        String model = modelList[random.nextInt(modelList.length)];
        int year = random.nextInt(30 - 1) + 1986;
        int price = random.nextInt(30000 - 1) + 15000;
        boolean fwd = (random.nextInt(100) % 2 == 0);

        Vehicle newVehicle = new Vehicle(make.toUpperCase(), model, year, fwd, price, 20);
        int id = random.nextInt(100);
        restTemplate.put(url + id, newVehicle);
    }

//    @Scheduled(fixedRate = 10000)
    @Scheduled(cron = "0/8 * * ? * *") //doesn't work with cron expression
    public void latestVehicleReport() {
        String url = "http://localhost:8080/getLatestVehicles";
        restTemplate.getForObject(url, Vehicle.class);
    }
}
