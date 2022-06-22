package com.rcastrucci.dev.main;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;
import javax.swing.JFileChooser;

import com.rcastrucci.dev.models.Scrapper;
import com.rcastrucci.dev.utils.Config;
import com.rcastrucci.dev.views.AdvancedWindow;
import com.rcastrucci.dev.views.MainWindow;
import com.rcastrucci.dev.views.PaneWindow;

public class Main {
	
	private MainWindow mainWindow;
	private AdvancedWindow advancedWindow;
	private String selectedSource;
	private String selectedDestination;
	private Properties config;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Main();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	private Main() {
		initialize();
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		// Instantiate Views
		mainWindow = MainWindow.getInstance();
		advancedWindow = AdvancedWindow.getInstance();
		config = Config.getInstance();
		
		// Add listeners to mainWindow
		addListeners();

	}

	
	/**
	 * Add button listeners
	 */
	private void addListeners() {
		
		// SELECTION OF A STANDARD MODE
		mainWindow.getRadioStandard().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainWindow.getRadioStandard().setSelected(true);
				mainWindow.getRadioAdvanced().setSelected(false);
				advancedWindow.getFrame().setVisible(false);
				config.setProperty("plataform", "standard");
			}
			
		});
		
		// SELECTION OF ADVANCED MODE
		mainWindow.getRadioAdvanced().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainWindow.getRadioStandard().setSelected(false);
				mainWindow.getRadioAdvanced().setSelected(true);
				Config.getInstance().setProperty("plataform", "advanced");
				advancedWindow.getFrame().setVisible(true);
			}
			
		});
		
		// START PROCEDURE
	    mainWindow.getBtnStart().addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	start();
	        }
	    });
	    
	    // SELECT A FILE OR A DIRECTORY AS A SOURCE
	    mainWindow.getBtnSource().addActionListener(new ActionListener() { 
	    	public void actionPerformed(ActionEvent e) {
	        	JFileChooser chooser;
	    	    chooser = new JFileChooser(); 
	    	    chooser.setCurrentDirectory(new java.io.File("/~"));
	    	    chooser.setDialogTitle("Select a source");
	    	    chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	    	    chooser.setAcceptAllFileFilterUsed(false);
	    	    if (chooser.showOpenDialog(mainWindow.getFrame()) == JFileChooser.APPROVE_OPTION) { 
	    	    	selectedSource = chooser.getSelectedFile().toString();
	    			mainWindow.getLabelSource().setText("Source: "+selectedSource);
	    	    } else {
	    	    	selectedSource = null;
	    	    }
	        }
	    });
	    
	    // SELECT A DESTINATION FOLDER
	    mainWindow.getBtnDestination().addActionListener(new ActionListener() { 
	    	public void actionPerformed(ActionEvent e) {
	        	JFileChooser chooser;
	    	    chooser = new JFileChooser(); 
	    	    chooser.setCurrentDirectory(new java.io.File("/~"));
	    	    chooser.setDialogTitle("Select a destination");
	    	    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    	    chooser.setAcceptAllFileFilterUsed(false);
	    	    if (chooser.showOpenDialog(mainWindow.getFrame()) == JFileChooser.APPROVE_OPTION) { 
	    	    	selectedDestination = chooser.getSelectedFile().toString();
	    	    	mainWindow.getLabelDestination().setText("Destination: "+selectedDestination);
	    	    } else {
	    	    	selectedDestination = null;
	    	    }
		    }
	    });
	    
	    // QUIT APPLICATION
	    mainWindow.getBtnQuit().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}
	
	
	/**
	 * Start the procedure to search if the condition of source and destination are filled
	 */
	public void start() {
		
		if (selectedSource == null) {
			PaneWindow.mensagem("Missing a source folder");
		} else {			
			if (selectedDestination == null) {
				PaneWindow.mensagem("Missing a destination folder");
			} else {
				Scrapper scrapper = new Scrapper(selectedSource, selectedDestination);
				scrapper.run();
			}
		}
	}
	
}
