package main;

import graph.Graph;

import javax.swing.UIManager;

import shell.MainFrame;

public class EditorGraphs {
    public static void main(String[] args) throws NoSuchMethodException,
	    SecurityException {

	try {
	    UIManager
		    .setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
	} catch (Exception e) {
	    e.printStackTrace();
	}

	Graph graph = new Graph();
	Controller controller = new Controller(graph);

	MainFrame mainFrame = new MainFrame(controller);
	controller.setView(mainFrame);
    }
}
