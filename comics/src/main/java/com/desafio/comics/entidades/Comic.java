package com.desafio.comics.entidades;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comic {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codigo;
	@Column(unique=true, length = 13, nullable = false)
	private String isbn;
	@Column(nullable = false, length = 200)
	private String title;
	@Column(length = 50)
	private BigDecimal prices;
	@Column(length = 200)
	private String creators;
	
}

