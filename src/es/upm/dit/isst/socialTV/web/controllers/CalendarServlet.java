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
import javax.servlet.http.HttpSession;

import es.upm.dit.isst.socialTV.bs.beans.AllProgramsBean;
import es.upm.dit.isst.socialTV.bs.model.ProgramaTV;
import es.upm.dit.isst.socialTV.bs.model.ProgramaTVDAO;
import es.upm.dit.isst.socialTV.bs.model.ProgramaTVImpl;
import es.upm.dit.isst.socialTV.bs.services.ConsultaAPITwitter;
import es.upm.dit.isst.socialTV.bs.services.GlobalUtil;

/**
 * Servlet que atiende a la petici�n del calendario de monitorizaciones.
 * Obtiene de bases de datos los programas guardados y los pasa a la vista
 * JSP en un bean.
 * 
 * @author Paco
 *
 */
public class CalendarServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Comprobar permisos
		if (!GlobalUtil.checkLogin(req)) {
			GlobalUtil.redirigirLogin(req, resp, GlobalUtil.LOGIN_ERROR_MESSAGE);
			return;
		}
		HttpSession session = req.getSession();
		ProgramaTVDAO dao = ProgramaTVImpl.getInstance();
		ArrayList <ProgramaTV> progs = new ArrayList<ProgramaTV>(dao.todosLosProgramas());
		AllProgramsBean apb = new AllProgramsBean(progs,"");
		session.setAttribute(GlobalUtil.ALL_PROGS_BEAN, apb);

		try {
			RequestDispatcher view = req.getRequestDispatcher("views/calendar.jsp");
			view.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}