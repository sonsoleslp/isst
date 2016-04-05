package com.dit.upm.socialTV.socialTVweb.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.dit.upm.socialTV.socialTVbs.servicesBS.ConsultaAPITwitter;

public class APIServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			RequestDispatcher view = getServletContext().getRequestDispatcher("/WEB-INF/views/mapa.jsp");
			view.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// YYYY-MM-DD
		String begin = req.getParameter("fecha_inicio");
		System.out.println(begin);
		Date fechaInicio = format(begin);
		System.out.println(fechaInicio);
		String hash = req.getParameter("hash");
		String titulo = req.getParameter("titulo");
		String end = req.getParameter("fecha_fin");
		Date fechaFin = format (end);
		ConsultaAPITwitter consulta = new ConsultaAPITwitter();
		consulta.crearConsulta(titulo, fechaInicio, fechaFin, hash);
	}

	private Date format (String date){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		Date fechaInicio = null;
		try {
			fechaInicio = format.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fechaInicio;
	}
}
