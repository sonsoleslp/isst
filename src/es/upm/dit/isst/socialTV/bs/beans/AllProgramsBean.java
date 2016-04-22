package es.upm.dit.isst.socialTV.bs.beans;

import java.io.Serializable;
import java.util.ArrayList;

import es.upm.dit.isst.socialTV.bs.model.ProgramaTV;

public class AllProgramsBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	ArrayList <ProgramaTV> progs;
	String error;
	
	public AllProgramsBean(ArrayList<ProgramaTV> progs, String error) {
		super();
		this.progs = progs;
		this.error = error;
	}

	public ArrayList<ProgramaTV> getProgs() {
		return progs;
	}

	public void setProgs(ArrayList<ProgramaTV> progs) {
		this.progs = progs;
	}
	
	public String getError() {
		if(error=="")
			return "0";
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	
}
