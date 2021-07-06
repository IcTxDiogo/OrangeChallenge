package com.desafio.comics.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.comics.entidades.Comic;
import com.desafio.comics.entidades.ComicView;
import com.desafio.comics.repository.ComicRepos;

@RestController
@RequestMapping("comic")
public class ComicController {

	@Autowired
	private ComicRepos comicRepos;
	
	@PostMapping
	public ResponseEntity<Void> cadastrar(@RequestBody Comic comicMake) throws Exception {
		String baseUrl = "http://gateway.marvel.com/v1/public/comics/";
		String apiKey = "?ts=1&apikey=8902572306c010f50f44cd7de7c5da6a&hash=03e5e4d746db36244a42bce2ed7810c9";
		if( comicMake.getCodigo() == null) return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		String urlString = baseUrl + comicMake.getCodigo() + apiKey;
		
		//coenction to serv
		URL url = new URL(urlString);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestProperty("accept", "application/json");
		URLConnection urlConnection = url.openConnection();
		
		//read the response
		BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		
		//trasform in jason object
		JSONObject jsonObject = new JSONObject(in.readLine());
		
		//get the results set of jason
		JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONArray("results");
		
		//pega o primeiro item do array(no caso esse array so tem um item)
		JSONObject results = (JSONObject) jsonArray.get(0);
		
		//get the isbn of comic
		String isbnString = (String) results.get("isbn");
		
		//get the title of comic
		String titleString = (String) results.get("title");
		
		//get the price of comic
		JSONArray prices = results.getJSONArray("prices");
		JSONObject price = prices.getJSONObject(0);
		BigDecimal priceString = (BigDecimal) price.get("price");
		
		//get the creators of comic
		JSONObject creatorJsonObject = results.getJSONObject("creators");
		Integer creatorsValue = (Integer) creatorJsonObject.get("available");
		JSONArray creatorArry = creatorJsonObject.getJSONArray("items");
		
		String creatorList = new String();
		for (Integer i = 0; i < creatorsValue; i++) {
			if( i > 0)creatorList += ", ";
			JSONObject x = creatorArry.getJSONObject(i);
			creatorList += (String) x.get("name");
			
		}
		Comic comic = new Comic(null,isbnString,titleString,priceString,creatorList);
		try {
			comicRepos.save(comic);
			return new ResponseEntity<Void>(HttpStatus.CREATED);
			
		} catch (Exception e) {
			
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		
		
	}
	
	@GetMapping
	public List<ComicView> viewAll() {
		List<Comic> list = comicRepos.findAll();
		List<ComicView> list2 = new ArrayList<ComicView>();
		for (Comic comic : list) {
			ComicView comicView = new ComicView(comic);
			list2.add(comicView);
		}
		return list2;
	}
}
