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
import com.rcastrucci.dev.model.Advanced;
import com.rcastrucci.dev.model.AdvancedWindow;
import com.rcastrucci.dev.model.Console;
import com.rcastrucci.dev.model.Reader;
import com.rcastrucci.dev.util.Config;
import com.rcastrucci.dev.view.ProgressBar;
import com.rcastrucci.dev.view.View;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JRadioButton;
import java.awt.Cursor;

public class URLScrapper {

	private static JFrame frame;
	private static JButton btnStart = new JButton("Start");
	private static JButton btnSource = new JButton("Source");
	private static JButton btnDestination =  new JButton("Destination");
	private static JButton btnQuit = new JButton("Quit");
	private static String selectedSource;
	private static String selectedDestination;
	private static JLabel labelSource = new JLabel("Source:");
	private static JLabel labelDestination = new JLabel("Destination:");
	private static JLabel labelStatus = new JLabel("Status: waiting");
	private static JRadioButton radioAdvanced = new JRadioButton("Advanced");
	private static JRadioButton radioStandard = new JRadioButton("Standard");
	private static Advanced advancedWindow = AdvancedWindow.getInstance();
	private static Console console = new Console();
	private static ProgressBar progressBar = new ProgressBar();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new URLScrapper();
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
		
		radioStandard.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				radioStandard.setSelected(true);
				radioAdvanced.setSelected(false);
				Config.getInstance().setProperty("plataform", "standard");
				advancedWindow.frame.setVisible(false);
			}
			
		});
		
		radioAdvanced.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				radioStandard.setSelected(false);
				radioAdvanced.setSelected(true);
				Config.getInstance().setProperty("plataform", "advanced");
				advancedWindow.frame.setVisible(true);
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
	    	    chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	    	    chooser.setAcceptAllFileFilterUsed(false);
	    	    if (chooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) { 
	    	    	selectedSource = chooser.getSelectedFile().toString();
	    			labelSource.setText("Source: "+selectedSource);
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
	    	    chooser.setDialogTitle("Select a destination");
	    	    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    	    chooser.setAcceptAllFileFilterUsed(false);
	    	    if (chooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) { 
	    	    	selectedDestination = chooser.getSelectedFile().toString();
	    			labelDestination.setText("Destination: "+selectedDestination);
	    	    } else {
	    	    	selectedDestination = null;
	    	    }
		    }
	    });
	    
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
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
				
		EventQueue.invokeLater(new Runnable() {
			@Override
		    public void run() {
		        //This will be called on the EDT
				progressBar.setTitle("Loading");
				progressBar.getProgressBarWindow().setVisible(true);
		        progressBar.getProgressBar().setValue(2);
		        progressBar.getProgressBar().update(progressBar.getProgressBar().getGraphics());
		        progressBar.getProgressBarWindow().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		    }
		});
		
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				
				File filesSource = new File(selectedSource);

				// LOADING A FOLDER
				if (filesSource.isDirectory()) {
					ArrayList<String> list = listFiles(filesSource);
					Reader.reset();
					double percentageLoad = 100.00 / list.size();
					int indexLoad = 1;
					for (String filename : list) {
						final int i = indexLoad;
						EventQueue.invokeLater(new Runnable() {
							@Override
						    public void run() {
								progressBar.getStatus().setText(filename);
								progressBar.getPanelStatus().update(progressBar.getPanelStatus().getGraphics());
						        progressBar.getProgressBar().setValue((int) (percentageLoad*i));
						        progressBar.getProgressBar().update(progressBar.getProgressBar().getGraphics());
						    }
						});
						EventQueue.invokeLater(new Runnable() {
							@Override
							public void run() {
								Reader.run(selectedSource, filename);
							}
						});
						indexLoad++;
					}
					EventQueue.invokeLater(new Runnable() {
						@Override
					    public void run() {
					        progressBar.getProgressBarWindow().setVisible(false);
					    }
					});
				} else {
				// LOADING A FILE
					EventQueue.invokeLater(new Runnable() {
						@Override
					    public void run() {
							progressBar.getStatus().setText(selectedSource);
							progressBar.getPanelStatus().update(progressBar.getPanelStatus().getGraphics());
					        progressBar.getProgressBar().setValue(40);
					        progressBar.getProgressBar().update(progressBar.getProgressBar().getGraphics());
					    }
					});
					EventQueue.invokeLater(new Runnable() {
						@Override
						public void run() {
							Reader.reset();
							Reader.run(selectedSource, null);
						}
					});
				}
				
				EventQueue.invokeLater(new Runnable() {
					@Override
					public void run() {
				
						System.out.println(Reader.linkList.size()+" url's found!");
						
						if (Reader.linkList.size() > 0) {
							if (View.option("Download",
									Reader.fileList.size()+" files were read!  "+
									Reader.linksFound.size()+" url's were found!  "+
									Reader.linkList.size()+" url's are actively working with status 200 OK!\nWould you like to download them into "+selectedDestination)
									) {
								
								EventQueue.invokeLater(new Runnable() {
									@Override
								    public void run() {
								        //This will be called on the EDT
										frame.setVisible(false);
										progressBar.setTitle("Downloading");
										progressBar.getProgressBarWindow().setVisible(true);
								        progressBar.getProgressBar().setValue(2);
								        progressBar.getProgressBar().update(progressBar.getProgressBar().getGraphics());
								        progressBar.getProgressBarWindow().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
								        progressBar.getProgressBarWindow().setFocusable(true);
								    }
								});
								
								EventQueue.invokeLater(new Runnable() {
									@Override
								    public void run() {
										double percentageDownloaded = 100.00 / Reader.linkList.size();
										int indexDownload = 1;
										for (String url : Reader.linkList) {
											final int i = indexDownload;
											EventQueue.invokeLater(new Runnable() {
												@Override
											    public void run() {
											        progressBar.getProgressBar().setValue((int)(percentageDownloaded*i));
											        progressBar.getProgressBar().update(progressBar.getProgressBar().getGraphics());
											        progressBar.getStatus().setText(Reader.titleList.get(i-1)+".mp4");
											        progressBar.getPanelStatus().update(progressBar.getPanelStatus().getGraphics());
											    }
											});
											EventQueue.invokeLater(new Runnable() {
												@Override
											    public void run() {		
													try {
														download(url, selectedDestination+"/"+Reader.titleList.get(i-1)+".mp4");
													} catch (IOException e) {
														e.printStackTrace();
													}
											    }
											});
											indexDownload++;
										}
										EventQueue.invokeLater(new Runnable() {
											@Override
										    public void run() {
										        progressBar.getProgressBarWindow().setVisible(false);
												if (Reader.linkList.size() == 1) {
													labelStatus.setText(Reader.linkList.size()+" video was downloaded successfully!");
												} else {
													labelStatus.setText(Reader.linkList.size()+" videos were downloaded successfully!");
												}
												frame.setVisible(true);
										    }
										});
									}
								});
							} else {
								labelStatus.setText("Job cancelled!");
							}
						} else {
							View.mensagem("Message", 
									Reader.fileList.size()+" files were read!  "+
									Reader.linksFound.size()+" url's were found!  "+
									"No actively URL's to download");
							labelStatus.setText("No url's were found to download");
						}	

						EventQueue.invokeLater(new Runnable() {
							@Override
							public void run() {
								if (advancedWindow.printConsole()) {
									System.out.println("Printing results found...");
									for (String target : Reader.linksFound) {
										System.out.println(target);
									}
									console.textConsole.setText(String.join("\n", Reader.linksFound));
									console.frame.setVisible(true);
								}
								
								frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
							}
						});
					}
				});
				
			}
		});
		
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
