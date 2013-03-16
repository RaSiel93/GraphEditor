package main;

import graph.Graph;

import javax.swing.UIManager;

import shell.MainFrame;

public class EditorGraphs {
	public static void main(String[] args) {
		try {
		    UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
		Controller controller = new Controller();	
		
		Graph graph = new Graph();
		controller.setModel(graph);
		
		MainFrame mainFrame = new MainFrame(controller);
		controller.setView(mainFrame);
	}
}
