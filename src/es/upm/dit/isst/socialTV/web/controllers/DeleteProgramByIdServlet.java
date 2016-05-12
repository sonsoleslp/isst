package es.upm.dit.isst.socialTV.web.controllers;

import java.io.IOException;
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

/**
 * Servlet para borrar el programa especificado.
 * 
 * @author Sonsoles
 *
 */
public class DeleteProgramByIdServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(Cron5MinServlet.class.getName());

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Comprobar permisos
		if (!GlobalUtil.checkLoginAdmin(req)) {
			GlobalUtil.redirigirLogin(req, resp, GlobalUtil.ACCESS_DENIED);
			return;
		}
		logger.info("DELETE PROGRAMASTV");
		String[] params = req.getRequestURL().toString().split("/"); 
		Long num = (long) 0;	
		//Compruebo si el último trozo es una cadena numérica
		try {
			num = Long.parseLong(params[params.length-1]);

		} catch(Exception e){
			return;
		}
		
		ProgramaTVDAO ptv = ProgramaTVImpl.getInstance();
		ProgramaTV prog = ptv.programaPorId(num);
		DatoAudienciaDAO dao = DatoAudienciaImpl.getInstance();
		ptv.deleteProgramaTV(num);
		dao.deleteAudienceForEpisodeWithId(num);
		resp.sendRedirect("/apitest");
	}
	
}