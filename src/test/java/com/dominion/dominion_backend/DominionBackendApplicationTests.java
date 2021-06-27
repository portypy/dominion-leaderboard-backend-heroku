package com.dominion.dominion_backend;

import com.dominion.dominion_backend.models.Game;
import com.dominion.dominion_backend.models.Player;
import com.dominion.dominion_backend.models.Season;
import com.dominion.dominion_backend.repositories.GameRepository;
import com.dominion.dominion_backend.repositories.PlayerRepository;
import com.dominion.dominion_backend.repositories.SeasonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class DominionBackendApplicationTests {

	@Test
	void contextLoads() {
	}

}
