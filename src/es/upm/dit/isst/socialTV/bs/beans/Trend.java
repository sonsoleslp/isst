package es.upm.dit.isst.socialTV.bs.beans;

import java.io.Serializable;
import java.util.ArrayList;

import es.upm.dit.isst.socialTV.bs.model.DatoAudiencia;
import es.upm.dit.isst.socialTV.bs.model.DatoAudienciaDAO;
import es.upm.dit.isst.socialTV.bs.model.DatoAudienciaImpl;
import es.upm.dit.isst.socialTV.bs.model.ProgramaTV;
import es.upm.dit.isst.socialTV.bs.model.ProgramaTVDAO;
import es.upm.dit.isst.socialTV.bs.model.ProgramaTVImpl;

/**
 * Bean empleado para recoger los datos de la API de twitter
 * 
 * @author Naomi
 *
 */
public class Trend implements Serializable {
	
	// Numero de tweets de dicho trend
	private int numTweets;
	// Nombre del hashtag asociado
	private String hashtag;
	private Long programaId;
	// TODO: Añadir lo que querais necesiteis
	
	public int getNumTweets() {
		DatoAudienciaDAO dao = DatoAudienciaImpl.getInstance();
		//Devuelve uuna lista con todas las entradas recogidas
		dao.getAudienceForEpisodeWithId(programaId);
		
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
