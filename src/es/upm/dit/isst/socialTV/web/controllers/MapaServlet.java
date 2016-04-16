package es.upm.dit.isst.socialTV.web.controllers;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.upm.dit.isst.socialTV.bs.model.ProgramaTV;
import es.upm.dit.isst.socialTV.bs.model.ProgramaTVDAO;
import es.upm.dit.isst.socialTV.bs.model.ProgramaTVImpl;
import es.upm.dit.isst.socialTV.bs.services.CalculoImpactoMapa;


/**
 * Servlet implementation class MapaServlet
 */
public class MapaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO llamar a los servicios business para recuperar la informacion deseada y pasarla al jsp correspondiente
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
			
		CalculoImpactoMapa calculoImpactoMapa = new CalculoImpactoMapa();
		//TODO @Naomi
		
		
		
		/****
		 * AQUI LÓGICA MAPA
		 */
		
		
		}else{
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
