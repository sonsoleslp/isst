package es.upm.dit.isst.socialTV.web.controllers;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.socialTV.bs.model.DatoAudienciaDAO;
import es.upm.dit.isst.socialTV.bs.model.DatoAudienciaImpl;
import es.upm.dit.isst.socialTV.bs.model.ProgramaTVDAO;
import es.upm.dit.isst.socialTV.bs.model.ProgramaTVImpl;
import es.upm.dit.isst.socialTV.bs.services.GlobalUtil;

/**
 * Servlet para borrar todos los datos de audiencia
 * @author Sonsoles
 *
 */
public class DeleteDatosAudiencia  extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(Cron5MinServlet.class.getName());

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Comprobar permisos
		if (!GlobalUtil.checkLoginAdmin(req)) {
			GlobalUtil.redirigirLogin(req, resp, GlobalUtil.ACCESS_DENIED);
			return;
		}
		logger.info("DELETE DatosAudiencia");
		DatoAudienciaDAO dao = DatoAudienciaImpl.getInstance();
		dao.deleteAll();
		resp.sendRedirect("/");
	}
	
}