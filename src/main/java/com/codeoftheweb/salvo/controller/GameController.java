package com.codeoftheweb.salvo.controller;

import com.codeoftheweb.salvo.model.Game;
import com.codeoftheweb.salvo.model.GamePlayer;
import com.codeoftheweb.salvo.repository.GamePlayerRepository;
import com.codeoftheweb.salvo.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class GameController {

    @Autowired
    private GameRepository gameRepo;
    private GamePlayerRepository gamePlayerRepo;

    @RequestMapping("/api/games")
 //   public List<Game> getAll() {
 //       return gameRepo.findAll();
    //  }

    public List<Map<String, Object>> getGameIds() {
            // return this.gameRepo.findAll().stream().map(game -> game.getId()).collect(Collectors.toList());
        List<Game> gamesList = gameRepo.findAll();
        List<GamePlayer> gamePlayerList = gamePlayerRepo.findAll();
        List<Map<String, Object>> result = new ArrayList<>();
            // List<Long> gameIds = new ArrayList<>();

        for (Game game: gamesList){

            Map <String, Object> mapResult = new LinkedHashMap<>();
            mapResult.put("id", game.getId());
            mapResult.put("created", game.getCreationDate());

            for(Set<GamePlayer> gamePlayer: gamePlayerList){
                mapResult.put("gamePlayers", gamePlayer.getPlayer());
            }
            result.add(mapResult);
        }
            //gamesList.forEach((id, creationDate) -> result.put(gamesList.getId(), ));
            // result.put(gamesList.getId(), gamesList.getCreationDate());
            //}
        return result;
    }
}

    /* public Map<String, Object> getGameIds() {
        // return this.gameRepo.findAll().stream().map(game -> game.getId()).collect(Collectors.toList());
        List<Game> gamesList = gameRepo.findAll();
        Map<String, Object> result = new LinkedHashMap<>();
        // List<Long> gameIds = new ArrayList<>();
        for (Game game: gamesList){
            result.put("id", game.getId());
            result.put("created", game.getCreationDate());
        }
        //gamesList.forEach((id, creationDate) -> result.put(gamesList.getId(), ));
        // result.put(gamesList.getId(), gamesList.getCreationDate());
        //}
        return result;
    } */

    // result.put(Long.toString(game.getId()), game.getCreationDate());

    /*public List<Long> getGameIds() {
        List<Long> result = this.listOf

        //List<Long> result = this.gameRepo.findAll().stream().map(Game::getId).collect(Collectors.toList());

        List<Salvo> salvos = repo.findAll();
        List<Long> idSalvos = new ArrayList<>();
        for (Salvo salvo: salvos) {
            idSalvos.add(salvo.getId());
        }
        return result; //return idGames;
    }*/

    /*
        Set<GameRepository> setOfGames = getAll().stream().collect(Collectors.toSet());
        return setOfGames.stream().map(Game::getId).collect(Collectors.toList());
     */