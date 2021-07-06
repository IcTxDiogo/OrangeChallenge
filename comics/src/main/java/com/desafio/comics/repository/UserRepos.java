package com.desafio.comics.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafio.comics.entidades.User;

public interface UserRepos extends JpaRepository<User, Integer>{

}
