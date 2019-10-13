package com.codeoftheweb.salvo.controller;

import com.codeoftheweb.salvo.model.Game;
import com.codeoftheweb.salvo.model.GamePlayer;
import com.codeoftheweb.salvo.model.Ship;
import com.codeoftheweb.salvo.repository.GamePlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class GamePlayerController {

    @Autowired
    private GamePlayerRepository gamePlayerRepo;

    @RequestMapping("api/game_view/{gamePlayerId}")
    private List<Map<String, Object>> getGameView(@PathVariable long gamePlayerId){
        return this.gameView(gamePlayerRepo.findById(gamePlayerId).orElse(null));
    }

    private List<Map<String, Object>> gameView(GamePlayer gamePlayer) {

        //List<GamePlayer> gamePlayersList = gamePlayerRepo.findById(gamePlayerView.getId()); // Obtengo todos los juegos existentes y los envío a una lista
        List<Map<String, Object>> result = new ArrayList<>(); // Creo una lista de Maps para retornar el resultado
        Map<String, Object> mapResult = new LinkedHashMap<>();
        mapResult.put("id", gamePlayer.getGame().getId()); // Por cada campo, busco id y fecha de creación
        mapResult.put("created", gamePlayer.getGame().getCreationDate()); // y los pongo en sus correspondientes campos
        List<Map<String, Object>> gamePlayers = new ArrayList<>();
        // Itero la lista de juegos

            if(gamePlayer != null) {
                // Itero y obtengo los jugadores en cada juego
                for (GamePlayer gamePlayerIt : gamePlayer.getGame().getGamePlayers()) {

                    Map<String, Object> gamePlayerDTO = new LinkedHashMap<>();
                    Map<String, Object> playerDTO = new LinkedHashMap<>();
                    System.out.println(gamePlayerIt.getId());
                    gamePlayerDTO.put("id", gamePlayerIt.getId()); // Rescato id de jugador en juego y lo llevo a su campo
                    playerDTO.put("id", gamePlayerIt.getPlayer().getId()); // Traigo id de jugador
                    playerDTO.put("email", gamePlayerIt.getPlayer().getUserName()); // Traigo email de jugador
                    gamePlayerDTO.put("player", playerDTO); // Creo el campo "player", donde vuelco el id y el email y lo hago depender del id de jugador en juego

                    gamePlayers.add(gamePlayerDTO); // Incorporo este tramo al general
                }

                mapResult.put("gamePlayers", gamePlayers); // Creo el campo "gamePlayers", que envuelve lo anterior
                mapResult.put("ships", gamePlayer.getShips().stream().map(Ship::shipDTO));
                result.add(mapResult); // Lo agrego a la lista resultante
            } else {
                mapResult.put("Offside error", "The ID you are looking for doesn't exist");
                result.add(mapResult);
            }
        return result;
    }

   /* private Map<String, Object> gameView(GamePlayer gamePlayer){
        Map<String,Object> gameViewDTO = new LinkedHashMap<>();

        if(gamePlayer != null){
            gameViewDTO.put("id", gamePlayer.getGame().getId());
            gameViewDTO.put("creationDate", gamePlayer.getGame().getCreationDate());
            gameViewDTO.put("gamePlayer", gamePlayer.getGame().getGamePlayers().stream().map(GamePlayer::gamePlayerDTO));
        }else{
            gameViewDTO.put("Total Kaos", "The game you are looking for doesn't exist");
        }

        return gameViewDTO;
    }*/
}
