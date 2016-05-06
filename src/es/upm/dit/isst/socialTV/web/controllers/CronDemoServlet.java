package es.upm.dit.isst.socialTV.web.controllers;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.socialTV.bs.services.ConsultaAPITwitter;
import es.upm.dit.isst.socialTV.bs.services.GlobalUtil;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class CronDemoServlet extends HttpServlet {
	
	private static final int MAXTWEETSPERROUND = 2;
	private static final ConsultaAPITwitter consulta = new ConsultaAPITwitter();
	private static final Logger logger = Logger.getLogger(CronDemoServlet.class.getName());

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List <Status> list = consulta.search(GlobalUtil.DEMOSTRING, null, -1);
		if (list.isEmpty() || list.size() > 15) return;
		int max = new Random().nextInt(MAXTWEETSPERROUND)+1;
		logger.info("Demo: "+String.valueOf(max));
		for (int i = 0;i<max;i++){
			tweet(list.size()+i+1 +" - "+ GlobalUtil.DEMOSTRING+" "+ new Date().toString());
			try {
				TimeUnit.SECONDS.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void tweet(String text){
		Twitter twitter = TwitterFactory.getSingleton();
	    try {
			twitter.updateStatus(text);
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
