package es.upm.dit.isst.socialTV.web.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.upm.dit.isst.socialTV.bs.beans.Top5Bean;
import es.upm.dit.isst.socialTV.bs.model.ProgramaTV;
import es.upm.dit.isst.socialTV.bs.model.ProgramaTVImpl;

import es.upm.dit.isst.socialTV.bs.services.GlobalUtil;


/**
 * Servlet implementation class MapaServlet
 */
public class Top5Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		
		// DATOS DE PRUEBA
		/*Top5Bean[] programas = new Top5Bean[GlobalUtil.NUM_PROGRAMAS_TOP];
		programas[0] = new Top5Bean("Modern Family", "T9E12", "#MF16", "16/02/2016 | 19:00-20:00", 3453453);
		programas[1] = new Top5Bean("Modern Family", "T9E11", "#MF16", "23/02/2016 | 19:00-20:00", 1453453);
		programas[2] = new Top5Bean("Friends", "T5E6", "#RossAndRachel", "03/02/2016 | 15:00-16:00", 353453);
		programas[3] = new Top5Bean("Game Of Thrones", "T3E4", "#GoT", "13/01/2016 | 21:00-22:00", 53453);
		programas[4] = new Top5Bean("The Big Bang Theory", "T8E8", "#Shamy", "15/01/2016 | 19:00-19:30", 5353);
		session.setAttribute(GlobalUtil.TOP_5_BEAN, programas);*/
		
		//DATOS SERIOS PARA PROBAR
		Top5Bean[] progsBean = new Top5Bean[GlobalUtil.NUM_PROGRAMAS_TOP];
		ProgramaTV[] progs = new ProgramaTV[GlobalUtil.NUM_PROGRAMAS_TOP];
		progs = ProgramaTVImpl.getInstance().programasTop5();
		progsBean = obtenerTop5Beans(progs);

		session.setAttribute(GlobalUtil.TOP_5_BEAN, progsBean);
		
		// Devolvemos la vista del Top 5
		 RequestDispatcher rd = request.getRequestDispatcher("views/top5.jsp");
         rd.forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * Método auxiliar que parsea los objetos obtenidos de bases de datos a un javabean
	 * 
	 * @param programas ProgramaTV[] 
	 * @return top5Bean Top5Bean[]
	 */
	private Top5Bean[] obtenerTop5Beans(ProgramaTV[] programas) {
		
		Top5Bean[] top5Bean = new Top5Bean[GlobalUtil.NUM_PROGRAMAS_TOP];
		
		for (int i = 0; i < GlobalUtil.NUM_PROGRAMAS_TOP; i++) {
			if (null != programas[i]) {
				top5Bean[i] = new Top5Bean(
						programas[i].getTitulo(),
						programas[i].getEpisodeCode(), 
						programas[i].getHashtag(), 
						//programas[i].getFechaInicio(), //TODO: //esto hay que construirlo en algun lado
						construirStringEmision(programas[i].getFechaInicio(), programas[i].getFechaFin()),
						programas[i].getCount());
			}
		}
		
		return top5Bean;
	}
	
	/**
	 * Método auxiliar que devuelve la emisión de un programa en un String
	 * 
	 * @param fechaInicio
	 * @param fechaFin
	 * @return concatenación emisión
	 */
	private String construirStringEmision(String fechaInicio, String fechaFin) {
		//Obtener XX/XX/XXXX en string de fechaInicio
		String strFechaInicio = fechaInicio
				.substring(0, fechaInicio.indexOf("T"))
				.replace('-', '/');
		//Obtener hora de fechaInicio
		String strHoraInicio = fechaInicio
				.substring(fechaInicio.indexOf("T") + 1, fechaInicio.length());
		//Obtener hora de fechaFin
		String strHoraFin = fechaFin
				.substring(fechaFin.indexOf("T") + 1, fechaFin.length());
		
		return strFechaInicio + " | " + strHoraInicio + "-" + strHoraFin;
	}
}
