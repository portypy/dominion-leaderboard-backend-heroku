package com.dominion.dominion_backend.repositories;

import com.dominion.dominion_backend.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
}
