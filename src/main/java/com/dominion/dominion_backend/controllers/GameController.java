package com.dominion.dominion_backend.controllers;

import com.dominion.dominion_backend.models.Game;
import com.dominion.dominion_backend.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class GameController {

    @Autowired
    GameRepository gameRepository;

    @GetMapping(value = "/games")
    public ResponseEntity<List<Game>> getAllGames() {
        return new ResponseEntity<>(gameRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/games/{id}")
    public ResponseEntity getGame(@PathVariable Long id){
        return new ResponseEntity<>(gameRepository.findById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/games")
    public ResponseEntity<Game> postGame(@RequestBody Game game) {
        gameRepository.save(game);
        return new ResponseEntity<>(game, HttpStatus.CREATED);
    }

    @PutMapping(value = "/games/{id}")
    public ResponseEntity<Game> updateGame(@RequestBody Game game, @PathVariable Long id) {
        Optional<Game> gameToUpdateOptional = gameRepository.findById(id);
        Game gameToUpdate = gameToUpdateOptional.get();

        gameToUpdate.setGameNumber(game.getGameNumber());
        gameToUpdate.setSeason(game.getSeason());
        gameToUpdate.setPlayers(game.getPlayers());

        gameRepository.save(gameToUpdate);
        return new ResponseEntity<>(gameToUpdate, HttpStatus.OK);
    }

    @DeleteMapping(value = "/games/{id}")
    public ResponseEntity<Game> deleteGame(@PathVariable Long id) {
        Game game = gameRepository.getById(id);
        gameRepository.delete(game);
        return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
    }
}
