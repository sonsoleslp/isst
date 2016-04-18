package es.upm.dit.isst.socialTV.web.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
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
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(Cron5MinServlet.class.getName());

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		logger.info("Cron Job every 5 min");
		ConsultaAPITwitter consulta = new ConsultaAPITwitter();
		ProgramaTVDAO dao = ProgramaTVImpl.getInstance();
		
		// Todos los programas
		List <ProgramaTV> list = dao.todosLosProgramas();
		SimpleDateFormat format = new SimpleDateFormat(GlobalUtil.FORMAT_DATE);
		// UTC en Google
		format.setTimeZone(TimeZone.getTimeZone("Etc/GMT+2"));
		Date ahora = null;
		try {
			String temp = format.format(new Date());
			logger.severe(temp);
			ahora = new SimpleDateFormat(GlobalUtil.FORMAT_DATE).parse(temp);
			logger.severe(ahora.toString());
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		format = new SimpleDateFormat(GlobalUtil.FORMAT_DATE);
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
				//System.out.println(prog.getTitulo()+", "+dato.getFecha() +", "+dato.getCount());
			}
			logger.severe(String.valueOf(ahora.after(init)));
			logger.severe(String.valueOf(ahora.before(end)));
			
			if (ahora.after(init) && ahora.before(end)){
				consulta.updateTweets(prog.getPrimaryKey());
			}
		}
	}

}
