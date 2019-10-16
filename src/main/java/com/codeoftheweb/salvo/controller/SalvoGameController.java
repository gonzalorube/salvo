package com.codeoftheweb.salvo.controller;


import com.codeoftheweb.salvo.model.SalvoGame;
import com.codeoftheweb.salvo.repository.GameRepository;
import com.codeoftheweb.salvo.repository.SalvoGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SalvoGameController {

    @Autowired
    private SalvoGameRepository repo;
    private GameRepository gameRepo;

    @RequestMapping("/api")
    public List<SalvoGame> getAll(){
        return repo.findAll();
    }
}
