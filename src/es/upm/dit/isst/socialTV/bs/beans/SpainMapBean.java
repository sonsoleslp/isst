package es.upm.dit.isst.socialTV.bs.beans;

import java.io.Serializable;
import java.util.Arrays;

/**
 * JavaBean contenedor de la información necesaria para ver el impacto
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
	 * Números de tweets medidos en el mismo orden de las provincias
	 */
	private String[] provinceTweets;
	private String title;
	private String hashtag;
	private int max;
	private int min;
	private Long id;
	private String episodeCode;
	private String dateStart;
	private String dateEnd;


	/**
	 * Constructor
	 */
	public SpainMapBean() {}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getHashtag() {
		return hashtag;
	}

	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}
	
	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public String getEpisodeCode() {
		return episodeCode;
	}

	public void setEpisodeCode(String episodeCode) {
		this.episodeCode = episodeCode;
	}

	public String getDateStart() {
		return dateStart;
	}

	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}

	public String getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}

	public void setProvinceTweets(String[] provinceTweets) {
		this.provinceTweets = provinceTweets;
	}
	
	public String[] getProvinceName() {
		return provinceName;
	}
	
	public void setProvinceName(String[] provinceName) {
		this.provinceName = provinceName;
	}

	public String[] getProvinceTweets() {
		return provinceTweets;
	}

	public void setProvinceTweets(int[] provinceTweets) {
		this.provinceTweets = Arrays.toString(provinceTweets).split("[\\[\\]]")[1].split(", ");
	}
	
}
