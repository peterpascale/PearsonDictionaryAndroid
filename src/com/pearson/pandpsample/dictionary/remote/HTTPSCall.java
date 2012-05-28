package com.pearson.pandpsample.dictionary.remote;

import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.SingleClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;


/**
 * Class representing a remote service call against an HTTPS-hosted service.
 * 
 * @author Peter Pascale
 */
public class HTTPSCall {
	
	private static final String HTTPS_SCHEME_NAME = "https";
	private String urlValue;
	
	
	public HTTPSCall(String urlValue) {
		this.urlValue = urlValue;
	}
	
	public String doRemoteCall() throws Exception {
				
		HttpClient httpsClient = makeHTTPSClient();
		HttpGet httpsGet = new HttpGet(urlValue);
		HttpResponse response;
		String result = null;
		    response = httpsClient.execute(httpsGet);
		    HttpEntity entity = response.getEntity();
		    if (entity != null) {
		        InputStream instream = entity.getContent();
		        result = InputStreamConverter.convertToString(instream);
		        // Closing the input stream triggers connection release
		        instream.close();
		    }
		return result;
	}

	private HttpClient makeHTTPSClient() {
		SchemeRegistry schemeRegistry = new SchemeRegistry();
		schemeRegistry.register(new Scheme(HTTPS_SCHEME_NAME, SSLSocketFactory.getSocketFactory(), 443));

		HttpParams params = new BasicHttpParams();
		SingleClientConnManager mgr = new SingleClientConnManager(params, schemeRegistry);

		return new DefaultHttpClient(mgr, params);
	}
}
