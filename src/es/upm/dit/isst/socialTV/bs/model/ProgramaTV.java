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
	private String fechainicio;
	private String horaInicio;
	private long duracion;
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
	public ProgramaTV(String titulo, String episodeCode, String fechainicio, String horaInicio, long duracion, String hashtag){
		this.titulo = titulo;
		this.episodeCode = episodeCode;
		this.fechainicio = fechainicio;
		this.horaInicio = horaInicio;
		this.duracion = duracion;
		this.hashtag = hashtag;
		this.count = 0;
		this.lastId = -1;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
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

	public long getDuracion() {
		return duracion;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setDuracion(long duracion) {
		this.duracion = duracion;
	}

	public String getFechainicio() {
		return fechainicio;
	}

	public void setFechainicio(String fechainicio) {
		this.fechainicio = fechainicio;
	}
	
	public String getHashtag(){
		return this.hashtag;
	}
	
	public void setHashtag(String hashtag){
		this.hashtag = hashtag;
	}
	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}
	public String getEpisodeCode() {
		return episodeCode;
	}

	public void setEpisodeCode(String episodeCode) {
		this.episodeCode = episodeCode;
	}


}