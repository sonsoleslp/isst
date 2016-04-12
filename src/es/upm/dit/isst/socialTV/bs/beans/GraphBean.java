package es.upm.dit.isst.socialTV.bs.beans;

import java.io.Serializable;
import java.util.Arrays;

@SuppressWarnings("serial") //pa que no salga un warning porculero
public class GraphBean implements Serializable {

	
	/**
	 * Horas a las que se ha medido el n�mero de tweets
	 */
	private String[] strHoras;
	/**
	 * N�meros de tweets medidos
	 */
	private String[] numTweets;
	/**
	 * M�ximo n�mero de tweets, para escalar bien la gr�fica
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

	public String[] getNumTweets() {
		return numTweets;
	}

	public void setNumTweets(int[] numTweets) {
		this.numTweets = Arrays.toString(numTweets).split("[\\[\\]]")[1].split(", ");;
	}

	public int getPtoMaximo() {
		return ptoMaximo;
	}

	public void setPtoMaximo(int ptoMaximo) {
		this.ptoMaximo = ptoMaximo;
	}
	
}
