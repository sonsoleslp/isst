package es.upm.dit.isst.socialTV.web.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.socialTV.bs.model.ProgramaTV;
import es.upm.dit.isst.socialTV.bs.model.ProgramaTVDAO;
import es.upm.dit.isst.socialTV.bs.model.ProgramaTVImpl;

public class ViewTestServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ProgramaTVDAO dao = ProgramaTVImpl.getInstance();
		List<ProgramaTV> progs =  dao.todosLosProgramas();
		if (progs != null){
			req.setAttribute("tweet", progs);
		} else {
			req.setAttribute("tweet", 0);
		}
		try {
			// Problemas con url tipo /api/view, jsp not found
			RequestDispatcher view = req.getRequestDispatcher("views/mostrarTest.jsp");
			view.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
