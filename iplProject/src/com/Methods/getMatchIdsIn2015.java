package com.Methods;
import com.service.matchesData;

import java.util.ArrayList;
import java.util.ListIterator;

public class getMatchIdsIn2015 {
    public static ArrayList <Integer> getIds(){
        matchesData mdata=new matchesData();
        mdata.readMatchesData();
        ArrayList matchIdsin2015=new ArrayList();
        ListIterator<getDataFromMatches> itr=mdata.matchData.listIterator();
        while (itr.hasNext()){
            getDataFromMatches obj= itr.next();
            int year=obj.getSeason();
            int matchId= obj.getMatchId();
            if(year==2015){
                matchIdsin2015.add(matchId);
            }
        }
        //System.out.println(matchIdsin2015);
        return matchIdsin2015;
    }
}
