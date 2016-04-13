
package es.upm.dit.isst.socialTV.bs.model;


import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMFServiceData {
	private static final EntityManagerFactory emfInstance = Persistence
			.createEntityManagerFactory("transactions-optional");
	private EMFServiceData() {
	}
	public static EntityManagerFactory get() {
		return emfInstance;
	}
}
