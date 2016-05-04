package es.upm.dit.isst.socialTV.bs.beans;

import java.io.Serializable;
import java.util.Arrays;
import java.util.logging.Level;

import com.sun.istack.internal.logging.Logger;

/**
 * JavaBean con las horas y número de tweets para la gráfica de evolución temporal
 * 
 * @author Paco
 *
 */
@SuppressWarnings("serial") //pa que no salga un warning porculero
public class GraphBean implements Serializable {

	private String[] strHoras;
	private String[] numTweets;
	private int ptoMaximo;
	private String title;
	private String hashtag;
	private Long id;
	private int count;
	private String episodeCode;
	private String dateStart;
	private String dateEnd;
	
	
	/**
	 * Constructor
	 */
	public GraphBean() {}
	
	public String[] getStrHoras() {
		return strHoras;
	}

	public void setStrHoras(String[] strHoras) {
		this.strHoras = strHoras;
	}

	public String[] getNumTweets() {
		return numTweets;
	}

	public void setNumTweets(int[] numTweets) {
		java.util.logging.Logger.getLogger(this.getClass().getName()).log(Level.WARNING, Arrays.toString(numTweets));
		this.numTweets = Arrays.toString(numTweets).split("[\\[\\]]")[1].split(", ");
	}

	public int getPtoMaximo() {
		return ptoMaximo;
	}

	public void setPtoMaximo(int ptoMaximo) {
		this.ptoMaximo = ptoMaximo;
	}

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
		this.dateStart = dateStart.replace('T', ' ');
	}

	public String getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd.replace('T', ' ');
	}	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setNumTweets(String[] numTweets) {
		this.numTweets = numTweets;
	}
}
