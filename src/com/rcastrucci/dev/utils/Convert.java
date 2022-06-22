package com.rcastrucci.dev.utils;

/**
 * Class for utilities to work with conversions
 * @author rcastrucci.dev
 *
 */
public class Convert {

	/**
	 * Class to convert String
	 * @author rcastrucci.dev
	 *
	 */
	public static class string {
		
		/**
		 * Method to decode strings with special letters from url
		 * @param strData is a String parameter with content to be decoded 
		 * @return String
		 */
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
