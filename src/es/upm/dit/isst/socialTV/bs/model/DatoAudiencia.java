package es.upm.dit.isst.socialTV.bs.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import twitter4j.Status;

/**
 * Definición de la Tabla DatoAudiencia
 * 
 * @author SonsolesLP
 *
 */
@Entity
public class DatoAudiencia implements Serializable {

	private static final long serialVersionUID = 01L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long primaryKey;
	private Long foreignKey;
	private String fecha;
	private int count;
	
	/**
	 * Constructor DatoAudiencia
	 * @param foreignKey
	 * @param hora
	 * @param count
	 */

	public DatoAudiencia(Long foreignKey, String fecha, Integer count){
		this.fecha= fecha;
		this.foreignKey = foreignKey;
		this.count= count;
	}

	public Long getprimaryKey() {
		return primaryKey;
	}

	public void primaryKey(Long primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Long getForeignKey() {
		return foreignKey;
	}

	public void setForeignKey(Long foreignKey) {
		this.foreignKey = foreignKey;
	}

	public String getFecha() {
		return fecha;
	}

	public void setHora(String fecha) {
		this.fecha = fecha;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}