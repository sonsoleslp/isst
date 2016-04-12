package es.upm.dit.isst.socialTV.web.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.socialTV.bs.model.ProgramaTV;
import es.upm.dit.isst.socialTV.bs.model.ProgramaTVDAO;
import es.upm.dit.isst.socialTV.bs.model.ProgramaTVImpl;
import es.upm.dit.isst.socialTV.bs.services.ConsultaAPITwitter;

public class ApiTestServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ProgramaTVDAO dao = ProgramaTVImpl.getInstance();
		ArrayList <ProgramaTV> progs = new ArrayList(dao.todosLosProgramas());
		req.setAttribute("progs", progs);
		try {
			RequestDispatcher view = req.getRequestDispatcher("views/insertProgTest.jsp");
			view.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// yyyy-MM-dd
		String begin = req.getParameter("fecha_inicio");
		//Date fechaInicio = format(begin);
		String hash = req.getParameter("hash");
		String titulo = req.getParameter("titulo");
		String duracion = req.getParameter("duracion");
		Long dur = Long.parseLong(duracion);
		ConsultaAPITwitter consulta = new ConsultaAPITwitter();
		// ProgramaTV(titulo, episodeCode, fechaInicio, horaInicio, duracion, hashtag);
		consulta.crearConsulta(titulo, "episodeCode", begin, "horaInicio", dur, hash);
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<html><body><h2>Hashtag actualizado</h2><a href='apitest'>Volver</a></body></head>");
		out.close();
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
