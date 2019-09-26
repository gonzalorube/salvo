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
    private GameRepository gameRepo;

    @RequestMapping("/api/games")

    private List<Map<String, Object>> getGames() {

        List<Game> gamesList = gameRepo.findAll();
        List<Map<String, Object>> result = new ArrayList<>();

        for (Game game: gamesList){

            Map <String, Object> mapResult = new LinkedHashMap<>();
            mapResult.put("id", game.getId());
            mapResult.put("created", game.getCreationDate());
            List<Map<String, Object>> gamePlayers = new ArrayList<>();

            for ( GamePlayer gamePlayer: game.getPlayers()) {

                Map<String, Object> gamePlayerDTO = new LinkedHashMap<>();
                Map<String, Object> playerDTO = new LinkedHashMap<>();

                gamePlayerDTO.put("id", gamePlayer.getId());
                playerDTO.put("id", gamePlayer.getPlayer().getId());
                playerDTO.put("email", gamePlayer.getPlayer().getUserName());
                gamePlayerDTO.put("player", playerDTO);

                gamePlayers.add(gamePlayerDTO);
            }

            mapResult.put("gamePlayers", gamePlayers);
            result.add(mapResult);
        }
        return result;
    }
}