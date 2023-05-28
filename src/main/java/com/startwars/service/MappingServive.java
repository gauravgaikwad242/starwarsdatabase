package com.startwars.service;

import com.startwars.entity.Movie;
import com.startwars.entity.StarWarsCharacter;
import com.startwars.entity.dto.MovieDTO;
import com.startwars.entity.dto.StarWarsCharacterDTO;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MappingServive {

    public List<MovieDTO> mapListOfMovieToDTO(Iterable<Movie> movies){
        List<MovieDTO> moviesList = ((List<Movie>) movies).stream().map((movie -> {
            return this.mapMovieToMovieDTO(movie);
        })).collect(Collectors.toList());
        return moviesList;
    }

    public List<StarWarsCharacterDTO> mapListOfCharacterToDto(
            Iterable<StarWarsCharacter> starWarsCharacters){
        List<StarWarsCharacterDTO> characterDTOS = ((List<StarWarsCharacter>) starWarsCharacters)
                                                    .stream()
                .map((character)->{
                    return this.mapStarWarsCharacterDtoToStarWarsCharacter(character);
                }).collect(Collectors.toList());
        return characterDTOS;
    }

    public MovieDTO mapMovieToMovieDTO(Movie movie){
        MovieDTO movieDTO = this.mapMovieToMovieDTOPartial(movie);
        Set<StarWarsCharacterDTO> starWarsCharacterDTOS = new HashSet<>();
        movie.getMovieCharacters().forEach(character -> {
            StarWarsCharacterDTO starWarsCharacterDTO =
                    this.mapStarWarsCharacterToStarWarsCharacterDTOPartial(character);
            starWarsCharacterDTOS.add(starWarsCharacterDTO);

        });
        movieDTO.setMovieCharacters(starWarsCharacterDTOS);
        return movieDTO;
    }

    public StarWarsCharacterDTO mapStarWarsCharacterDtoToStarWarsCharacter(
            StarWarsCharacter starWarsCharacter){
        StarWarsCharacterDTO starWarsCharacterDTO =
                this.mapStarWarsCharacterToStarWarsCharacterDTOPartial(starWarsCharacter);
      Set<MovieDTO> movieDTOS = new HashSet<>();
      starWarsCharacter.getMovies().forEach((movie)->{
          MovieDTO movieDTO = this.mapMovieToMovieDTOPartial(movie);
          movieDTOS.add(movieDTO);

      });
      starWarsCharacterDTO.setMovies(movieDTOS);
        return starWarsCharacterDTO;
    }

    public MovieDTO mapMovieToMovieDTOPartial(Movie movie){
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setMovieId(movie.getMovieId());
        movieDTO.setMovieName(movie.getMovieName());
        return movieDTO;
    }

    public StarWarsCharacterDTO mapStarWarsCharacterToStarWarsCharacterDTOPartial(
                                StarWarsCharacter starWarsCharacter){
        StarWarsCharacterDTO starWarsCharacterDTO = new StarWarsCharacterDTO();
        starWarsCharacterDTO.setPeopleId(starWarsCharacter.getPeopleId());
        starWarsCharacterDTO.setCharacterHeight(starWarsCharacter.getCharacterHeight());
        starWarsCharacterDTO.setCharacterName(starWarsCharacter.getCharacterName());
        return starWarsCharacterDTO;
    }
    public Movie mapMovieDTOToMoviePartial(MovieDTO movieDTO){
        Movie movie = new Movie();
        movie.setMovieId(movieDTO.getMovieId());
        movie.setMovieName(movieDTO.getMovieName());
        return movie;
    }

    public StarWarsCharacter mapStarWarsCharacterDTOToStarWarsCharacterPartial(
                                StarWarsCharacterDTO starWarsCharacterDTO){
        StarWarsCharacter starWarsCharacter = new StarWarsCharacter();
        starWarsCharacter.setPeopleId(starWarsCharacterDTO.getPeopleId());
        starWarsCharacter.setCharacterHeight(starWarsCharacterDTO.getCharacterHeight());
        starWarsCharacter.setCharacterName(starWarsCharacterDTO.getCharacterName());
        return starWarsCharacter;
    }



}
