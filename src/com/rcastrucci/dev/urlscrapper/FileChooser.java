package com.rcastrucci.dev.urlscrapper;

import javax.swing.*;
import java.awt.event.*;


public class FileChooser extends JPanel implements ActionListener { 
	
    private static final long serialVersionUID = 1L;
	private JButton button;
	private JFileChooser chooser;
	private String choosertitle;
	private String selectedFolder;
	   
    public FileChooser(String title, String buttonName) {
    	this.choosertitle = title;
    	button = new JButton(buttonName);
	    button.addActionListener(this);
	    add(button);
    }
	
	public void actionPerformed(ActionEvent e) {            
	    chooser = new JFileChooser(); 
	    chooser.setCurrentDirectory(new java.io.File("/~"));
	    chooser.setDialogTitle(choosertitle);
	    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    chooser.setAcceptAllFileFilterUsed(false);
	    if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) { 
	    	this.selectedFolder = chooser.getSelectedFile().toString();
	    } else {
	    	this.selectedFolder = null;
	    }
	}
	
	public String getSelectedFolder() {
		return this.selectedFolder;
	}
}