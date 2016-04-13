package es.upm.dit.isst.socialTV.bs.model;
import java.util.List;


public interface DatoAudienciaDAO {
	//POST
	public void apuntaDato(DatoAudiencia dato);

	//GET
	public List<DatoAudiencia> getAudienceForEpisodeWithId(Long primaryKey);

	//DELETE
	public void deleteDato(DatoAudiencia dato);

	}
