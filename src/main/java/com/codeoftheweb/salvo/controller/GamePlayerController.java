package com.codeoftheweb.salvo.controller;

import com.codeoftheweb.salvo.model.GamePlayer;
import com.codeoftheweb.salvo.model.Ship;
import com.codeoftheweb.salvo.repository.GamePlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class GamePlayerController {

    @Autowired
    GamePlayerRepository gamePlayerRepo;

    @RequestMapping("api/game_view/{gamePlayerId}")
    private Map<String, Object> getGameView(@PathVariable long gamePlayerId){
        return this.gameView(gamePlayerRepo.findById(gamePlayerId));
    }

    private Map<String, Object> gameView(GamePlayer gamePlayer){
        Map<String,Object> gameViewDTO = new LinkedHashMap<>();

        if(gamePlayer != null){
            gameViewDTO.put("id", gamePlayer.getGame().getId());
            gameViewDTO.put("creationDate", gamePlayer.getGame().getCreationDate());
            gameViewDTO.put("gamePlayer", gamePlayer.getGame().getGamePlayers().stream().map(GamePlayer::gamePlayerDTO));
            gameViewDTO.put("ships", gamePlayer.getShips().stream().map(Ship::shipDTO));
        }else{
            gameViewDTO.put("Total Kaos", "The game you are looking for doesn't exist");
        }

        return gameViewDTO;
    }
}
