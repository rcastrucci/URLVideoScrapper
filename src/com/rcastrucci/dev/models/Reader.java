package com.rcastrucci.dev.models;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import com.rcastrucci.dev.utils.Config;
import com.rcastrucci.dev.utils.Connection;
import com.rcastrucci.dev.utils.Convert;
import com.rcastrucci.dev.views.AdvancedWindow;  

public class Reader {
	
	public static ArrayList<String> linkList;
	public static ArrayList<String> linksFound;
	public static ArrayList<String> filesFound;
	public static ArrayList<String> titleList;
	public static ArrayList<String> fileList;
	private static AdvancedWindow advancedWindow = AdvancedWindow.getInstance();
	
	public static void reset() {
		// Reset lists
		linkList 	= new ArrayList<String>();
		linksFound 	= new ArrayList<String>();
		filesFound 	= new ArrayList<String>();
		titleList 	= new ArrayList<String>();
		fileList 	= new ArrayList<String>();
		advancedWindow.setConfig();
	}
	
	public static void run(String filePath, String fileName) {  
		try { 
			
			File file;
			String generalTitle;
			
			if (fileName != null) {
				file=new File(filePath+"/"+fileName);  			//creates a new file instance with a folder and filename
				generalTitle = fileName.replaceAll("/", "_");
			} else {
				file=new File(filePath);						//creates a new file instance just with a path containing filename
				generalTitle = filePath.split("/")[filePath.split("/").length-1];
			}

			FileReader fileReader = new FileReader(file);   				//reads the file  
			BufferedReader bufferedReader = new BufferedReader(fileReader);  			//creates a buffering character input stream  
			StringBuffer stringBuffer = new StringBuffer();    				//constructs a string buffer with no characters  
			String line; 
			
			// ADVANCED MODE
			if (Config.getInstance().getProperty("plataform").equals("advanced")) {
				System.out.println("ADVANCED MODE");
				String[] fileTypes = Config.getInstance().getProperty("filetype").replaceAll("\\s","").split(",");
				
				if (Arrays.asList(fileTypes).contains("*") || Arrays.stream(fileTypes).anyMatch(file.getName()::contains)) {
					
					// Add a file to the read list to count later
					fileList.add(file.getName());
					
					while((line = bufferedReader.readLine())!=null) {
						// Specific Embbed videos
						if (line.contains(advancedWindow.getInputLineContains().getText())) {	
							String[] parts = line.split(advancedWindow.getInputStart().getText());
							String[] parts1 = parts[1].split(advancedWindow.getInputEnd().getText());
							String link = Convert.string.decode(parts1[0]);
							
							linksFound.add(link);
							filesFound.add(file.getAbsolutePath());
							
							// If connection returns 200 Ok adds to the list
							if (Connection.isLink200(link)) {							
								linkList.add(link);
								titleList.add(generalTitle+"_"+linkList.size()+1);
								stringBuffer.append(link);
								stringBuffer.append("\n");
							}
						}
					}
				}
			}
			// STANDARD MODE SEARCHS ONLY IN HTML FILES
			else {
				if (file.getName().toLowerCase().contains(".html")) {
					
					// Add a file to the read list
					fileList.add(file.getName());
					
					// READS EVERY LINE AND SEARCH FOR THE PATTERN
					while((line = bufferedReader.readLine())!=null) {
						String urlTitle = "";
						String link = "";
						boolean foundCondition = false;
						
						// VIMEO PATTERN
						if (line.contains("\"width\":"+Config.getInstance().getProperty("width")+",\"mime\":\"video/mp4\"")) {		
							String[] parts = line.split("\"width\":"+Config.getInstance().getProperty("width")+",\"mime\":\"video/mp4\"");
							String[] parts1 = parts[1].split("https");
							String[] parts2 = parts1[1].split("\"");
							link = "https"+parts2[0];
							String[] tparts = line.split("<title>");
							String[] tparts1 = tparts[1].split("</title>");
							String vimeoTitle = tparts1[0];
							urlTitle = generalTitle+"_"+vimeoTitle;							
							foundCondition = true;
						}
						
						// LINKEDIN PATTERN
						if (line.contains("<video")) {		
							String[] parts = line.split("src=\"");
							String[] parts1 = parts[1].split("\"");
							link = Convert.string.decode(parts1[0]);
							urlTitle = generalTitle+"_"+linkList.size()+1;
							foundCondition = true;
						}
						
						if (foundCondition) {
							// Add links to list
							linksFound.add(link);
							filesFound.add(file.getAbsolutePath());
							
							// If connection returns 200 Ok adds to the list
							if (Connection.isLink200(link)) {							
								linkList.add(link);
								titleList.add(urlTitle);
								stringBuffer.append(link);
								stringBuffer.append("\n");
								System.out.println("URL Response -> 200 OK");
							} else {
								System.out.println("URL Response -> Failed!");
							}
						}
					}
				}
			}
			fileReader.close();
		} catch(IOException e) {  
			System.out.println("Not a URL... "+e.getMessage());			
		}
	}
}  