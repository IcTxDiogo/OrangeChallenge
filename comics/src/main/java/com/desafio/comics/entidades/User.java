package com.desafio.comics.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codigo;
	
	@Column(unique=true, length = 11, nullable = false)
	private String cpf;
	
	@Column(unique=true, length = 50, nullable = false)
	private String email;
	
	@Column(length = 50, nullable = false )
	private String nome;
	
	@Column(nullable = false )
	private String data;
	
}
