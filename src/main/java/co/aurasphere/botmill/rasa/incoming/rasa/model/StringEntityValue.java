package co.aurasphere.botmill.rasa.incoming.rasa.model;

import com.google.gson.annotations.SerializedName;

public class StringEntityValue extends RasaEntityValue{
	
	@SerializedName("value")
	private String stringValue;

	pu