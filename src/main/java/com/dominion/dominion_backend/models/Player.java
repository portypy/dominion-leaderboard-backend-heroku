package com.dominion.dominion_backend.models;

import javax.persistence.*;

@Entity
@Table(name = "players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column(name = "season_points")
    private int seasonPoints;
    @Column(name = "season_games")
    private int seasonGames;
    @Column(name = "season_av_position")
    private double seasonAvPosition;
    @Column(name = "total_points")
    private int totalPoints;
    @Column(name = "total_games")
    private int totalGames;
    @Column(name = "total_av_position")
    private double totalAvPosition;
    @Column(name = "game_points")
    private int gamePoints;
    @Column(name = "game_position")
    private int gamePosition;


    public Player(String name){
        this.name = name;
        this.seasonPoints = seasonPoints;
        this.seasonGames = seasonGames;
        this.seasonAvPosition = seasonAvPosition;
        this.totalPoints = totalPoints;
        this.totalGames = totalGames;
        this.totalAvPosition = totalAvPosition;
        this.gamePoints = 0;
        this.gamePosition = 0;
    }

    public Player(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeasonPoints() {
        return seasonPoints;
    }

    public void setSeasonPoints(int seasonPoints) {
        this.seasonPoints = seasonPoints;
    }

    public int getSeasonGames() {
        return seasonGames;
    }

    public void setSeasonGames(int seasonGames) {
        this.seasonGames = seasonGames;
    }

    public double getSeasonAvPosition() {
        return seasonAvPosition;
    }

    public void setSeasonAvPosition(double seasonAvPosition) {
        this.seasonAvPosition = seasonAvPosition;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public int getTotalGames() {
        return totalGames;
    }

    public void setTotalGames(int totalGames) {
        this.totalGames = totalGames;
    }

    public double getTotalAvPosition() {
        return totalAvPosition;
    }

    public void setTotalAvPosition(double totalAvPosition) {
        this.totalAvPosition = totalAvPosition;
    }

    public int getGamePoints() {
        return gamePoints;
    }

    public void setGamePoints(int gamePoints) {
        this.gamePoints = gamePoints;
    }

    public int getGamePosition() {
        return gamePosition;
    }

    public void setGamePosition(int gamePosition) {
        this.gamePosition = gamePosition;
    }
}
