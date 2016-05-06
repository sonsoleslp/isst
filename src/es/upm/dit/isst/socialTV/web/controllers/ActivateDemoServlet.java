package es.upm.dit.isst.socialTV.web.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.socialTV.bs.services.ConsultaAPITwitter;
import es.upm.dit.isst.socialTV.bs.services.GlobalUtil;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class ActivateDemoServlet extends HttpServlet {

	private static final Logger logger = Logger.getLogger(ActivateDemoServlet.class.getName());

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ConsultaAPITwitter consulta = new ConsultaAPITwitter();
		consulta.crearConsulta("Demo Presentación","Demo Episodio", new Date(), new Date(2016,6,15), GlobalUtil.DEMOSTRING);
		logger.info("Demo ACTIVADO");
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("Demo Activado");
		out.close();
		Twitter twitter = TwitterFactory.getSingleton();
		try {
			twitter.updateStatus(GlobalUtil.DEMOSTRING);
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
