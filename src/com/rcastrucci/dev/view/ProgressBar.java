package com.rcastrucci.dev.view;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window.Type;
import javax.swing.JProgressBar;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

public class ProgressBar {

	private JFrame progressBarWindow;
	private JLabel progressBarLabel;
	private JProgressBar progressBar;

	/**
	 * Create the application.
	 */
	public ProgressBar() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		progressBarWindow = new JFrame();
		progressBarWindow.getContentPane().setBackground(Color.DARK_GRAY);
		
		progressBarLabel = new JLabel("Downloading");
		progressBarLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		progressBarLabel.setBackground(Color.DARK_GRAY);
		progressBarLabel.setForeground(Color.LIGHT_GRAY);
		progressBarLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		progressBar = new JProgressBar();
		progressBar.setEnabled(false);
		progressBar.setValue(0);
		GroupLayout groupLayout = new GroupLayout(progressBarWindow.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(progressBarLabel, GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
							.addGap(47))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(progressBarLabel, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(41, Short.MAX_VALUE))
		);
		progressBarWindow.getContentPane().setLayout(groupLayout);
		progressBarWindow.setType(Type.UTILITY);
		progressBarWindow.setResizable(false);
		progressBarWindow.setAlwaysOnTop(true);
		progressBarWindow.setBackground(Color.DARK_GRAY);
		progressBarWindow.setBounds((int) (screenSize.getWidth()/2 - 225), (int) (screenSize.getHeight()/2 - 70), 450, 140);
	}
	
	public JFrame getProgressBarWindow() {
		return this.progressBarWindow;
	}
	
	public JProgressBar getProgressBar() {
		return this.progressBar;
	}
}