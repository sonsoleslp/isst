package es.upm.dit.isst.socialTV.bs.beans;

import java.io.Serializable;
import java.util.Arrays;

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
		this.numTweets = Arrays.toString(numTweets).split("[\\[\\]]")[1].split(", ");
	}

	public int getPtoMaximo() {
		return ptoMaximo;
	}

	public void setPtoMaximo(int ptoMaximo) {
		this.ptoMaximo = ptoMaximo;
	}
	
}
