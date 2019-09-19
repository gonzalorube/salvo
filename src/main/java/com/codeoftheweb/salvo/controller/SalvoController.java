package com.codeoftheweb.salvo.controller;


import com.codeoftheweb.salvo.model.Game;
import com.codeoftheweb.salvo.model.Salvo;
import com.codeoftheweb.salvo.repository.GameRepository;
import com.codeoftheweb.salvo.repository.SalvoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class SalvoController {

    @Autowired
    private SalvoRepository repo;
    private GameRepository gameRepo;

    @RequestMapping("/api")
    public List<Salvo> getAll(){
        return repo.findAll();
    }
}
