package es.upm.dit.isst.socialTV.bs.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
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
	public static long counter = 1;
	@Id
	private Long primaryKey;
	private Long foreignKey;
	private String hora;
	private int count;
	
	/**
	 * Constructor DatoAudiencia
	 * @param foreignKey
	 * @param hora
	 * @param count
	 */

	public DatoAudiencia(Long foreignKey, String hora, Integer count){
		this.primaryKey = DatoAudiencia.counter++;
		this.hora= hora;
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

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "DatosAudiencia [primaryKey=" + primaryKey + ", foreignKey=" + foreignKey + ", hora=" + hora + ", count=" + count + "]";
	}


}