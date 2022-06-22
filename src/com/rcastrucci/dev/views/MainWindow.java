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
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.rcastrucci.dev.utils.Config;

public class MainWindow {

	private JFrame frame;
	private JButton btnStart;
	private JButton btnSource;
	private JButton btnDestination;
	private JButton btnQuit;
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
		btnQuit = new JButton("Quit");
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
		frame.setBounds((int) (screenSize.getWidth()/2 - 225), (int) (screenSize.getHeight()/2-150), 450, 320);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.setVisible(true);
		
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
							.addGap(17)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(labelSource, GroupLayout.PREFERRED_SIZE, 417, GroupLayout.PREFERRED_SIZE)
								.addComponent(labelDestination, GroupLayout.PREFERRED_SIZE, 417, GroupLayout.PREFERRED_SIZE)
								.addComponent(labelStatus, GroupLayout.PREFERRED_SIZE, 417, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 438, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(61)
							.addComponent(txtpnUrlScrapperWill, GroupLayout.PREFERRED_SIZE, 329, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(70)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(radioAdvanced)
								.addComponent(radioStandard))
							.addGap(59)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnQuit, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(btnStart, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnDestination, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnSource, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(27)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtpnUrlScrapperWill, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnSource)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnDestination)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnStart)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnQuit))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(radioStandard)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(radioAdvanced)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(labelSource, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(labelDestination, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(labelStatus)
					.addContainerGap(10, Short.MAX_VALUE))
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


	public JButton getBtnQuit() {
		return btnQuit;
	}


	public void setBtnQuit(JButton btnQuit) {
		this.btnQuit = btnQuit;
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
