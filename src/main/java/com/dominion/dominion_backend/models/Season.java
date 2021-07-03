package com.dominion.dominion_backend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "seasons")
public class Season {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "season_number")
    private int seasonNumber;
    @Column(name = "completed")
    private boolean completed;

    @JsonIgnoreProperties(value = {"season", "seasons"}, allowSetters=true)
    @OneToMany(mappedBy = "season", fetch = FetchType.LAZY)
    private List<Game> games;

    @JsonIgnoreProperties(value = {"seasons", "games", "season"})
    @ManyToMany
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinTable(
            joinColumns = {@JoinColumn(name = "season_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "player_id", nullable = false)}
    )
    private List<Player> players;

    public Season(int seasonNumber) {
        this.seasonNumber = seasonNumber;
        this.games = new ArrayList<>();
        this.players = new ArrayList<>();
        this.completed = false;
    }

    public Season() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(int seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
