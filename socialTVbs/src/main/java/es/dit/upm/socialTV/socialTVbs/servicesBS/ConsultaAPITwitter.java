package es.dit.upm.socialTV.socialTVbs.servicesBS;

import es.dit.upm.socialTV.socialTVbs.beans.PaisEnum;
import es.dit.upm.socialTV.socialTVbs.beans.Trend;

/**
 * Clase que consulta a la API de Twitter y almacena los datos en Trends
 * @author Naomi
 *
 */
public class ConsultaAPITwitter {
	
	/**
	 * Realiza una consulta a partir de un hashtag dado (nivel mundial)
	 * @param hashtag
	 * @return
	 */
	public Trend consultarHashtag(String hashtag) {
		// TODO: llamar a la API de twitter, mapear la respuesta, etc.
		String respuestaJSON = llamarAPITwitter(hashtag, null);
		return mapearJSON2Trend(respuestaJSON);	
	}
	
	/**
	 * Realiza una consulta a partir de un hashtag dado y en un determinado 
	 * pais
	 * @param hashtag
	 * @return
	 */
	public Trend consultarHashtag(String hashtag, PaisEnum pais) {
		// TODO: llamar a la API de twitter, mapear la respuesta, etc.
		String respuestaJSON = llamarAPITwitter(hashtag, pais);
		return mapearJSON2Trend(respuestaJSON);	
	}
	
	private String llamarAPITwitter(String hashtag, PaisEnum pais) {
		//TODO llamada
		String json = "";
		
		// Caso mundial
		if(pais == null) {
			return "300";
		}
		switch(pais) {
		case SPAIN:
			json = "80";
			break;
		case EEUU:
			json = "200";
			break;
		case GERMANY:
			json = "20";
			break;
		default:
			json = "0";
		}
		return json;
	}
	
	private Trend mapearJSON2Trend(String json) {
		//TODO mapear JSON
		Trend trend = new Trend();
		trend.setHashtag("Soy un hashtag");
		trend.setNumTweets(Integer.parseInt(json));
		
		return trend;
	}
}
