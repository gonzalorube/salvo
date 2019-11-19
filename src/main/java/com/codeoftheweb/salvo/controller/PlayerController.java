package com.codeoftheweb.salvo.controller;

import com.codeoftheweb.salvo.model.Player;
import com.codeoftheweb.salvo.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class PlayerController {

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @RequestMapping(path = "/players", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> createUser(@RequestParam String username, @RequestParam String firstName, @RequestParam String lastName, @RequestParam String password) {
        ResponseEntity<Map<String, Object>> response;
        Player player = playerRepository.findByUserName(username);
        if (username.isEmpty() || password.isEmpty()) {
            response = new ResponseEntity<>(userRespMap("error", "No name"), HttpStatus.FORBIDDEN);
        } else if (player != null) {
            response = new ResponseEntity<>(userRespMap("error", "Username already exists"), HttpStatus.FORBIDDEN);
        } else {
            Player newPlayer = playerRepository.save(new Player(username, firstName, lastName, password));
            response = new ResponseEntity<>(userRespMap("id", newPlayer.getId()), HttpStatus.CREATED);
        }
        return response;
    }

    private Map<String, Object> userRespMap(String key, Object value) {
        Map<String, Object> map = new HashMap<>();
        map.put(key, value);
        return map;
    }
}
