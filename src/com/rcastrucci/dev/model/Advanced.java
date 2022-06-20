package com.rcastrucci.dev.model;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.rcastrucci.dev.util.Config;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JSeparator;

public class Advanced {

	public JFrame frame;
	protected JTextField inputSpecific;
	protected JTextField inputLineContains;
	protected JTextField inputStart;
	protected JTextField inputEnd;
	protected JCheckBox ckHtml = new JCheckBox("html");
	protected JCheckBox ckJs = new JCheckBox("js");
	protected JCheckBox ckCss = new JCheckBox("css");
	protected JCheckBox ckAll = new JCheckBox("*");
	protected JCheckBox ckSpecific = new JCheckBox("specific");
	protected JCheckBox ckConsole = new JCheckBox("Console print results");

	/**
	 * Create the application.
	 */
	public Advanced() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Advanced window = new Advanced();
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
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		
		JLabel lblNewLabel = new JLabel("Advanced options");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.WHITE);
		
		JLabel lblNewLabel_1 = new JLabel("File type");
		lblNewLabel_1.setBackground(Color.DARK_GRAY);
		lblNewLabel_1.setForeground(Color.LIGHT_GRAY);
		
		
		ckHtml.setForeground(Color.GRAY);
		ckHtml.setSelected(true);

		ckJs.setSelected(false);
		ckJs.setForeground(Color.GRAY);
		
		ckCss.setForeground(Color.GRAY);
		
		ckAll.setForeground(Color.GRAY);
		
		ckSpecific.setForeground(Color.GRAY);
		
		inputSpecific = new JTextField();
		inputSpecific.setEditable(false);
		inputSpecific.setText(" type here extensions separated by comma's");
		inputSpecific.setForeground(Color.DARK_GRAY);
		inputSpecific.setBackground(Color.GRAY);
		inputSpecific.setColumns(10);
		
		JSeparator separator = new JSeparator();
		
		JLabel lblNewLabel_1_1 = new JLabel("Condition");
		lblNewLabel_1_1.setForeground(Color.LIGHT_GRAY);
		
		JSeparator separator_1 = new JSeparator();
		
		JLabel lblNewLabel_2 = new JLabel("If line contains:");
		lblNewLabel_2.setBackground(Color.DARK_GRAY);
		lblNewLabel_2.setForeground(Color.GRAY);
		
		inputLineContains = new JTextField();
		inputLineContains.setForeground(Color.DARK_GRAY);
		inputLineContains.setColumns(10);
		inputLineContains.setBackground(Color.GRAY);
		
		JLabel lblNewLabel_2_1 = new JLabel("Start after: ");
		lblNewLabel_2_1.setForeground(Color.GRAY);
		lblNewLabel_2_1.setBackground(Color.DARK_GRAY);
		
		inputStart = new JTextField();
		inputStart.setForeground(Color.DARK_GRAY);
		inputStart.setColumns(10);
		inputStart.setBackground(Color.GRAY);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Ends at: ");
		lblNewLabel_2_1_1.setForeground(Color.GRAY);
		lblNewLabel_2_1_1.setBackground(Color.DARK_GRAY);
		
		inputEnd = new JTextField();
		inputEnd.setForeground(Color.DARK_GRAY);
		inputEnd.setColumns(10);
		inputEnd.setBackground(Color.GRAY);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Option");
		lblNewLabel_1_1_1.setForeground(Color.LIGHT_GRAY);
		
		JSeparator separator_1_1 = new JSeparator();
		
		ckConsole.setForeground(Color.GRAY);
		ckConsole.setBackground(Color.DARK_GRAY);
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 437, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(7, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(25)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(ckConsole)
							.addContainerGap())
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(separator_1_1, GroupLayout.PREFERRED_SIZE, 404, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel_1_1_1, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
									.addContainerGap())
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(lblNewLabel_2_1_1, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(inputEnd, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
										.addGap(21))
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblNewLabel_2_1, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(inputStart, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
											.addGap(21))
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
											.addGroup(groupLayout.createSequentialGroup()
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
													.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 404, GroupLayout.PREFERRED_SIZE)
													.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
													.addGroup(groupLayout.createSequentialGroup()
														.addComponent(lblNewLabel_2)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(inputLineContains)))
												.addContainerGap())
											.addGroup(groupLayout.createSequentialGroup()
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
													.addGroup(groupLayout.createSequentialGroup()
														.addComponent(ckHtml)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(ckJs, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(ckCss, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(ckAll))
													.addComponent(separator, GroupLayout.PREFERRED_SIZE, 404, GroupLayout.PREFERRED_SIZE)
													.addComponent(lblNewLabel_1)
													.addGroup(groupLayout.createSequentialGroup()
														.addComponent(ckSpecific, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(inputSpecific, GroupLayout.PREFERRED_SIZE, 322, GroupLayout.PREFERRED_SIZE)))
												.addGap(16)))))))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addGap(19)
					.addComponent(lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(ckHtml)
						.addComponent(ckJs)
						.addComponent(ckCss)
						.addComponent(ckAll))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(ckSpecific)
						.addComponent(inputSpecific, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(lblNewLabel_1_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(inputLineContains, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2_1)
						.addComponent(inputStart, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2_1_1)
						.addComponent(inputEnd, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(lblNewLabel_1_1_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator_1_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(ckConsole)
					.addContainerGap(20, Short.MAX_VALUE))
		);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.getContentPane().setLayout(groupLayout);
		frame.setBackground(Color.DARK_GRAY);
		frame.setResizable(false);
		frame.setBounds((int) (screenSize.getWidth()/2 - 225), (int) (screenSize.getHeight()/2-150), 450, 480);
		
		
		ckSpecific.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ckAll.setSelected(false);
				ckHtml.setSelected(false);
				ckJs.setSelected(false);
				ckCss.setSelected(false);
				inputSpecific.setEditable(true);
				inputSpecific.setText("");
			}
		});
		
		ckAll.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ckSpecific.setSelected(false);
				inputSpecific.setEditable(false);
				ckHtml.setSelected(false);
				ckJs.setSelected(false);
				ckCss.setSelected(false);
			}
		});
		
		
		ckHtml.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ckSpecific.setSelected(false);
				inputSpecific.setEditable(false);
				ckAll.setSelected(false);
			}
		});
		
		ckJs.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ckSpecific.setSelected(false);
				inputSpecific.setEditable(false);
				ckAll.setSelected(false);
			}
		});
		
		ckCss.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ckSpecific.setSelected(false);
				inputSpecific.setEditable(false);
				ckAll.setSelected(false);
			}
		});
		
		setConfig();
	}
	
	public void setConfig() {
		ArrayList<String> fileType = new ArrayList<String>();
		if (ckAll.isSelected()) {
			fileType.add("*");
		} else if (ckSpecific.isSelected()) {
			if (inputSpecific.getText().replaceAll(" ", "").equals("")) {
				inputSpecific.setText("html");
			} else {			
				fileType.add(inputSpecific.getText().toLowerCase());
			}
		} else {
			if (ckHtml.isSelected()) fileType.add("html"); 
			if (ckJs.isSelected()) fileType.add("js");
			if (ckCss.isSelected()) fileType.add("css");
			if (fileType.size() <= 0) fileType.add("*");
		}
		
		if (inputLineContains.getText().replaceAll(" ", "").equals("")) {
			inputLineContains.setText("<video");
		}
		if (inputStart.getText().replaceAll(" ", "").equals("")) {
			inputStart.setText("src=\"");
		}
		if (inputEnd.getText().replaceAll(" ", "").equals("")) {
			inputEnd.setText("\"");
		}
		
		Config.getInstance().setProperty("filetype", String.join(",", fileType));
	}

	public JTextField getInputSpecific() {
		return inputSpecific;
	}

	public void setInputSpecific(JTextField inputSpecific) {
		this.inputSpecific = inputSpecific;
	}

	public JTextField getInputLineContains() {
		return inputLineContains;
	}

	public void setInputLineContains(JTextField inputLineContains) {
		this.inputLineContains = inputLineContains;
	}

	public JTextField getInputStart() {
		return inputStart;
	}

	public void setInputStart(JTextField inputStart) {
		this.inputStart = inputStart;
	}

	public JTextField getInputEnd() {
		return inputEnd;
	}

	public void setInputEnd(JTextField inputEnd) {
		this.inputEnd = inputEnd;
	}
	
	public boolean printConsole() {
		return ckConsole.isSelected();
	}
}
