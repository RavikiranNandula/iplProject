package com.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class matchesData {
    public void readMatchesData() {
        String csvFile = "/home/ravikiran/Downloads/archive/matches.csv";
        BufferedReader br = null;
        String line = "";
        String SplitBycomma = ",";
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                String[] matches = line.split(SplitBycomma);
                System.out.println(Arrays.stream(matches).toList());
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
