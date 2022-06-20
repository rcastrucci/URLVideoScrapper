package com.rcastrucci.dev.model;

public class AdvancedWindow {
	
	private static Advanced windowAdvanced;
	
	private AdvancedWindow() {}
	
	public static Advanced getInstance() {
		if (windowAdvanced == null) {
			windowAdvanced = new Advanced();
		}
		return windowAdvanced;
	}
}