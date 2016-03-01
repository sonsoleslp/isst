package es.dit.upm.socialTV.socialTVbs.beans;

import java.io.Serializable;

/**
 * Bean empleado para recoger los datos de la API de twitter
 * @author Naomi
 *
 */
public class Trend implements Serializable {
	
	// Numero de tweets de dicho trend
	private int numTweets;
	// Nombre del hashtag asociado
	private String hashtag;
	
	// TODO: Añadir lo que querais necesiteis
	
	public int getNumTweets() {
		return numTweets;
	}
	public void setNumTweets(int numTweets) {
		this.numTweets = numTweets;
	}
	public String getHashtag() {
		return hashtag;
	}
	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}
}
