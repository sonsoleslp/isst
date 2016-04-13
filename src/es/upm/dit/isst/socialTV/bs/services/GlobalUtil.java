package es.upm.dit.isst.socialTV.bs.services;

/**
 * @author Paco
 *
 * Clase para definir variables y m�todos est�ticos, �tiles para todo el proyecto.
 */
public class GlobalUtil {
	
	/**
	 * Igual te sirve para el Mapa, Naomi.
	 * Se puede hacer que el SpainMapBean tenga este valor siempre.
	 */
	public static final String[] SPAIN_PROVINCES_ARRAY= {
			"Alava","Albacete","Alicante","Almer�a","Asturias","Avila","Badajoz","Barcelona","Burgos","C�ceres",
			 "C�diz","Cantabria","Castell�n","Ciudad Real","C�rdoba","La Coru�a","Cuenca","Gerona","Granada","Guadalajara",
			 "Guip�zcoa","Huelva","Huesca","Islas Baleares","Ja�n","Le�n","L�rida","Lugo","Madrid","M�laga","Murcia","Navarra",
			 "Orense","Palencia","Las Palmas","Pontevedra","La Rioja","Salamanca","Segovia","Sevilla","Soria","Tarragona",
			 "Santa Cruz de Tenerife","Teruel","Toledo","Valencia","Valladolid","Vizcaya","Zamora","Zaragoza"
	};
	
	/**	NOMBRES PARA DECLARACIONES DE BEANS EN JSP **/
	public static final String TOP_5_BEAN = "top5Bean";
	public static final String GRAPH_BEAN = "graphBean";
	public static final String SPAIN_MAP_BEAN = "spainMapBean";
	
	//Esto puede ser �til por si nos da por cambiar el nombre a alguna tabla, 
	//y as� s�lo tener que cambiarlo aqu�
	/**	NOMBRES DE LAS TABLAS DE BASES DE DATOS **/
	public static final String TABLE_PROGRAMA_TV = "ProgramaTV"; 
	public static final String TABLE_DATO_AUDIENCIA = "DatoAudiencia"; 
	public static final String TABLE_USERS = "Users";
	
	/** CONSTANTES DE LA APLICACION **/
	public static final int NUM_PROGRAMAS_TOP = 5;
	
	/**
	 * M�todo para imprimir arrays de la forma "[a, b, c... ]"
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
