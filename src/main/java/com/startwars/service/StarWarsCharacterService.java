package com.startwars.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import com.startwars.entity.dto.MovieDTO;
import com.startwars.entity.dto.StarWarsCharacterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.startwars.dao.MoviesDao;
import com.startwars.dao.StarWarsCharacterDao;
import com.startwars.entity.Movie;
import com.startwars.entity.StarWarsCharacter;

@Service
public class StarWarsCharacterService {
	
	@PostConstruct
	public void handler() {
//		for (StarWarsCharacter character : this.findByCharacterName("Owen Lars", PageRequest.of(2, 3))) {
//			System.out.println("::"+character.toString());
//		}
//		Movie movie1 = new Movie("firstMovie");
//		Movie movie2 = new Movie("secondMovie");
//		Movie movie3 = new Movie("thirdMovie");
//		Movie movie4 = new Movie("fourthMovie");
//		
//		StarWarsCharacter character1 = new StarWarsCharacter("sam",120,List.of(movie1,movie3,movie4));
//		StarWarsCharacter character2 = new StarWarsCharacter("john",180,List.of(movie1,movie2,movie4));
//		this.savePeopleInfo(character1);
//		this.savePeopleInfo(character2);
		
	}

	@Autowired
	private StarWarsCharacterDao starWarsCharacterDao;
	
	@Autowired private MoviesDao moviesDao;
	
	public List<StarWarsCharacter> findByCharacterName(String abc,PageRequest pageable ){
		return this.starWarsCharacterDao.findByCharacterName(abc, pageable);
	}
	
	public StarWarsCharacter savePeopleInfo(StarWarsCharacter character) {
		return this.starWarsCharacterDao.save(character);
	}
	public Movie saveMovie(Movie movie) {
		return this.moviesDao.save(movie);
	}
	public Iterable<StarWarsCharacterDTO> getAllCharacters(){
		Iterable<StarWarsCharacter> characters = this.starWarsCharacterDao.findAll();
		List<StarWarsCharacterDTO> characterDTOs = new ArrayList<>();
		characters.forEach((character)->{
			System.out.println();
			System.out.println(character.getCharacterName());
			System.out.println(character.getMovies());
			System.out.println("-------------------");
			//mapping
			StarWarsCharacterDTO starWarsCharacterDTO = new StarWarsCharacterDTO();
			starWarsCharacterDTO.setCharacterName(character.getCharacterName());
			starWarsCharacterDTO.setCharacterHeight(character.getCharacterHeight());
			starWarsCharacterDTO.setMovies(character.getMovies());
			characterDTOs.add(starWarsCharacterDTO);
		});
		return characterDTOs;
	}
	
	public Iterable<MovieDTO> getAllMovies(){
		Iterable<Movie> allMovies= this.moviesDao.findAll();
		List<MovieDTO> allMoviesDto = new ArrayList<>();
		allMovies.forEach((movie)->{
			System.out.println();
			System.out.println(movie.getMovieName());
			System.out.println(movie.getMovieCharacters());
			System.out.println("-------------------");

			//mapping
			MovieDTO movieDTO = new MovieDTO();
			movieDTO.setMovieName(movie.getMovieName());
			movieDTO.setMovieCharacters(movie.getMovieCharacters());
			allMoviesDto.add(movieDTO);
		});
		return allMoviesDto;
	}

	public StarWarsCharacter saveCharacterInfo(StarWarsCharacterDTO starWarsCharacterDTO){
		StarWarsCharacter starWarsCharacter = new StarWarsCharacter();
		Set<Movie> movies = new HashSet<>();
		starWarsCharacterDTO.getMovies().forEach((movie) -> {
			Movie moviefetched = this.moviesDao.findById(movie.getMovieId()).orElse(new Movie());
			movies.add(moviefetched);
		});
		starWarsCharacter.setCharacterHeight(starWarsCharacterDTO.getCharacterHeight());
		starWarsCharacter.setCharacterName(starWarsCharacterDTO.getCharacterName());
		starWarsCharacter.setMovies(movies);

		StarWarsCharacter savedCharacter = this.starWarsCharacterDao.save(starWarsCharacter);
		return savedCharacter;
	}
}
