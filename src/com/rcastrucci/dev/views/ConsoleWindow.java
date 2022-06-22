package com.rcastrucci.dev.views;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Font;

public class ConsoleWindow {

	private JFrame frame;
	private JTextArea textConsole;

	/**
	 * Create the application.
	 */
	public ConsoleWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsoleWindow window = new ConsoleWindow();
					window.frame.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		textConsole = new JTextArea();
		
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setTitle("Console");
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 634, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(10, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 339, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(7, Short.MAX_VALUE))
		);
		textConsole.setFont(new Font("Courier", Font.BOLD, 14));
		textConsole.setText("Console");
		
		textConsole.setForeground(new Color(0, 100, 0));
		textConsole.setBackground(Color.BLACK);
		scrollPane.setViewportView(textConsole);
		frame.getContentPane().setLayout(groupLayout);
		frame.setBackground(Color.BLACK);
		frame.setResizable(false);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setBounds((int) (screenSize.getWidth()/2 - 325), (int) (screenSize.getHeight()/2-190), 650, 380);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}

	public JTextArea getTextConsole() {
		return textConsole;
	}

	public void setTextConsole(String textConsole) {
		this.textConsole.setText(textConsole);
	}

	public JFrame getFrame() {
		return frame;
	}
}
