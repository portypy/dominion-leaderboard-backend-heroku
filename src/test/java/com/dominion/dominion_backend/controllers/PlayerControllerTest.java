package com.dominion.dominion_backend.controllers;

import com.dominion.dominion_backend.models.Player;
import com.dominion.dominion_backend.repositories.PlayerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class PlayerControllerTest implements IAsJsonString {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    PlayerRepository playerRepository;

    @Test
    public void shouldGetAllPlayers() throws Exception {

        Player player = new Player("Prince");
        playerRepository.save(player);

        this.mockMvc.perform(get("/players")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Prince")));
    }

    @Test
    public void shouldGetPlayerById() throws Exception {

        Player player = new Player("Prince");
        playerRepository.save(player);
        Long playerToGetId = player.getId();

        this.mockMvc.perform(get("/players/{id}", playerToGetId)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(playerToGetId));
    }

    @Test
    public void shouldPostPlayer() throws Exception {

        Player player = new Player("King");

        this.mockMvc.perform(post("/players")
                .content(IAsJsonString.asJsonString(player))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("King"));
    }

    @Test
    public void shouldUpdatePlayer() throws Exception {

        Player player = new Player("Duke");
        playerRepository.save(player);
        player.setSeasonPoints(21);
        player.setSeasonGames(7);
        player.setSeasonAvPosition(2.12);
        player.setTotalPoints(42);
        player.setTotalGames(27);
        player.setTotalAvPosition(2.32);
        Long playerToUpdateId = player.getId();

        this.mockMvc.perform(put("/players/{id}", playerToUpdateId)
                .content(IAsJsonString.asJsonString(player))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Duke"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.seasonPoints").value(21))
                .andExpect(MockMvcResultMatchers.jsonPath("$.seasonGames").value(7))
                .andExpect(MockMvcResultMatchers.jsonPath("$.seasonAvPosition").value(2.12))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalPoints").value(42))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalGames").value(27))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalAvPosition").value(2.32));
    }

    @Test
    public void shouldDeletePlayer() throws Exception {

        Player player = new Player("Duke");
        playerRepository.save(player);
        Long playerToBeDeletedId = player.getId();

        this.mockMvc.perform(delete("/players/{id}", playerToBeDeletedId))
                .andExpect(status().isAccepted());
    }


}
