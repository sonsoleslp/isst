package es.upm.dit.isst.socialTV.bs.services;

/**
 * @author Paco
 *
 * Clase para definir variables y métodos estáticos, útiles para todo el proyecto.
 */
public class GlobalUtil {

	
	/**	NOMBRES PARA DECLARACIONES DE BEANS EN JSP **/
	public static final String TOP_5_BEAN = "top5Bean";
	public static final String GRAPH_BEAN = "graphBean";
	public static final String SPAIN_MAP_BEAN = "spainMapBean";
	public static final String ALL_PROGS_BEAN = "allProgramsBean";

	//Esto puede ser útil por si nos da por cambiar el nombre a alguna tabla, 
	//y así sólo tener que cambiarlo aquí
	/**	NOMBRES DE LAS TABLAS DE BASES DE DATOS **/
	public static final String TABLE_PROGRAMA_TV = "ProgramaTV"; 
	public static final String TABLE_DATO_AUDIENCIA = "DatoAudiencia"; 
	public static final String TABLE_USERS = "Users";
	
	/** CONSTANTES DE LA APLICACION **/
	public static final int NUM_PROGRAMAS_TOP = 5;
	public static final String FORMAT_DATE = "yyyy-MM-dd'T'HH:mm";
	
	/**
	 * Igual te sirve para el Mapa, Naomi.
	 * Se puede hacer que el SpainMapBean tenga este valor siempre.
	 */
	public static final String[] SPAIN_PROVINCES_ARRAY= {
			"Alava","Albacete","Alicante","Almeria","Asturias","Avila","Badajoz","Barcelona","Burgos","Caceres",
			 "Cadiz","Cantabria","Castellon","Ciudad Real","Cordoba","La Coruña","Cuenca","Gerona","Granada","Guadalajara",
			 "Guipuzcoa","Huelva","Huesca","Islas Baleares","Jaen","Leon","Lerida","Lugo","Madrid","Malaga","Murcia","Navarra",
			 "Orense","Palencia","Las Palmas","Pontevedra","La Rioja","Salamanca","Segovia","Sevilla","Soria","Tarragona",
			 "Santa Cruz de Tenerife","Teruel","Toledo","Valencia","Valladolid","Vizcaya","Zamora","Zaragoza"
	};
	

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
}
