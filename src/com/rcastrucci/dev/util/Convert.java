package com.rcastrucci.dev.util;

public class Convert {

	public static class string {
		
	    public static String decode(String strData) {
	        if (strData == null) {
	            return "";
	        }/*from   w ww . j a  v a  2s.c o m*/
	        return strData.replaceAll("&lt;", "<").replaceAll("&gt;", ">")
	                .replaceAll("&apos;", "'").replaceAll("&quot;", "\"")
	                .replaceAll("&amp;", "&");
	    }
	}
}
