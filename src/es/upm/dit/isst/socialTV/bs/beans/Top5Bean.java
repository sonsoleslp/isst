package es.upm.dit.isst.socialTV.bs.beans;

import java.io.Serializable;
import es.upm.dit.isst.socialTV.bs.model.ProgramaTV;

@SuppressWarnings("serial") //pa que no salga un warning porculero
public class Top5Bean implements Serializable {
	
	/**
	 * Array de filas de la base de datos de ProgramasTV
	 */
	// Paco: he tenido que cambiarlo pq si no no me funcionaba
	// la jsp, lo dejo para que lo borres si le das el visto bueno
	// a los cambios
	//private ProgramaTV[] programasTop5;
	//	public ProgramaTV[] getProgramasTop5() {
	//	return programasTop5;
	//}
	//
	//public void setProgramasTop5(ProgramaTV[] programasTop5) {
	//	this.programasTop5 = programasTop5;
	//}
	
	private String titulo;
	private String episodeCode;
	private String hashtag;
	/**
	 * Este atributo debe contener concatenadas la
	 * fecha de emision del programa con las horas
	 * Ejemplo 16/02/2016 | 19:00-20:00
	 * 
	 * 
	 * NOTA: Si es mas sencillo puedes pasarme la fecha y las horas en 
	 * atributos diferentes y ya le doy formato desde la jsp que tb se puede
	 */
	private String emision;
	private int numTweets;
	
	/**
	 * Constructor
	 */
	public Top5Bean(String titulo, String episodeCode, String hashtag, String emision, int numTweets) {
		this.titulo = titulo;
		this.episodeCode = episodeCode;
		this.hashtag = hashtag;
		this.emision = emision;
		this.numTweets = numTweets;
	}
	
	/**
	 * Setter y getters
	 */
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
