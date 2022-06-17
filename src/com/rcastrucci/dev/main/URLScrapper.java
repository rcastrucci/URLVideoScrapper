package com.rcastrucci.dev.main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
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
import javax.swing.JFileChooser;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.rcastrucci.dev.model.Reader;
import com.rcastrucci.dev.view.View;

import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class URLScrapper {

	private JFrame frame;
	static JButton btnStart = new JButton("Start");
	static JButton btnSource = new JButton("Source");
	static JButton btnDestination =  new JButton("Destination");
	static String selectedSource;
	static String selectedDestination;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					URLScrapper window = new URLScrapper();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public URLScrapper() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblNewJgoodiesTitle = DefaultComponentFactory.getInstance().createTitle("URL Scrapper for Vimeo");
		lblNewJgoodiesTitle.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
		lblNewJgoodiesTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewJgoodiesTitle.setForeground(Color.WHITE);
		
		JTextPane txtpnUrlScrapperWill = new JTextPane();
		txtpnUrlScrapperWill.setEnabled(false);
		txtpnUrlScrapperWill.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		txtpnUrlScrapperWill.setEditable(false);
		txtpnUrlScrapperWill.setForeground(Color.LIGHT_GRAY);
		txtpnUrlScrapperWill.setBackground(Color.DARK_GRAY);
		txtpnUrlScrapperWill.setText("URL Scrapper will search for vimeo links in html files. Just save the pages in a folder and select as a source!");
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNewJgoodiesTitle, GroupLayout.PREFERRED_SIZE, 450, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
									.addComponent(txtpnUrlScrapperWill, GroupLayout.PREFERRED_SIZE, 329, GroupLayout.PREFERRED_SIZE)
									.addGap(59))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(146)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnSource, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnDestination, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnStart, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(lblNewJgoodiesTitle, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtpnUrlScrapperWill, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(31)
					.addComponent(btnSource)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnDestination)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnStart)
					.addContainerGap(47, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
		
	    btnStart.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	start();
	        }
	    });
	    
	    btnSource.addActionListener(new ActionListener() { 
	    	public void actionPerformed(ActionEvent e) {
	        	JFileChooser chooser;
	    	    chooser = new JFileChooser(); 
	    	    chooser.setCurrentDirectory(new java.io.File("/~"));
	    	    chooser.setDialogTitle("Select a source");
	    	    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    	    chooser.setAcceptAllFileFilterUsed(false);
	    	    if (chooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) { 
	    	    	selectedSource = chooser.getSelectedFile().toString();
	    	    } else {
	    	    	selectedSource = null;
	    	    }
	        }
	    });
	    
	    
	    btnDestination.addActionListener(new ActionListener() { 
	    	public void actionPerformed(ActionEvent e) {
	        	JFileChooser chooser;
	    	    chooser = new JFileChooser(); 
	    	    chooser.setCurrentDirectory(new java.io.File("/~"));
	    	    chooser.setDialogTitle("Select a source");
	    	    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    	    chooser.setAcceptAllFileFilterUsed(false);
	    	    if (chooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) { 
	    	    	selectedDestination = chooser.getSelectedFile().toString();
	    	    } else {
	    	    	selectedDestination = null;
	    	    }
		    }
	    });
	}
	
	public static void start() {
		
		if (selectedSource == null) {
			View.mensagem("Missing a source folder");
		} else {			
			if (selectedDestination == null) {
				View.mensagem("Missing a destination folder");
			} else {				
				if (View.option("Confirmation", "Source: "+selectedSource+"\r\n Destination: "+selectedDestination)) {			
					scrapper();
				} else {
					System.out.println("Job cancelled!");
				}
			}
		}
	}
	
	public static void scrapper() {
		
		System.out.println("Readding folder...");
		System.out.println(selectedSource);
		File folderSource = new File(selectedSource);

		ArrayList<String> list = listFiles(folderSource);
		
		Reader.reset();
		for (String filename : list) {
			Reader.run(selectedSource+"/"+filename);
		}
		
		System.out.println(Reader.linkList.size()+" links found!");
		if (View.option("Download", Reader.linkList.size()+" links found! Would you like to download them into "+selectedDestination)) {
			int index = 0;
			for (String url : Reader.linkList) {
				try {
					download(url, selectedDestination+"/"+Reader.titleList.get(index)+".mp4");
				} catch (IOException e) {
					e.printStackTrace();
				}
				index++;
			}
			View.mensagem("Download completed", Reader.linkList.size()+" links were downloaded successfully!");
		} else {
			System.out.println("Job cancelled!");
		}
	}
	
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
	
	public static void download(String url, String path) throws IOException {
		InputStream in = new URL(url).openStream();
		Files.copy(in, Paths.get(path), StandardCopyOption.REPLACE_EXISTING);
	}
}
