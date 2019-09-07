package com.codeoftheweb.salvo;

import com.codeoftheweb.salvo.model.Player;
import com.codeoftheweb.salvo.repository.PlayerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SalvoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalvoApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(PlayerRepository playerRepository){
		return (args) -> {
			// save a couple of players
			playerRepository.save(new Player("Jack", "Bauer", "j.bauer@ctu.gov"));
			playerRepository.save(new Player("Chloe", "O'Brian", "c.obrian@ctu.gov"));
			playerRepository.save(new Player("Kim", "Bauer", "kim_bauer@gmail.com"));
			playerRepository.save(new Player("Tony", "Almeida", "t.almeida@ctu.gov"));
		};
	}

}
