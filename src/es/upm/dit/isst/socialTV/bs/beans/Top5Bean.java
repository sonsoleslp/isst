package es.upm.dit.isst.socialTV.bs.beans;

import java.io.Serializable;

/**
 * JavaBean que contiene los datos del Top 5 de programas.
 * 
 * @author Paco
 *
 */
@SuppressWarnings("serial")
public class Top5Bean implements Serializable {
	
	private Long primaryKey;
	private String titulo;
	private String episodeCode;
	private String hashtag;
	/**
	 * Este atributo debe contener concatenadas la
	 * fecha de emision del programa con las horas
	 * Ejemplo 16/02/2016 | 19:00-20:00
	 * 
	 * A hacer en el servlet
	 */
	private String emision;
	private int numTweets;
	
	/**
	 * Constructor
	 */
	public Top5Bean(Long primaryKey, String titulo, String episodeCode, String hashtag, String emision, int numTweets) {
		this.primaryKey = primaryKey;
		this.titulo = titulo;
		this.episodeCode = episodeCode;
		this.hashtag = hashtag;
		this.emision = emision;
		this.numTweets = numTweets;
	}
	
	/**
	 * Setter y getters
	 */
	public Long getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(Long primaryKey) {
		this.primaryKey = primaryKey;
	}
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getEpisodeCode() {
		return episodeCode;
	}

	public void setEpisodeCode(String episodeCode) {
		this.episodeCode = episodeCode;
	}

	public String getHashtag() {
		return hashtag;
	}

	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}

	public String getEmision() {
		return emision;
	}

	public void setEmision(String emision) {
		this.emision = emision;
	}

	public int getNumTweets() {
		return numTweets;
	}

	public void setNumTweets(int numTweets) {
		this.numTweets = numTweets;
	}
	
}
