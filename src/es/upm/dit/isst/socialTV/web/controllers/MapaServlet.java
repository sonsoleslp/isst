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
import es.upm.dit.isst.socialTV.bs.services.GlobalUtil;


/**
 * Servlet de respuesta a la petici�n del mapa asociado a un programa.
 * 
 * @author Paco
 *
 */
public class MapaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Comprobar permisos
		if (!GlobalUtil.checkLogin(request)) {
			GlobalUtil.redirigirLogin(request, response, GlobalUtil.LOGIN_ERROR_MESSAGE);
			return;
		}

		// Recuperamos la sesion
		HttpSession session = request.getSession();
		
//		CalculoImpactoMapa cim = new CalculoImpactoMapa();
		
		//Separo la URL por /
		String[] params = request.getRequestURL().toString().split("/"); 
		Long num = (long) 0;
		
		//Compruebo si el �ltimo trozo es una cadena num�rica
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
			
			spainMapBean.setDateEnd(prog.getFechaFin().replace("T", "  "));
			spainMapBean.setDateStart(prog.getFechaInicio().replace("T", "  "));
			spainMapBean.setEpisodeCode(prog.getEpisodeCode());
			spainMapBean.setHashtag(prog.getHashtag());
			spainMapBean.setCount(prog.getCount());
			spainMapBean.setId(num);
			spainMapBean.setTitle(prog.getTitulo());
			spainMapBean.setProvinceName(GlobalUtil.SPAIN_PROVINCES_ARRAY);
			int[] tweets = new int[GlobalUtil.SPAIN_PROVINCES_ARRAY.length];
		
			int max = 0;
			int min = Integer.MAX_VALUE;
			for (int i = 0; i < GlobalUtil.SPAIN_PROVINCES_ARRAY.length ; i++){
				String province = GlobalUtil.SPAIN_PROVINCES_ARRAY[i];
				tweets[i] = prog.getProvince(province);
				if(tweets[i]>max) max = tweets[i];
				if(tweets[i]<min) min = tweets[i];
			}
			
			
			spainMapBean.setProvinceTweets(tweets);
			spainMapBean.setMax(max);
			spainMapBean.setMin(min);
	
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
