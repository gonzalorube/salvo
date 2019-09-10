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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.GregorianCalendar;


@SpringBootApplication
public class SalvoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalvoApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(PlayerRepository playerRepository, GameRepository gameRepository, GamePlayerRepository gamePlayerRepository){
		return (args) -> {
			Date date = new Date();
			//Date date2 = Date.from(dateJoined);

			Game game1 = new Game(date);
			Game game2 = new Game(Date.from(date.toInstant().plusSeconds(3600)));
			Game game3 = new Game(Date.from(date.toInstant().plusSeconds(7200)));
			//game1.setCreationDate();

			Player player1 = new Player("Jack", "Bauer", "j.bauer@ctu.gov");
			Player player2 = new Player("Chloe", "O'Brian", "c.obrian@ctu.gov");
			Player player3 = new Player("Kim", "Bauer", "kim_bauer@gmail.com");
			Player player4 = new Player("Tony", "Almeida", "t.almeida@ctu.gov");

			// save a couple of players
			player1 = playerRepository.save(player1);
			player2 = playerRepository.save(player2);
			player3 = playerRepository.save(player3);
			player4 = playerRepository.save(player4);

			game1= gameRepository.save(game1);
			game2= gameRepository.save(game2);
			game3 = gameRepository.save(game3);

			//Player testPlayer = new Player();
			//testPlayer.setId(player2.getId());
			gamePlayerRepository.save(new GamePlayer(date, game1, player1));
			gamePlayerRepository.save(new GamePlayer(date, game1, player2));
			gamePlayerRepository.save(new GamePlayer(Date.from(date.toInstant().plusSeconds(3600)), game2, player1));
			gamePlayerRepository.save(new GamePlayer(Date.from(date.toInstant().plusSeconds(3600)), game2, player2));
			gamePlayerRepository.save(new GamePlayer(Date.from(date.toInstant().plusSeconds(3600)), game3, player2));
			gamePlayerRepository.save(new GamePlayer(Date.from(date.toInstant().plusSeconds(3600)), game3, player4));
		};
	}

}
