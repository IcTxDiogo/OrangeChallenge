package com.desafio.comics.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafio.comics.entidades.Comic;

public interface ComicRepos extends JpaRepository<Comic, Integer>{

}
