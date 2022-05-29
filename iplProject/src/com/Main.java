package com;

import com.iplProject.Delivery;
import com.iplProject.Match;
import com.iplProject.dbConnection;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        List<Match> matches = getMatchesData();
        List<Delivery> deliveries = getDeliveriesData();

        findNumberOfMatchesPlayedPerYear(matches);
        findNumberOfMatchesOwnPerTeam(matches);
        findNumberOfExtraRunsConcededPerTeamIn2016(matches, deliveries);
        findTopEconomicalBowlersIn2015(matches, deliveries);
        findTotalNumberOfRunsMadeByBatsManInAllSeasons(deliveries);
    }

    private static List<Match> getMatchesData() {
        List<Match> matches = new ArrayList<Match>();

        String query="select * from matches";
        Connection connection = null;

        try {
            dbConnection connection1=new dbConnection();
            connection=connection1.initializeConnection();
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(query);
            while (resultSet.next()) {

                Match match = new Match();
                match.setMatchId(resultSet.getInt("id"));
                match.setSeason(resultSet.getInt("season"));
                match.setCity(resultSet.getString("city"));
                match.setDate(resultSet.getString("date"));
                match.setTeam1(resultSet.getString("team1"));
                match.setTeam2(resultSet.getString("team2"));
                match.setTossWinner(resultSet.getString("toss_winner"));
                match.setTossDecision(resultSet.getString("toss_decision"));
                match.setResult(resultSet.getString("result"));
                match.setDlApplied(resultSet.getInt("dl_applied"));
                match.setWinner(resultSet.getString("winner"));
                match.setWinByRuns(resultSet.getInt("win_by_runs"));
                match.setWinByWickets(resultSet.getInt("win_by_wickets"));
                match.setPlayerOfMatch(resultSet.getString("player_of_match"));
                match.setVenue(resultSet.getString("venue"));
                match.setUmpire1(resultSet.getString("umpire1"));
                match.setUmpire2(resultSet.getString("umpire2"));

                matches.add(match);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return matches;
    }

    private static List<Delivery> getDeliveriesData() {
        List<Delivery> deliveries = new ArrayList<Delivery>();

        String query="select * from deliveries";
        Connection connection=null;
        try {
            dbConnection connection1=new dbConnection();
            connection=connection1.initializeConnection();
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(query);
            while (resultSet.next()) {
                Delivery delivery = new Delivery();

                delivery.setMatchId(resultSet.getInt("match_id"));
                delivery.setInning(resultSet.getInt("inning"));
                delivery.setBattingTeam(resultSet.getString("batting_team"));
                delivery.setBowlingTeam(resultSet.getString("bowling_team"));
                delivery.setOver(resultSet.getInt("over_no"));
                delivery.setBall(resultSet.getInt("ball"));
                delivery.setBatsMan(resultSet.getString("batsman"));
                delivery.setNonStriker(resultSet.getString("non_striker"));
                delivery.setBowler(resultSet.getString("bowler"));
                delivery.setIsSuperOver(resultSet.getInt("is_super_over"));
                delivery.setWideRuns(resultSet.getInt("wide_runs"));
                delivery.setByeRuns(resultSet.getInt("bye_runs"));
                delivery.setLegByeRuns(resultSet.getInt("legbye_runs"));
                delivery.setNoBallRuns(resultSet.getInt("noball_runs"));
                delivery.setPenaltyRuns(resultSet.getInt("penalty_runs"));
                delivery.setBatsmanRuns(resultSet.getInt("batsman_runs"));
                delivery.setExtraRuns(resultSet.getInt("extra_runs"));
                delivery.setTotalRuns(resultSet.getInt("total_runs"));
                delivery.setPlayerDismissed(resultSet.getString("player_dismissed"));
                delivery.setDismissalKind(resultSet.getString("dismissal_kind"));
                delivery.setFielder(resultSet.getString("fielder"));

                deliveries.add(delivery);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return deliveries;
    }

    private static void findNumberOfMatchesPlayedPerYear(List<Match> matches) {
        HashMap<Integer, Integer> numberOfMatchesPerYear = new HashMap<>();
        for (Match match : matches) {
            if (numberOfMatchesPerYear.containsKey(match.getSeason())) {
                numberOfMatchesPerYear.put(match.getSeason(), numberOfMatchesPerYear.get(match.getSeason()) + 1);
            } else {
                numberOfMatchesPerYear.put(match.getSeason(), 1);
            }
        }
        System.out.println(numberOfMatchesPerYear);
    }

    private static void findNumberOfMatchesOwnPerTeam(List<Match> matches) {
        int[] a=new int[10];
        HashMap<String, Integer> matchesWonPerTeam = new HashMap<>();
        for (Match match : matches) {
            String winner = match.getWinner();
            if (matchesWonPerTeam.containsKey(winner)) {
                matchesWonPerTeam.put(winner, matchesWonPerTeam.get(winner) + 1);
            } else {
                if (winner != null) {
                    matchesWonPerTeam.put(winner, 1);
                }

            }
        }
        System.out.println(matchesWonPerTeam);
    }

    private static void findNumberOfExtraRunsConcededPerTeamIn2016(List<Match> matches, List<Delivery> deliveries) {
        ArrayList<Integer> matchIdsOfMatchesIn2016 = new ArrayList<>();
        for (Match match : matches) {
            int season = match.getSeason();
            if ((2016) == season) {
                matchIdsOfMatchesIn2016.add(match.getMatchId());
            }
        }
        //System.out.println(matchIdsOfMatchesIn2016);

        HashMap<String, Integer> extraRunsConcededPerTeamIn2016 = new HashMap<>();
        ListIterator<Delivery> deliveriesIterator = deliveries.listIterator();
        while (deliveriesIterator.hasNext()) {
            Delivery delivery = deliveriesIterator.next();
            int matchId = delivery.getMatchId();
            if (matchIdsOfMatchesIn2016.contains(matchId)) {
                String bowlingTeam = delivery.getBowlingTeam();
                int extraRuns = delivery.getExtraRuns();
                if (extraRunsConcededPerTeamIn2016.containsKey(bowlingTeam)) {
                    extraRunsConcededPerTeamIn2016.put(bowlingTeam, extraRunsConcededPerTeamIn2016.get(bowlingTeam) + extraRuns);
                } else {
                    extraRunsConcededPerTeamIn2016.put(bowlingTeam, extraRuns);
                }
            }
        }
        System.out.println(extraRunsConcededPerTeamIn2016);
    }


    private static void findTopEconomicalBowlersIn2015(List<Match> matches, List<Delivery> deliveries) {
        ArrayList<Integer> matchIdsOfMatchesIn2015 = new ArrayList<>();
        for (Match match : matches) {
            int season = match.getSeason();
            if (season == (2015)) {
                //System.out.println(match.getMatchId());
                matchIdsOfMatchesIn2015.add(match.getMatchId());
            }
        }
        //System.out.println(matchIdsOfMatchesIn2015);

        HashMap<String, Integer> bowlersDataWithTotalRunsGiven = new LinkedHashMap<>();
        for (Delivery delivery : deliveries) {
            int matchId = delivery.getMatchId();
            String bowler = delivery.getBowler();
            int totalRuns = delivery.getTotalRuns();
            if ((matchIdsOfMatchesIn2015.contains(matchId))) {
                if (bowlersDataWithTotalRunsGiven.containsKey(bowler)) {
                    bowlersDataWithTotalRunsGiven.put(bowler, bowlersDataWithTotalRunsGiven.get(bowler) + totalRuns);
                } else {
                    bowlersDataWithTotalRunsGiven.put(bowler, totalRuns);
                }
            }
        }
        //System.out.println(bowlersDataWithTotalRunsGiven);

        HashMap<String, Integer> bowlersDataWithTotalOvers = new LinkedHashMap<>();
        for (String key : bowlersDataWithTotalRunsGiven.keySet()) {
            bowlersDataWithTotalOvers.put(key, 0);
        }

        String initialName = new String();
        for (String key : bowlersDataWithTotalOvers.keySet()) {
            initialName = key;
            break;
        }

        for (Delivery delivery : deliveries) {
            int matchId = delivery.getMatchId();
            if (matchIdsOfMatchesIn2015.contains(matchId)) {
                String bowlerName = delivery.getBowler();
                if (initialName.equals(bowlerName)) {
                } else {
                    bowlersDataWithTotalOvers.put(bowlerName, bowlersDataWithTotalOvers.get(bowlerName) + 1);
                    initialName = bowlerName;
                }
            }
        }

        HashMap<String, Double> topEconomicalBowlersIn2015 = new LinkedHashMap<>();
        for (String key : bowlersDataWithTotalOvers.keySet()) {
            topEconomicalBowlersIn2015.put(key, 0.0);
        }
        for (String key : bowlersDataWithTotalRunsGiven.keySet()) {
            int totalRuns = bowlersDataWithTotalRunsGiven.get(key);
            int totalOvers = bowlersDataWithTotalOvers.get(key);
            double economy = (double) totalRuns / totalOvers;
            topEconomicalBowlersIn2015.put(key, economy);
        }

        List<Map.Entry<String, Double>> topEconomicalBowlersIn2015AfterSorting = new LinkedList<Map.Entry<String, Double>>(topEconomicalBowlersIn2015.entrySet());
        Collections.sort(topEconomicalBowlersIn2015AfterSorting, new Comparator<Map.Entry<String, Double>>() {
            @Override
            public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        System.out.println(topEconomicalBowlersIn2015AfterSorting);
    }

    private static void findTotalNumberOfRunsMadeByBatsManInAllSeasons(List<Delivery> deliveries) {
        HashMap<String, Integer> totalNumberOfRunsMadeByBatsManInAllSeasons = new HashMap<>();
        for (Delivery delivery : deliveries) {
            String batsMan = delivery.getBatsMan();
            int totalRuns = delivery.getTotalRuns();
            if (totalNumberOfRunsMadeByBatsManInAllSeasons.containsKey(batsMan)) {
                totalNumberOfRunsMadeByBatsManInAllSeasons.put(batsMan, totalNumberOfRunsMadeByBatsManInAllSeasons.get(batsMan) + 1);
            } else {
                totalNumberOfRunsMadeByBatsManInAllSeasons.put(batsMan, totalRuns);
            }
        }
        System.out.println(totalNumberOfRunsMadeByBatsManInAllSeasons);
    }

}
