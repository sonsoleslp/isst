package es.upm.dit.isst.socialTV.bs.model;
import java.util.Date;
import java.util.List;
/**
 * Interfaz para interactuar con la DB programas de TV
 * @author Sonsoles
 *
 */
public interface ProgramaTVDAO {
	/**
	 * Crea una nueva monitorización
	 * @param titulo Título del programa
	 * @param episodeCode Código del Episodio (ej. T01E03)
	 * @param fechaInicio Fecha y hora de Inicio
	 * @param fechaFin Fecha y hora de Fin
	 * @param hashtag Hashtag que se monitorizará
	 * @return Objeto ProgramaTV
	 */
	public ProgramaTV crearMonitorizacion(String titulo, String episodeCode, Date fechaInicio, Date fechaFin, String hashtag);

	/**
	 * Monitorización por clave primaria
	 * @param primaryKey Clave primaria
	 * @return Objeto ProgramaTV
	 */
	public ProgramaTV programaPorId(Long primaryKey);
	
	/**
	 * Todas las monitorizaciones que llevan el mismo hashtag
	 * @param hashtag Hashtag
	 * @return Lista de objetos ProgramaTV
	 */
	public List<ProgramaTV> ProgramasPorHashtag(String hashtag);
	
	/**
	 * Lista de monitorizaciones del mismo programa
	 * @param titulo Título del programa
	 * @return Lista de objetos ProgramaTV
	 */
	public List<ProgramaTV> programasPorTitulo(String titulo);
	
	 /**
	  * Todas las monitorizaciones
	  * @return Lists de todos los objetos ProgramaTV
	  */
	public List<ProgramaTV> todosLosProgramas();
	
	/**
	 * Top 5 programas más vistos
	 * @return Array ordenado de los 5 programas más vistos
	 */
	public ProgramaTV[] programasTop5();

	/**
	 * Actualiza monitorizacion
	 * @param prog Objeto ProgramaTV
	 */
	public void updateProgramaTV(ProgramaTV prog);


	/**
	 * Borra una monitorización
	 * @param prog Clave primaria de la monitorización
	 */
	public void deleteProgramaTV(Long prog);
	
	/**
	 * Borra todos los programas
	 */
	public void deleteAll();
	

	

	}
