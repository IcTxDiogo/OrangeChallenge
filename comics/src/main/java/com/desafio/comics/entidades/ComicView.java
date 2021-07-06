package com.desafio.comics.entidades;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;

import lombok.Data;

@Data
public class ComicView {

	private Comic comic;
	private Boolean descontoAtivo;
	
	public 	ComicView(Comic comic) {
		this.comic = comic;
		String isbnString = this.comic.getIsbn();
		String lastString = isbnString.substring(isbnString.length()-1);
		if(verifiyDiscont(lastString)) {
			this.descontoAtivo = true;
			BigDecimal priceAtual = this.comic.getPrices();
			BigDecimal desconto = new BigDecimal("0.9");
			priceAtual = priceAtual.multiply(desconto);
			this.comic.setPrices(priceAtual);
		}
	}
	
	public Boolean verifiyDiscont(String string) {
		
		LocalDate localDate = LocalDate.now();
		DayOfWeek dayOfWeek = DayOfWeek.from(localDate);
		
		if((string.equals("0") ||  string.equals("1")) && dayOfWeek.getValue() == 1) return true;
		if((string.equals("2") ||  string.equals("3")) && dayOfWeek.getValue() == 2) return true;
		if((string.equals("4") ||  string.equals("5")) && dayOfWeek.getValue() == 3) return true;
		if((string.equals("6") ||  string.equals("7")) && dayOfWeek.getValue() == 4) return true;
		if((string.equals("8") ||  string.equals("9")) && dayOfWeek.getValue() == 5) return true;
		return false;
		
	}
}
