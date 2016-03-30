package es.dit.upm.socialTV.socialTVbs.programatv;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;


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
		Query q = em.createQuery("select t from ProgramaTV t where t.hashtag = :hashtag");
		q.setParameter("hashtag", hashtag);
		List<ProgramaTV> programas = q.getResultList();
		em.close();
		return programas;
	}
	public List<ProgramaTV> ProgramasPorNombre(String titulo) {
		
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select t from ProgramaTV t where t.titulo = :titulo");
		q.setParameter("titulo", titulo);
		List<ProgramaTV> programas = q.getResultList();
		em.close();
		return programas;
	}
	public List<ProgramaTV> ProgramasPorFecha(Date fechainicio, Date fechafin) {
		// TODO Auto-generated method stub
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select m from ProgramaTV m where and m.fechainicio = :fechainicio and m.fechafin = :fechafin");
		q.setParameter("fechainicio", fechainicio);
		q.setParameter("fechafin", fechafin);
		List<ProgramaTV> programas = q.getResultList();
		em.close();
		return programas;
	}
	public List<ProgramaTV> todosLosProgramas() {
		
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select m from ProgramaTV m");
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
}