package com.ingenieriahuemul.flamencoserver.model.dto;

/**
 * This class wraps status mas data for it will be send to cloud.
 * 
 * @author jonathan
 *
 */

public class EstadoMasDTO {
	
	private String id;
	
	private String shortName;
	
	private String longName;
	
	private String value;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getShortName() {
		return shortName;
	}
	
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	
	public String getLongName() {
		return longName;
	}
	
	public void setLongName(String longName) {
		this.longName = longName;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
}
