package com.dominion.dominion_backend.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTest {

    Player player;
    Game game;
    Season season;
    ArrayList<Season> seasons;
    ArrayList<Game> games;

    @BeforeEach
    void setUp() {
        player = new Player("Wielki Shu");
        season = new Season(2);
        game = new Game(2, season);
        seasons = new ArrayList<Season>();
        games = new ArrayList<Game>();
    }

    @Test
    void getName() { assertEquals("Wielki Shu", player.getName()); }

    @Test
    void setName() {
        player.setName("Big Shu");
        assertEquals( "Big Shu", player.getName());}

    @Test
    void setSeasonPoints() {
        player.setSeasonPoints(13);
        assertEquals(13, player.getSeasonPoints());
    }

    @Test
    void setSeasonGames() {
        player.setSeasonGames(7);
        assertEquals(7, player.getSeasonGames());
    }

    @Test
    void setSeasonAvPosition() {
        player.setSeasonAvPosition(2.217);
        assertEquals(2.217, player.getSeasonAvPosition());
    }

    @Test
    void setTotalPoints() {
        player.setTotalPoints(99);
        assertEquals(99, player.getTotalPoints());
    }

    @Test
    void setTotalGames() {
        player.setTotalGames(9);
        assertEquals(9, player.getTotalGames());
    }

    @Test
    void setTotalAvPosition() {
        player.setTotalAvPosition(1.11111);
        assertEquals(1.11111, player.getTotalAvPosition());
    }

    @Test
    void setGamePoints() {
        player.setGamePoints(44);
        assertEquals(44, player.getGamePoints());
    }

    @Test
    void setSeasons() {
        seasons.add(season);
        player.setSeasons(seasons);
        assertEquals(1, player.getSeasons().size());
    }

    @Test
    void  setGames() {
        games.add(game);
        player.setGames(games);
        assertEquals(1, player.getGames().size());
    }
}
