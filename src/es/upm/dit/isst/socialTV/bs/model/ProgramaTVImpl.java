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
	public ProgramaTV programaPorId(Long primaryKey) {
		
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("SELECT t FROM "+GlobalUtil.TABLE_PROGRAMA_TV+" t WHERE t.primaryKey = :primaryKey");
		q.setParameter("primaryKey", primaryKey);
		List<ProgramaTV> programas = q.getResultList();
		ProgramaTV prog = null;
		if (programas.size() > 0) prog = (ProgramaTV) programas.get(0);
		em.close();
		return prog;
	}
	public List<ProgramaTV> ProgramasPorHashtag(String hashtag) {
		
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("SELECT t FROM "+GlobalUtil.TABLE_PROGRAMA_TV+" t WHERE t.hashtag = :hashtag");
		q.setParameter("hashtag", hashtag);
		List<ProgramaTV> programas = q.getResultList();
		em.close();
		return programas;
	}
	public List<ProgramaTV> programasPorTitulo(String titulo) {
		
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("SELECT t FROM "+GlobalUtil.TABLE_PROGRAMA_TV+" t WHERE t.titulo = :titulo");
		q.setParameter("titulo", titulo);
		List<ProgramaTV> programas = q.getResultList();
		em.close();
		return programas;
	}
	public List<ProgramaTV> programasPorFecha(String fechainicio) {
		// TODO Auto-generated method stub
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("SELECT m FROM "+GlobalUtil.TABLE_PROGRAMA_TV+" m WHERE m.fechainicio = :fechainicio");
		q.setParameter("fechainicio", fechainicio);
		List<ProgramaTV> programas = q.getResultList();
		em.close();
		return programas;
	}
	public List<ProgramaTV> todosLosProgramas() {
		System.out.println("todosLosProgramas");
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
		/*String internalQuery = "SELECT m, rownum FROM "+GlobalUtil.TABLE_PROGRAMA_TV+" m ORDER BY m.count DESC";
		Query q = em.createQuery("SELECT * IN ("+internalQuery+") WHERE rownum < 6");*/
		//Query q = em.createQuery("SELECT m FROM "+GlobalUtil.TABLE_PROGRAMA_TV+" m WHERE m.rownum < 6 ORDER BY m.count DESC");
		Query q = em.createQuery("SELECT m FROM "+GlobalUtil.TABLE_PROGRAMA_TV+" m ORDER BY m.count").setMaxResults(5);
		List<ProgramaTV> programas = q.getResultList();
		//ProgramaTV[] programasArray = (ProgramaTV[]) programas.toArray();
		ProgramaTV[] programasArray = new ProgramaTV[programas.size()]; //debería ser 5 anyways
		programas.toArray(programasArray);
		em.close();
		return programasArray;
	}
}