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
			LocalDateTime date = LocalDateTime.now();
			//Date date2 = Date.from(dateJoined);

			Game game1 = new Game(date);
			Game game2 = new Game(date.plus(1, ChronoUnit.HOURS));
			Game game3 = new Game(date.plus(2, ChronoUnit.HOURS));
			Game game4 = new Game(date.plus(3, ChronoUnit.HOURS));
			Game game5 = new Game(date.plus(4, ChronoUnit.HOURS));
			Game game6 = new Game(date.plus(5, ChronoUnit.HOURS));
			//game1.setCreationDate();

			Player player1 = new Player("Jack", "Bauer", "j.bauer@ctu.gov");
			Player player2 = new Player("Chloe", "O'Brian", "c.obrian@ctu.gov");
			Player player3 = new Player("Kim", "Bauer", "kim_bauer@gmail.com");
			Player player4 = new Player("Tony", "Almeida", "t.almeida@ctu.gov");
			Player player5 = new Player("d", "Palmer", "d.palmer@whitehouse.gov");

			// save a couple of players
			player1 = playerRepository.save(player1);
			player2 = playerRepository.save(player2);
			player3 = playerRepository.save(player3);
			player4 = playerRepository.save(player4);
			player5 = playerRepository.save(player5);

			game1 = gameRepository.save(game1);
			game2 = gameRepository.save(game2);
			game3 = gameRepository.save(game3);
			game4 = gameRepository.save(game4);
			game5 = gameRepository.save(game5);
			game6 = gameRepository.save(game6);


			//Player testPlayer = new Player();
			//testPlayer.setId(player2.getId());
			GamePlayer gamePlayer1 = gamePlayerRepository.save(new GamePlayer(date, game1, player1));
			GamePlayer gamePlayer2 = gamePlayerRepository.save(new GamePlayer(date, game1, player2));

			// gamePlayer2 = gamePlayerRepository.save(new GamePlayer(date, game1, player2));
			/* gamePlayerRepository.save(new GamePlayer(date.plus(1, ChronoUnit.HOURS), game1, player1));
			gamePlayerRepository.save(new GamePlayer(date.plus(1, ChronoUnit.HOURS), game1, player2));
			gamePlayerRepository.save(new GamePlayer(date.plus(1, ChronoUnit.HOURS), game2, player1));
			gamePlayerRepository.save(new GamePlayer(date.plus(1, ChronoUnit.HOURS), game2, player2));
			gamePlayerRepository.save(new GamePlayer(date.plus(2, ChronoUnit.HOURS), game3, player2));
			gamePlayerRepository.save(new GamePlayer(date.plus(2, ChronoUnit.HOURS), game3, player4));
			gamePlayerRepository.save(new GamePlayer(date.plus(3, ChronoUnit.HOURS), game4, player1));
			gamePlayerRepository.save(new GamePlayer(date.plus(3, ChronoUnit.HOURS), game4, player2));
			gamePlayerRepository.save(new GamePlayer(date.plus(4, ChronoUnit.HOURS), game5, player4));
			gamePlayerRepository.save(new GamePlayer(date.plus(4, ChronoUnit.HOURS), game5, player1));
			gamePlayerRepository.save(new GamePlayer(date.plus(5, ChronoUnit.HOURS), game6, player5));*/

			// game1 = game1.addGamePlayer(gamePlayer1);
			// game1.addGamePlayer(gamePlayer2);
		};
	}

}
