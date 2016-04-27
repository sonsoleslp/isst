package es.upm.dit.isst.socialTV.bs.model;

import java.util.Date;
import java.util.List;

public interface DatoAudienciaDAO {
	//POST
	public DatoAudiencia apuntaDato(Long foreignKey, Date fecha, Integer count);

	//GET
	public List<DatoAudiencia> getAudienceForEpisodeWithId(Long primaryKey);

	//DELETE
	public void deleteAudienceForEpisodeWithId(Long primaryKey);
	public void deleteDato(Long dato);
	public void deleteAll();
	
}