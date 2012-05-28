package com.pearson.pandpsample.dictionary.remote;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Conversion utility for HTTP request handling
 * 
 * @author Peter Pascale
 */
public class InputStreamConverter {

	public static String convertToString(InputStream is) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is), 8192);
		StringBuffer sb = new StringBuffer();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} finally {
			is.close();
		}
		return sb.toString();
	}
	
	public static String toLogString(Exception e) {
		final StringBuffer buffer = new StringBuffer();
		buffer.append(e.getClass().getName());
		buffer.append(": ");
		buffer.append(e.getMessage());
		return buffer.toString();
	}

}

