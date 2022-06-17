package com.rcastrucci.dev.model;

import java.io.*;
import java.util.ArrayList;

import com.rcastrucci.dev.util.Config;  

public class Reader {
	
	public static ArrayList<String> linkList;
	public static ArrayList<String> titleList;
	
	public static void reset() {
		// Reset lists
		linkList = new ArrayList<String>();
		titleList = new ArrayList<String>();
	}
	
	public static void run(String filename) {  
		try { 
			File file=new File(filename);    			//creates a new file instance  
			FileReader fr=new FileReader(file);   		//reads the file  
			BufferedReader br=new BufferedReader(fr);  	//creates a buffering character input stream  
			StringBuffer sb=new StringBuffer();    		//constructs a string buffer with no characters  
			String line;  
			while((line=br.readLine())!=null) {  
				if (line.contains("\"width\":"+Config.getInstance().getProperty("width")+",\"mime\":\"video/mp4\"")) {				
					String[] parts = line.split("\"width\":"+Config.getInstance().getProperty("width")+",\"mime\":\"video/mp4\"");
					String[] parts1 = parts[1].split("https");
					String[] parts2 = parts1[1].split("\"");
					String link = "https"+parts2[0];
					
					String[] tparts = line.split("<title>");
					String[] tparts1 = tparts[1].split("</title>");
					String title = tparts1[0];					
					
					linkList.add(link);
					titleList.add(title);
					sb.append(link);
					sb.append("\n");
				}
			}  
			fr.close();
		} catch(IOException e) {  
			e.printStackTrace();  
		}  
	}
}  