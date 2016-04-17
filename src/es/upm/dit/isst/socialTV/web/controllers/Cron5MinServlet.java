package es.upm.dit.isst.socialTV.web.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.socialTV.bs.model.DatoAudiencia;
import es.upm.dit.isst.socialTV.bs.model.DatoAudienciaDAO;
import es.upm.dit.isst.socialTV.bs.model.DatoAudienciaImpl;
import es.upm.dit.isst.socialTV.bs.model.ProgramaTV;
import es.upm.dit.isst.socialTV.bs.model.ProgramaTVDAO;
import es.upm.dit.isst.socialTV.bs.model.ProgramaTVImpl;
import es.upm.dit.isst.socialTV.bs.services.ConsultaAPITwitter;
import es.upm.dit.isst.socialTV.bs.services.GlobalUtil;

public class Cron5MinServlet extends HttpServlet {
	
	private static final Logger logger = Logger.getLogger(Cron5MinServlet.class.getName());

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		logger.info("Cron Job every 5 min");
		ConsultaAPITwitter consulta = new ConsultaAPITwitter();
		ProgramaTVDAO dao = ProgramaTVImpl.getInstance();
		
		// Todos los programas
		List <ProgramaTV> list = dao.todosLosProgramas();
		SimpleDateFormat format = new SimpleDateFormat(GlobalUtil.FORMAT_DATE);
		Date ahora = new Date();
		for (ProgramaTV prog : list) {
			String fechaInicio = prog.getFechaInicio();
			String fechaFin = prog.getFechaFin();
			Date init = null;
			Date end = null;
			try {
				init = format.parse(fechaInicio);
				end = format.parse(fechaFin);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			// Check audiencia
			DatoAudienciaDAO datos = DatoAudienciaImpl.getInstance();
			List<DatoAudiencia> aa = datos.getAudienceForEpisodeWithId(prog.getPrimaryKey());
			for (DatoAudiencia dato: aa){
				System.out.println(dato.getFecha());
				System.out.println(dato.getCount());
			}
			if (ahora.after(init) && ahora.before(end)){
				consulta.updateTweets(prog.getPrimaryKey());
			}
		}
	}

}
