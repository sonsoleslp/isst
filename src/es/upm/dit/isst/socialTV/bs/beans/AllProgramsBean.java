package es.upm.dit.isst.socialTV.bs.beans;

import java.io.Serializable;
import java.util.ArrayList;

import es.upm.dit.isst.socialTV.bs.model.ProgramaTV;

public class AllProgramsBean implements Serializable {
	
	public AllProgramsBean(ArrayList<ProgramaTV> progs) {
		super();
		this.progs = progs;
	}

	ArrayList <ProgramaTV> progs;

	public ArrayList<ProgramaTV> getProgs() {
		return progs;
	}

	public void setProgs(ArrayList<ProgramaTV> progs) {
		this.progs = progs;
	}
	
}
