package com.startwars.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.startwars.entity.Movie;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
public class StarWarsCharacterDTO {

    
    private Integer peopleId;

    private String characterName;

    private int characterHeight;

    private Set<MovieDTO> movies;
}
