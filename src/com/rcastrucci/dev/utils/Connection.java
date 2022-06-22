package com.rcastrucci.dev.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Connection {
	
	public static boolean isLink200(String address) throws IOException {
		URL url = new URL(address);
		HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		connection.setRequestMethod("GET");
		connection.connect();

		int code = connection.getResponseCode();
		return (code == 200);
	}
	
}