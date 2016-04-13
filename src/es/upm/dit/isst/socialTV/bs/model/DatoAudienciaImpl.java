package es.upm.dit.isst.socialTV.bs.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
	@Override
	public void apuntaDato(Long foreignKey, Date fecha, Integer count) {
		String date = format(fecha);
		DatoAudiencia dato = new DatoAudiencia(foreignKey, date, count);
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
	public String format(Date date){
		DateFormat dateFormat = new SimpleDateFormat(GlobalUtil.FORMAT_DATE);
		return dateFormat.format(date);
	}
}