package com.rcastrucci.dev.main;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Font;
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
import javax.swing.JFileChooser;
import com.rcastrucci.dev.model.Reader;
import com.rcastrucci.dev.util.Config;
import com.rcastrucci.dev.view.View;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JRadioButton;

public class URLScrapper {

	private JFrame frame;
	private static JButton btnStart = new JButton("Start");
	private static JButton btnSource = new JButton("Source");
	private static JButton btnDestination =  new JButton("Destination");
	private static String selectedSource;
	private static String selectedDestination;
	private static JLabel labelStatus = new JLabel("Status: waiting");
	private static JRadioButton radioAdvanced = new JRadioButton("Advanced");
	private static JRadioButton radioStandard = new JRadioButton("Standard");

	//frame.setLocation((int) , ;

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
		labelStatus.setBackground(Color.DARK_GRAY);
		labelStatus.setFont(new Font("Helvetica Neue", Font.ITALIC, 12));
		labelStatus.setForeground(Color.GRAY);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setBounds((int) (screenSize.getWidth()/2 - 225), (int) (screenSize.getHeight()/2-150), 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTextPane txtpnUrlScrapperWill = new JTextPane();
		txtpnUrlScrapperWill.setEnabled(false);
		txtpnUrlScrapperWill.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		txtpnUrlScrapperWill.setEditable(false);
		txtpnUrlScrapperWill.setForeground(Color.LIGHT_GRAY);
		txtpnUrlScrapperWill.setBackground(Color.DARK_GRAY);
		txtpnUrlScrapperWill.setText("URL Scrapper will search for video links in html files. Just save the pages in a folder and select as a source!");
		
		JLabel lblNewLabel = new JLabel("URL Video Scrapper");
		lblNewLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		

		radioAdvanced.setBackground(Color.DARK_GRAY);
		radioAdvanced.setForeground(Color.GRAY);
		radioAdvanced.setSelected(Config.getInstance().getProperty("plataform").equals("advanced"));
		
		radioStandard.setBackground(Color.DARK_GRAY);
		radioStandard.setForeground(Color.GRAY);
		radioStandard.setSelected(Config.getInstance().getProperty("plataform").equals("standard"));
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addGap(17)
							.addComponent(labelStatus, GroupLayout.PREFERRED_SIZE, 417, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 438, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addGap(58)
							.addComponent(txtpnUrlScrapperWill, GroupLayout.PREFERRED_SIZE, 329, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 57, Short.MAX_VALUE)))
					.addGap(68))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(91)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(radioAdvanced)
						.addComponent(radioStandard))
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(btnStart, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnDestination, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnSource, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(106, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtpnUrlScrapperWill, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(31)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSource)
						.addComponent(radioStandard))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnDestination)
						.addComponent(radioAdvanced))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnStart)
					.addGap(18)
					.addComponent(labelStatus, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
					.addContainerGap())
		);
		frame.getContentPane().setLayout(groupLayout);
		
		radioStandard.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (radioStandard.isSelected()) {
					radioAdvanced.setSelected(false);
					Config.getInstance().setProperty("plataform", "standard");
				} else {
					radioAdvanced.setSelected(true);
					Config.getInstance().setProperty("plataform", "advanced");
				}
			}
			
		});
		
		radioAdvanced.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (radioAdvanced.isSelected()) {
					radioStandard.setSelected(false);
					Config.getInstance().setProperty("plataform", "advanced");
				} else {
					radioStandard.setSelected(true);
					Config.getInstance().setProperty("plataform", "standard");
				}
			}
			
		});
		
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
	    			labelStatus.setText("Source: "+selectedSource);
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
	    			labelStatus.setText("Destination: "+selectedDestination);
	    	    } else {
	    	    	selectedDestination = null;
	    	    }
		    }
	    });
	    
	    frame.setAutoRequestFocus(true);
	}
	
	public static void start() {
		if (selectedSource == null) {
			View.mensagem("Missing a source folder");
		} else {			
			if (selectedDestination == null) {
				View.mensagem("Missing a destination folder");
			} else {
				scrapper();
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
		
		System.out.println(Reader.linkList.size()+" url's found!");
		if (Reader.linkList.size() > 0) {
			if (View.option("Download", Reader.linkList.size()+" url's found! Would you like to download them into "+selectedDestination)) {
				int index = 0;
				for (String url : Reader.linkList) {
					try {
						System.out.println("Downloading: "+Reader.titleList.get(index));
						labelStatus.setText("Downloading: "+Reader.titleList.get(index));
						download(url, selectedDestination+"/"+Reader.titleList.get(index)+".mp4");
					} catch (IOException e) {
						e.printStackTrace();
					}
					index++;
				}
				if (Reader.linkList.size() == 1) {
					labelStatus.setText(Reader.linkList.size()+" video was downloaded successfully!");
				} else {
					labelStatus.setText(Reader.linkList.size()+" videos were downloaded successfully!");
				}
			} else {
				labelStatus.setText("Job cancelled!");
			}
		} else {
			View.mensagem("Message", "No URL's were found to download");
			labelStatus.setText("No url's were found to download");
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
