package co.aurasphere.botmill.rasa.incoming.rasa.model;

import com.google.gson.annotations.SerializedName;

public abstract class RasaEntityValue {
	@SerializedName("value")
	private String stringValue;

	public String getStringValue() {
		if(stringValue != null) {
			return stringValue.replaceAll("^\"|\"$", "");
		}
		retu