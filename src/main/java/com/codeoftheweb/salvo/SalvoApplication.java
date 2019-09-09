package com.codeoftheweb.salvo;

import com.codeoftheweb.salvo.model.Game;
import com.codeoftheweb.salvo.model.GamePlayer;
import com.codeoftheweb.salvo.model.Player;
import com.codeoftheweb.salvo.repository.GamePlayerRepository;
import com.codeoftheweb.salvo.repository.GameRepository;
import com.codeoftheweb.salvo.repository.PlayerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;


@SpringBootApplication
public class SalvoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalvoApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(PlayerRepository playerRepository, GameRepository gameRepository){
		return (args) -> {
			Date date = new Date();
			//Instant dateJoined = Instant.parse("2018-17-02T15:20:15");
			// save a couple of players
			playerRepository.save(new Player("Jack", "Bauer", "j.bauer@ctu.gov"));
			playerRepository.save(new Player("Chloe", "O'Brian", "c.obrian@ctu.gov"));
			playerRepository.save(new Player("Kim", "Bauer", "kim_bauer@gmail.com"));
			playerRepository.save(new Player("Tony", "Almeida", "t.almeida@ctu.gov"));

			gameRepository.save(new Game(1, date));
			gameRepository.save(new Game(2, Date.from(date.toInstant().plusSeconds(3600))));
			gameRepository.save(new Game(3, Date.from(date.toInstant().plusSeconds(7200))));

			//gamePlayerRepository.save(new GamePlayer(1, Date.from(dateJoined)));
			//gamePlayerRepository.save(new GamePlayer(1, 2008-08-03T03:20:00, 1, 2));
		};
	}

}
