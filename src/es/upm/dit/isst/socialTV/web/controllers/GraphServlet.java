package es.upm.dit.isst.socialTV.web.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.upm.dit.isst.socialTV.bs.beans.GraphBean;
import es.upm.dit.isst.socialTV.bs.model.DatoAudiencia;
import es.upm.dit.isst.socialTV.bs.model.DatoAudienciaDAO;
import es.upm.dit.isst.socialTV.bs.model.DatoAudienciaImpl;
import es.upm.dit.isst.socialTV.bs.model.ProgramaTVDAO;
import es.upm.dit.isst.socialTV.bs.model.ProgramaTVImpl;
import es.upm.dit.isst.socialTV.bs.services.GlobalUtil;

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Servlet implementation class MapaServlet
 */
public class GraphServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//TODO: Obtener el titulo del programa que se ha seleccionado...
		String tituloPrograma = "Modern Family";
			//el jsp debería tener el parámetro "título" o algo en el request
		Logger.getLogger(this.getClass().getName()).log(Level.WARNING, request.getRequestURL().toString());
		System.out.println(request.getRequestURL().toString());
		HttpSession session = request.getSession();
		String[] params = request.getRequestURL().toString().split("/"); 
		Long num = (long) 0;
		try {
			System.out.println(request.getRequestURL().toString());
			System.out.println(num);
			Logger.getLogger(this.getClass().getName()).log(Level.WARNING, num.toString());

			System.out.println(params.toString());
			num = Long.parseLong(params[params.length-1]);
			Logger.getLogger(this.getClass().getName()).log(Level.WARNING, num.toString());

		} catch(Exception e){
//			response.sendRedirect("/");
			return;
		}
		
		
		//TODO: Consulta SQL para obtener los tweets cada 5 min, y las horas dependiendo de cuando ha sido su emisión
			//Obtenemos el Bean a pasarle a la gráfica, que será un graphBean
			DatoAudienciaDAO dao = DatoAudienciaImpl.getInstance();
			List<DatoAudiencia> list = dao.getAudienceForEpisodeWithId(num);
			
			String[] horas = new String[list.size()];
			int[] numTweets = new int[list.size()];
			Logger.getLogger(this.getClass().getName()).log(Level.WARNING,numTweets.toString());

			int index = 0;
			for(DatoAudiencia dato : list){
				String date = dato.getFecha();
				System.out.println(date);
				horas[index] =  date.length() > 5? date.substring(date.length() - 5) : date;
				int cuenta = dato.getCount();
				
				numTweets[index]= cuenta;
				
				System.out.println(cuenta);
				Logger.getLogger(this.getClass().getName()).log(Level.WARNING, ""+cuenta);
				Logger.getLogger(this.getClass().getName()).log(Level.WARNING,dato.toString());

				index++;
				System.out.println(dato);
			}
			String[] horas3 = {"20:00","20:05","20:10","20:15","20:20","20:25","20:30"};
			int[] numTweets3 = {2000, 10000, 2000, 2341, 13452, 14345, 16445};
			//GraphBean gb = new GraphBean(horas, numTweets, 20000);
			
		GraphBean gb = new GraphBean();
			gb.setNumTweets(numTweets);
			gb.setStrHoras(horas);
			gb.setPtoMaximo(20000);
		
		session.setAttribute(GlobalUtil.GRAPH_BEAN, gb);
		
		// Devolvemos la vista de la gráfica
		 RequestDispatcher rd = request.getRequestDispatcher("/views/graph.jsp");
         rd.forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
