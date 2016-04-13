package es.upm.dit.isst.socialTV.bs.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Definición de la Tabla ProgramaTV
 * 
 * @author Paco
 *
 */
@Entity
public class ProgramaTV implements Serializable {

	private static final long serialVersionUID = 01L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long primaryKey;
	private String titulo;
	private String episodeCode;
	private String fechaInicio;
	private String fechaFin;
	private String hashtag;
	private int count;
	private long lastId;
	private String lastTweet;
	
	/**
	 * Constructor
	 * @param titulo
	 * @param episodeCode
	 * @param fechainicio
	 * @param horaInicio
	 * @param duracion
	 * @param hashtag
	 */
	public ProgramaTV(String titulo, String episodeCode, String fechaInicio, String fechaFin, String hashtag){
		this.titulo = titulo;
		this.episodeCode = episodeCode;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.hashtag = hashtag;
		this.count = 0;
		this.lastId = -1;
		this.lastTweet = "";
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Long getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(Long primaryKey) {
		this.primaryKey = primaryKey;
	}

	public String getLastTweet() {
		return lastTweet;
	}

	public void setLastTweet(String lastTweet) {
		this.lastTweet = lastTweet;
	}

	public long getLastId() {
		return lastId;
	}

	public void setLastId(long lastId) {
		this.lastId = lastId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getHashtag(){
		return this.hashtag;
	}
	
	public void setHashtag(String hashtag){
		this.hashtag = hashtag;
	}
	
	public String getEpisodeCode() {
		return episodeCode;
	}

	public void setEpisodeCode(String episodeCode) {
		this.episodeCode = episodeCode;
	}


}