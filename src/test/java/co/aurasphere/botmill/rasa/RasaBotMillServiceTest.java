/**
 * 
 * MIT License
 *
 * Copyright (c) 2017 BotMill.io
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 * 
 */
package co.aurasphere.botmill.rasa;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.LinkedTreeMap;

import co.aurasphere.botmill.rasa.incoming.rasa.model.DucklingTimeEntityValue;
import co.aurasphere.botmill.rasa.incoming.rasa.model.Response;
import co.aurasphere.botmill.rasa.incoming.rasa.model.StringEntityValue;
import co.aurasphere.botmill.rasa.service.RasaService;
import co.aurasphere.botmill.util.JsonUtils;

/**
 * The Class RasaBotMillServiceTest.
 */
public class RasaBotMillServiceTest {

	/**
	 * Setup.
	 */
	@Before
	public void setup() {
		RasaBotMillContext.configure().setup("http://<RASA_SERVER>:5001","<TOKEN>");
	}

	/**
	 * Test parse.
	 */
	@Test
	@Ignore
	public void testParse() {
		if (checkConnection()) {
			Response resp = RasaService.sendParseRequest("CYYZ KJFK 15JUL2017 20JUN2017");
			resp.searchForDucklingValue("time");
			System.out.println(resp.getText());
			assertNotNull(resp);
		}
		assert (true);
	}
	
	@Test
	@Ignore
	public void testParseComplexDate() {
		if (checkConnection()) {
			Response resp = RasaService.sendParseRequest("can you setup request from uggw to cyyz tomorrow to friday?");
			System.out.println(resp.searchForStringEntityValue("departure_icao").getStringValue());
			System.out.println(((DucklingTimeEntityValue)resp.searchForDucklingValue("start_date")).getTo());
			System.out.println(((DucklingTimeEntityValue)resp.searchForDuckli