package es.upm.dit.isst.socialTV.bs.services;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * Clase de Prueba de Spain.java
 * @author Sonsoles
 *
 */
public class SpainTest {

	@Before
	public void setUp() throws Exception {
		Spain spain = Spain.getInstance();
	}

	@After
	public void tearDown() throws Exception {
	
	}



	@Test
	public void testWhichProvince() {
		Spain spain = Spain.getInstance();
		assertEquals("Alava", spain.whichProvince( -3 ,43.03));
		assertEquals("Albacete", spain.whichProvince( -1.52 ,39));
		assertEquals("Alicante", spain.whichProvince( -0.703482,38.510440));
		assertEquals("Almería", spain.whichProvince( -2.445761, 37.083216));
		assertEquals("Asturias", spain.whichProvince(  -5.850804, 43.360314));
		assertEquals("Avila", spain.whichProvince(-4.698547, 40.661640));
		assertEquals("Badajoz", spain.whichProvince( -6.973072, 38.878466));
		assertEquals("Illes Balears", spain.whichProvince( 2.652467, 39.581091));
		assertEquals("Barcelona", spain.whichProvince( 2.160097, 41.396593));
		assertEquals("Burgos", spain.whichProvince( -3.698727, 42.342286));
		assertEquals("Caceres", spain.whichProvince(-6.371054, 39.455542));
		assertEquals("Cadiz", spain.whichProvince( -6.198246, 36.578779));
		assertEquals("Santa Cruz De Tenerife", spain.whichProvince( -16.633230, 28.265266));
		assertEquals("Las Palmas", spain.whichProvince( -15.625434, 27.971956));
		assertEquals("Castellon", spain.whichProvince(-0.051067, 39.984237));
		assertEquals("Ciudad Real", spain.whichProvince( -3.927862, 38.993443));
		assertEquals("Cordoba", spain.whichProvince( -4.735694, 37.876538));
		assertEquals("A Coruña", spain.whichProvince( -8.416203, 43.357194));
		assertEquals("Cuenca", spain.whichProvince(-2.143133, 40.070052));
		assertEquals("Girona", spain.whichProvince( 2.801691, 42.254951));
		assertEquals("Granada", spain.whichProvince( -3.604404, 37.170893));
		assertEquals("Guadalajara", spain.whichProvince(-3.144681, 40.656942));
		assertEquals("Huelva", spain.whichProvince(  -6.937716, 37.269160));
		assertEquals("Huesca", spain.whichProvince( -0.144602, 42.187048));
		assertEquals("Jaen", spain.whichProvince(  -3.767713, 37.795808));
		assertEquals("Leon", spain.whichProvince( -5.581245, 42.595784));
		assertEquals("La Rioja", spain.whichProvince(-2.452841, 42.464954));
		assertEquals("Lugo", spain.whichProvince(-7.551264, 43.017744));
		assertEquals("Lleida", spain.whichProvince(  0.626200, 41.625336));
		assertEquals("Madrid", spain.whichProvince(-3.689767, 40.459187));
		assertEquals("Malaga", spain.whichProvince( -4.442385, 36.771708));
		assertEquals("Murcia", spain.whichProvince( -1.131553, 37.988447));
		assertEquals("Navarra", spain.whichProvince( -1.625749, 42.816165));
		assertEquals("Ourense", spain.whichProvince( -7.108838, 42.180845));
		assertEquals("Palencia", spain.whichProvince(  -4.530473, 42.011431));
		assertEquals("Pontevedra", spain.whichProvince( -8.730964, 42.225136));
		assertEquals("Salamanca", spain.whichProvince( -5.660543, 40.969207));
		assertEquals("Cantabria", spain.whichProvince( -4.622453, 43.156419));
		assertEquals("Segovia", spain.whichProvince(  -4.109713, 40.940018));
		assertEquals("Sevilla", spain.whichProvince(-5.954248, 37.344974));
		assertEquals("Soria", spain.whichProvince(-2.473163, 41.768050));
		assertEquals("Tarragona", spain.whichProvince( 1.244324, 41.125740));
		assertEquals("Teruel", spain.whichProvince(  -1.106887, 40.341281));
		assertEquals("Toledo", spain.whichProvince(  -4.029814, 39.862078));
		assertEquals("Valencia", spain.whichProvince( -0.394127, 39.462144));
		assertEquals("Valladolid", spain.whichProvince( -4.740535, 41.649813));
		assertEquals("Vizcaya", spain.whichProvince( -2.918636, 43.258520));
		assertEquals("Zamora", spain.whichProvince(-5.747197, 41.505417));
		assertEquals("Zaragoza", spain.whichProvince( -0.886547, 41.678755));
		assertEquals("Ceuta", spain.whichProvince(-5.321283, 35.889508));
		assertEquals("Melilla", spain.whichProvince( -2.946501, 35.291665));
	}

}
