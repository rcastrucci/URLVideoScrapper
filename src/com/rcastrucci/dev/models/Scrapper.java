package com.rcastrucci.dev.models;

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
	private ProgressBar progressBar;
	private String source, destination;
	
	/**
	 * Scrapper constructor
	 */
	public Scrapper(String source, String destination) {
		mainWindow = MainWindow.getInstance();
		advancedWindow = AdvancedWindow.getInstance();
		progressBar = new ProgressBar();
		this.source = source;
		this.destination = destination;
	}
	
	/**
	 * Method Scrapper
	 */
	public void run() {
		
		mainWindow.getFrame().setVisible(false);
		
		progressBar.setProgress("Loading", null, 2);
		
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				
				File filesSource = new File(source);
	
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
								Reader.run(source, filename);
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
							progressBar.getStatus().setText(source);
							progressBar.getPanelStatus().update(progressBar.getPanelStatus().getGraphics());
					        progressBar.getProgressBar().setValue(40);
					        progressBar.getProgressBar().update(progressBar.getProgressBar().getGraphics());
					    }
					});
					EventQueue.invokeLater(new Runnable() {
						@Override
						public void run() {
							Reader.reset();
							Reader.run(source, null);
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
									Reader.linkList.size()+" url's are actively working with status 200 OK!\nWould you like to download them into "+destination)
									) {
								
								progressBar.setProgress("Downloading", null, 2);
								
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
														FileUtil.download(url, destination+"/"+Reader.titleList.get(i-1)+".mp4");
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
									
									ArrayList<String> consoleList = new ArrayList<String>();

									// SYSTEM OUT PRINT
									System.out.println("Printing results found...");
									for (String fileName : Reader.filesFound) {
										System.out.println(fileName+" -> ");
										for (String target : Reader.linksFound) {
											System.out.print(target);
											consoleList.add(fileName+" -> "+target);
										}
									}
									
									// GENERATED CONSOLE OUT PRINT
									ConsoleWindow consoleWindow = new ConsoleWindow();
									consoleWindow.setTextConsole(String.join("\n", consoleList));
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