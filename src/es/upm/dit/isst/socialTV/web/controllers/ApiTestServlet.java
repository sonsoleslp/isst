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
import es.upm.dit.isst.socialTV.bs.services.GlobalUtil;

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
		SimpleDateFormat format = new SimpleDateFormat(GlobalUtil.FORMAT_DATE);
		String fechaInicio = req.getParameter("fecha_inicio");
		String fechaFin = req.getParameter("fecha_fin");
		Date dateFin = null;
		Date dateInicio = null;
		try {
			dateFin = format.parse(fechaFin);
			dateInicio = format.parse(fechaInicio);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String episodeCode = req.getParameter("episode");
		//Date fechaInicio = format(begin);
		String hash = req.getParameter("hash");
		String titulo = req.getParameter("titulo");
		ConsultaAPITwitter consulta = new ConsultaAPITwitter();
		consulta.crearConsulta(titulo, episodeCode, dateInicio, dateFin, hash);
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<html><body><h2>Hashtag actualizado</h2><a href='apitest'>Volver</a></body></head>");
		out.close();
	}
}