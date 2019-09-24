package com.codeoftheweb.salvo.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class GamePlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private LocalDateTime joinDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="game_id")
    private Game game;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="player_id")
    private Player player;
    private String userName;

    public GamePlayer(){
    }

    public GamePlayer(LocalDateTime joinDate, Game game, Player player){
        this.joinDate = joinDate;
        this.game = game;
        this.player = player;
    }

    /* public GamePlayer(LocalDateTime joinDate, Game game, String userName){
        this.joinDate = joinDate;
        this.game = game;
        this.userName = userName;
    }*/

    /* public GamePlayer(long id, LocalDateTime joinDate, Game game, Player player){
        this.id = id;
        this.joinDate = joinDate;
        this.game = game;
        this.player = player;
    }*/

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public LocalDateTime getJoinDate(){
        return joinDate;
    }

    public void setJoinDate(LocalDateTime joinDate){
        this.joinDate = joinDate;
    }

    public Game getGame(){
        return game;
    }

    public void setGame(Game game){
        this.game = game;
    }

    public Player getPlayer(){
        return player;
    }

    public void setPlayer(Player player){
        this.player = player;
    }

    public String getUserName(){
        return userName;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }
}


