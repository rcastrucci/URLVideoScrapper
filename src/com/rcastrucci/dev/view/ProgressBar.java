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
import java.awt.Font;
import javax.swing.JPanel;

public class ProgressBar {

	private JFrame progressBarWindow;
	private JLabel progressBarLabel;
	private JProgressBar progressBar;
	private JPanel panelStatus;
	private JLabel labelStatus;

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
		progressBarWindow.setUndecorated(true);
		
		progressBarLabel = new JLabel("Downloading");
		progressBarLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		progressBarLabel.setBackground(Color.DARK_GRAY);
		progressBarLabel.setForeground(Color.LIGHT_GRAY);
		progressBarLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		progressBar = new JProgressBar();
		progressBar.setEnabled(true);
		progressBar.setValue(0);
		
		panelStatus = new JPanel();
		panelStatus.setBackground(Color.DARK_GRAY);
		GroupLayout groupLayout = new GroupLayout(progressBarWindow.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(progressBarLabel, GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(panelStatus, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(progressBar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE))
							.addGap(47))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(progressBarLabel, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelStatus, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(20, Short.MAX_VALUE))
		);
		
		labelStatus = new JLabel("Status");
		labelStatus.setHorizontalAlignment(SwingConstants.LEFT);
		labelStatus.setForeground(Color.GRAY);
		labelStatus.setFont(new Font("Helvetica Neue", Font.PLAIN, 11));
		labelStatus.setBackground(Color.DARK_GRAY);
		GroupLayout gl_panelStatus = new GroupLayout(panelStatus);
		gl_panelStatus.setHorizontalGroup(
			gl_panelStatus.createParallelGroup(Alignment.LEADING)
				.addComponent(labelStatus, GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
		);
		gl_panelStatus.setVerticalGroup(
			gl_panelStatus.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panelStatus.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(labelStatus, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panelStatus.setLayout(gl_panelStatus);
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
	
	public JLabel getStatus() {
		return this.labelStatus;
	}
	
	public JPanel getPanelStatus() {
		return this.panelStatus;
	}
	
	public void setTitle(String title) {
		this.progressBarLabel.setText(title);
	}
	
	public void setStatus(String status) {
		this.labelStatus.setText(status);
	}
}