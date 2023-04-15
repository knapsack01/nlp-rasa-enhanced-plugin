package co.aurasphere.botmill.rasa.incoming.rasa.model;

import com.google.gson.annotations.SerializedName;

public class DucklingTimeEntityValue extends RasaEntityValue{
	
	@SerializedName("from")
	private String from;
	@SerializedName("to")
	private String to;
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.fro