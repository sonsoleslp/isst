package es.upm.dit.isst.socialTV.web.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.socialTV.bs.model.ProgramaTV;
import es.upm.dit.isst.socialTV.bs.model.ProgramaTVDAO;
import es.upm.dit.isst.socialTV.bs.model.ProgramaTVImpl;
import es.upm.dit.isst.socialTV.bs.services.ConsultaAPITwitter;

public class Cron5MinServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("CRON Task Every 5 minutes");
		ConsultaAPITwitter consulta = new ConsultaAPITwitter();
		ProgramaTVDAO dao = ProgramaTVImpl.getInstance();
		
		// Get fecha actual
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String datestr = dateFormat.format(date);
		
		// Todos los programas
		List <ProgramaTV> list = dao.todosLosProgramas();
		for (ProgramaTV prog : list) {
			consulta.updateTweets(prog.getPrimaryKey());
		}
	}

}
