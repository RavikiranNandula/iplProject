package com.problems;
import com.service.DeliveryData;
import com.service.matchesData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Problem3 {
    public static void main(String args[]){
        DeliveryData dData=new DeliveryData();
        matchesData mData=new matchesData();
        ArrayList matchesData= mData.readMatchesData();
        ArrayList totalData = dData.readDeliveriesData();
        HashMap<String,Integer> matches2016=new HashMap<>();
        for(int i=1;i<totalData.size();i++){
            ArrayList data=new ArrayList((Collection) totalData.get(i));
            //ArrayList data1=new ArrayList((Collection) matchesData.get(i));
            System.out.println(data.get(2));
            //String battingTeam =((String) data.get(2));
            Integer extraRuns= Integer.parseInt((String) data.get(17));
            //Integer year=new Integer((Integer)data1.get(1));

        }
        System.out.println(matches2016);
    }
}
