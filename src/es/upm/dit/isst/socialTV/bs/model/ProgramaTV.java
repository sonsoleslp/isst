package es.upm.dit.isst.socialTV.bs.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

import twitter4j.Status;

@Entity
public class ProgramaTV implements Serializable {

	private static final long serialVersionUID = 01L;
	
	@Id
	private String titulo;
	private String fechainicio;
	private long duracion;
	private String hashtag;
	private int count;
	private long lastId;
	private String lastTweet;
	
	public ProgramaTV(String titulo, String fechainicio, long duracion, String hashtag){
		this.titulo = titulo;
		this.fechainicio = fechainicio;
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

}