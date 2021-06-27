package com.dominion.dominion_backend.components;

import com.dominion.dominion_backend.models.Game;
import com.dominion.dominion_backend.models.Player;
import com.dominion.dominion_backend.models.Season;
import com.dominion.dominion_backend.repositories.GameRepository;
import com.dominion.dominion_backend.repositories.PlayerRepository;
import com.dominion.dominion_backend.repositories.SeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    GameRepository gameRepository;

    @Autowired
    SeasonRepository seasonRepository;

    public DataLoader(){

    }

    public void run(ApplicationArguments args){

        Player player1 = new Player("Max");
        playerRepository.save(player1);
        Player player2 = new Player("Bo");
        playerRepository.save(player2);

        Season season1 = new Season(1);
        seasonRepository.save(season1);

        List<Season> seasons = new ArrayList<>();
        seasons.add(season1);
        player1.setSeasons(seasons);
        playerRepository.save(player1);
        player2.setSeasons(seasons);
        playerRepository.save(player2);

        Game game1 = new Game(1, season1);
        gameRepository.save(game1);
        Game game2 = new Game(2, season1);
        gameRepository.save(game2);

        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        game1.setPlayers(players);
        gameRepository.save(game1);

        player1.setTotalGames(2);
        player1.setTotalPoints(56);
        player1.setSeasonGames(2);
        player1.setSeasonPoints(56);
        player1.setSeasonAvPosition(2);
        player1.setTotalAvPosition(2);
        playerRepository.save(player1);

    }
}
