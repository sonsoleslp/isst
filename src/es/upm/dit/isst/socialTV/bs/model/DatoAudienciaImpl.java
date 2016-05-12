package es.upm.dit.isst.socialTV.bs.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import es.upm.dit.isst.socialTV.bs.services.GlobalUtil;

/**	
 * Implementación de las funciones definidas en DatoAudienciaDAO
 * 
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
	public DatoAudiencia apuntaDato(Long foreignKey, Date fecha, Integer count) {
		String date = format(fecha);
		EntityManager em = EMFService.get().createEntityManager();
		DatoAudiencia dato = new DatoAudiencia(foreignKey, date, count);
		em.persist(dato);
		em.close();
		return dato;
	}
	@Override
	public List<DatoAudiencia> getAudienceForEpisodeWithId(Long foreignKey) {
		// TODO Auto-generated method stub
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("SELECT m FROM "+GlobalUtil.TABLE_DATO_AUDIENCIA+" m WHERE m.foreignKey = :foreignKey");
		q.setParameter("foreignKey", foreignKey);
		List<DatoAudiencia> datos = new ArrayList <DatoAudiencia>(q.getResultList());
		em.close();
		return orderByDate(datos);
	}
	@Override
	public void deleteDato(Long dato) {
		EntityManager em = EMFService.get().createEntityManager();
		DatoAudiencia datoaudiencia = em.find(DatoAudiencia.class, dato);
		em.remove(datoaudiencia);
		em.close();
	}
	public String format(Date date){
		DateFormat dateFormat = new SimpleDateFormat(GlobalUtil.FORMAT_DATE);
		return dateFormat.format(date);
	}
	public List<DatoAudiencia> orderByDate(List<DatoAudiencia> datos){
		List<DatoAudiencia> ordered = new ArrayList <DatoAudiencia>();
		if (datos == null || datos.isEmpty() || datos.size()==-1){
			return datos;
		}
		ordered.add(datos.get(0));
		datos.remove(0);
		SimpleDateFormat format = new SimpleDateFormat(GlobalUtil.FORMAT_DATE);
		Date meter = null;
		Date listado = null;
		boolean added = false;
		for (DatoAudiencia dato : datos){
			try {
				meter = format.parse(dato.getFecha());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			for (DatoAudiencia push : ordered){
				try {
					listado = format.parse(push.getFecha());
				} catch (ParseException e) {
					e.printStackTrace();
				}
				if (meter.after(listado)){
					ordered.add(ordered.indexOf(push),dato);
					added = true;
					break;
				}
			}
			if (!added) ordered.add(dato);
			added = false;
		}
		return ordered;
	}
	
	
	@Override
	public void deleteAudienceForEpisodeWithId(Long primaryKey){
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("DELETE FROM "+GlobalUtil.TABLE_DATO_AUDIENCIA+" e WHERE e.foreignKey = :foreignKey");
		q.setParameter("foreignKey", primaryKey);
		q.executeUpdate();
		em.close();
	}
	
	@Override
	public void deleteAll(){
		EntityManager em = EMFService.get().createEntityManager();
		em.createQuery("DELETE FROM "+GlobalUtil.TABLE_DATO_AUDIENCIA+" e").executeUpdate();
		em.close();
	}

	
}	