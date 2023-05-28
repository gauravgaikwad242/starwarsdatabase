package com.startwars.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "movie_table")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
	
	@Id
	@Column(name = "movie_id")
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "movie_sequence")
	@SequenceGenerator(name = "movie_sequence",sequenceName = "movie_sequence",
			initialValue = 70001,allocationSize = 1)
	private Integer movieId;
	
	@Column(name = "movie_name")
	private String movieName;
	
//	@Column(name = "movie_characters")
	@ManyToMany(cascade = {CascadeType.ALL},mappedBy = "movies",fetch = FetchType.LAZY)
//	@JsonBackReference
	@JsonIgnore
	private Set<StarWarsCharacter> movieCharacters; 
	
	public Movie(String movieName) {
		this.movieName  = movieName;
	
	}
	public Movie(String movieName,Set<StarWarsCharacter> movieCharacters) {
		this.movieName = movieName;
		this.movieCharacters = movieCharacters;

	}
	@Override
	public String toString() {
		return "Movie [movieId=" + movieId + ", movieName=" + movieName +
				", movieCharacters="  +  "]";
	}
	
	

}
