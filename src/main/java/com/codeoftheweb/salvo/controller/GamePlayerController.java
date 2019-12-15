package com.codeoftheweb.salvo.controller;

import com.codeoftheweb.salvo.model.*;
import com.codeoftheweb.salvo.repository.GamePlayerRepository;
import com.codeoftheweb.salvo.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class GamePlayerController {

    @Autowired
    private GamePlayerRepository gamePlayerRepo;

    @Autowired
    private PlayerRepository playerRepository;


/*    private Map<String, Object> getGameView(@PathVariable long gamePlayerId){
        return this.gameView(gamePlayerRepo.findById(gamePlayerId).orElse(null));
    }*/

 //   @RequestMapping(path = "api/game_view/{gamePlayerId}", method = RequestMethod.POST)
    @RequestMapping("api/game_view/{gamePlayerId}")
    public ResponseEntity<Map<String, Object>> getGameView(@PathVariable long gamePlayerId, Authentication authentication){
        System.out.println(authentication.getName());
        ResponseEntity<Map<String, Object>> resp;
        GamePlayer gamePlayerParam = gamePlayerRepo.findById(gamePlayerId);
        System.out.println(gamePlayerId);
        System.out.println(gamePlayerParam.getPlayer().getId());
        Player player = playerRepository.findByUserName(authentication.getName());
        if(gamePlayerParam != null && player != null) {
            if (player.getId() != gamePlayerParam.getPlayer().getId()) {

                resp = new ResponseEntity<>(makeMap("error", "user unauthorized"), HttpStatus.UNAUTHORIZED);

            } else {
                resp = new ResponseEntity<>(this.gameView(gamePlayerParam), HttpStatus.OK);
            }
        } else {
            resp = new ResponseEntity<>(makeMap("error", "no user with that id"), HttpStatus.NOT_FOUND);
        }
        return resp;
    }


    private Map<String,Object> gameView(GamePlayer gamePlayer){
        Map<String,Object> dto = new LinkedHashMap<>();

        if(gamePlayer != null){
            dto.put("id", gamePlayer.getGame().getId());
            dto.put("creationDate", gamePlayer.getGame().getCreationDate());
            dto.put("gamePlayer", gamePlayer.getGame().getGamePlayers().stream().map(GamePlayer::gamePlayerDTO));
            dto.put("ships", gamePlayer.getShips().stream().map(Ship::shipDTO));
            dto.put("salvoes", gamePlayer.getGame().getGamePlayers()
                    .stream().flatMap(gp -> gp.getSalvo()
                            .stream().map(salvo -> salvo.salvoDTO()))
            );
        }else{
            dto.put("error", "no such game");
        }

        return dto;

    }

    /*private List<Map<String, Object>> gameView(GamePlayer gamePlayer) {

        //List<GamePlayer> gamePlayersList = gamePlayerRepo.findById(gamePlayerView.getId()); // Obtengo todos los juegos existentes y los envío a una lista
        List<Map<String, Object>> result = new ArrayList<>(); // Creo una lista de Maps para retornar el resultado
        Map<String, Object> mapResult = new LinkedHashMap<>();
        mapResult.put("id", gamePlayer.getGame().getId()); // Por cada campo, busco id y fecha de creación
        mapResult.put("created", gamePlayer.getGame().getCreationDate()); // y los pongo en sus correspondientes campos
        List<Map<String, Object>> gamePlayers = new ArrayList<>();
        // Itero la lista de juegos


                // Itero y obtengo los jugadores en cada juego
                for (GamePlayer gamePlayerIt : gamePlayer.getGame().getGamePlayers()) {

                    if(gamePlayerIt != null) {
                        Map<String, Object> gamePlayerDTO = new LinkedHashMap<>();
                        Map<String, Object> playerDTO = new LinkedHashMap<>();
                        // System.out.println(gamePlayerIt.getId());
                        gamePlayerDTO.put("id", gamePlayerIt.getId()); // Rescato id de jugador en juego y lo llevo a su campo
                        playerDTO.put("id", gamePlayerIt.getPlayer().getId()); // Traigo id de jugador
                        playerDTO.put("email", gamePlayerIt.getPlayer().getUserName()); // Traigo email de jugador
                        gamePlayerDTO.put("player", playerDTO); // Creo el campo "player", donde vuelco el id y el email y lo hago depender del id de jugador en juego

                        gamePlayers.add(gamePlayerDTO); // Incorporo este tramo al general

                    } else {
                        mapResult.put("Offside error", "The ID you are looking for doesn't exist");
                        result.add(mapResult);
                    }
                }

                mapResult.put("gamePlayers", gamePlayers); // Creo el campo "gamePlayers", que envuelve lo anterior
                mapResult.put("ships", gamePlayer.getShips().stream().map(Ship::shipDTO)); // Incorporo los barcos, que surgen del DTO de Ship.java
                mapResult.put("salvoes", gamePlayer.getGame().getGamePlayers().stream().flatMap(gp -> gp.getSalvo().stream().map(Salvo::salvoDTO)));
                result.add(mapResult); // Los agrego a la lista resultante

        return result;
    }*/

    private Map<String, Object> makeMap(String key, Object value) {
        Map<String, Object> map = new HashMap<>();
        map.put(key, value);
        return map;
    }
}