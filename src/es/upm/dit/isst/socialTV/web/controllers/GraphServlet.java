package es.upm.dit.isst.socialTV.web.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.xml.internal.bind.v2.TODO;

import es.upm.dit.isst.socialTV.bs.beans.GraphBean;
import es.upm.dit.isst.socialTV.bs.model.DatoAudiencia;
import es.upm.dit.isst.socialTV.bs.model.DatoAudienciaDAO;
import es.upm.dit.isst.socialTV.bs.model.DatoAudienciaImpl;
import es.upm.dit.isst.socialTV.bs.model.ProgramaTV;
import es.upm.dit.isst.socialTV.bs.model.ProgramaTVDAO;
import es.upm.dit.isst.socialTV.bs.model.ProgramaTVImpl;
import es.upm.dit.isst.socialTV.bs.services.GlobalUtil;

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Servlet implementation class MapaServlet
 * @author Paco
 *
 */
public class GraphServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Comprobar permisos
		if (!GlobalUtil.checkLogin(request)) {
			GlobalUtil.redirigirLogin(request, response, GlobalUtil.LOGIN_ERROR_MESSAGE);
			return;
		}
		HttpSession session = request.getSession();
		//Separo la URL por /
		String[] params = request.getRequestURL().toString().split("/"); 
		Long num = (long) 0;	
		//Compruebo si el último trozo es una cadena numérica
		try {
			num = Long.parseLong(params[params.length-1]);

		} catch(Exception e){
			return;
		}
		
		ProgramaTVDAO ptv = ProgramaTVImpl.getInstance();
		ProgramaTV prog = ptv.programaPorId(num);
		// Compruebo que existe ese programa
		if( prog!=null){
			
			DatoAudienciaDAO dao = DatoAudienciaImpl.getInstance();
			// Extraigo la audiencia de dicho programa
			List<DatoAudiencia> list = dao.getAudienceForEpisodeWithId(num);
			// Hace que no salga el primer valor de tweets, que no es significativo
			
			if (list.size()<3) {
				GlobalUtil.redirigirLogin(request, response, GlobalUtil.PROGRAMATV_DENIED);
				return;
			}
			
			if(list.size()>1)list.remove(list.size()-1);
			// Número de monitorizaciones (menos la primera)
			int size = list.size()-1;
			//Array de minutos de medición
			String[] horas = new String[size];
			//Array de medidas
			int[] numTweets = new int[size];
			int index = 0;
			
			// Recorro los resultados para rellenar los arrays
			for(DatoAudiencia dato : list){
				if(index < size){
					String date = dato.getFecha();
					horas[size -1 - index] =  date.length() > 5? date.substring(date.toString().length() - 5) : date;
					int cuenta = dato.getCount();
					numTweets[size-1-index]= cuenta;
					index++;
				}
			}

		// Paso todos los valores al Bean	
		GraphBean gb = new GraphBean();
		if(size != 0) {
		// Monitorización
			gb.setNumTweets(numTweets);
			gb.setStrHoras(horas);
			gb.setPtoMaximo(100); 
		}
		
		// Datos del programa
		gb.setTitle(prog.getTitulo());
		gb.setHashtag(prog.getHashtag());
		gb.setId(num);
		gb.setEpisodeCode(prog.getEpisodeCode());
		gb.setDateStart(prog.getFechaInicio().replace("T", " "));
		gb.setDateEnd(prog.getFechaFin().replace("T", " "));
		gb.setCount(prog.getCount());
			
		session.setAttribute(GlobalUtil.GRAPH_BEAN, gb);
		
		// Devolvemos la vista de la gráfica
		 RequestDispatcher rd = request.getRequestDispatcher("/views/graph.jsp");
         rd.forward(request, response);	
		} else {
			response.sendRedirect("/");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
