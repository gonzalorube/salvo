package com.codeoftheweb.salvo.controller;

import com.codeoftheweb.salvo.model.Game;
import com.codeoftheweb.salvo.model.GamePlayer;
import com.codeoftheweb.salvo.model.Player;
import com.codeoftheweb.salvo.repository.GamePlayerRepository;
import com.codeoftheweb.salvo.repository.GameRepository;
import com.codeoftheweb.salvo.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.*;

@RestController
public class GameController {

    @Autowired
    private GameRepository gameRepo; // Repositorio de games

    @Autowired
    private PlayerRepository playerRepo;

    @Autowired
    private GamePlayerRepository gamePlayerRepo;

    @RequestMapping("api/games") // Queremos que ésta sea la ruta

    // Método para iterar sobre los juegos creados y generar el JSON con los campos y valores adecuados
    private List<Map<String, Object>> getGames(Authentication authentication) {

        List<Game> gamesList = gameRepo.findAll(); // Obtengo todos los juegos existentes y los envío a una lista
        List<Map<String, Object>> result = new ArrayList<>(); // Creo una lista de Maps para retornar el resultado

//        if(authentication != null) {
            // Itero la lista de juegos
            for (Game game : gamesList) {

                if (game != null) {
                    Map<String, Object> mapResult = new LinkedHashMap<>();
                    Map<String, Object> playerLoggedInDTO = new LinkedHashMap<>();
                    Map<String, Object> metaMapResult = new LinkedHashMap<>();
                    if(authentication != null) {
                        for (GamePlayer gamePlayer : game.getGamePlayers()) {

                            if (authentication.getName().equals(gamePlayer.getPlayer().getUserName())) {
                                playerLoggedInDTO.put("id", gamePlayer.getPlayer().getId());
                                playerLoggedInDTO.put("name", gamePlayer.getPlayer().getUserName());
                                metaMapResult.put("player", playerLoggedInDTO);
                            }
                        }
                    }

                    mapResult.put("id", game.getId()); // Por cada campo, busco id y fecha de creación
                    mapResult.put("created", game.getCreationDate()); // y los pongo en sus correspondientes campos
                    List<Map<String, Object>> gamePlayers = new ArrayList<>();

                    // Itero y obtengo los jugadores en cada juego
                    for (GamePlayer gamePlayer : game.getGamePlayers()) {

                        if (gamePlayer != null) {

                            Map<String, Object> gamePlayerDTO = new LinkedHashMap<>();
                            Map<String, Object> playerDTO = new LinkedHashMap<>();

                            gamePlayerDTO.put("id", gamePlayer.getId()); // Rescato id de jugador en juego y lo llevo a su campo
                            playerDTO.put("id", gamePlayer.getPlayer().getId()); // Traigo id de jugador
                            playerDTO.put("email", gamePlayer.getPlayer().getUserName()); // Traigo email de jugador
                            if (gamePlayer.getScore() != null) {
                                playerDTO.put("scores", gamePlayer.getScore().getPoints());
                            }
                            gamePlayerDTO.put("player", playerDTO); // Creo el campo "player", donde vuelco el id y el email y lo hago depender del id de jugador en juego

                            gamePlayers.add(gamePlayerDTO); // Incorporo este tramo al general
                        }
                    }

                    mapResult.put("gamePlayers", gamePlayers); // Creo el campo "gamePlayers", que envuelve lo anterior
                    metaMapResult.put("games", mapResult);
                    result.add(metaMapResult); // Lo agrego a la lista resultante
                }
            }
        return result;
    }

    @RequestMapping(path = "api/games", method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> createGame(Authentication authentication){
        ResponseEntity<Map<String, Object>> response;
        if(authentication == null){
            response = new ResponseEntity<>(makeMap("error", "You must be logged in"), HttpStatus.UNAUTHORIZED);
        }else {
            Player player = playerRepo.findByUserName(authentication.getName());
            Game newGame = gameRepo.save(new Game(LocalDateTime.now()));
            GamePlayer newGamePlayer = gamePlayerRepo.save(new GamePlayer(newGame.getCreationDate(), newGame, player));

            response = new ResponseEntity<>(makeMap("gpId", newGamePlayer.getId()), HttpStatus.CREATED);
        }

        return response;
    }

    @RequestMapping(path = "api/games/{gameId}/players", method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> joinGame(Authentication authentication, @PathVariable long gameId){
        ResponseEntity<Map<String, Object>> response;
        if(authentication == null){
            response = new ResponseEntity<>(makeMap("error", "You must be logged in first"), HttpStatus.UNAUTHORIZED);
        }else {

            Game game = gameRepo.findById(gameId).orElse(null);
            if(game == null){
                response = new ResponseEntity<>(makeMap("error", "No such game"), HttpStatus.NOT_FOUND);
            } else if (game.getGamePlayers().size() > 1){
                response = new ResponseEntity<>(makeMap("error", "No place in this game"), HttpStatus.FORBIDDEN);
            } else {
                Player player = playerRepo.findByUserName(authentication.getName());
                if(game.getGamePlayers().stream().anyMatch(gp -> gp.getPlayer().getId() == player.getId())){
                    response = new ResponseEntity<>(makeMap("error", "User already joined"), HttpStatus.FORBIDDEN);
                } else{
                    GamePlayer newGamePlayer = gamePlayerRepo.save(new GamePlayer(LocalDateTime.now(), game, player));
                    response = new ResponseEntity<>(makeMap("gpId", newGamePlayer.getId()), HttpStatus.CREATED);
                }
            }

        }

        return response;
    }

    private Map<String, Object> makeMap(String key, Object value) {
        Map<String, Object> map = new HashMap<>();
        map.put(key, value);
        return map;
    }

    /*       } else {
            for (Game game : gamesList) {

                if (game != null) {
                    Map<String, Object> metaMapResult = new LinkedHashMap<>();
                    Map<String, Object> mapResult = new LinkedHashMap<>();

                    mapResult.put("id", game.getId()); // Por cada campo, busco id y fecha de creación
                    mapResult.put("created", game.getCreationDate()); // y los pongo en sus correspondientes campos
                    List<Map<String, Object>> gamePlayers = new ArrayList<>();

                    // Itero y obtengo los jugadores en cada juego
                    for (GamePlayer gamePlayer : game.getGamePlayers()) {

                        if (gamePlayer != null) {

                            Map<String, Object> gamePlayerDTO = new LinkedHashMap<>();
                            Map<String, Object> playerDTO = new LinkedHashMap<>();

                            gamePlayerDTO.put("id", gamePlayer.getId()); // Rescato id de jugador en juego y lo llevo a su campo
                            playerDTO.put("id", gamePlayer.getPlayer().getId()); // Traigo id de jugador
                            playerDTO.put("email", gamePlayer.getPlayer().getUserName()); // Traigo email de jugador
                            if (gamePlayer.getScore() != null) {
                                playerDTO.put("scores", gamePlayer.getScore().getPoints());
                            }
                            gamePlayerDTO.put("player", playerDTO); // Creo el campo "player", donde vuelco el id y el email y lo hago depender del id de jugador en juego

                            gamePlayers.add(gamePlayerDTO); // Incorporo este tramo al general
                        }
                    }

                    mapResult.put("gamePlayers", gamePlayers); // Creo el campo "gamePlayers", que envuelve lo anterior
                    result.add(mapResult); // Lo agrego a la lista resultante
                }
            }
        }*/
}
