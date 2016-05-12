package es.upm.dit.isst.socialTV.bs.model;


import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Manejar transacciones con la DB
 * 
 * @author Sonsoles
 *
 */
public class EMFService {
	private static final EntityManagerFactory emfInstance = Persistence
			.createEntityManagerFactory("transactions-optional");
	private EMFService() {
	}
	public static EntityManagerFactory get() {
		return emfInstance;
	}
}
