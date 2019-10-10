package com.codeoftheweb.salvo.controller;

import com.codeoftheweb.salvo.model.Game;
import com.codeoftheweb.salvo.model.GamePlayer;
import com.codeoftheweb.salvo.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

@RestController
public class GameController {

    @Autowired
    private GameRepository gameRepo; // Repositorio de games

    @RequestMapping("/api/games") // Queremos que ésta sea la ruta

    // Método para iterar sobre los juegos creados y generar el JSON con los campos y valores adecuados
    private List<Map<String, Object>> getGames() {

        List<Game> gamesList = gameRepo.findAll(); // Obtengo todos los juegos existentes y los envío a una lista
        List<Map<String, Object>> result = new ArrayList<>(); // Creo una lista de Maps para retornar el resultado

        // Itero la lista de juegos
        for (Game game: gamesList){

            if(game != null) {

                Map<String, Object> mapResult = new LinkedHashMap<>();
                mapResult.put("id", game.getId()); // Por cada campo, busco id y fecha de creación
                mapResult.put("created", game.getCreationDate()); // y los pongo en sus correspondientes campos
                List<Map<String, Object>> gamePlayers = new ArrayList<>();

                // Itero y obtengo los jugadores en cada juego
                for (GamePlayer gamePlayer : game.getGamePlayers()) {

                    if(gamePlayer != null){

                    Map<String, Object> gamePlayerDTO = new LinkedHashMap<>();
                    Map<String, Object> playerDTO = new LinkedHashMap<>();

                    gamePlayerDTO.put("id", gamePlayer.getId()); // Rescato id de jugador en juego y lo llevo a su campo
                    playerDTO.put("id", gamePlayer.getPlayer().getId()); // Traigo id de jugador
                    playerDTO.put("email", gamePlayer.getPlayer().getUserName()); // Traigo email de jugador
                    gamePlayerDTO.put("player", playerDTO); // Creo el campo "player", donde vuelco el id y el email y lo hago depender del id de jugador en juego

                    gamePlayers.add(gamePlayerDTO); // Incorporo este tramo al general
                    }
                }

                mapResult.put("gamePlayers", gamePlayers); // Creo el campo "gamePlayers", que envuelve lo anterior
                result.add(mapResult); // Lo agrego a la lista resultante
            }
        }
        return result;
    }
}