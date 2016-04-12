package es.upm.dit.isst.socialTV.bs.beans;

import java.io.Serializable;

@SuppressWarnings("serial") //pa que no salga un warning porculero
public class GraphBean implements Serializable {

	
	/**
	 * Horas a las que se ha medido el número de tweets
	 */
	private String[] strHoras;
	/**
	 * Números de tweets medidos
	 */
	private int[] numTweets;
	/**
	 * Máximo número de tweets, para escalar bien la gráfica
	 */
	private int ptoMaximo;
	
	/*
	public GraphBean(String[] strHoras, int[] numTweets, int ptoMaximo) {
		super();
		this.strHoras = strHoras;
		this.numTweets = numTweets;
		this.ptoMaximo = ptoMaximo;
	}*/
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

	public int[] getNumTweets() {
		return numTweets;
	}

	public void setNumTweets(int[] numTweets) {
		this.numTweets = numTweets;
	}

	public int getPtoMaximo() {
		return ptoMaximo;
	}

	public void setPtoMaximo(int ptoMaximo) {
		this.ptoMaximo = ptoMaximo;
	}
	
}
