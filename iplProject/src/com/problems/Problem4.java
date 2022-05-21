package com.problems;
import com.service.DeliveryData;
import com.Methods.getDataFromDeliveries;
import com.Methods.getMatchIdsIn2015;

import java.util.*;

public class Problem4 {
    public static void main(String args[]){
        DeliveryData dData=new DeliveryData();
        dData.readDeliveriesData();
       getMatchIdsIn2015 data1=new getMatchIdsIn2015();
       ArrayList matchData2015=data1.getIds();
        //System.out.println(matchData2015);
        HashMap<String,Integer> bowlersData=new LinkedHashMap<>();
        ListIterator<getDataFromDeliveries> itr=dData.deliveryDataList.listIterator();
        while(itr.hasNext()){
            getDataFromDeliveries obj= itr.next();
            int matchId=obj.getMatch_id();
            String bowler=obj.getBowler();
            int totalRuns=obj.getTotalRuns();
            if(matchData2015.contains(matchId)){
                if(bowlersData.containsKey(bowler)){
                    bowlersData.put(bowler,bowlersData.get(bowler)+totalRuns);
                }
                else{
                    bowlersData.put(bowler,totalRuns);;
                }
            }
        }
        HashMap<String,Integer> bowlersDataWithOvers=new LinkedHashMap<>();
        for (String key : bowlersData.keySet()){
            bowlersDataWithOvers.put(key,0);
        }

        String initialName=new String();
        for (String key : bowlersDataWithOvers.keySet()){
            initialName=key;
            break;
        }
        //System.out.println(initialName);
        int count=0;
        ListIterator<getDataFromDeliveries> itr2=dData.deliveryDataList.listIterator();
        while (itr2.hasNext()){
            getDataFromDeliveries obj2=itr2.next();

            int matchId2= obj2.getMatch_id();
            if(matchData2015.contains(matchId2)){
                String bowlerName= (String) obj2.getBowler();
                if(initialName.equals(bowlerName)){
                    //System.out.println("fff");
                }else{
                 bowlersDataWithOvers.put(bowlerName,bowlersDataWithOvers.get(bowlerName)+1);
                 initialName=bowlerName;
                }
            }
        }
        HashMap<String,Double> finalData=new LinkedHashMap<>();
        for (String key : bowlersDataWithOvers.keySet()){
            finalData.put(key,0.0);
        }
        for(String key: bowlersData.keySet()){
            int totalRuns=bowlersData.get(key);
            int totalOvers=bowlersDataWithOvers.get(key);
            double economy= (double) totalRuns/totalOvers;
            finalData.put(key,economy);
        }
        
        System.out.println(finalData);
        //System.out.println(bowlersDataWithOvers);
        //System.out.println(bowlersData);
    }

}
