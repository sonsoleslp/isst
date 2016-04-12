package es.upm.dit.isst.socialTV.bs.services;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import es.upm.dit.isst.socialTV.bs.model.ProgramaTV;
import es.upm.dit.isst.socialTV.bs.model.ProgramaTVDAO;
import es.upm.dit.isst.socialTV.bs.model.ProgramaTVImpl;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.TwitterObjectFactory;
import twitter4j.Status;
import twitter4j.Trends;

/**
 * Clase que consulta a la API de Twitter
 * Dado un hashtag el endpoint search te da los hashtags del tweet y su texto
 * El endpoint trends/place (15xmin) te da el volumen de tweets en las ultimas
 * 24 horas especificando provincia
 * @author Antonio
 *
 */
public class ConsultaAPITwitter {

	private static final String NAME_FIELD = "name";
	private static final String VOLUME_FIELD = "tweet_volume";
	private static final int MAX_SEARCH = 100;
	private static final int FIRST_ID = -1;
	// 180 peticiones cada 15 minutos para search
	private static final int MAX_CALLS = 12;
	// WOEID Spain
	private static final int SPAIN = 23424950;

	private Twitter twitter;

	public ConsultaAPITwitter() {
		this.twitter = TwitterFactory.getSingleton();
	}

	public void crearConsulta(String titulo, String episodeCode, String fechaInicio, String horaInicio, long duracion, String hashtag){
		ProgramaTVDAO dao = ProgramaTVImpl.getInstance();
		ProgramaTV prog = new ProgramaTV(titulo, episodeCode, fechaInicio, horaInicio, duracion, hashtag);
		dao.crearMonitorizacion(prog);
	}
	
	public void updateTweets(String titulo){
		ProgramaTVDAO dao = ProgramaTVImpl.getInstance();
		ProgramaTV prog = dao.ProgramaPorTitulo(titulo);
		List <Status> list = search(prog.getHashtag(),prog.getFechainicio().toString(), prog.getLastId());
		if (!list.isEmpty()){
			Status temp = list.get(list.size()-1);
			prog.setLastId(temp.getId());
			prog.setLastTweet(temp.getText());
			prog.setCount(prog.getCount()+list.size());
			dao.updateProgramaTV(prog);
		}
	}

	// API de busqueda en Twitter. Ultimos 4 dias
	// Permite query operators
	public List<Status> search(String text, String date, long sinceId){
		Query query = new Query(text);
		// Solo tweets recientes
		query.setResultType(Query.RECENT);

		// 100 tweets MAX_SEARCH
		query.setCount(MAX_SEARCH);

		//Fecha de inicio
		query.setSince(date);
		
		if (sinceId != FIRST_ID){
			query.sinceId(sinceId);
		}

		QueryResult result=null;
		try {
			result = twitter.search(query);
		} catch (TwitterException e) {
			System.out.println("Error en search(): "+e.getMessage());
			e.printStackTrace();
		}
		return result.getTweets();
	}
	
	// Una peticion por minuto
	public Map<String, Integer> getTrends(int place){
		Map<String, Integer> result = new HashMap<String, Integer>();
		Trends trends = null;
		try {
			trends=twitter.getPlaceTrends(place);
		} catch (TwitterException e) {
			System.out.println("Error en getTrends(): "+e.getMessage());
			e.printStackTrace();
		}
		// Get JSON
		String rawJSON = TwitterObjectFactory.getRawJSON(trends);
		JsonReader jsonReader = Json.createReader(new StringReader(rawJSON));
		JsonArray json_array = jsonReader.readArray();
		jsonReader.close();
		JsonObject json_temp =json_array.getJsonObject(0);
		json_array = json_temp.getJsonArray("trends");
		for (int i=0; i<json_array.size(); i++){
			JsonObject json = json_array.getJsonObject(i);
			if (!json.isNull(NAME_FIELD) && !json.isNull(VOLUME_FIELD)){
				System.out.println(json.getString(NAME_FIELD)+" "+json.getInt(VOLUME_FIELD));
			}
		}
		//ArrayList <String> trends_name= new ArrayList<String>();
		//ArrayList <Integer> volume = new ArrayList <Integer>();

		// Quit irrelevant trends
		for (int i=0; i<json_array.size(); i++){
			JsonObject json = json_array.getJsonObject(i);
			if (!json.isNull(NAME_FIELD) && !json.isNull(VOLUME_FIELD)){
				/*
				 * Array in order
				int index = insertOrder(volume, json.getInt(VOLUME_FIELD));
				trends_name.add(index,json.getString(NAME_FIELD));
				volume.add(index, json.getInt(VOLUME_FIELD));
				*/
				result.put(json.getString(NAME_FIELD), json.getInt(VOLUME_FIELD));
			}
		}
		return result;
	}
	public int insertOrder(ArrayList<Integer>volumes,int volume){
		for (int i = 0; i < volumes.size(); i++) {
			if (volumes.get(i) < volume) return i;        
		}
		return volumes.size();
	}
}


