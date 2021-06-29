package com.dominion.dominion_backend.controllers;

import com.dominion.dominion_backend.models.Game;
import com.dominion.dominion_backend.models.Player;
import com.dominion.dominion_backend.models.Season;
import com.dominion.dominion_backend.repositories.GameRepository;
import com.dominion.dominion_backend.repositories.PlayerRepository;
import com.dominion.dominion_backend.repositories.SeasonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SeasonControllerTest implements IAsJsonString {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    SeasonRepository seasonRepository;

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    GameRepository gameRepository;

    @Test
    public void shouldGetAllSeasons() throws Exception {

        Season season = new Season(95);
        seasonRepository.save(season);

        this.mockMvc.perform(get("/seasons")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("95")));
    }

    @Test
    public void shouldGetSeasonById() throws Exception {
        this.mockMvc.perform(get("/seasons/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }

    @Test
    public void shouldPostSeason() throws Exception {

        Season season = new Season(91);

        this.mockMvc.perform(post("/seasons")
                .content(IAsJsonString.asJsonString(season))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.seasonNumber").value(91));
    }

    @Test
    public void shouldUpdateSeason() throws Exception {
        Season season = new Season(44);
        List<Player> playersUpdatedList = new ArrayList<>();
        List<Game> gamesUpdatedList = new ArrayList<>();
        Player player = new Player("Gonzo");
        playerRepository.save(player);
        playersUpdatedList.add(player);
        seasonRepository.save(season);
        Game game = new Game(1, season);
        gameRepository.save(game);
        gamesUpdatedList.add(game);
        season.setSeasonNumber(45);
        season.setPlayers(playersUpdatedList);
        season.setGames(gamesUpdatedList);
        season.setCompleted(true);
        Long seasonToUpdateId = season.getId();

        this.mockMvc.perform(put("/seasons/{id}", seasonToUpdateId)
                .content(IAsJsonString.asJsonString(season))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.seasonNumber").value(45))
                .andExpect(MockMvcResultMatchers.jsonPath("$.completed").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.players").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.games").isNotEmpty());
    }

    @Test
    public void shouldDeleteSeason() throws Exception {
        Season season = new Season(44);
        seasonRepository.save(season);
        Long seasonToDeleteId = season.getId();
        this.mockMvc.perform(delete("/seasons/{id}", seasonToDeleteId))
                .andDo(print())
                .andExpect(status().isAccepted());
    }

}
