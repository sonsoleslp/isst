package es.upm.dit.isst.socialTV.bs.services;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase que calcula el top 5
 * @author Naomi
 *
 */
public class CalculoImpactoMapa {
	
	private ConsultaAPITwitter consultaAPITwitter;
	
	/**
	 * No se si lo he hecho bien, con Spring que es como lo hacia yo en el
	 * trabajo no se crean constructores pq Spring te lo gestiona internamente
	 */
	public CalculoImpactoMapa() {
		super();
		this.consultaAPITwitter = new ConsultaAPITwitter();
    	
			Spain spain = Spain.getInstance();
			
			System.out.println( spain.whichProvince( -6.6630487, 38.1303432));
			System.out.println( spain.whichProvince( -4.321606, 42.064189));
			System.out.println( spain.whichProvince( -3.2902537, 41.820583));
			System.out.println( spain.whichProvince( -5.046704, 40.328340));
			
	}
	
}
	/*
	public EstadisticasPais calculoImpactoMapa(PaisEnum pais, String hashtag) {
		// Obtenemos el numero total de tweets a nivel mundial
		Trend trendMundial = consultaAPITwitter.consultarHashtag(hashtag);
		
		// Obtenemos el numero total de tweets en el pais dado
		Trend trendPais = consultaAPITwitter.consultarHashtag(hashtag, pais);
		
		// Calculamos el impacto como porcentaje de tweets
		int impacto = (int)((double)trendPais.getNumTweets()*100/trendMundial.getNumTweets());
		
		/*
		 *  OJO! Al crear unas nuevas estadisticas estaria cargandome los resultados
		 *  que pudiesen estar almacenados previamente. Ahora mismo se que no hay nada,
		 *  pero es importante llevar el control.
		 
		EstadisticasPais estadisticas = new EstadisticasPais();
		estadisticas.setPais(pais);
		Map<String, Integer> impactoPais = new HashMap<String, Integer>();
		impactoPais.put(hashtag, impacto);
		estadisticas.setImpactoPorPais(impactoPais);
		
		// Devolvemos las estadisticas a la web
		return estadisticas;
	}*/