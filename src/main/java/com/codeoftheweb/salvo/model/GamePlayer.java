package com.codeoftheweb.salvo.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
public class GamePlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private Date joinDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="game_id")
    private Game game;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="player_id")
    private Player player;

    public GamePlayer(){
    }

    public GamePlayer(long id, Date joinDate, Game game, Player player){
        this.id = id;
        this.joinDate = joinDate;
        this.game = game;
        this.player = player;
    }

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public Date getJoinDate(){
        return joinDate;
    }

    public void setJoinDate(Date joinDate){
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
}
