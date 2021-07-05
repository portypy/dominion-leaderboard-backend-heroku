package com.dominion.dominion_backend.controllers;

import com.dominion.dominion_backend.models.Player;
import com.dominion.dominion_backend.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PlayerController {
    @Autowired
    PlayerRepository playerRepository;

    @GetMapping(value = "/players")
    public ResponseEntity<List<Player>> getAllPlayers() {
        return new ResponseEntity<>( playerRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/players/{id}")
    public ResponseEntity getPlayer(@PathVariable Long id){
        return new ResponseEntity<>(playerRepository.findById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/players")
    public ResponseEntity<Player> postPlayer(@RequestBody Player player){
        playerRepository.save(player);
        return new ResponseEntity<>(player, HttpStatus.CREATED);
    }

    @PutMapping(value = "/players/{id}")
    public ResponseEntity<Player> updatePlayer(@RequestBody Player player, @PathVariable Long id){
        Optional<Player> playerToUpdateOptional = playerRepository.findById(id);
        Player playerToUpdate = playerToUpdateOptional.get();

        playerToUpdate.setName(player.getName());
        playerToUpdate.setSeasonGames(player.getSeasonGames());
        playerToUpdate.setSeasonPoints(player.getSeasonPoints());
        playerToUpdate.setSeasonAvPosition(player.getSeasonAvPosition());
        playerToUpdate.setTotalGames(player.getTotalGames());
        playerToUpdate.setTotalPoints(player.getTotalPoints());
        playerToUpdate.setTotalAvPosition(player.getTotalAvPosition());
        playerToUpdate.setGamePoints(player.getGamePoints());
        playerToUpdate.setGames(player.getGames());
        playerToUpdate.setSeasons(player.getSeasons());

        playerRepository.save(playerToUpdate);
        return new ResponseEntity<>(playerToUpdate, HttpStatus.OK);
    }

    @DeleteMapping(value = "/players/{id}")
    public ResponseEntity<Player> deletePlayer(@PathVariable Long id) {
        Player player = playerRepository.getById(id);
        playerRepository.delete(player);
        return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
    }
}
