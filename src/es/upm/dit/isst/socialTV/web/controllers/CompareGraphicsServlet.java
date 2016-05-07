package es.upm.dit.isst.socialTV.web.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.xml.internal.bind.v2.TODO;

import es.upm.dit.isst.socialTV.bs.beans.GraphBean;
import es.upm.dit.isst.socialTV.bs.model.DatoAudiencia;
import es.upm.dit.isst.socialTV.bs.model.DatoAudienciaDAO;
import es.upm.dit.isst.socialTV.bs.model.DatoAudienciaImpl;
import es.upm.dit.isst.socialTV.bs.model.ProgramaTV;
import es.upm.dit.isst.socialTV.bs.model.ProgramaTVDAO;
import es.upm.dit.isst.socialTV.bs.model.ProgramaTVImpl;
import es.upm.dit.isst.socialTV.bs.services.GlobalUtil;

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Servlet que atiende las peticiones de comparativa de las graficas
 * @author Naomi
 *
 */
public class CompareGraphicsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Comprobar permisos
		if (!GlobalUtil.checkLogin(request)) {
			GlobalUtil.redirigirLogin(request, response, GlobalUtil.LOGIN_ERROR_MESSAGE);
			return;
		}
		
		HttpSession session = request.getSession();
		
		// Datos de prueba 
		String[] horas = {"20:00","20:05","20:10","20:15","20:20","20:25","20:30"};
		int[] numTweets = {2000, 10000, 2000, 2341, 13452, 14345, 16445};
		GraphBean gb = new GraphBean();
		gb.setNumTweets(numTweets);
		gb.setStrHoras(horas);
		gb.setPtoMaximo(20000);
		gb.setTitle("titulo0");
		gb.setId((long)2);
		
		session.setAttribute(GlobalUtil.GRAPH_BEAN, gb);

		/*
		// Recuperamos el bean de la grafica para evitar toda la logica de BBDD
		GraphBean gb = (GraphBean)session.getAttribute(GlobalUtil.GRAPH_BEAN);
		
		// Si es nulo, recuperamos el programa por medio del id 
		if (gb == null) {
			//Recuperamos el id de la url
			//Separo la URL por /
			String[] params = request.getRequestURL().toString().split("/"); 
			Long num = (long) 0;	
			//Compruebo si el último trozo es una cadena numérica
			try {
				num = Long.parseLong(params[params.length-1]);
			} catch(Exception e){
				return;
			}
			// Guardamos los valores en el bean
			gb = getProgramFromId(num);
			
			// Si sigue siendo nulo, no existe el programa y salimos
			if(gb == null) {
				response.sendRedirect("/");
			}
		}
		*/
		
		// Guardamos los datos en el bean
		List<GraphBean> gbList = getProgramasSimultaneos(gb.getDateStart(), gb.getDateEnd());
		request.setAttribute(GlobalUtil.COMPARE_GRAPH_BEAN, gbList);
		
		// Devolvemos la vista de la gráfica
		RequestDispatcher rd = request.getRequestDispatcher("/views/graph.jsp");
        rd.forward(request, response);	

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private GraphBean getProgramFromId(long id) {
		// Recuperamos el programa correspondiente
		ProgramaTVDAO ptv = ProgramaTVImpl.getInstance();
		ProgramaTV prog = ptv.programaPorId(id);

		// Si existe ese programa en BBDD
		if( prog!=null){
			DatoAudienciaDAO dao = DatoAudienciaImpl.getInstance();
			// Extraigo la audiencia de dicho programa
			List<DatoAudiencia> list = dao.getAudienceForEpisodeWithId(id);	
			// Hace que no salga el primer valor de tweets, que no es significativo
			if(list.size()>1)list.remove(list.size()-1);
			// Número de monitorizaciones (menos la primera)
			int size = list.size();
			//Array de minutos de medición
			String[] horas = new String[size];
			//Array de medidas
			int[] numTweets = new int[size];
			int index = 0;

			// Recorro los resultados para rellenar los arrays
			for(DatoAudiencia dato : list){
				String date = dato.getFecha();
				horas[size -1 - index] =  date.length() > 5? date.substring(date.toString().length() - 5) : date;
				int cuenta = dato.getCount();
				numTweets[size-1-index]= cuenta;
				index++;
			}

			// Paso todos los valores al Bean	
			GraphBean gb = new GraphBean();
			if(size != 0) {
				// Monitorización
				gb.setNumTweets(numTweets);
				gb.setStrHoras(horas);
				gb.setPtoMaximo(100); 
			}

			// Datos del programa
			gb.setTitle(prog.getTitulo());
			gb.setHashtag(prog.getHashtag());
			gb.setId(id);
			gb.setEpisodeCode(prog.getEpisodeCode());
			gb.setDateStart(prog.getFechaInicio());
			gb.setDateEnd(prog.getFechaFin());
			
			return gb;
		}
		
		// Si no existe el programa salimos sin hacer nada
		return null;
	}
	
	private List<GraphBean> getProgramasSimultaneos(String fechaIni, String fechaFin) {
		// TODO llamada a BBDD: query de BBDD
		//getProgramFromId(bla);
		
		// Datos de prueba
		List<GraphBean> gbList = new ArrayList<GraphBean>();
		
		String[] horas = {"20:00","20:05","20:10","20:15","20:20","20:25","20:30"};
		int[] numTweets = {200, 30000, 15000, 31, 1342, 1345, 36445};
		GraphBean gb = new GraphBean();
		gb.setNumTweets(numTweets);
		gb.setStrHoras(horas);
		gb.setPtoMaximo(20000);
		gb.setTitle("titulo1");
		gb.setId((long)2);
		gbList.add(gb);
		
		String[] horas2 = {"20:00","20:05","20:10","20:15","20:20","20:25","20:30"};
		int[] numTweets2 = {10000, 10000, 20, 14345, 13452, 2341, 16445};
		GraphBean gb2 = new GraphBean();
		gb2.setNumTweets(numTweets2);
		gb2.setStrHoras(horas2);
		gb2.setPtoMaximo(20000);
		gb2.setTitle("titulo2");
		gb2.setId((long)1);
		gbList.add(gb2);
		
		return gbList;
	}

}
