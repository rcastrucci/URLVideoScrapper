package com.rcastrucci.dev.views;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.rcastrucci.dev.utils.Config;

public class MainWindow {

	private JFrame frame;
	private JButton btnStart;
	private JButton btnSource;
	private JButton btnDestination;
	private JLabel labelSource;
	private JLabel labelDestination;
	private JLabel labelStatus;
	private JRadioButton radioAdvanced;
	private JRadioButton radioStandard;
	private static MainWindow mainWindow;

	
	/**
	 * Method SingleTon to get a instance of this class
	 * @return
	 */
	public static MainWindow getInstance() {
		if (mainWindow == null) {
			mainWindow = new MainWindow();
		}
		return mainWindow;
	}
	
	/**
	 * Create the application.
	 */
	private MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		btnStart = new JButton("Start");
		btnSource = new JButton("Source");
		btnDestination =  new JButton("Destination");
		labelSource = new JLabel("Source:");
		labelDestination = new JLabel("Destination:");
		labelStatus = new JLabel("Status: waiting");
		radioAdvanced = new JRadioButton("Advanced");
		radioStandard = new JRadioButton("Standard");
		
		labelStatus.setBackground(Color.DARK_GRAY);
		labelStatus.setFont(new Font("Helvetica Neue", Font.ITALIC, 12));
		labelStatus.setForeground(Color.GRAY);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setBackground(Color.DARK_GRAY);
		frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setBounds((int) (screenSize.getWidth()/2 - 225), (int) (screenSize.getHeight()/2-131), 450, 262);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(false);
		frame.setTitle("URL Video Scrapper");
		frame.setVisible(true);
		

		radioAdvanced.setBackground(Color.DARK_GRAY);
		radioAdvanced.setForeground(Color.GRAY);
		radioAdvanced.setSelected(Config.getInstance().getProperty("plataform").equals("advanced"));
		
		radioStandard.setBackground(Color.DARK_GRAY);
		radioStandard.setForeground(Color.GRAY);
		radioStandard.setSelected(Config.getInstance().getProperty("plataform").equals("standard"));

		labelDestination.setHorizontalAlignment(SwingConstants.LEFT);
		labelDestination.setForeground(Color.GRAY);
		labelDestination.setFont(new Font("Helvetica Neue", Font.ITALIC, 12));
		labelDestination.setBackground(Color.DARK_GRAY);
		
		labelSource.setHorizontalAlignment(SwingConstants.LEFT);
		labelSource.setForeground(Color.GRAY);
		labelSource.setFont(new Font("Helvetica Neue", Font.ITALIC, 12));
		labelSource.setBackground(Color.DARK_GRAY);
				
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(16)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(labelSource, GroupLayout.PREFERRED_SIZE, 417, GroupLayout.PREFERRED_SIZE)
								.addComponent(labelDestination, GroupLayout.PREFERRED_SIZE, 417, GroupLayout.PREFERRED_SIZE)
								.addComponent(labelStatus, GroupLayout.PREFERRED_SIZE, 417, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(75)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(radioAdvanced)
								.addComponent(radioStandard))
							.addGap(59)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(btnStart, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnDestination, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnSource, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(17, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(61)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnSource)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnDestination)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnStart))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(radioStandard)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(radioAdvanced)
							.addGap(25)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(labelSource, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(labelDestination, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(labelStatus)
					.addContainerGap(11, Short.MAX_VALUE))
		);
		
		frame.getContentPane().setLayout(groupLayout);
	    frame.setAutoRequestFocus(true);
	}

	
	// GETTERS AND SETTERS
	
	public JButton getBtnStart() {
		return btnStart;
	}


	public void setBtnStart(JButton btnStart) {
		this.btnStart = btnStart;
	}


	public JButton getBtnSource() {
		return btnSource;
	}


	public void setBtnSource(JButton btnSource) {
		this.btnSource = btnSource;
	}


	public JButton getBtnDestination() {
		return btnDestination;
	}


	public void setBtnDestination(JButton btnDestination) {
		this.btnDestination = btnDestination;
	}


	public JLabel getLabelSource() {
		return labelSource;
	}


	public void setLabelSource(JLabel labelSource) {
		this.labelSource = labelSource;
	}


	public JLabel getLabelDestination() {
		return labelDestination;
	}


	public void setLabelDestination(JLabel labelDestination) {
		this.labelDestination = labelDestination;
	}


	public JLabel getLabelStatus() {
		return labelStatus;
	}


	public void setLabelStatus(JLabel labelStatus) {
		this.labelStatus = labelStatus;
	}


	public JRadioButton getRadioAdvanced() {
		return radioAdvanced;
	}


	public void setRadioAdvanced(JRadioButton radioAdvanced) {
		this.radioAdvanced = radioAdvanced;
	}


	public JRadioButton getRadioStandard() {
		return radioStandard;
	}
	

	public void setRadioStandard(JRadioButton radioStandard) {
		this.radioStandard = radioStandard;
	}
	

	public JFrame getFrame() {
		return frame;
	}
}
