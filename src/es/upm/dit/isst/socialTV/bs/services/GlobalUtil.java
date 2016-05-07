package es.upm.dit.isst.socialTV.bs.services;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Clase para definir variables y métodos estáticos, útiles para todo el proyecto.
 * @author Paco
 */
public class GlobalUtil {

	
	/**	NOMBRES PARA DECLARACIONES DE BEANS EN JSP **/
	public static final String TOP_5_BEAN = "top5Bean";
	public static final String GRAPH_BEAN = "graphBean";
	public static final String SPAIN_MAP_BEAN = "spainMapBean";
	public static final String ALL_PROGS_BEAN = "allProgramsBean";
	public static final String COMPARE_GRAPH_BEAN = "compareGraphBean";

	/**	NOMBRES DE LAS TABLAS DE BASES DE DATOS **/
	public static final String TABLE_PROGRAMA_TV = "ProgramaTV"; 
	public static final String TABLE_DATO_AUDIENCIA = "DatoAudiencia"; 
	public static final String TABLE_USERS = "Users";
	
	/** CONSTANTES DE LA APLICACION **/
	public static final int NUM_PROGRAMAS_TOP = 5;
	public static final String FORMAT_DATE = "yyyy-MM-dd'T'HH:mm";
	public static final String[] SPAIN_PROVINCES_ARRAY= {
			"Alava","Albacete","Alicante","Almeria","Asturias","Avila","Badajoz","Barcelona","Burgos","Caceres",
			 "Cadiz","Cantabria","Castellon","Ciudad Real","Cordoba","A Coruna","Cuenca","Girona","Granada","Guadalajara",
			 "Guipuzcoa","Huelva","Huesca","Baleares","Jaen","Leon","Lleida","Lugo","Madrid","Malaga","Murcia","Navarra",
			 "Palencia","Las Palmas","Pontevedra", "Ourense", "La Rioja","Salamanca","Segovia","Sevilla","Soria","Tarragona",
			 "Santa Cruz de Tenerife","Teruel","Toledo","Valencia","Valladolid","Vizcaya","Zamora","Zaragoza"
	};
	public static final Set<String> ADMINISTRATORS = new HashSet<String>(Arrays.asList(
		     new String[] {
		 			"poynting.slp@gmail.com",
					"naomi.glanga@gmail.com",
					"ajalza94@gmail.com",
					"pacoard@gmail.com"
			}
		));
	public static final String LOGIN_ERROR_MESSAGE = "Ha habido un error con tus credenciales";
	public static final String ACCESS_DENIED = "No se le permite acceder a este recurso";
	public static final String PROGRAMATV_DENIED = "Aún no se dispone de los datos suficientes para mostrar este recurso.";
	
	public static final String DEMOSTRING = "#DemoSocialTVISST";
	/**
	 * Método para imprimir arrays de la forma "[a, b, c... ]"
	 * 
	 * @param items
	 * @return result
	 */
	public static String getArrayString(String[] items){
	    String result = "[";
	    for(int i = 0; i < items.length; i++) {
	        result += "\"" + items[i] + "\"";
	        if(i < items.length - 1) {
	            result += ", ";
	        }
	    }
	    result += "]";

	    return result;
	}
	
	/**
	 * Arreglo del anterior para imprimir array de ints
	 * 
	 * @param items
	 * @return result
	 */
	public static String getArrayInt(int[] items){
	    String result = "[";
	    for(int i = 0; i < items.length; i++) {
	        result += items[i];
	        if(i < items.length - 1) {
	            result += ", ";
	        }
	    }
	    result += "]";

	    return result;
	}
	
	/**
	 * Comprueba el login de la sesión. 
	 * 		=> True si el login es válido
	 * 		=> False si el login no es válido de acuerdo con el rol.
	 * 
	 * @param req HttpServletRequest
	 * @param res HttpServletResponse
	 * @param rol String
	 */
	public static boolean checkLogin(HttpServletRequest req) {
		
		if (req.getUserPrincipal() == null){
			return false;
		}
		String user = req.getUserPrincipal().getName();
		
		//Validar el login
		if (ADMINISTRATORS.contains(user)) {
			return true;
		} else if (!"".equals(user)) { //un usuario cualquiera
			return true;
		}
		return false;
	}
	
	/**
	 * Comprueba el login de admin sólo. 
	 * 		=> True si el login es válido
	 * 		=> False si el login no es válido de acuerdo con el rol.
	 * 
	 * @param req HttpServletRequest
	 * @param res HttpServletResponse
	 * @param rol String
	 */
	public static boolean checkLoginAdmin(HttpServletRequest req) {
		if (req.getUserPrincipal() == null){
			return false;
		}
		String user = req.getUserPrincipal().getName();
		//Validar el login
		if (ADMINISTRATORS.contains(user)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Redirige a la página de inicio con un mensaje de error
	 * 
	 * @param req HttpServletRequest
	 * @param res HttpServletResponse
	 * @param error String
	 */
	public static void redirigirLogin(HttpServletRequest req, HttpServletResponse res, String error) {
		try {
			req.getSession().setAttribute("errorMsg", error);
			res.sendRedirect("/welcome");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
