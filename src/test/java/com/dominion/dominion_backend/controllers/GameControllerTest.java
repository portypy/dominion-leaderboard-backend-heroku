package com.dominion.dominion_backend.controllers;

import com.dominion.dominion_backend.models.Game;
import com.dominion.dominion_backend.models.Season;
import com.dominion.dominion_backend.repositories.SeasonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GameControllerTest implements IAsJsonString{

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    SeasonRepository seasonRepository;

    @Test
    public void shouldGetAllGames() throws Exception {

        this.mockMvc.perform(get("/games")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void shouldGetGameById() throws Exception {

        this.mockMvc.perform(get("/games/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }

    @Test
    public void shouldPostGame() throws Exception {

        Season season = new Season(1);
        seasonRepository.save(season);
        Game game = new Game(99, season);

        this.mockMvc.perform(post("/games")
                .content(IAsJsonString.asJsonString(game))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.gameNumber").value(99));
    }

    @Test
    public void shouldUpdateGame() throws Exception {

        Season season = new Season(1);
        seasonRepository.save(season);
        Game game = new Game(1, season);

        this.mockMvc.perform(put("/games/{id}", 1)
                .content(IAsJsonString.asJsonString(game))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.players").isArray());

    }

    @Test
    public void shouldDeletePlayer() throws Exception {
        this.mockMvc.perform(delete("/games/{id}", 1))
                .andExpect(status().isAccepted());

    }
}
