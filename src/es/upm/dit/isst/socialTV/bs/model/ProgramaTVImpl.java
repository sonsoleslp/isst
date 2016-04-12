package es.upm.dit.isst.socialTV.bs.model;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import es.upm.dit.isst.socialTV.bs.services.GlobalUtil;

public class ProgramaTVImpl implements ProgramaTVDAO {
	private static ProgramaTVImpl instance;
	private ProgramaTVImpl () {
	}
	public static ProgramaTVImpl getInstance() {
		if (instance == null)
			instance = new ProgramaTVImpl();
		return instance;
	}
	public void crearMonitorizacion(ProgramaTV prog) {
		
		EntityManager em = EMFService.get().createEntityManager();
		em.persist(prog);
		em.close();
	}
	
	public List<ProgramaTV> ProgramasPorHashtag(String hashtag) {
		
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("SELECT t FROM "+GlobalUtil.TABLE_PROGRAMA_TV+" t WHERE t.hashtag = :hashtag");
		q.setParameter("hashtag", hashtag);
		List<ProgramaTV> programas = q.getResultList();
		em.close();
		return programas;
	}
	public ProgramaTV ProgramaPorTitulo(String titulo) {
		
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("SELECT t FROM "+GlobalUtil.TABLE_PROGRAMA_TV+" t WHERE t.titulo = :titulo");
		q.setParameter("titulo", titulo);
		List<ProgramaTV> programas = q.getResultList();
		ProgramaTV prog = null;
		if (programas.size() > 0) prog = (ProgramaTV) programas.get(0);
		em.close();
		return prog;
	}
	public List<ProgramaTV> ProgramasPorFecha(String fechainicio) {
		// TODO Auto-generated method stub
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("SELECT m FROM "+GlobalUtil.TABLE_PROGRAMA_TV+" m WHERE m.fechainicio = :fechainicio");
		q.setParameter("fechainicio", fechainicio);
		List<ProgramaTV> programas = q.getResultList();
		em.close();
		return programas;
	}
	public List<ProgramaTV> todosLosProgramas() {
		
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("SELECT m FROM "+GlobalUtil.TABLE_PROGRAMA_TV+" m");
		List<ProgramaTV> programas = q.getResultList();
		em.close();
		return programas;
	}
	public void updateProgramaTV(ProgramaTV prog) {
		
		EntityManager em = EMFService.get().createEntityManager();
		em.merge(prog);
		em.close();
	}
	public void deleteProgramaTV(ProgramaTV prog) {
		
		EntityManager em = EMFService.get().createEntityManager();
		em.remove(prog);
		em.close();
	}
	
	public ProgramaTV[] programasTop5() {
		EntityManager em = EMFService.get().createEntityManager();
		//Obtener los 5 resultados con mayor count
		Query q = em.createQuery("SELECT m FROM "+GlobalUtil.TABLE_PROGRAMA_TV+" m WHERE ROWNUM < 6 ORDER BY m.count DESC");
		List<ProgramaTV> programas = q.getResultList();
		ProgramaTV[] programasArray = (ProgramaTV[]) programas.toArray();
		em.close();
		return programasArray;
	}
}