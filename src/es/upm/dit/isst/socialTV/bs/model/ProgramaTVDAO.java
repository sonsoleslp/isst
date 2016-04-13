package es.upm.dit.isst.socialTV.bs.model;
import java.util.List;


public interface ProgramaTVDAO {
	//POST
	public void crearMonitorizacion(ProgramaTV prog);

	//GET
	public ProgramaTV programaPorId(Long primaryKey);
	public List<ProgramaTV> ProgramasPorHashtag(String hashtag);
	public List<ProgramaTV> programasPorTitulo(String titulo);
	public List<ProgramaTV> programasPorFecha(String fechainicio);
	public ProgramaTV[] programasTop5();

	//PUT
	public void updateProgramaTV(ProgramaTV prog);

	
	//DELETE
	public void deleteProgramaTV(ProgramaTV prog);

	public List<ProgramaTV> todosLosProgramas();

	}
