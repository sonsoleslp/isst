package es.upm.dit.isst.socialTV.bs.model;

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
	public ProgramaTV ProgramaPorTitulo(String titulo) {
		
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select t from ProgramaTV t where t.titulo = :titulo");
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
		Query q = em.createQuery("select m from ProgramaTV m where m.fechainicio = :fechainicio");
		q.setParameter("fechainicio", fechainicio);
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