package es.upm.dit.isst.socialTV.bs.model;
import java.util.Date;
import java.util.List;


public interface ProgramaTVDAO {
	//POST
	public void crearMonitorizacion(String titulo, String episodeCode, Date fechaInicio, Date fechaFin, String hashtag);

	//GET
	public ProgramaTV programaPorId(Long primaryKey);
	public List<ProgramaTV> ProgramasPorHashtag(String hashtag);
	public List<ProgramaTV> programasPorTitulo(String titulo);
	public List<ProgramaTV> todosLosProgramas();
	public ProgramaTV[] programasTop5();
	

	//PUT
	public void updateProgramaTV(ProgramaTV prog);

	
	//DELETE
	public void deleteProgramaTV(ProgramaTV prog);
	public void deleteAll();
	

	

	}
