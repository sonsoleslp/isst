package es.upm.dit.isst.socialTV.web.controllers;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import es.upm.dit.isst.socialTV.bs.beans.SpainMapBean;

import es.upm.dit.isst.socialTV.bs.services.GlobalUtil;


/**
 * Servlet de control de Login
 * @author Paco
 *
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		UserService userService = UserServiceFactory.getUserService();
		String url = userService.createLoginURL(request.getRequestURI());
		String urlLinktext = "Login";
		String user = "";
		String rol = "";
		
		if (request.getUserPrincipal() != null){
			user = request.getUserPrincipal().getName();
			url = userService.createLogoutURL(request.getRequestURI());
			urlLinktext = "Logout";
		}
		
		//Rol dependiendo del usuario loggeado
		if (GlobalUtil.ADMINISTRATORS.contains(user)) {
			rol = "admin";
		} else if (!"".equals(user)) {
			rol = "client";
		}
		
		request.getSession().setAttribute("user", user);
		request.getSession().setAttribute("rol", rol);
		request.getSession().setAttribute("urlLinktext", urlLinktext);
		request.getSession().setAttribute("urlLog_in_out", url);
		
		try {
			 RequestDispatcher rd = request.getRequestDispatcher("/views/index.jsp");
	         rd.forward(request, response);	
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
