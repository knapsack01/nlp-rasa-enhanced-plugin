package co.aurasphere.botmill.rasa.incoming.rasa.model;

import com.google.gson.annotations.SerializedName;

public class DucklingTimeEntityValue extends RasaEntityValue{
	
	@SerializedName("from")
	private String from;
	@SerializedName("to")
	private 