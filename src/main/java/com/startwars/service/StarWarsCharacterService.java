package com.startwars.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
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
		
	}
	@Autowired
	private  MappingServive mappingServive;
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
	public List<StarWarsCharacterDTO> getAllCharacters(){
		Iterable<StarWarsCharacter> characters = this.starWarsCharacterDao.findAll();

		return this.mappingServive.mapListOfCharacterToDto(characters);
	}
	
	public List<MovieDTO> getAllMovies(){
		Iterable<Movie> allMovies= this.moviesDao.findAll();
		return this.mappingServive.mapListOfMovieToDTO(allMovies);
	}

	public StarWarsCharacterDTO saveCharacterInfo(StarWarsCharacterDTO starWarsCharacterDTO){
		StarWarsCharacter starWarsCharacter = new StarWarsCharacter();
		Set<Movie> movies = new HashSet<>();
		starWarsCharacterDTO.getMovies().forEach((movie) -> {
			Optional<Movie> moviefetched = this.moviesDao.findById(movie.getMovieId());
			if(moviefetched.isPresent()){
				movies.add(moviefetched.get());
			}
		});
		starWarsCharacter.setCharacterHeight(starWarsCharacterDTO.getCharacterHeight());
		starWarsCharacter.setCharacterName(starWarsCharacterDTO.getCharacterName());
		starWarsCharacter.setMovies(movies);

		StarWarsCharacter savedCharacter = this.starWarsCharacterDao.save(starWarsCharacter);
		return this.mappingServive.mapStarWarsCharacterDtoToStarWarsCharacter(savedCharacter);
	}

	public MovieDTO saveMovieInfo(MovieDTO movieDTO){
		Movie movie = this.mappingServive.mapMovieDTOToMoviePartial(movieDTO);
		Set<StarWarsCharacter> starWarsCharacters = new HashSet<>();
		movieDTO.getMovieCharacters().forEach(character -> {
			Optional<StarWarsCharacter> starWarCharacter =
								 this.starWarsCharacterDao.findById(character.getPeopleId());
			if(starWarCharacter.isPresent()){
				starWarsCharacters.add(starWarCharacter.get());
			}
		});
		movie.setMovieCharacters(starWarsCharacters);
		Movie savedMovie = this.moviesDao.save(movie);
		System.out.println(savedMovie);
		System.out.println(savedMovie.getMovieCharacters());
		return this.mappingServive.mapMovieToMovieDTO(savedMovie);
	}
}
