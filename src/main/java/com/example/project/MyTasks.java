package com.example.project;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyTasks {
    @Scheduled()
    public void addVehicle(){

    }

    @Scheduled()
    public void deleteVehicle(){

    }

    @Scheduled(cron = "00****")
    public void latestVehicleReport(){

    }
}
