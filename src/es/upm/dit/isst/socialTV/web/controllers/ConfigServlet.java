package es.upm.dit.isst.socialTV.web.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.upm.dit.isst.socialTV.bs.beans.AllProgramsBean;
import es.upm.dit.isst.socialTV.bs.model.ProgramaTV;
import es.upm.dit.isst.socialTV.bs.model.ProgramaTVDAO;
import es.upm.dit.isst.socialTV.bs.model.ProgramaTVImpl;
import es.upm.dit.isst.socialTV.bs.services.ConsultaAPITwitter;
import es.upm.dit.isst.socialTV.bs.services.GlobalUtil;

public class ConfigServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = Logger.getLogger(Cron5MinServlet.class.getName());

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Comprobar permisos
		if (!GlobalUtil.checkLoginAdmin(req)) {
			GlobalUtil.redirigirLogin(req, resp, GlobalUtil.ACCESS_DENIED);
			return;
		}	
		render(req,resp, "");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Comprobar permisos
		if (!GlobalUtil.checkLoginAdmin(req)) {
			GlobalUtil.redirigirLogin(req, resp, GlobalUtil.ACCESS_DENIED);
			return;
		}
		SimpleDateFormat format = new SimpleDateFormat(GlobalUtil.FORMAT_DATE);
		if (req.getParameter("fecha_inicio").isEmpty()){
			render(req,resp,"Error. La fecha de inicio está vacía.");
			return;
		}
		if (req.getParameter("fecha_fin").isEmpty()){
			render(req,resp,"Error. La fecha de finalización está vacía.");
			return;
		}
		String fechaInicio = req.getParameter("fecha_inicio");
		String fechaFin = req.getParameter("fecha_fin");
		Date dateFin = null;
		Date dateInicio = null;
		try {
			
			dateFin = format.parse(fechaFin);
			dateInicio = format.parse(fechaInicio);
			if(dateFin.before(dateInicio)){
				render(req,resp,"Error. La fecha de inicio es posterior a la de fin.");
				return;
			}
			logger.severe(dateInicio.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			render(req,resp,"Error. La fecha introducida es incorrecta.");
			return;
		}
		String episodeCode = req.getParameter("episode");
		if (episodeCode == "") {
			render(req,resp,"Error. Debes introducir un código de episodio.");
			return;
		}		
		String hash = req.getParameter("hash");
		if (hash == "") {
			render(req,resp,"Error. Debes introducir un hashtag.");
			return;
		}
		String titulo = req.getParameter("titulo");
		if (titulo == "") {
			render(req,resp,"Error. Debes introducir un título.");
			return;
		}
		ConsultaAPITwitter consulta = new ConsultaAPITwitter();
		consulta.crearConsulta(titulo, episodeCode, dateInicio, dateFin, hash);
		
		render(req,resp,"");
		
	}
	
	private void render( HttpServletRequest req, HttpServletResponse resp, String error) throws ServletException, IOException {
		System.out.println(error);
		HttpSession session = req.getSession();
		ProgramaTVDAO dao = ProgramaTVImpl.getInstance();
		ArrayList <ProgramaTV> progs = new ArrayList<ProgramaTV>(dao.todosLosProgramas());
		AllProgramsBean apb = new AllProgramsBean(progs,error);
		session.setAttribute(GlobalUtil.ALL_PROGS_BEAN, apb);
		try {
			RequestDispatcher view = req.getRequestDispatcher("views/insertProg.jsp");
			view.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}