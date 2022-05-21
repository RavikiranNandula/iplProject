package com.problems;
import com.service.DeliveryData;

import java.util.HashMap;
import java.util.ListIterator;
import com.Methods.getDataFromDeliveries;
//Find total runs made by each batsman in all seasons.
public class Problem5 {
    public static void main(String args[]){
        DeliveryData dData=new DeliveryData();
        dData.readDeliveriesData();
        HashMap<String,Integer> playerTotalRuns=new HashMap<>();
        ListIterator<getDataFromDeliveries> itr=dData.deliveryDataList.listIterator();
        while (itr.hasNext()){
            getDataFromDeliveries obj=itr.next();
            String batsMan=obj.getBatsManName();
            int totalRuns=obj.getTotalRuns();
            if(playerTotalRuns.containsKey(batsMan)){
                playerTotalRuns.put(batsMan,playerTotalRuns.get(batsMan)+1);
            }
            else {
                playerTotalRuns.put(batsMan,totalRuns);
            }
        }
        System.out.println(playerTotalRuns);
    }
}
