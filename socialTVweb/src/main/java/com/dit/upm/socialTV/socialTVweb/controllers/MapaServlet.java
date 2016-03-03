package com.dit.upm.socialTV.socialTVweb.controllers;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.dit.upm.socialTV.socialTVbs.beans.EstadisticasPais;
import es.dit.upm.socialTV.socialTVbs.beans.PaisEnum;
import es.dit.upm.socialTV.socialTVbs.servicesBS.CalculoImpactoMapa;

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
		
		/* Llamamos al servicio business que nos permite recuperar las estadisticas de 
		 * un determinado pais (nunca he empleado servlets y no se como se recuperan parametros
		 *  de la url
		 */
		CalculoImpactoMapa calculoImpactoMapa = new CalculoImpactoMapa();
		
		// Suponiendo que recogemos de la web de alguna forma el hashtag y el pais
		EstadisticasPais estadisticas = calculoImpactoMapa.calculoImpactoMapa(PaisEnum.SPAIN, "Soy un hashtag");
		
		// Recuperamos la sesión y añadimos el resultado a la respuesta
		HttpSession session = request.getSession();
		
		// La string que se define es el nombre de la variable en la jsp
        session.setAttribute("estadistica", estadisticas);
		
		// Redirigimos a la jsp deseada
		try {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/mapa.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
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
