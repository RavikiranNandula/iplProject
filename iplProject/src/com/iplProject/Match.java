package com.iplProject;

public class Match {
    int matchId;
    String season;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    String city;
    String date;
    String team1;
    String team2;
    String tossWinner;
    String tossDecision;
    String result;
    int dlApplied;

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public String getTossWinner() {
        return tossWinner;
    }

    public void setTossWinner(String tossWinner) {
        this.tossWinner = tossWinner;
    }

    public String getTossDecision() {
        return tossDecision;
    }

    public void setTossDecision(String tossDecision) {
        this.tossDecision = tossDecision;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getDlApplied() {
        return dlApplied;
    }

    public void setDlApplied(int dlApplied) {
        this.dlApplied = dlApplied;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public int getWinByRuns() {
        return winByRuns;
    }

    public void setWinByRuns(int winByRuns) {
        this.winByRuns = winByRuns;
    }

    public int getWinByWickets() {
        return winByWickets;
    }

    public void setWinByWickets(int winByWickets) {
        this.winByWickets = winByWickets;
    }

    public String getPlayerOfMatch() {
        return playerOfMatch;
    }

    public void setPlayerOfMatch(String playerOfMatch) {
        this.playerOfMatch = playerOfMatch;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getUmpire1() {
        return umpire1;
    }

    public void setUmpire1(String umpire1) {
        this.umpire1 = umpire1;
    }

    public String getUmpire2() {
        return umpire2;
    }

    public void setUmpire2(String umpire2) {
        this.umpire2 = umpire2;
    }

    String winner;
    int winByRuns;
    int winByWickets;
    String playerOfMatch;
    String venue;
    String umpire1;
    String umpire2;
}