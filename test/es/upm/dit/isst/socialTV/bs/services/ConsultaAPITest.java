package es.upm.dit.isst.socialTV.bs.services;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

import es.upm.dit.isst.socialTV.bs.model.ProgramaTV;
import es.upm.dit.isst.socialTV.bs.model.ProgramaTVDAO;
import es.upm.dit.isst.socialTV.bs.model.ProgramaTVImpl;
import twitter4j.Status;

public class ConsultaAPITest {
	
	private final LocalServiceTestHelper helper =
		      new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
	ConsultaAPITwitter consulta;

	@Before
	public void setUp() throws Exception {
		helper.setUp();
		consulta = new ConsultaAPITwitter();
	}

	@After
	public void tearDown() throws Exception {
		helper.tearDown();
	}

	@Test
	public void testCrearConsulta() {
		consulta.crearConsulta("Junit", null, new Date(), new Date(), null);
		ProgramaTVDAO dao = ProgramaTVImpl.getInstance();
		List<ProgramaTV> list = dao.programasPorTitulo("Junit");
		assertNotNull(list.get(0));
	}

	@Test
	public void testSearch() {
		List <Status> list = consulta.search("SocialTVJunit", null, -1);
		assertEquals("SocialTVJunit",list.get(0).getText());
	}

}
