package es.upm.dit.isst.socialTV.web.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.upm.dit.isst.socialTV.bs.beans.Top5Bean;

import es.upm.dit.isst.socialTV.bs.model.ProgramaTVImpl;

import es.upm.dit.isst.socialTV.bs.services.GlobalUtil;


/**
 * Servlet implementation class MapaServlet
 */
public class Top5Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		
		Top5Bean tb = new Top5Bean();
		tb.setProgramasTop5(ProgramaTVImpl.getInstance().programasTop5()); //No se si me he pegado mucho triple con cómo se usa esta clase
		session.setAttribute(GlobalUtil.TOP_5_BEAN, tb);
		
		// Devolvemos la vista del Top 5
		 RequestDispatcher rd = request.getRequestDispatcher("views/top5.jsp");
         rd.forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
