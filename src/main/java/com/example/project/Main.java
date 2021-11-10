package com.example.project;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Dealer dealer = new Dealer();
        dealer.updateInventory();
        dealer.printDealerInventory();

        Vehicle v1 = new Vehicle("Ford", "F150", 2015, true, 35000, 10);
        Vehicle v2 = new Vehicle("Ford", "Focus", 2010, false, 15000, 10);
        Vehicle v3 = new Vehicle("Ford", "Fiesta", 2012, false, 18000, 10);
        Vehicle v4 = new Vehicle("Ford", "Fusion", 2015, false, 20000, 10);
        Vehicle v5 = new Vehicle("Ford", "Mustang", 1999, false, 8000, 10);
        Vehicle v6 = new Vehicle("Ford", "GT", 2017, false, 30000, 10);
        Vehicle v7 = new Vehicle("Ford", "Taurus", 2015, false, 25000, 10);
        Vehicle v8 = new Vehicle("GM", "Sierra", 2016, true, 40000, 10);
        Vehicle v9 = new Vehicle("Chevy", "Silverado", 2016, true, 35000, 10);

        Inventory inv = new Inventory();

        inv.add(v1);
        inv.add(v2);
        inv.add(v3);
        inv.add(v4);
        inv.add(v5);
        inv.add(v6);
        inv.add(v7);
        inv.add(v8);
        inv.add(v9);

        Dealer dealer1 = new Dealer();
        dealer1.generateReport(inv);
    }
}
