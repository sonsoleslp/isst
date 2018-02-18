package edu.iit.itmd515;

import java.io.IOException;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@SuppressWarnings("serial")
public class ProcessCustomerDataServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		

//		req.getSession().setAttribute( "bean" , bean );
		resp.setContentType("text/plain");
		RequestDispatcher view = req.getRequestDispatcher("ProcessCustomerDataRequest.jsp");
		view.forward ( req , resp );
	}
}
