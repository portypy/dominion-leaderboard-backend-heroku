package com.dominion.dominion_backend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "game_number")
    private int gameNumber;

    @ManyToMany
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinTable(
            joinColumns = {@JoinColumn(name = "game_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "player_id", nullable = false, updatable = false)}
    )
    private List<Player> players;

    @JsonIgnoreProperties(value = "games", allowSetters=true)
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "season_id", nullable = false)
    private Season season;

    @ElementCollection
    @Column (name="results")
    private Map<Integer, Integer> results;   // to store the number of points by each player

    @OneToMany( targetEntity = Player.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "players_positions_mapping",
            joinColumns = {@JoinColumn(name = "player_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "position_id", referencedColumnName = "id")})
    private Map<Player, Integer> positions; // to store the position of each player(in case of a draw it can't be worked out automatically because of the game logic)

    public Game(int gameNumber, Season season) {
        this.gameNumber = gameNumber;
        this.season = season;
        this.players = new ArrayList<>();
        this.results = new HashMap<>();
        this.positions = new HashMap<>();
    }

    public Game() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getGameNumber() {
        return gameNumber;
    }

    public void setGameNumber(int gameNumber) {
        this.gameNumber = gameNumber;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public Map<Integer, Integer> getResults() {
        return results;
    }

    public void setResults(Map<Integer, Integer> results) {
        this.results = results;
    }

    public Map<Player, Integer> getPositions() {
        return positions;
    }

    public void setPositions(Map<Player, Integer> positions) {
        this.positions = positions;
    }
}

