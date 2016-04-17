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
		EntityManager em = EMFService.get().createEntityManager();
		DatoAudiencia dato = new DatoAudiencia(foreignKey, date, count);
		em.persist(dato);
		em.close();
	}
	@Override
	public List<DatoAudiencia> getAudienceForEpisodeWithId(Long foreignKey) {
		// TODO Auto-generated method stub
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("SELECT m FROM "+GlobalUtil.TABLE_DATO_AUDIENCIA+" m WHERE m.foreignKey = :foreignKey");
		q.setParameter("foreignKey", foreignKey);
		List<DatoAudiencia> datos = q.getResultList();
		em.close();
		return orderByDate(new ArrayList <DatoAudiencia>(datos));
	}
	@Override
	public void deleteDato(DatoAudiencia dato) {
		// TODO Auto-generated method stub
		EntityManager em = EMFService.get().createEntityManager();
		em.remove(dato);
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
				if (meter.before(listado)){
					ordered.add(ordered.indexOf(push),dato);
					break;
				}
			}
			ordered.add(dato);
		}
		return ordered;
	}
}