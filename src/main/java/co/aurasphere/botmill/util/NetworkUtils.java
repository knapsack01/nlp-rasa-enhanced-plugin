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
package co.aurasphere.botmill.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import co.aurasphere.botmill.rasa.RasaBotMillContext;
import co.aurasphere.botmill.rasa.exception.RasaPluginError;
import co.aurasphere.botmill.rasa.exception.RasaPluginErrorMessage;


/**
 * The Class NetworkUtils.
 */
public class NetworkUtils {

	/**
	 * The logger.
	 */
	private static final Logger logger = LoggerFactory.getLogger(NetworkUtils.class);

	/**
	 * Post json config.
	 *
	 * @param input
	 *            the input
	 * @return the string
	 */
	public static String postParse(Object input) {
		StringEntity stringEntity = toStringEntity(input);
		HttpPost post = new HttpPost(RasaBotMillContext.getRasaConfig().toString() + NetworkConstants.PRASE_EP + concatToken());
		post.setHeader("Content-Type", "application/json");
		post.setEntity(stringEntity);
		return send(post);
	}

	/**
	 * Post json message.
	 *
	 * @param jsonData the json data
	 * @return the string
	 */
	public static String postTrainingString(String jsonData) {
		StringEntity stringEntity;
		try {
			stringEntity = new StringEntity(jsonData);
			stringEntity.setContentType("application/json");
			HttpPost post = new HttpPost(RasaBotMillContext.getRasaConfig().toString() + NetworkConstants.TRAIN_EP + concatToken());
			post.setEntity(stringEntity);
			return send(post);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Post json message broadcast.
	 *
	 * @return the string
	 */
	public static String getStatus() {
		HttpGet get = new HttpGet(RasaBotMillContext.getRasaConfig().toString()  + NetworkConstants.STATUS_EP + concatToken());
		get.setHeader("Content-Type", "application/json");
		return send(get);
	}

	/**
	 * Send.
	 *
	 * @param request
	 *            the request
	 * @return the string
	 */
	private static String send(HttpRequestBase request) {

		CloseableHttpClient httpClient = HttpClientBuilder.create().build();

		logger.debug(request.getRequestLine().toString());
		HttpResponse httpResponse = null;
		String response = null;
		try {
			httpResponse = httpClient.execute(request);
			response = logResponse(httpResponse);
		} catch (Exception e) {
			logger.error("Error during HTTP connection to RASA: ", e);
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				logger.error("Error while closing HTTP connection: ", e);
			}
		}
		return response;
	}

	/**
	 * DELETEs a JSON string to Facebook.
	 * 
	 * @param input
	 *            the JSON data to send.
	 */
	public static void delete(Object input) {
		StringEntity stringEntity = toStringEntity(input);
		delete(stringEntity);
	}

	/**
	 * Post.
	 *
	 * @param url
	 *            the url
	 * @param entity
	 *            the entity
	 * @return the string
	 */
	public static String post(String url, StringEntity entity) {
		HttpPost post = new HttpPost(url);
		post.setHeader("Content-Type", "application/x-www-form-urlencoded");
		post.setEntity(entity);
		return send(post);
	}

	/**
	 * Utility to send a GET request.
	 * 
	 * @param url
	 *            the url we need to send the get request to.
	 * @return {@link String}
	 */
	public static String get(String url) {
		HttpGet get = new HttpGet(url);
		return send(get);
	}

	/**
	 * To string entity.
	 *
	 * @param object
	 *            the object
	 * @return the string entity
	 */
	private static StringEntity toStringEntity(Object object) {
		StringEntity input = null;
		try {
			String json = JsonUtils.toJson(object);
			input = new StringEntity(json);