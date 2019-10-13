package com.codeoftheweb.salvo;

import com.codeoftheweb.salvo.model.Game;
import com.codeoftheweb.salvo.model.GamePlayer;
import com.codeoftheweb.salvo.model.Player;
import com.codeoftheweb.salvo.model.Ship;
import com.codeoftheweb.salvo.repository.GamePlayerRepository;
import com.codeoftheweb.salvo.repository.GameRepository;
import com.codeoftheweb.salvo.repository.PlayerRepository;
import com.codeoftheweb.salvo.repository.ShipRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;


@SpringBootApplication
public class SalvoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalvoApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(PlayerRepository playerRepository, GameRepository gameRepository, GamePlayerRepository gamePlayerRepository, ShipRepository shipRepository){
		return (args) -> {
			// Instancio la fecha pedida
			LocalDateTime date = LocalDateTime.of(2018, 2, 17, 15, 20, 15);

			// Instancio los 6 juegos
			Game game1 = new Game(date);
			Game game2 = new Game(date.plus(1, ChronoUnit.HOURS));
			Game game3 = new Game(date.plus(2, ChronoUnit.HOURS));
			Game game4 = new Game(date.plus(3, ChronoUnit.HOURS));
			Game game5 = new Game(date.plus(4, ChronoUnit.HOURS));
			Game game6 = new Game(date.plus(5, ChronoUnit.HOURS));

			// Instancio los 5 jugadores
			Player player1 = new Player("Jack", "Bauer", "j.bauer@ctu.gov");
			Player player2 = new Player("Chloe", "O'Brian", "c.obrian@ctu.gov");
			Player player3 = new Player("Kim", "Bauer", "kim_bauer@gmail.com");
			Player player4 = new Player("Tony", "Almeida", "t.almeida@ctu.gov");
			Player player5 = new Player("d", "Palmer", "d.palmer@whitehouse.gov");

			// Guardo los jugadores
			player1 = playerRepository.save(player1);
			player2 = playerRepository.save(player2);
			player3 = playerRepository.save(player3);
			player4 = playerRepository.save(player4);
			player5 = playerRepository.save(player5);

			// Guardo los juegos
			game1 = gameRepository.save(game1);
			game2 = gameRepository.save(game2);
			game3 = gameRepository.save(game3);
			game4 = gameRepository.save(game4);
			game5 = gameRepository.save(game5);
			game6 = gameRepository.save(game6);

			GamePlayer gamePlayer1 = new GamePlayer();
			GamePlayer gamePlayer2 = new GamePlayer();
			GamePlayer gamePlayer3 = new GamePlayer();
			GamePlayer gamePlayer4 = new GamePlayer();
			GamePlayer gamePlayer5 = new GamePlayer();
			GamePlayer gamePlayer6 = new GamePlayer();
			GamePlayer gamePlayer7 = new GamePlayer();
			GamePlayer gamePlayer8 = new GamePlayer();
			GamePlayer gamePlayer9 = new GamePlayer();
			GamePlayer gamePlayer10 = new GamePlayer();
			GamePlayer gamePlayer11 = new GamePlayer();

			// Instancio y guardo jugadores en sendos juegos
			gamePlayer1 = gamePlayerRepository.save(new GamePlayer(date.plus(1, ChronoUnit.HOURS), game1, player1));
			gamePlayer2 = gamePlayerRepository.save(new GamePlayer(date.plus(1, ChronoUnit.HOURS), game1, player2));
			gamePlayer3 = gamePlayerRepository.save(new GamePlayer(date.plus(1, ChronoUnit.HOURS), game2, player1));
			gamePlayer4 = gamePlayerRepository.save(new GamePlayer(date.plus(1, ChronoUnit.HOURS), game2, player2));
			gamePlayer5 = gamePlayerRepository.save(new GamePlayer(date.plus(2, ChronoUnit.HOURS), game3, player2));
			gamePlayer6 = gamePlayerRepository.save(new GamePlayer(date.plus(2, ChronoUnit.HOURS), game3, player4));
			gamePlayer7 = gamePlayerRepository.save(new GamePlayer(date.plus(3, ChronoUnit.HOURS), game4, player1));
			gamePlayer8 = gamePlayerRepository.save(new GamePlayer(date.plus(3, ChronoUnit.HOURS), game4, player2));
			gamePlayer9 = gamePlayerRepository.save(new GamePlayer(date.plus(4, ChronoUnit.HOURS), game5, player4));
			gamePlayer10 = gamePlayerRepository.save(new GamePlayer(date.plus(4, ChronoUnit.HOURS), game5, player1));
			gamePlayer11 = gamePlayerRepository.save(new GamePlayer(date.plus(5, ChronoUnit.HOURS), game6, player5));


			gamePlayer1.addShip(new Ship("Submarine", Arrays.asList("E1", "F1", "G1")));
			gamePlayer1.addShip(new Ship("Destroyer", Arrays.asList("H2", "H3", "H4")));
			gamePlayer1.addShip(new Ship("Patro Boat", Arrays.asList("B4", "B5")));

			gamePlayer2.addShip(new Ship("Destroyer", Arrays.asList("B5", "C5", "D5")));
			gamePlayer2.addShip(new Ship("Patrol Boat", Arrays.asList("F1", "F2")));

			gamePlayer3.addShip(new Ship("Patrol Boat", Arrays.asList("C6","C7")));
			gamePlayer3.addShip(new Ship("Destroyer", Arrays.asList("B5", "C5", "D5")));

			gamePlayer4.addShip(new Ship("Submarine", Arrays.asList("A2", "A3", "A4")));
			gamePlayer4.addShip(new Ship("Patrol Boat", Arrays.asList("G6", "H6")));

			gamePlayer5.addShip(new Ship("Destroyer", Arrays.asList("B5", "C5", "D5")));
			gamePlayer5.addShip(new Ship("Patrol Boat", Arrays.asList("C6", "C7")));

			gamePlayer6.addShip(new Ship("Submarine", Arrays.asList("A2", "A3", "A4")));
			gamePlayer6.addShip(new Ship("Patrol Boat", Arrays.asList("G6", "H6")));

			gamePlayer7.addShip(new Ship("Destroyer", Arrays.asList("B5", "C5", "D5")));
			gamePlayer7.addShip(new Ship("Patrol Boat", Arrays.asList("C6", "C7")));

			gamePlayer8.addShip(new Ship("Submarine", Arrays.asList("A2", "A3", "A4")));
			gamePlayer8.addShip(new Ship("Patrol Boat", Arrays.asList("G6", "H6")));

			gamePlayer9.addShip(new Ship("Destroyer", Arrays.asList("B5", "C5", "D5")));
			gamePlayer9.addShip(new Ship("Patrol Boat", Arrays.asList("C6", "C7")));

			gamePlayer10.addShip(new Ship("Submarine", Arrays.asList("A2", "A3", "A4")));
			gamePlayer10.addShip(new Ship("Patrol Boat", Arrays.asList("G6", "H6")));

			gamePlayerRepository.save(gamePlayer1);
			gamePlayerRepository.save(gamePlayer2);
			gamePlayerRepository.save(gamePlayer3);
			gamePlayerRepository.save(gamePlayer4);
			gamePlayerRepository.save(gamePlayer5);
			gamePlayerRepository.save(gamePlayer6);
			gamePlayerRepository.save(gamePlayer7);
			gamePlayerRepository.save(gamePlayer8);
			gamePlayerRepository.save(gamePlayer9);
			gamePlayerRepository.save(gamePlayer10);

		};
	}

}
