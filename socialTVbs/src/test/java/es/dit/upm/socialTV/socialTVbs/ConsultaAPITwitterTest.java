package es.dit.upm.socialTV.socialTVbs;

import es.dit.upm.socialTV.socialTVbs.beans.Trend;
import es.dit.upm.socialTV.socialTVbs.servicesBS.ConsultaAPITwitter;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Conjunto de pruebas JUnit que prueban los metodos que consultan a la API
 * de Twitter
 */
public class ConsultaAPITwitterTest extends TestCase {
	
	/**
	 * Test estupido (aunque conviene tener un test para cada cosa)
	 */
	public void consultaMundialHashtagTest() {
		ConsultaAPITwitter consultaAPITwitter = new ConsultaAPITwitter();
		try {
			Trend trend = consultaAPITwitter.consultarHashtag("Soy un hashtag");
			assertTrue("El numero de tweets mundial deberia ser 300", trend.getNumTweets() == 300);
		} catch (Exception e) {
			// Si ocurre alguna excepcion el test no ha ido bien
			fail();
		}
	}
	
}
