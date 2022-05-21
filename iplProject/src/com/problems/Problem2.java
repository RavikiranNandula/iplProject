package com.problems;
import com.service.matchesData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Problem2 {
    public static void main(String args[]){
        matchesData md2=new matchesData();
        ArrayList totaldata = md2.readMatchesData();
        HashMap<String,Integer> winnerMap=new HashMap<>();
        for(int i=1;i<totaldata.size();i++){
            ArrayList data=new ArrayList((Collection) totaldata.get(i));
            String winner= (String) data.get(10);
            //System.out.println(winner);
            if(winnerMap.containsKey(winner)){
                winnerMap.put(winner,winnerMap.get(winner)+1);
            }
            else {
                if(winner!=""){
                    winnerMap.put(winner,1);
                    //System.out.println(winner);
                }

            }
        }
        System.out.println(winnerMap);
    }
}
