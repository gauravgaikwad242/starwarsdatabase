package com.startwars;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.startwars.entity.Movie;
import com.startwars.entity.StarWarsCharacter;
import com.startwars.service.StarWarsCharacterService;

@SpringBootApplication
public class StarWarsApiAppApplication implements CommandLineRunner{

	@Autowired
	private StarWarsCharacterService characterService;
	
	public static void main(String[] args){
		SpringApplication.run(StarWarsApiAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Movie movie1 = new Movie("firstMovie");
		Movie movie2 = new Movie("secondMovie");
		Movie movie3 = new Movie("thirdMovie");
		Movie movie4 = new Movie("fourthMovie");
		
		StarWarsCharacter character1 = new StarWarsCharacter("sam",120,new HashSet<>(Arrays.asList(movie1,movie2)));
		StarWarsCharacter character2 = new StarWarsCharacter("sam",120,new HashSet<>(Arrays.asList(movie3,movie4)));

		this.characterService.savePeopleInfo(character1);
		this.characterService.savePeopleInfo(character2);
//		Thread.sleep(3000);
//		Iterable<StarWarsCharacter> characters = this.characterService.getAllCharacters();
//		for(StarWarsCharacter character : characters) {
//			System.out.println("::"+character.toString());
//		}
//		this.characterService.savePeopleInfo(character2);

	}

}
