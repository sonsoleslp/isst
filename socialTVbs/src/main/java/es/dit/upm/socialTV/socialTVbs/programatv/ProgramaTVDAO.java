package es.dit.upm.socialTV.socialTVbs.programatv;
import java.util.Date;
import java.util.List;


public interface ProgramaTVDAO {
	//POST
	public void crearMonitorizacion(ProgramaTV prog);

	//GET
	public List<ProgramaTV> ProgramasPorHashtag(String hashtag);
	public List<ProgramaTV> ProgramasPorNombre(String titulo);
	public List<ProgramaTV> ProgramasPorFecha(Date fechainicio, Date fechafin);
	public List<ProgramaTV> todosLosProgramas();

	//PUT
	public void updateProgramaTV(ProgramaTV prog);

	
	//DELETE
	public void deleteProgramaTV(ProgramaTV prog);
	


		
	}
