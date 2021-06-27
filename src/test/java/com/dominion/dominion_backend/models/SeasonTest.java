package com.dominion.dominion_backend.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SeasonTest {

    Season season;
    Game game;
    Player player;
    ArrayList<Game> games;
    ArrayList<Player> players;

    @BeforeEach
    void setUp() {
        season = new Season(2);
        game = new Game(1,season);
        games = new ArrayList<>();
        players = new ArrayList<>();
    }

    @Test
    void setSeasonNumber(){
        season.setSeasonNumber(5);
        assertEquals(5, season.getSeasonNumber());
    }

    @Test
    void setGames() {
        games.add(game);
        season.setGames(games);
        assertEquals(1, season.getGames().size());
    }

    @Test
    void setPlayers() {
        players.add(player);
        season.setPlayers(players);
        assertEquals(1, season.getPlayers().size());
    }

    @Test void setSeasonAsCompleted() {
        season.setCompleted(true);
        assertEquals(true, season.isCompleted());
    }
}
