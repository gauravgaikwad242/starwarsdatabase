package com.startwars.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.startwars.entity.Movie;

public interface MoviesDao extends JpaRepository<Movie, Integer>{

}
