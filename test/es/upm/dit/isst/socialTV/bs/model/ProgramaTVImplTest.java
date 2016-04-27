package es.upm.dit.isst.socialTV.bs.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

import es.upm.dit.isst.socialTV.bs.services.GlobalUtil;

public class ProgramaTVImplTest {

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
	public void testCrearMonitorizacion() {
		ProgramaTVDAO dao = ProgramaTVImpl.getInstance();
		Date fecha = new Date();
		ProgramaTV prog = dao.crearMonitorizacion("titulo", "episodeCode", fecha, fecha, "hashtag");
		assertEquals(prog.getCount(),0);
		assertEquals(prog.getTitulo(),"titulo");
		assertEquals(prog.getEpisodeCode(),"episodeCode");
		assertEquals(prog.getHashtag(),"hashtag");
		for(int i = 0; i < GlobalUtil.SPAIN_PROVINCES_ARRAY.length; i++){
			assertEquals(0,prog.getProvince(GlobalUtil.SPAIN_PROVINCES_ARRAY[i]));
		}

		
	}

	@Test
	public void testProgramaPorId() {
		ProgramaTVDAO dao = ProgramaTVImpl.getInstance();
		Date fecha = new Date();
		ProgramaTV prog = dao.crearMonitorizacion("titulo", "episodeCode", fecha, fecha, "hashtag");
		assertEquals(prog.toString(),dao.programaPorId(prog.getPrimaryKey()).toString());
	}

	@Test
	public void testProgramasPorHashtag() {
		ProgramaTVDAO dao = ProgramaTVImpl.getInstance();
		Date fecha = new Date();
		ProgramaTV prog = dao.crearMonitorizacion("titulo", "episodeCode", fecha, fecha, "hashtag");
		assertEquals(prog.toString(),dao.ProgramasPorHashtag("hashtag").get(0).toString());
	}

	@Test
	public void testProgramasPorTitulo() {
		ProgramaTVDAO dao = ProgramaTVImpl.getInstance();
		Date fecha = new Date();
		ProgramaTV prog = dao.crearMonitorizacion("titulo", "episodeCode", fecha, fecha, "hashtag");
		assertEquals(prog.toString(),dao.programasPorTitulo("titulo").get(0).toString());

	}

	@Test
	public void testTodosLosProgramas() {
		ProgramaTVDAO dao = ProgramaTVImpl.getInstance();
		Date fecha = new Date();
		ProgramaTV prog1 = dao.crearMonitorizacion("titulo", "episodeCode", fecha, fecha, "hashtag");
		ProgramaTV prog2 = dao.crearMonitorizacion("titulo2", "episodeCode", fecha, fecha, "hashtag2");
		assertEquals(prog1.toString(), dao.todosLosProgramas().get(0).toString());
		assertEquals(prog2.toString(), dao.todosLosProgramas().get(1).toString());

	}

	@Test
	public void testUpdateProgramaTV() {
		ProgramaTVDAO dao = ProgramaTVImpl.getInstance();
		Date fecha = new Date();
		ProgramaTV prog = dao.crearMonitorizacion("titulo", "episodeCode", fecha, fecha, "hashtag");
		prog.setCount(30);
		for(int i = 0; i < GlobalUtil.SPAIN_PROVINCES_ARRAY.length; i++){
			prog.setProvince(GlobalUtil.SPAIN_PROVINCES_ARRAY[i]);
		}
		dao.updateProgramaTV(prog);

		assertEquals(30,prog.getCount());
		ProgramaTV progactual = dao.programaPorId(prog.getPrimaryKey());
		for(int i = 0; i < GlobalUtil.SPAIN_PROVINCES_ARRAY.length; i++){
			assertEquals(1,progactual.getProvince(GlobalUtil.SPAIN_PROVINCES_ARRAY[i]));
			
		}

	}

	@Test
	public void testDeleteProgramaTV() {
		ProgramaTVDAO dao = ProgramaTVImpl.getInstance();
		Date fecha = new Date();
		ProgramaTV prog = dao.crearMonitorizacion("titulo", "episodeCode", fecha, fecha, "hashtag");
		dao.deleteProgramaTV(prog.getPrimaryKey());
		assertEquals(0,dao.todosLosProgramas().size());
		
	}

	@Test
	public void testProgramasTop5() {
		ProgramaTVDAO dao = ProgramaTVImpl.getInstance();
		Date fecha = new Date();
		ProgramaTV prog1 = dao.crearMonitorizacion("titulo", "episodeCode", fecha, fecha, "hashtag");
		prog1.setCount(5);
		dao.updateProgramaTV(prog1);
		
		ProgramaTV prog2 = dao.crearMonitorizacion("titulo", "episodeCode", fecha, fecha, "hashtag");
		prog2.setCount(15);
		dao.updateProgramaTV(prog2);
		
		ProgramaTV prog3 = dao.crearMonitorizacion("titulo", "episodeCode", fecha, fecha, "hashtag");
		prog3.setCount(10);
		dao.updateProgramaTV(prog3);
		
		ProgramaTV prog4 = dao.crearMonitorizacion("titulo", "episodeCode", fecha, fecha, "hashtag");
		prog4.setCount(45);
		dao.updateProgramaTV(prog4);
		
		ProgramaTV prog5 = dao.crearMonitorizacion("titulo", "episodeCode", fecha, fecha, "hashtag");
		prog5.setCount(65);
		dao.updateProgramaTV(prog5);
		
		ProgramaTV prog6 = dao.crearMonitorizacion("titulo", "episodeCode", fecha, fecha, "hashtag");
		prog6.setCount(20);
		dao.updateProgramaTV(prog6);
		
		ProgramaTV[] lista = dao.programasTop5();
		
		assertEquals(prog5.toString(),lista[0].toString());
		assertEquals(prog4.toString(),lista[1].toString());
		assertEquals(prog6.toString(),lista[2].toString());
		assertEquals(prog2.toString(),lista[3].toString());
		assertEquals(prog3.toString(),lista[4].toString());
	}


	@Test
	public void testDeleteAll() {
		ProgramaTVDAO dao = ProgramaTVImpl.getInstance();
		Date fecha = new Date();
		ProgramaTV prog1 = dao.crearMonitorizacion("titulo", "episodeCode", fecha, fecha, "hashtag");
		ProgramaTV prog2 = dao.crearMonitorizacion("titulo", "episodeCode", fecha, fecha, "hashtag");
		ProgramaTV prog3 = dao.crearMonitorizacion("titulo", "episodeCode", fecha, fecha, "hashtag");
		ProgramaTV prog4 = dao.crearMonitorizacion("titulo", "episodeCode", fecha, fecha, "hashtag");
		dao.deleteAll();
		assertEquals(0,dao.todosLosProgramas().size());
	}

}
