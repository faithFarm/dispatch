package org.dispatch.faithfarm.domain;

import java.io.Serializable;



public class DropDownItem implements Serializable {
	
	private String value;
	private String label;
	
	public DropDownItem(String value, String label) {
		this.value=value.toUpperCase();
		this.label=label.toUpperCase();
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
	
	

}