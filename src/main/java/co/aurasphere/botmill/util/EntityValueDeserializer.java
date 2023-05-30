
package co.aurasphere.botmill.util;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import co.aurasphere.botmill.rasa.incoming.rasa.model.DucklingTimeEntityValue;
import co.aurasphere.botmill.rasa.incoming.rasa.model.Entity;
import co.aurasphere.botmill.rasa.incoming.rasa.model.RasaEntityValue;
import co.aurasphere.botmill.rasa.incoming.rasa.model.StringEntityValue;
