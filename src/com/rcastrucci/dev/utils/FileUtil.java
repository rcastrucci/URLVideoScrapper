package com.rcastrucci.dev.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

/**
 * Utility class to perform actions related with files and directories
 * @author rcastrucci.dev
 *
 */
public class FileUtil {

	/**
	 * Method to list all files in a folder and subfolders
	 * @param folder is a String with the full path to a directory to scan
	 * @return a List of Strings with all filenames
	 */
	public static ArrayList<String> listFiles(File folder) {
		ArrayList<String> files = new ArrayList<String>();
	    for (File fileEntry : folder.listFiles()) {
	    	// If finds a directory open and list files in
	        if (fileEntry.isDirectory()) {
	        	// Get files from this directory into a list
	        	ArrayList<String> children = new ArrayList<String>();
	            children = listFiles(fileEntry);
	            // Get the name of the directory isolated
	            String[] dir = fileEntry.toString().split("/");
	            // Loop throught all filenames found inside the directory and add to the files list
	            for (String filename : children) {
	            	files.add(dir[dir.length-1]+"/"+filename);
	            }
	        // Else if is a file just add to the list
	        } else {
	        	files.add(fileEntry.getName());
	        }
	    }
	    return files;
	}
	
	
	/**
	 * Method to download a file streaming from a URL address
	 * @param url is a parameter type String with a http address
	 * @param path is a full path to a folder where file should be written
	 * @throws IOException exception for any type of IO exceptions
	 */
	public static void download(String url, String path) throws IOException {
		InputStream in = new URL(url).openStream();
		Files.copy(in, Paths.get(path), StandardCopyOption.REPLACE_EXISTING);
	}
	
}