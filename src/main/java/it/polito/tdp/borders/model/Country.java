package it.polito.tdp.borders.model;

import java.util.Objects;

public class Country {
	
	private String StateAbb;
	private int CCod;
	private String StateName;
	
	
	public Country(String stateAbb, int cCod, String stateName) {
		super();
		StateAbb = stateAbb;
		CCod = cCod;
		StateName = stateName;
	}


	@Override
	public int hashCode() {
		return Objects.hash(CCod, StateAbb);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		return CCod == other.CCod && Objects.equals(StateAbb, other.StateAbb);
	}


	public String getStateAbb() {
		return StateAbb;
	}


	public void setStateAbb(String stateAbb) {
		StateAbb = stateAbb;
	}


	public int getCCod() {
		return CCod;
	}


	public void setCCod(int cCod) {
		CCod = cCod;
	}


	public String getStateName() {
		return StateName;
	}


	public void setStateName(String stateName) {
		StateName = stateName;
	}


	@Override
	public String toString() {
		return StateName;
	}
	
	
	

}
