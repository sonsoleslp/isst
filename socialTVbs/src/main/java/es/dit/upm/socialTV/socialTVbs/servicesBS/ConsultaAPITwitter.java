package es.dit.upm.socialTV.socialTVbs.servicesBS;

import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import es.dit.upm.socialTV.socialTVbs.beans.PaisEnum;
import es.dit.upm.socialTV.socialTVbs.beans.Trend;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

/**
 * Clase que consulta a la API de Twitter y almacena los datos en Trends
 * @author Naomi
 *
 */
public class ConsultaAPITwitter {

	private static final String NAME_FIELD = "name";
	private static final String VOLUME_FIELD = "tweet_volume";
	private static final int MAX_SEARCH = 100;
	// 180 peticiones cada 15 minutos para search
	private static final int MAX_CALLS = 12;

	private Twitter twitter;

	public ConsultaAPITwitter() {
		this.twitter = TwitterFactory.getSingleton();
	}

	/**
	 * API de búsqueda en Twitter. Últimos 7 días
	 * @param text
	 * @return Lista de tweets
	 */
	public List<Status> search(String text, String date){
		Query query = new Query(text);
		// Solo tweets recientes
		query.setResultType(Query.RECENT);

		// 100 tweets MAX_SEARCH
		query.setCount(MAX_SEARCH);

		//Fecha de inicio
		query.setSince(date);

		QueryResult result=null;
		try {
			result = twitter.search(query);
		} catch (TwitterException e) {
			System.out.println("Error en search(): "+e.getMessage());
			e.printStackTrace();
		}
		return result.getTweets();
	}

	/**
	 * 
	 * @param hashtag
	 * @param inicio Fecha de inicio del programa
	 * @param periodo Periodo de la peticion en ms
	 */
	public void setTimer(String hashtag, Date inicio, int periodo){
		TimerExec timer = new TimerExec();
		timer.setTimer(hashtag, inicio, periodo);
	}
	
	/**
	 * Clase que desarrolla la tarea periódica
	 * @author Antonio
	 *
	 */
	private class TimerExec{
		public void setTimer(String hashtag, Date inicio, int periodo){
			Timer timer;
			timer = new Timer();
			TimerTask task = new TimerTask() {
				@Override
				public void run()
				{
					//search(hashtag, inicio);
					//TODO: Guardar en base de datos
				}
			};
			timer.schedule(task, inicio, periodo);
		}
	}


	// Naomi

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
