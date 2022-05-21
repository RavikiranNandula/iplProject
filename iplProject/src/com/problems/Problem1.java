package com.problems;
import com.service.matchesData;

import java.util.*;

public class Problem1 {
    public static void main(String args[]) {
        matchesData md = new matchesData();
        ArrayList totalData=md.readMatchesData();
        HashMap<String,Integer> matchesPerYear= new HashMap<>();
        for(int i=1;i<totalData.size();i++){
            ArrayList data=new ArrayList((Collection) totalData.get(i));
            //System.out.println(data.get(2).getClass());
            String year= (String) data.get(1);
            if(matchesPerYear.containsKey(year)){
                matchesPerYear.put(year,matchesPerYear.get(year)+1);
            }
            else{
                matchesPerYear.put(year,1);
            }
        }
        System.out.println(matchesPerYear);
    }
}
