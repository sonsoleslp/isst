package es.dit.upm.socialTV.socialTVbs.programatv;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ProgramaTV implements Serializable {

	private static final long serialVersionUID = 01L;
	
	@Id
	private String titulo;
	private Date fechainicio;
	private Date fechafin;
	private String hashtag;
	private String monitorizacion;
	
	public ProgramaTV(String titulo, Date fechainicio, Date fechafin, String hashtag){
		super();
		this.titulo = titulo;
		this.fechainicio = fechainicio;
		this.fechafin = fechafin;
		this.hashtag = hashtag;
	}

	@Override
	public String toString() {
		return "ProgramaTV [titulo=" + titulo + ", fechainicio=" + fechainicio + ", fechafin=" + fechafin + ", hashtag="
				+ hashtag + ", monitorizacion=" + monitorizacion + "]";
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Date getFechainicio() {
		return fechainicio;
	}

	public void setFechainicio(Date fechainicio) {
		this.fechainicio = fechainicio;
	}

	public Date getFechafin() {
		return fechafin;
	}

	public void setFechafin(Date fechafin) {
		this.fechafin = fechafin;
	}

	public String getHashtag() {
		return hashtag;
	}

	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}

	public String getMonitorizacion() {
		return monitorizacion;
	}

	public void setMonitorizacion(String monitorizacion) {
		this.monitorizacion = monitorizacion;
	}

	

}