package com.dominion.dominion_backend.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTest {
    Player player;
    Game game;
    Season season1;
    Season season2;
    ArrayList<Player> players;
    HashMap<Integer, Integer> results;
    HashMap<Integer, Integer> positions;

    @BeforeEach
    void setUp() {
        players = new ArrayList<>();
        results = new HashMap<>();
        positions = new HashMap<>();
        season1 = new Season(1);
        season2 = new Season(2);
        game = new Game(2, season1);
    }

    @Test
    void setGameNumber() {
        game.setGameNumber(5);
        assertEquals(5, game.getGameNumber());
    }

    @Test
    void setSeason() {
        game.setSeason(season2);
        assertEquals(2, game.getSeason().getSeasonNumber());
    }

    @Test
    void setPlayers() {
        players.add(player);
        game.setPlayers(players);
        assertEquals(1, game.getPlayers().size());
    }

    @Test
    void setResults() {
        results.put(3, 1);
        game.setResults(results);
        assertEquals(1, game.getResults().size());
        assertEquals(true, game.getResults().containsKey(3));
        assertEquals(true, game.getResults().containsValue(1));
    }

    @Test
    void setPositions() {
        positions.put(2, 57);
        game.setPositions(positions);
        assertEquals(1, game.getPositions().size());
        assertEquals(true, game.getPositions().containsKey(2));
        assertEquals(true, game.getPositions().containsValue(57));
    }


}
