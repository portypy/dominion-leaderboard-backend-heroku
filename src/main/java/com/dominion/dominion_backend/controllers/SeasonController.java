package com.dominion.dominion_backend.controllers;

import com.dominion.dominion_backend.models.Game;
import com.dominion.dominion_backend.models.Season;
import com.dominion.dominion_backend.repositories.GameRepository;
import com.dominion.dominion_backend.repositories.SeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class SeasonController {

    @Autowired
    SeasonRepository seasonRepository;

    @Autowired
    GameRepository gameRepository;

    @GetMapping(value = "/seasons")
    public ResponseEntity<List<Season>> getAllSeasons(){
        return new ResponseEntity<>(seasonRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/seasons/{id}")
    public ResponseEntity getSeason(@PathVariable Long id){
        return new ResponseEntity<>(seasonRepository.findById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/seasons")
    public ResponseEntity<Season> postSeason(@RequestBody Season season) {
        seasonRepository.save(season);
        return new ResponseEntity<>(season, HttpStatus.CREATED);
    }

    @PutMapping(value = "/seasons/{id}")
    public ResponseEntity<Season> updateSeason(@RequestBody Season season, @PathVariable Long id) {
        Optional<Season> seasonToUpdateOptional = seasonRepository.findById(id);
        Season seasonToUpdate = seasonToUpdateOptional.get();

        seasonToUpdate.setSeasonNumber(season.getSeasonNumber());
        seasonToUpdate.setCompleted(season.isCompleted());
        seasonToUpdate.setGames(seasonToUpdate.getGames());
        seasonToUpdate.setPlayers(season.getPlayers());

        seasonRepository.save(seasonToUpdate);
        return new ResponseEntity<Season>(seasonToUpdate, HttpStatus.OK);
    }

    @DeleteMapping(value = "/seasons/{id}")
    public ResponseEntity<Season> deleteSeason(@PathVariable Long id) {
        Season season = seasonRepository.getById(id);
        seasonRepository.delete(season);
        return new ResponseEntity<> (null, HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "/seasons/{id}/new_game")
    public ResponseEntity<Game> createNewGame(@PathVariable Long id) {
        Season season = seasonRepository.findById(id).get();
        int newGameNumber = season.getGames().size() + 1;
        Game game = new Game(newGameNumber, season);
        gameRepository.save(game);

        return new ResponseEntity<>(game, HttpStatus.OK);
    }

}
