package com.rcastrucci.dev.urlscrapper;

import java.io.*;
import java.util.ArrayList;  

public class Reader {
	
	public static ArrayList<String> linkList = new ArrayList<String>();
	public static ArrayList<String> titleList = new ArrayList<String>();
	
	public static void run(String filename) {  
		try {  
			File file=new File(filename);    //creates a new file instance  
			FileReader fr=new FileReader(file);   //reads the file  
			BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream  
			StringBuffer sb=new StringBuffer();    //constructs a string buffer with no characters  
			String line;  
			while((line=br.readLine())!=null) {  
				if (line.contains("\"width\":1280,\"mime\":\"video/mp4\"")) {				
					String[] parts = line.split("\"width\":1280,\"mime\":\"video/mp4\"");
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