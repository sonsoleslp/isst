package es.upm.dit.isst.socialTV.bs.model;

import java.util.Date;
import java.util.List;

/**
 * Interfaz para interactuar con la DB de datos de audiencia
 * 
 * @author Sonsoles
 *
 */
public interface DatoAudienciaDAO {
	/**
	 * Crea una monitorización nueva
	 * @param foreignKey Programa que se está monitorizando
	 * @param hora Instante de la toma de datos
	 * @param count Número de tweets
	 * @return Objeto DatoAudiencia
	 */
	public DatoAudiencia apuntaDato(Long foreignKey, Date fecha, Integer count);

	/**
	 * Devuelve todos los datos recogidos para un determinado programa
	 * @param primaryKey Clave primaria del programa consultado
	 * @return Lista de todos los datos de audiencia para el programa especificado
	 */
	public List<DatoAudiencia> getAudienceForEpisodeWithId(Long primaryKey);

	/**
	 * Borra todos los datos de un determinado programa
	 * @param primaryKey Clave primaria del programa
	 */
	public void deleteAudienceForEpisodeWithId(Long primaryKey);
	/**
	 * Borra un dato determinado
	 * @param dato Clave primaria del dato a borrar
	 */
	public void deleteDato(Long dato);
	/**
	 * Borra todos los datos de monitorizaciones
	 */
	public void deleteAll();
	
}