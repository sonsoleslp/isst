package es.upm.dit.isst.socialTV.bs.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import es.upm.dit.isst.socialTV.bs.services.GlobalUtil;
/**	
 * Implementación de DatoAudienciaDAO
 * @author Sonsoles
 *
 */
public class DatoAudienciaImpl implements DatoAudienciaDAO {
	private static DatoAudienciaImpl instance;
	private DatoAudienciaImpl () {
	}
	public static DatoAudienciaImpl getInstance() {
		if (instance == null)
			instance = new DatoAudienciaImpl();
		return instance;
	}
	//Esto no sé si va con Override
	@Override
	public void apuntaDato(DatoAudiencia dato) {
		// TODO Auto-generated method stub
		EntityManager em = EMFServiceData.get().createEntityManager();
		em.persist(dato);
		em.close();
	}
	@Override
	public List<DatoAudiencia> getAudienceForEpisodeWithId(Long foreignKey) {
		// TODO Auto-generated method stub
		EntityManager em = EMFServiceData.get().createEntityManager();
		Query q = em.createQuery("SELECT m FROM "+GlobalUtil.TABLE_DATO_AUDIENCIA+" m WHERE m.foreignKey = :foreignKey");
		q.setParameter("foreignKey", foreignKey);
		List<DatoAudiencia> datos = q.getResultList();
		em.close();
		return datos;
	}
	@Override
	public void deleteDato(DatoAudiencia dato) {
		// TODO Auto-generated method stub
		EntityManager em = EMFServiceData.get().createEntityManager();
		em.remove(dato);
		em.close();
		
	}
}