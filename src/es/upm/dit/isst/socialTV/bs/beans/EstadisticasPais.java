package es.upm.dit.isst.socialTV.bs.beans;

import java.awt.List;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

import es.upm.dit.isst.socialTV.bs.model.ProgramaTV;
import es.upm.dit.isst.socialTV.bs.model.ProgramaTVDAO;
import es.upm.dit.isst.socialTV.bs.model.ProgramaTVImpl;

/**
 * Bean que sirve para transmitir datos entre el proyecto business y el web
 * (puede haber mas de uno)
 * @author Naomi
 *
 */
public class EstadisticasPais implements Serializable {
	
	// Pais al que pertenecen las estadisticas
	private PaisEnum pais;
	// Hashtag asociado a su impacto con respecto al numero de tweets mundiales
	private Map<String, Integer> impactoPorPais;
	
	public PaisEnum getPais() {
		return pais;
	}
	public void setPais(PaisEnum pais) {
		this.pais = pais;
	}
	public Map<String, Integer> getImpactoPorPais() {
		ProgramaTVDAO dao = ProgramaTVImpl.getInstance();
		ArrayList<ProgramaTV> prog = (ArrayList<ProgramaTV>) dao.todosLosProgramas();
		return impactoPorPais;
	}
	public void setImpactoPorPais(Map<String, Integer> impactoPorPais) {
		this.impactoPorPais = impactoPorPais;
	}	
}
