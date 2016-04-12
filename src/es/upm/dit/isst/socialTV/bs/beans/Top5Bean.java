package es.upm.dit.isst.socialTV.bs.beans;

import java.io.Serializable;
import es.upm.dit.isst.socialTV.bs.model.ProgramaTV;

@SuppressWarnings("serial") //pa que no salga un warning porculero
public class Top5Bean implements Serializable {
	/**
	 * Array de filas de la base de datos de ProgramasTV
	 */
	private ProgramaTV[] programasTop5;
	
	/**
	 * Constructor
	 */
	public Top5Bean() {}

	public ProgramaTV[] getProgramasTop5() {
		return programasTop5;
	}

	public void setProgramasTop5(ProgramaTV[] programasTop5) {
		this.programasTop5 = programasTop5;
	}
	
	
}
