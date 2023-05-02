package co.aurasphere.botmill.rasa.incoming.rasa.model;

import com.google.gson.annotations.SerializedName;

public class StringEntityValue extends RasaEntityValue{
	
	@SerializedName("value")
	private String stringValue;

	public String getStringValue() {
		if(stringValue != null) {
			return stringValue.replaceAll("^\"|\"$", "");
		}
		return stringValue;
	}

	public void setStringValue(String stringValue) {
