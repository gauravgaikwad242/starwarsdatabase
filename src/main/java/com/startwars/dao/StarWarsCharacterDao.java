package com.startwars.dao;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.startwars.entity.StarWarsCharacter;

public interface StarWarsCharacterDao extends JpaRepository<StarWarsCharacter, Integer>{

//	List<StarWarsCharacter> findByCharacterName(String abc,Pageable pageable );

	List<StarWarsCharacter> findByCharacterName(String abc, Pageable pageable);

	
}
