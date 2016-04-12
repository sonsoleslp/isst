package es.upm.dit.isst.socialTV.web.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.upm.dit.isst.socialTV.bs.beans.GraphBean;
import es.upm.dit.isst.socialTV.bs.services.GlobalUtil;


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
		
		
		HttpSession session = request.getSession();
		
		//TODO: Consulta SQL para obtener los tweets cada 5 min, y las horas dependiendo de cuando ha sido su emisión
			//Obtenemos el Bean a pasarle a la gráfica, que será un graphBean
			String[] horas = {"20:00","20:05","20:10","20:15","20:20","20:25","20:30"};
			int[] numTweets = {2000, 10000, 2000, 2341, 13452, 14345, 16445};
			//GraphBean gb = new GraphBean(horas, numTweets, 20000);
		GraphBean gb = new GraphBean();
			gb.setNumTweets(numTweets);
			gb.setStrHoras(horas);
			gb.setPtoMaximo(20000);
		
		session.setAttribute(GlobalUtil.GRAPH_BEAN, gb);
		
		// Devolvemos la vista de la gráfica
		 RequestDispatcher rd = request.getRequestDispatcher("views/graph.jsp");
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
