package com.pearson.pandpsample.dictionary.remote;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.pearson.pandpsample.dictionary.DictionaryEntry;
import com.pearson.pandpsample.dictionary.Settings;

/**
 * Wrapper around the Longman Dictionary API on Plug and Play.
 * 
 * @author Peter Pascale
 */
public class LongmanAPIHelper {

	private static final String URL_PREFIX = 
		"https://api.pearson.com/longman/dictionary/entry/random.json?apikey=";

	private static final String KEY_ENTRY = "Entry";
	private static final String KEY_HEAD = "Head"; 
	private static final String KEY_WORD = "HWD";
	private static final String KEY_TEXT = "#text";
	private static final String KEY_SENSE = "Sense";
	
	private static final String KEY_DEFINITION = "DEF";
	
	public String getRandomJSON() throws Exception {
		
		HTTPSCall call = new HTTPSCall(URL_PREFIX + Settings.API_KEY);
		String s = call.doRemoteCall();
		return s;
	}
		
	public DictionaryEntry getRandomDictionaryEntry() throws Exception {
		
		String url = URL_PREFIX + Settings.API_KEY;
		HTTPSCall call = new HTTPSCall(url);
		Log.i(Settings.LOG_TAG, url);
		return deserializeDictionaryEntry(call.doRemoteCall());
	}
	
	
	private DictionaryEntry deserializeDictionaryEntry(String entryJSONAsString) throws Exception {
		Log.i(Settings.LOG_TAG, entryJSONAsString);
		DictionaryEntry entry;
		try {
			JSONObject entryJSON = new JSONObject(entryJSONAsString);
			entry = deserializeEntry(entryJSON);
		} catch (JSONException e) {
			throw new RuntimeException(e);
		}
		return entry;
	}
	
	private DictionaryEntry deserializeEntry(JSONObject entryJSON) throws Exception {
		return new DictionaryEntry(entryJSON.getJSONObject(KEY_ENTRY).
				getJSONObject(KEY_HEAD).getJSONObject(KEY_WORD).getString(KEY_TEXT), 
				
				entryJSON.getJSONObject(KEY_ENTRY).getJSONObject(KEY_SENSE).
				getJSONObject(KEY_DEFINITION).getString(KEY_TEXT));
	}

}
