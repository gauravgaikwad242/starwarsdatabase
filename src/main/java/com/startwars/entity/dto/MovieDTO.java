package com.startwars.entity.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.startwars.entity.StarWarsCharacter;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
public class MovieDTO {

    @JsonIgnore
    private Integer movieId;

    private String movieName;

    private Set<StarWarsCharacter> movieCharacters;
}
