package es.upm.dit.isst.socialTV.bs.model;

import java.util.Date;
import java.util.List;

public interface DatoAudienciaDAO {
	//POST
	public void apuntaDato(Long foreignKey, Date fecha, Integer count);

	//GET
	public List<DatoAudiencia> getAudienceForEpisodeWithId(Long primaryKey);

	//DELETE
	public void deleteDato(DatoAudiencia dato);

	//MAX
	public int getMaxValueForEpisodeWithId(Long primaryKey);
}