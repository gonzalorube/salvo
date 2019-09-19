package com.codeoftheweb.salvo.controller;

import com.codeoftheweb.salvo.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class GameController {

    @Autowired
    private GameRepository gameRepo;

    @RequestMapping("/api/games")
 //   public List<Game> getAll() {
 //       return gameRepo.findAll();
    //  }

    public List<Long> getGameIds() {
        return this.gameRepo.findAll().stream().map(game -> game.getId()).collect(Collectors.toList());
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