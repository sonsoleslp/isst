package es.upm.dit.isst.socialTV.bs.model;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class DatoAudienciaImplTest {
	private final LocalServiceTestHelper helper =
		      new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
	@Before
	public void setUp() throws Exception {
		helper.setUp();
	}

	@After
	public void tearDown() throws Exception {
		helper.tearDown();
	}

	@Test
	public void testApuntaDato() {
		DatoAudienciaDAO dao = DatoAudienciaImpl.getInstance();
		ProgramaTVDAO prgdao = ProgramaTVImpl.getInstance();
		Date today = new Date(2016,4,27);
		Date tomorrow = new Date(2016,4,28);
		ProgramaTV prog =  prgdao.crearMonitorizacion("titulo", "episodeCode", today, tomorrow, "hashtag");
		DatoAudiencia d1 = dao.apuntaDato(prog.getPrimaryKey(), new Date(), 7);
		assertEquals(d1.getCount(),dao.getAudienceForEpisodeWithId(prog.getPrimaryKey()).get(0).getCount());
		
	}

	@Test
	public void testGetAudienceForEpisodeWithId() {
		DatoAudienciaDAO dao = DatoAudienciaImpl.getInstance();
		ProgramaTVDAO prgdao = ProgramaTVImpl.getInstance();
		Date today = new Date(2016,4,27);
		Date tomorrow = new Date(2016,4,28);
		ProgramaTV prog =  prgdao.crearMonitorizacion("titulo", "episodeCode", today, tomorrow, "hashtag");
		DatoAudiencia d1 = dao.apuntaDato(prog.getPrimaryKey(), new Date(), 7);
		DatoAudiencia d2 = dao.apuntaDato(prog.getPrimaryKey(), new Date(), 71);
		assertEquals(d1.getCount(),dao.getAudienceForEpisodeWithId(prog.getPrimaryKey()).get(0).getCount());
		assertEquals(d2.getCount(),dao.getAudienceForEpisodeWithId(prog.getPrimaryKey()).get(1).getCount());
	}

	@Test
	public void testDeleteDato() {
		DatoAudienciaDAO dao = DatoAudienciaImpl.getInstance();
		ProgramaTVDAO prgdao = ProgramaTVImpl.getInstance();
		Date today = new Date(2016,4,27);
		Date tomorrow = new Date(2016,4,28);
		ProgramaTV prog =  prgdao.crearMonitorizacion("titulo", "episodeCode", today, tomorrow, "hashtag");
		DatoAudiencia d1 = dao.apuntaDato(prog.getPrimaryKey(), new Date(), 7);
		dao.deleteDato(d1.getprimaryKey());
		assertEquals(0,dao.getAudienceForEpisodeWithId(prog.getPrimaryKey()).size()   );
	}



	@Test
	public void deleteAudienceForEpisodeWithId() {
		DatoAudienciaDAO dao = DatoAudienciaImpl.getInstance();
		ProgramaTVDAO prgdao = ProgramaTVImpl.getInstance();
		Date today = new Date(2016,4,27);
		Date tomorrow = new Date(2016,4,28);
		Date yesterday = new Date(2016,4,26);
		
		ProgramaTV prog =  prgdao.crearMonitorizacion("titulo", "episodeCode", today, tomorrow, "hashtag");
		ProgramaTV prog2 =  prgdao.crearMonitorizacion("titulo", "episodeCode", today, tomorrow, "hashtag");
		DatoAudiencia d1 = dao.apuntaDato(prog.getPrimaryKey(), yesterday, 17);
		DatoAudiencia d2 = dao.apuntaDato(prog.getPrimaryKey(), tomorrow, 27);
		DatoAudiencia d3 = dao.apuntaDato(prog.getPrimaryKey(), today, 27);
		DatoAudiencia d4 = dao.apuntaDato(prog2.getPrimaryKey(), yesterday, 17);
		DatoAudiencia d5 = dao.apuntaDato(prog2.getPrimaryKey(), tomorrow, 27);
		DatoAudiencia d6 = dao.apuntaDato(prog2.getPrimaryKey(), today, 27);

		assertEquals(3,dao.getAudienceForEpisodeWithId(prog.getPrimaryKey()).size());

		dao.deleteAudienceForEpisodeWithId(prog.getPrimaryKey());
		assertEquals(0,dao.getAudienceForEpisodeWithId(prog.getPrimaryKey()).size());
		assertEquals(3,dao.getAudienceForEpisodeWithId(prog2.getPrimaryKey()).size());

		
	}
	
	@Test
	public void testDeleteAll() {
		DatoAudienciaDAO dao = DatoAudienciaImpl.getInstance();
		ProgramaTVDAO prgdao = ProgramaTVImpl.getInstance();
		Date today = new Date(2016,4,27);
		Date tomorrow = new Date(2016,4,28);
		Date yesterday = new Date(2016,4,26);
		
		ProgramaTV prog =  prgdao.crearMonitorizacion("titulo", "episodeCode", today, tomorrow, "hashtag");
		ProgramaTV prog2 =  prgdao.crearMonitorizacion("titulo", "episodeCode", today, tomorrow, "hashtag");
		DatoAudiencia d1 = dao.apuntaDato(prog.getPrimaryKey(), yesterday, 17);
		DatoAudiencia d2 = dao.apuntaDato(prog.getPrimaryKey(), tomorrow, 27);
		DatoAudiencia d3 = dao.apuntaDato(prog.getPrimaryKey(), today, 27);
		DatoAudiencia d4 = dao.apuntaDato(prog2.getPrimaryKey(), yesterday, 17);
		DatoAudiencia d5 = dao.apuntaDato(prog2.getPrimaryKey(), tomorrow, 27);
		DatoAudiencia d6 = dao.apuntaDato(prog2.getPrimaryKey(), today, 27);

		assertEquals(3,dao.getAudienceForEpisodeWithId(prog.getPrimaryKey()).size());
		assertEquals(3,dao.getAudienceForEpisodeWithId(prog2.getPrimaryKey()).size());

		dao.deleteAll();
		assertEquals(0,dao.getAudienceForEpisodeWithId(prog.getPrimaryKey()).size());
		assertEquals(0,dao.getAudienceForEpisodeWithId(prog2.getPrimaryKey()).size());

	}

}
