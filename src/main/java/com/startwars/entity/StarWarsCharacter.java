package com.startwars.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "people_table")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StarWarsCharacter {

	@Id
	@Column(name = "people_id")
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "character_sequence")
	@SequenceGenerator(name = "character_sequence",sequenceName = "character_sequence",
							initialValue = 10001,allocationSize = 1)
	private Integer peopleId;
	
	@Column(name = "people_name")
	@JsonProperty("name")
	private String characterName;
	
	@Column(name = "people_height")
	@JsonProperty("height")
	private int characterHeight;
	
	@ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST},fetch = FetchType.LAZY)
//	@JsonManagedReference
	@JoinColumn(name = "character_movie"
				)
	@JsonIgnore
	private Set<Movie> movies;
	
	public StarWarsCharacter(String characterName, int characterHeight, Set<Movie> movies){
	
		this.characterName = characterName;
		this.characterHeight = characterHeight;
		this.movies = movies;
	}
	public StarWarsCharacter(String characterName, int characterHeight){
		
		this.characterName = characterName;
		this.characterHeight = characterHeight;
	}
	@Override
	public String toString() {
		return "StarWarsCharacter [peopleId=" + peopleId + ", characterName=" + characterName + ", characterHeight="
				+ characterHeight + ", movies= ";
	}
//	public String toString() {
//		return "StarWarsCharacter [peopleId=" + peopleId + ", characterName=" + characterName + ", characterHeight="
//				+ characterHeight + ","+ "]";
//	}
	
	
}
