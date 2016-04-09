package es.upm.dit.isst.socialTV.bs.services;

import java.util.List;

import es.upm.dit.isst.socialTV.bs.model.ProgramaTV;
import es.upm.dit.isst.socialTV.bs.model.ProgramaTVDAO;
import es.upm.dit.isst.socialTV.bs.model.ProgramaTVImpl;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.Status;

/**
 * Clase que consulta a la API de Twitter y almacena los datos en Trends
 * @author Naomi
 *
 */
public class ConsultaAPITwitter {

	private static final String NAME_FIELD = "name";
	private static final String VOLUME_FIELD = "tweet_volume";
	private static final int MAX_SEARCH = 100;
	private static final int FIRST_ID = -1;
	// 180 peticiones cada 15 minutos para search
	private static final int MAX_CALLS = 12;

	private Twitter twitter;

	public ConsultaAPITwitter() {
		this.twitter = TwitterFactory.getSingleton();
	}

	public void crearConsulta(String titulo, String fechaInicio, long duracion, String hashtag){
		ProgramaTVDAO dao = ProgramaTVImpl.getInstance();
		ProgramaTV prog = new ProgramaTV(titulo, fechaInicio, duracion, hashtag);
		dao.crearMonitorizacion(prog);
	}
	
	// Se pasa una copia de prog?
	public void updateTweets(String titulo){
		ProgramaTVDAO dao = ProgramaTVImpl.getInstance();
		ProgramaTV prog = dao.ProgramaPorTitulo(titulo);
		List <Status> list = search(prog.getHashtag(),prog.getFechainicio().toString(), prog.getLastId());
		Status temp = list.get(list.size()-1);
		prog.setLastId(list.get(list.size()-1).getId());
		prog.setCount(prog.getCount()+list.size());
		// Test
		prog.setLastTweet(temp.getText());
		dao.updateProgramaTV(prog);
	}

	// API de busqueda en Twitter. Ultimos 4 dias
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
}
