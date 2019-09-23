package com.codeoftheweb.salvo.controller;

import com.codeoftheweb.salvo.model.Game;
import com.codeoftheweb.salvo.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class GameController {

    @Autowired
    private GameRepository gameRepo;

    @RequestMapping("/api/games")
 //   public List<Game> getAll() {
 //       return gameRepo.findAll();
    //  }

    public Map<String, Object> getGameIds() {
    //    return this.gameRepo.findAll().stream().map(game -> game.getId()).collect(Collectors.toList());
        List<Game> gamesList = gameRepo.findAll();
        Map<String, Object> result = new HashMap<>();
        List<Long> gameIds = new ArrayList<>();
        for (Game game: gamesList){
            result.put(Long.toString(game.getId()), game.getCreationDate());
        }
        //gamesList.forEach((id, creationDate) -> result.put(gamesList.getId(), ));
        //    result.put(gamesList.getId(), gamesList.getCreationDate());
        //}
        return result;
    }
}

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