package com.rcastrucci.dev.models;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.rcastrucci.dev.utils.FileUtil;
import com.rcastrucci.dev.views.AdvancedWindow;
import com.rcastrucci.dev.views.ConsoleWindow;
import com.rcastrucci.dev.views.MainWindow;
import com.rcastrucci.dev.views.PaneWindow;
import com.rcastrucci.dev.views.ProgressBar;

public class Scrapper {
	
	private MainWindow mainWindow;
	private AdvancedWindow advancedWindow;
	private ConsoleWindow consoleWindow;
	private ProgressBar progressBar;
	private String selectedSource, selectedDestination;
	
	/**
	 * Scrapper constructor
	 */
	public Scrapper(String source, String destination) {
		mainWindow = MainWindow.getInstance();
		advancedWindow = AdvancedWindow.getInstance();
		consoleWindow = new ConsoleWindow();
		progressBar = new ProgressBar();
		this.selectedSource = source;
		this.selectedDestination = destination;
	}
	
	/**
	 * Method Scrapper
	 */
	public void run() {
		
		EventQueue.invokeLater(new Runnable() {
			@Override
		    public void run() {
		        //This will be called on the EDT
				mainWindow.getFrame().setVisible(false);
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
					ArrayList<String> list = FileUtil.listFiles(filesSource);
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
							if (PaneWindow.option("Download",
									Reader.fileList.size()+" files were read!  "+
									Reader.linksFound.size()+" url's were found!  "+
									Reader.linkList.size()+" url's are actively working with status 200 OK!\nWould you like to download them into "+selectedDestination)
									) {
								
								EventQueue.invokeLater(new Runnable() {
									@Override
								    public void run() {
								        //This will be called on the EDT
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
														FileUtil.download(url, selectedDestination+"/"+Reader.titleList.get(i-1)+".mp4");
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
													mainWindow.getLabelStatus().setText(Reader.linkList.size()+" video was downloaded successfully!");
												} else {
													mainWindow.getLabelStatus().setText(Reader.linkList.size()+" videos were downloaded successfully!");
												}
										    }
										});
									}
								});
							} else {
								mainWindow.getLabelStatus().setText("Job cancelled!");
							}
						} else {
							PaneWindow.mensagem("Message", 
									Reader.fileList.size()+" files were read!  "+
									Reader.linksFound.size()+" url's were found!  "+
									"No actively URL's to download");
							mainWindow.getLabelStatus().setText("No url's were found to download");
						}	
	
						EventQueue.invokeLater(new Runnable() {
							@Override
							public void run() {
								if (advancedWindow.printConsole()) {
									System.out.println("Printing results found...");
									for (String target : Reader.linksFound) {
										System.out.println(target);
									}
									consoleWindow.setTextConsole(String.join("\n", Reader.linksFound));
									consoleWindow.getFrame().setVisible(true);
								}
							}
						});
						
						mainWindow.getFrame().setVisible(true);
					}
				});
				
			}
		});
	}

}
