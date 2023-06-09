package com.startwars.controller;

import java.util.List;

import com.startwars.entity.Movie;
import com.startwars.entity.dto.MovieDTO;
import com.startwars.entity.dto.StarWarsCharacterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.startwars.entity.StarWarsCharacter;
import com.startwars.service.StarWarsCharacterService;

@RestController
@RequestMapping("people")
@CrossOrigin(allowedHeaders = "*",origins = "*")
public class StarWarsCharacterController {
	
	@Autowired
	private StarWarsCharacterService characterService;
	
	@PostMapping("save")
	public ResponseEntity<StarWarsCharacter> savePeople(@RequestBody StarWarsCharacter character) {
		StarWarsCharacter character2 = this.characterService.savePeopleInfo(character);
		return new ResponseEntity<StarWarsCharacter>(character2, HttpStatus.CREATED);
	}

	@PostMapping("saveCharacter")
	public ResponseEntity<StarWarsCharacterDTO> saveCharacter(@RequestBody StarWarsCharacterDTO character) {
		StarWarsCharacterDTO character2 = this.characterService.saveCharacterInfo(character);
		return new ResponseEntity<StarWarsCharacterDTO>(character2, HttpStatus.CREATED);
	}
	@PostMapping("saveMovie")
	public ResponseEntity<MovieDTO> saveMovie(@RequestBody MovieDTO movie) {
		MovieDTO savedMovie = this.characterService.saveMovieInfo(movie);
		return new ResponseEntity<MovieDTO>(savedMovie, HttpStatus.CREATED);
	}
	
	@GetMapping("getCharacters/{characterName}/{pageNo}/{entries}")
	public List<StarWarsCharacter> getCharacterByPage(
										@PathVariable("characterName") String characterName,
										@PathVariable("pageNo")int pageNo,
										@PathVariable("entries") int entries){
		
		return this.characterService.findByCharacterName(characterName,PageRequest.of(pageNo, entries));
		
	}

	@GetMapping("getAll")
	public List<StarWarsCharacterDTO> getAllCharacters(){
		List<StarWarsCharacterDTO> characters = this.characterService.getAllCharacters();
		System.out.println(characters);
		return characters;
	}

	@GetMapping("getAllMovies")
	public List<MovieDTO> getAllMovies(){
		List<MovieDTO> characters = this.characterService.getAllMovies();
		System.out.println(characters);
		return characters;
	}

//	public MovieDTO updateMovieData()

}
