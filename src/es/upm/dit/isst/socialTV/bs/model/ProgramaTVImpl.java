package es.upm.dit.isst.socialTV.bs.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
	public ProgramaTV crearMonitorizacion(String titulo, String episodeCode, Date fechaInicio, Date fechaFin, String hashtag) {
		String init = format(fechaInicio);
		String end = format(fechaFin);
		ProgramaTV prog = new ProgramaTV(titulo, episodeCode, init,end, hashtag);
		EntityManager em = EMFService.get().createEntityManager();
		em.persist(prog);
		em.close();
		return prog;
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
	public void deleteProgramaTV(Long prog) {

		EntityManager em = EMFService.get().createEntityManager();
		ProgramaTV programa = em.find(ProgramaTV.class, prog);
		em.remove(programa);
		em.close();
	}

	public ProgramaTV[] programasTop5() {
		EntityManager em = EMFService.get().createEntityManager();
		//Obtener los 5 resultados con mayor count
		//Query q = em.createQuery("SELECT m FROM "+GlobalUtil.TABLE_PROGRAMA_TV+" m WHERE m.rownum < 6 ORDER BY m.count DESC");
		Query q = em.createQuery("SELECT m FROM "+GlobalUtil.TABLE_PROGRAMA_TV+" m ORDER BY m.count DESC")
				.setMaxResults(GlobalUtil.NUM_PROGRAMAS_TOP);
		List<ProgramaTV> programas = q.getResultList();
		ProgramaTV[] programasArray = new ProgramaTV[GlobalUtil.NUM_PROGRAMAS_TOP];
		programas.toArray(programasArray);
		em.close();
		return programasArray;
	}
	
	public String format(Date date){
		DateFormat dateFormat = new SimpleDateFormat(GlobalUtil.FORMAT_DATE);
		return dateFormat.format(date);
	}
	@Override
	public void deleteAll(){
		EntityManager em = EMFService.get().createEntityManager();
		em.createQuery("DELETE FROM ProgramaTV e").executeUpdate();
		em.close();
	}
}