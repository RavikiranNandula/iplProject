package com;
import com.iplProject.Delivery;
import com.iplProject.Match;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Main {

    public static final int MATCH_MATCH_ID = 0;
    public static final int DELIVERIES_INNINGS = 1;
    public static final int MATCH_SEASON = 1;
    public static final int MATCH_CITY = 2;
    public static final int MATCH_DATE = 3;
    public static final int MATCH_TEAM_1 = 4;
    public static final int MATCH_TEAM_2 = 5;
    public static final int DELIVERIES_BATSMAN = 6;
    public static final int MATCH_TOSS_WINNER = 6;
    public static final int DELIVERIES_NONSTRIKER = 7;
    public static final int MATCH_TOSS_DECISION = 7;
    public static final int MATCH_RESULT = 8;
    public static final int MATCH_DL_APPLIED = 9;
    public static final int MATCH_WINNER = 10;
    public static final int MATCH_WIN_BY_RUNS = 11;
    public static final int MATCH_WIN_BY_WICKETS = 12;
    public static final int MATCH_PLAYER_OF_MATCH = 13;
    public static final int MATCH_VENUE = 14;
    public static final int MATCH_UMPIRE_1 = 15;
    public static final int MATCH_UMPIRE_2 = 16;
    public static final int DELIVERIES_MATCH_ID = 0;
    public static final int DELIVERIES_BATTING_TEAM = 2;
    public static final int DELIVERIES_BOWLING_TEAM = 3;
    public static final int DELIVERIES_OVER = 4;
    public static final int DELIVERIES_BALL = 5;
    public static final int DELIVERIES_BOWLER = 8;
    public static final int DELIVERIES_IS_SUPER_OVER = 9;
    public static final int DELIVERIES_WIDE_RUNS = 10;
    public static final int DELIVERIES_BYE_RUNS = 11;
    public static final int DELIVERIES_LEG_BYE_RUNS = 12;
    public static final int DELIVERIES_NO_BALL_RUNS = 13;
    public static final int DELIVERIES_PENALTY_RUNS = 14;
    public static final int DELIVERIES_BATS_MAN_RUNS = 15;
    public static final int DELIVERIES_EXTRA_RUNS = 16;
    public static final int DELIVERIES_TOTAL_RUNS = 17;
    public static final int DELIVERIES_PLAYER_DISMISSED = 18;
    public static final int DELIVERIES_DISMISSAL_KIND = 19;
    public static final int DELIVERIES_FIELDER = 20;

    public static void main(String[] args){
      List<Match> matches=getMatchesData();
      List<Delivery> deliveries=getDeliveriesData();

      findNumberOfMatchesPlayedPerYear(matches);
      findNumberOfMatchesOwnPerTeam(matches);
      findNumberOfExtraRunsConcededPerTeamIn2016(matches,deliveries);
      findTopEconomicalBowlersIn2015(matches,deliveries);
      findTotalNumberOfRunsMadeByBatsManInAllSeasons(deliveries);
    }

    private static List<Match> getMatchesData() {
        List <Match> matches=new ArrayList<Match>();

        String csvFile = "/home/ravikiran/Downloads/archive/matches.csv";
        BufferedReader br = null;
        String line = "";
        String splitByComma = ",";
        int skipFirstLine=1;
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                if(skipFirstLine==1){
                    skipFirstLine++;
                    continue;
                }
                String[] matchData = line.split(splitByComma);
                //System.out.println(Arrays.stream(matchData).toList());

                Match match=new Match();
                match.setMatchId(Integer.parseInt(matchData[MATCH_MATCH_ID]));
                match.setSeason((matchData[MATCH_SEASON]));
                match.setCity(matchData[MATCH_CITY]);
                match.setDate((matchData[MATCH_DATE]));
                match.setTeam1(matchData[MATCH_TEAM_1]);
                match.setTeam2(matchData[MATCH_TEAM_2]);
                match.setTossWinner(matchData[MATCH_TOSS_WINNER]);
                match.setTossDecision(matchData[MATCH_TOSS_DECISION]);
                match.setResult(matchData[MATCH_RESULT]);
                match.setDlApplied(Integer.parseInt(matchData[MATCH_DL_APPLIED]));
                match.setWinner(matchData[MATCH_WINNER]);
                match.setWinByRuns(Integer.parseInt(matchData[MATCH_WIN_BY_RUNS]));
                match.setWinByWickets(Integer.parseInt(matchData[MATCH_WIN_BY_WICKETS]));
                match.setPlayerOfMatch(matchData[MATCH_PLAYER_OF_MATCH]);
                match.setVenue(matchData[MATCH_VENUE]);
                if(matchData.length>15){
                    match.setUmpire1(matchData[MATCH_UMPIRE_1]);
                    match.setUmpire2(matchData[MATCH_UMPIRE_2]);
                }


                matches.add(match);
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
        return matches;
    }

    private static List<Delivery> getDeliveriesData() {
        List<Delivery> deliveriesDataList=new ArrayList<Delivery>();

        String csvFile = "/home/ravikiran/Downloads/archive/deliveries.csv";
        BufferedReader bufferReader = null;
        String line = "";
        String SplitComma = ",";
        int skipFirstLine=1;
        try {
            bufferReader = new BufferedReader(new FileReader(csvFile));
            while ((line = bufferReader.readLine()) != null) {
                if(skipFirstLine==1){
                    skipFirstLine++;
                    continue;
                }
                String[] deliveryData = line.split(SplitComma);

                Delivery delivery=new Delivery();
                //System.out.println(deliveryData[0]);
                delivery.setMatchId(Integer.parseInt(deliveryData[DELIVERIES_MATCH_ID]));
                delivery.setInning(Integer.parseInt(deliveryData[DELIVERIES_INNINGS]));
                delivery.setBattingTeam(deliveryData[DELIVERIES_BATTING_TEAM]);
                delivery.setBowlingTeam(deliveryData[DELIVERIES_BOWLING_TEAM]);
                delivery.setOver(Integer.parseInt(deliveryData[DELIVERIES_OVER]));
                delivery.setBall(Integer.parseInt(deliveryData[DELIVERIES_BALL]));
                delivery.setBatsMan(deliveryData[DELIVERIES_BATSMAN]);
                delivery.setNonStriker(deliveryData[DELIVERIES_NONSTRIKER]);
                delivery.setBowler(deliveryData[DELIVERIES_BOWLER]);
                delivery.setIsSuperOver(Integer.parseInt(deliveryData[DELIVERIES_IS_SUPER_OVER]));
                delivery.setWideRuns(Integer.parseInt(deliveryData[DELIVERIES_WIDE_RUNS]));
                delivery.setByeRuns(Integer.parseInt(deliveryData[DELIVERIES_BYE_RUNS]));
                delivery.setLegByeRuns(Integer.parseInt(deliveryData[DELIVERIES_LEG_BYE_RUNS]));
                delivery.setNoBallRuns(Integer.parseInt(deliveryData[DELIVERIES_NO_BALL_RUNS]));
                delivery.setPenaltyRuns(Integer.parseInt(deliveryData[DELIVERIES_PENALTY_RUNS]));
                delivery.setBatsmanRuns(Integer.parseInt(deliveryData[DELIVERIES_BATS_MAN_RUNS]));
                delivery.setExtraRuns(Integer.parseInt(deliveryData[DELIVERIES_EXTRA_RUNS]));
                delivery.setTotalRuns(Integer.parseInt(deliveryData[DELIVERIES_TOTAL_RUNS]));
                if(deliveryData.length>18){
                    delivery.setPlayerDismissed(deliveryData[DELIVERIES_PLAYER_DISMISSED]);
                }
                if(deliveryData.length>19){
                    delivery.setDismissalKind(deliveryData[DELIVERIES_DISMISSAL_KIND]);
                }
                if(deliveryData.length>20){
                    delivery.setFielder(deliveryData[DELIVERIES_FIELDER]);
                }

                deliveriesDataList.add(delivery);
                //System.out.println(deliveryData[DELIVERIES_MATCH_ID]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bufferReader != null) {
                try {
                    bufferReader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            return  deliveriesDataList;
        }
    }

    private static void findNumberOfMatchesPlayedPerYear(List<Match> matches) {
        HashMap<String,Integer> numberOfMatchesPerYear= new HashMap<>();
        for(Match match:matches){
            if(numberOfMatchesPerYear.containsKey(match.getSeason())){
                numberOfMatchesPerYear.put(match.getSeason(),numberOfMatchesPerYear.get(match.getSeason())+1);
            }
            else{
                numberOfMatchesPerYear.put(match.getSeason(),1);
            }
        }
        System.out.println(numberOfMatchesPerYear);
    }

    private static void findNumberOfMatchesOwnPerTeam(List<Match> matches) {
        HashMap<String,Integer> matchesWonPerTeam=new HashMap<>();
        for(Match match:matches){
            String winner= match.getWinner();
            if(matchesWonPerTeam.containsKey(winner)){
                matchesWonPerTeam.put(winner,matchesWonPerTeam.get(winner)+1);
            }
            else {
                if(winner!=""){
                    matchesWonPerTeam.put(winner,1);
                }

            }
        }
        System.out.println(matchesWonPerTeam);
    }

    private static void findNumberOfExtraRunsConcededPerTeamIn2016(List<Match> matches, List<Delivery> deliveries) {
        ArrayList<Integer> matchIdsOfMatchesIn2016=new ArrayList<>();
        for(Match match:matches){
            String season=match.getSeason();
            if(season.equals("2016")){
                matchIdsOfMatchesIn2016.add(match.getMatchId());
            }
        }
        //System.out.println(matchIdsOfMatchesIn2016);

        HashMap<String,Integer> extraRunsConcededPerTeamIn2016=new HashMap<>();
        ListIterator<Delivery> deliveriesIterator = deliveries.listIterator();
        while (deliveriesIterator.hasNext()){
            Delivery delivery=deliveriesIterator.next();
            int matchId= delivery.getMatchId();
            if(matchIdsOfMatchesIn2016.contains(matchId)){
                String bowlingTeam= delivery.getBowlingTeam();
                int extraRuns= delivery.getExtraRuns();
                if(extraRunsConcededPerTeamIn2016.containsKey(bowlingTeam)){
                    extraRunsConcededPerTeamIn2016.put(bowlingTeam,extraRunsConcededPerTeamIn2016.get(bowlingTeam)+extraRuns);
                }
                else{
                    extraRunsConcededPerTeamIn2016.put(bowlingTeam,extraRuns);
                }
            }
        }
        System.out.println(extraRunsConcededPerTeamIn2016);
    }


    private static void findTopEconomicalBowlersIn2015(List<Match> matches, List<Delivery> deliveries) {
        ArrayList<Integer> matchIdsOfMatchesIn2015=new ArrayList<>();
        for(Match match:matches){
            String season=match.getSeason();
            if(season.equals("2015")){
                //System.out.println(match.getMatchId());
                matchIdsOfMatchesIn2015.add(match.getMatchId());
            }
        }
        //System.out.println(matchIdsOfMatchesIn2015);

        HashMap<String,Integer> bowlersDataWithTotalRunsGiven=new LinkedHashMap<>();
        for (Delivery delivery:deliveries){
            int matchId= delivery.getMatchId();
            String bowler= delivery.getBowler();
            int totalRuns= delivery.getTotalRuns();
            if((matchIdsOfMatchesIn2015.contains(matchId))){
                if(bowlersDataWithTotalRunsGiven.containsKey(bowler)){
                    bowlersDataWithTotalRunsGiven.put(bowler,bowlersDataWithTotalRunsGiven.get(bowler)+totalRuns);
                }
                else{
                    bowlersDataWithTotalRunsGiven.put(bowler,totalRuns);
                }
            }
        }
        //System.out.println(bowlersDataWithTotalRunsGiven);

        HashMap<String,Integer> bowlersDataWithTotalOvers=new LinkedHashMap<>();
        for (String key : bowlersDataWithTotalRunsGiven.keySet()){
            bowlersDataWithTotalOvers.put(key,0);
        }

        String initialName= new String();
        for (String key : bowlersDataWithTotalOvers.keySet()){
            initialName=key;
            break;
        }

        for(Delivery delivery:deliveries)
        {
            int matchId= delivery.getMatchId();
            if(matchIdsOfMatchesIn2015.contains(matchId)){
                String bowlerName= delivery.getBowler();
                if(initialName.equals(bowlerName)){
                }else{
                    bowlersDataWithTotalOvers.put(bowlerName,bowlersDataWithTotalOvers.get(bowlerName)+1);
                    initialName=bowlerName;
                }
            }
        }

        HashMap<String,Double> topEconomicalBowlersIn2015=new LinkedHashMap<>();
        for (String key : bowlersDataWithTotalOvers.keySet()){
            topEconomicalBowlersIn2015.put(key,0.0);
        }
        for(String key: bowlersDataWithTotalRunsGiven.keySet()){
            int totalRuns=bowlersDataWithTotalRunsGiven.get(key);
            int totalOvers=bowlersDataWithTotalOvers.get(key);
            double economy= (double) totalRuns/totalOvers;
            topEconomicalBowlersIn2015.put(key,economy);
        }

        List<Map.Entry<String,Double>> convertedList=new LinkedList<Map.Entry<String, Double>>(topEconomicalBowlersIn2015.entrySet());
        Collections.sort(convertedList, new Comparator<Map.Entry<String, Double>>() {
            @Override
            public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        System.out.println(topEconomicalBowlersIn2015);
    }

    private static void findTotalNumberOfRunsMadeByBatsManInAllSeasons(List<Delivery> deliveries) {
        HashMap<String,Integer> totalNumberOfRunsMadeByBatsManInAllSeasons=new HashMap<>();
        for(Delivery delivery:deliveries){
            String batsMan= delivery.getBatsMan();
            int totalRuns= delivery.getTotalRuns();
            if(totalNumberOfRunsMadeByBatsManInAllSeasons.containsKey(batsMan)){
                totalNumberOfRunsMadeByBatsManInAllSeasons.put(batsMan,totalNumberOfRunsMadeByBatsManInAllSeasons.get(batsMan)+1);
            }
            else {
                totalNumberOfRunsMadeByBatsManInAllSeasons.put(batsMan,totalRuns);
            }
        }
        System.out.println(totalNumberOfRunsMadeByBatsManInAllSeasons);
    }

}
