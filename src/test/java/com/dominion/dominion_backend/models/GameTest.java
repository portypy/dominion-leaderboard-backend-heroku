package com.dominion.dominion_backend.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTest {
    Player player;
    Game game;
    Season season1;
    Season season2;
    ArrayList<Player> players;

    @BeforeEach
    void setUp() {
        players = new ArrayList<Player>();
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


}
