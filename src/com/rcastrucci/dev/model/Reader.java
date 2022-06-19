package com.rcastrucci.dev.model;

import java.io.*;
import java.util.ArrayList;

import com.rcastrucci.dev.util.Config;
import com.rcastrucci.dev.util.Connection;
import com.rcastrucci.dev.util.Convert;  

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
				// VIMEO
				if (Config.getInstance().getProperty("plataform").equals("vimeo")) {
					if (line.contains("\"width\":"+Config.getInstance().getProperty("width")+",\"mime\":\"video/mp4\"")) {				
						String[] parts = line.split("\"width\":"+Config.getInstance().getProperty("width")+",\"mime\":\"video/mp4\"");
						String[] parts1 = parts[1].split("https");
						String[] parts2 = parts1[1].split("\"");
						String link = "https"+parts2[0];
						
						String[] tparts = line.split("<title>");
						String[] tparts1 = tparts[1].split("</title>");
						String title = tparts1[0];					
						
						// If connection returns 200 Ok adds to the list
						if (Connection.isLink200(link)) {							
							linkList.add(link);
							titleList.add(title);
							sb.append(link);
							sb.append("\n");
						}
						
					}
				} else if (Config.getInstance().getProperty("plataform").equals("linkedin")) {
					if (line.contains("<video")) {			
						String[] parts = line.split("src=\"");
						String[] parts1 = parts[1].split("\"");
						String link = Convert.string.decode(parts1[0]);
						
						String[] path = filename.split("/");
						String title = path[path.length-2]+"_"+linkList.size()+1;
						
						// If connection returns 200 Ok adds to the list
						if (Connection.isLink200(link)) {							
							linkList.add(link);
							titleList.add(title);
							sb.append(link);
							sb.append("\n");
						}
					}
				}
			}  
			fr.close();
		} catch(IOException e) {  
			e.printStackTrace();  
		}  
	}
}  