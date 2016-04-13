package es.upm.dit.isst.socialTV.bs.beans;

import java.io.Serializable;

/**
 * JavaBean contenedor de la informaci�n necesaria para ver el impacto
 * en Twitter por provincias
 * 
 * @author Paco
 *
 */
@SuppressWarnings("serial")
public class SpainMapBean implements Serializable {

	
	/**
	 * Nombre de la provincia medida
	 */
	private String[] provinceName;

	/**
	 * N�meros de tweets medidos
	 */
	private int[] provinceTweets;


	/**
	 * Constructor
	 */
	public SpainMapBean() {}
	
	public String[] getProvinceName() {
		return provinceName;
	}


	public void setProvinceName(String[] provinceName) {
		this.provinceName = provinceName;
	}


	public int[] getProvinceTweets() {
		return provinceTweets;
	}


	public void setProvinceTweets(int[] provinceTweets) {
		this.provinceTweets = provinceTweets;
	}



	
}
