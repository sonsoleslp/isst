package es.upm.dit.isst.socialTV.web.controllers;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.upm.dit.isst.socialTV.bs.beans.SpainMapBean;
import es.upm.dit.isst.socialTV.bs.model.ProgramaTV;
import es.upm.dit.isst.socialTV.bs.model.ProgramaTVDAO;
import es.upm.dit.isst.socialTV.bs.model.ProgramaTVImpl;
import es.upm.dit.isst.socialTV.bs.services.CalculoImpactoMapa;
import es.upm.dit.isst.socialTV.bs.services.GlobalUtil;


/**
 * Servlet implementation class MapaServlet
 */
public class MapaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Recuperamos la sesion
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

			// Creamos el bean
			SpainMapBean spainMapBean = new SpainMapBean();
			
			spainMapBean.setDateEnd(prog.getFechaFin());
			spainMapBean.setDateStart(prog.getFechaInicio());
			spainMapBean.setEpisodeCode(prog.getEpisodeCode());
			spainMapBean.setHashtag(prog.getHashtag());
			spainMapBean.setId(num);
			spainMapBean.setTitle(prog.getTitulo());
			spainMapBean.setProvinceName(GlobalUtil.SPAIN_PROVINCES_ARRAY);
			int[] tweets = new int[GlobalUtil.SPAIN_PROVINCES_ARRAY.length];
			
			// DATOS DE PRUEBA
			// TODO : @PacoArd
			for(int i=0; i<tweets.length; i++) {
				tweets[i] = 3*i;
			}
			spainMapBean.setProvinceTweets(tweets);
			
			// Pasamos el bean a la vista
			session.setAttribute(GlobalUtil.SPAIN_MAP_BEAN, spainMapBean);
		
			// Devolvemos la vista del mapa
			RequestDispatcher rd = request.getRequestDispatcher("/views/mapa.jsp");
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
