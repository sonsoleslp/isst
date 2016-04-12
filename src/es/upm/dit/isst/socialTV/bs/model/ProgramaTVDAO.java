package es.upm.dit.isst.socialTV.bs.model;
import java.util.List;


public interface ProgramaTVDAO {
	//POST
	public void crearMonitorizacion(ProgramaTV prog);

	//GET
	public List<ProgramaTV> ProgramasPorHashtag(String hashtag);
	public ProgramaTV ProgramaPorTitulo(String titulo);
	public List<ProgramaTV> ProgramasPorFecha(String fechainicio);
	public List<ProgramaTV> todosLosProgramas();
	public ProgramaTV[] programasTop5();

	//PUT
	public void updateProgramaTV(ProgramaTV prog);

	
	//DELETE
	public void deleteProgramaTV(ProgramaTV prog);

	}
