package es.upm.dit.isst.socialTV.web.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.upm.dit.isst.socialTV.bs.beans.GraphBean;
import es.upm.dit.isst.socialTV.bs.model.DatoAudiencia;
import es.upm.dit.isst.socialTV.bs.model.DatoAudienciaDAO;
import es.upm.dit.isst.socialTV.bs.model.DatoAudienciaImpl;
import es.upm.dit.isst.socialTV.bs.model.ProgramaTV;
import es.upm.dit.isst.socialTV.bs.model.ProgramaTVDAO;
import es.upm.dit.isst.socialTV.bs.model.ProgramaTVImpl;
import es.upm.dit.isst.socialTV.bs.services.GlobalUtil;



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
            
            // Recuperamos el programa correspondiente
            ProgramaTVDAO ptv = ProgramaTVImpl.getInstance();
            ProgramaTV prog = ptv.programaPorId(num);

            // Si existe ese programa en BBDD
            if( prog!=null){
                // Guardamos los valores en el bean
                gb = getGraphBeanFromProgram(prog);
            }
            // Si sigue siendo nulo, no existe el programa y salimos
            if(gb == null) {
                response.sendRedirect("/");
            }
        }
        
        // Guardamos los datos en el bean
        List<GraphBean> gbList = new ArrayList<GraphBean>(getProgramasSimultaneos(gb.getDateStart(), gb.getDateEnd(), gb.getId()));
        String[] horas = getLabels(gbList, gb);
        
        gb.setNumTweets(rellenarConNull(gb, horas));
        gb.setStrHoras(horas);
        for(GraphBean gbSimul: gbList) {
        	gbSimul.setNumTweets(rellenarConNull(gbSimul, horas));
        	gbSimul.setStrHoras(horas);
        }
        request.setAttribute(GlobalUtil.COMPARE_GRAPH_BEAN, gbList);
        session.setAttribute(GlobalUtil.GRAPH_BEAN, gb);
        
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
    
    private GraphBean getGraphBeanFromProgram(ProgramaTV prog) {
        DatoAudienciaDAO dao = DatoAudienciaImpl.getInstance();
        // Extraigo la audiencia de dicho programa
        List<DatoAudiencia> list = dao.getAudienceForEpisodeWithId(prog.getPrimaryKey());
        // Hace que no salga el primer valor de tweets, que no es significativo

        if (list.size()<1) {
            return null;
        }

//        if(list.size()>1)list.remove(list.size()-1);
        // Número de monitorizaciones (menos la primera)
        int size = list.size();
        //Array de minutos de medición
        String[] horas = new String[size];
        //Array de medidas
        int[] numTweets = new int[size];
        int index = 0;

        // Recorro los resultados para rellenar los arrays
        for(DatoAudiencia dato : list){
            if(index < size){
                String date = dato.getFecha();
                horas[size -1 - index] =  date.length() > 5? date.substring(date.toString().length() - 5) : date;
                int cuenta = dato.getCount();
                numTweets[size-1-index]= cuenta;
                index++;
            }
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
        gb.setId(prog.getPrimaryKey());
        gb.setEpisodeCode(prog.getEpisodeCode());
        gb.setDateStart(prog.getFechaInicio().replace("T", " "));
        gb.setDateEnd(prog.getFechaFin().replace("T", " "));
        gb.setCount(prog.getCount());

        return gb;

    }
    
    private List<GraphBean> getProgramasSimultaneos(String fechaIni, String fechaFin, long id) {
        // Recogemos todos los programas
        ProgramaTVDAO dao = ProgramaTVImpl.getInstance();
        List<ProgramaTV> allPrograms = dao.todosLosProgramas();
        
        // Comprobamos que las fechas esten en el intervalo
        List<GraphBean> gbList = new ArrayList<GraphBean>();
        for(ProgramaTV programa : allPrograms) {
            if(((Long)programa.getPrimaryKey()).equals((Long)id)){
                continue;
            }
            
            if(isInRange(fechaIni, fechaFin, programa.getFechaInicio(), programa.getFechaFin())) {
                gbList.add(getGraphBeanFromProgram(programa));
            }
        }
        
        return gbList;
    }
    
    private boolean isInRange(String ini, String fin, String iniProg, String finProg) {
        Date iniDate = GlobalUtil.str2Date(ini);
        Calendar cal = Calendar.getInstance();
        cal.setTime(iniDate);
        cal.add(Calendar.HOUR, -1);
        iniDate = cal.getTime();
        Date finDate = GlobalUtil.str2Date(fin);
        cal.setTime(finDate);
        cal.add(Calendar.HOUR, 1);
        finDate = cal.getTime();
        Date iniProgDate = GlobalUtil.str2Date(iniProg);
        Date finProgDate = GlobalUtil.str2Date(finProg);
        
        return (iniProgDate.after(iniDate) || iniProgDate.equals(iniDate)) && (finProgDate.before(finDate) || finProgDate.equals(finDate));
    }
    
    private String[] getLabels(List<GraphBean> gbList, GraphBean gbAct) {
    	TreeSet<String> hashedArray = new TreeSet<String>();
    	gbList.add(gbAct);
    	for(GraphBean gb : gbList) {
    		if(gb.getStrHoras()!= null) {
    			for (String entry : gb.getStrHoras()) {
    	    		hashedArray.add(entry);
    	    	}
    	    }
    	}
    	gbList.remove(gbAct);
    	return (String[])hashedArray.toArray(new String[hashedArray.size()]);
    }
    
    private String[] rellenarConNull(GraphBean gb, String[] horas) {
    	List<String> gbValores = new ArrayList<String>(Arrays.asList(gb.getNumTweets()));
    	List<String> gbHoras = new ArrayList<String>(Arrays.asList(gb.getStrHoras()));
    	for(int i = 0; i<horas.length; i++) {
    		if(!gbHoras.contains(horas[i])) {
    			gbValores.add(i, null);
    		}
    	}
    	return (String[])gbValores.toArray(new String[horas.length]);
    }

}
