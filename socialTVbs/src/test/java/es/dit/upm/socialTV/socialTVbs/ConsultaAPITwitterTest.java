package es.dit.upm.socialTV.socialTVbs;

import es.dit.upm.socialTV.socialTVbs.beans.Trend;
import es.dit.upm.socialTV.socialTVbs.servicesBS.ConsultaAPITwitter;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Conjunto de pruebas JUnit que prueban los metodos que consultan a la API
 * de Twitter
 */
public class ConsultaAPITwitterTest {
	
	/**
	 * Test estupido (aunque conviene tener un test para cada cosa)
	 */
	@Test
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
