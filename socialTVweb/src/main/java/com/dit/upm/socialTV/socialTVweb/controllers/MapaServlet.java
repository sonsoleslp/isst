package com.dit.upm.socialTV.socialTVweb.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		// Añadimos el resultado a la respuesta
		response.getWriter().append("El porcentaje de tweets en España es: ").append(estadisticas.getImpactoPorPais().get("Soy un hashtag").toString()).append("%.");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
