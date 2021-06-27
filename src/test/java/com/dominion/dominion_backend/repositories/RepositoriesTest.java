package com.dominion.dominion_backend.repositories;

import com.dominion.dominion_backend.models.Game;
import com.dominion.dominion_backend.models.Player;
import com.dominion.dominion_backend.models.Season;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RepositoriesTest {

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    GameRepository gameRepository;

    @Autowired
    SeasonRepository seasonRepository;

    @Test
    void createPlayer(){
        Player player1 = new Player("Wielki Shu");
        playerRepository.save(player1);
        assertEquals(true, playerRepository.existsById(player1.getId()));
    }
    @Test
    void createSeason(){
        Season season1 = new Season(1);
        seasonRepository.save(season1);
        assertEquals(true, seasonRepository.existsById(season1.getId()));
    }
    @Test
    void createGame(){
        Season season2 = new Season(2);
        seasonRepository.save(season2);
        Game game1 = new Game(1, season2);
        gameRepository.save(game1);
        assertEquals(true, gameRepository.existsById(game1.getId()));
    }
    @Test
    void addGamesToSeason(){
        Season season3 = new Season(3);
        seasonRepository.save(season3);
        Game game2 = new Game(2, season3);
        gameRepository.save(game2);
        Game game3 = new Game(3, season3);
        gameRepository.save(game3);
        List<Game> games = new ArrayList<>();
        games.add(game2);
        games.add(game3);
        season3.setGames(games);
        seasonRepository.save(season3);
        assertEquals(2, season3.getGames().size());
    }
    @Test
    void addPlayersToGame(){
        Season season4 = new Season(4);
        seasonRepository.save(season4);
        Game game4 = new Game(4, season4);
        gameRepository.save(game4);
        Player player2 = new Player("Bob");
        playerRepository.save(player2);
        Player player3 = new Player("Rob");
        playerRepository.save(player3);
        List<Player> players = new ArrayList<>();
        players.add(player2);
        players.add(player3);
        game4.setPlayers(players);
        gameRepository.save(game4);
        assertEquals(2, game4.getPlayers().size());
    }
    @Test
    void addPlayersToSeason(){
        Season season5 = new Season(5);
        seasonRepository.save(season5);
        Player player4 = new Player("Ian");
        playerRepository.save(player4);
        Player player5 = new Player("Gus");
        playerRepository.save(player5);
        List<Player> players = new ArrayList<>();
        players.add(player4);
        players.add(player5);
        season5.setPlayers(players);
        seasonRepository.save(season5);
        assertEquals(2, season5.getPlayers().size());
    }

}
