package com.rcastrucci.dev.urlscrapper;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.rcastrucci.dev.view.View;

public class Scrapper {
	
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	static FileChooser btnSource;
    static FileChooser btnDestination;
	
	public static void main(String[] args) {
		
		btnSource = new FileChooser("Choose a source folder", "Source");
	    btnDestination = new FileChooser("Choose a folder to save", "Destination");
		JPanel panel = new JPanel();
		JFrame frame = new JFrame("URL Scrapper");
		JButton start= new JButton("Start"); 
	    
	    start.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	start();
	        }
	    });

	    // Adding stuff to panel
		panel.setLayout(new BorderLayout());
		panel.add(btnSource, BorderLayout.BEFORE_FIRST_LINE);
		panel.add(btnDestination, BorderLayout.CENTER);
		panel.add(start, BorderLayout.AFTER_LAST_LINE);

	    // Setting up frame
		frame.setSize(new Dimension(400, 150));
	    frame.setLocation((int) screenSize.getWidth()/2-200, (int) screenSize.getHeight()/2-75);
	    frame.getContentPane().add(panel, "Center");
	    frame.setVisible(true);
		
	}
		
	public static void start() {
		
		if (btnSource.getSelectedFolder() == null) {
			View.mensagem("Missing a source folder");
		} else {			
			if (btnDestination.getSelectedFolder() == null) {
				View.mensagem("Missing a destination folder");
			} else {				
				if (View.option("Confirmation", "Source: "+btnSource.getSelectedFolder()+"\n Destination: "+btnDestination.getSelectedFolder())) {			
					scrapper();
				} else {
					System.out.println("Job cancelled!");
				}
			}
		}
	}
	
	public static void scrapper() {
		
		System.out.println("Readding folder...");
		System.out.println(btnSource.getSelectedFolder());
		
		File folderSource = new File(btnSource.getSelectedFolder());
		
		ArrayList<String> list = listFilesForFolder(folderSource);
		for (String filename : list) {
			Reader.run(btnSource.getSelectedFolder()+"/"+filename);
		}
		
		System.out.println(Reader.linkList.size()+" links found!");
		if (View.option("Download", Reader.linkList.size()+" links found! Would you like to download them into "+btnDestination.getSelectedFolder())) {
			int index = 0;
			for (String url : Reader.linkList) {
				try {
					download(url, btnDestination.getSelectedFolder()+"/"+Reader.titleList.get(index)+".mp4");
				} catch (IOException e) {
					e.printStackTrace();
				}
				index++;
			}
		} else {
			System.out.println("Job cancelled!");
		}
	}
	
	public static ArrayList<String> listFilesForFolder(File folder) {
		ArrayList<String> files = new ArrayList<String>();
	    for (File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            // listFilesForFolder(fileEntry);
	        } else {
	        	files.add(fileEntry.getName());
	        }
	    }
	    return files;
	}
	
	public static void download(String url, String path) throws IOException {
		InputStream in = new URL(url).openStream();
		Files.copy(in, Paths.get(path), StandardCopyOption.REPLACE_EXISTING);
	}
}