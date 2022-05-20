package com.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class DeliveryData {
    public void readDeliveriesData() {
        String csvFile = "/home/ravikiran/Downloads/archive/deliveries.csv";
        BufferedReader br = null;
        String line = "";
        String SplitBycomma = ",";
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                String[] delivery = line.split(SplitBycomma);
                System.out.println(Arrays.stream(delivery).toList());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
