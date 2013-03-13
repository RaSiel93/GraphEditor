package by.bsuir.II;

import javax.swing.UIManager;

public class EditorGraphs {
	public static void main(String[] args) {
		/*try {
		    UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
		} catch (Exception e) {
		    e.printStackTrace();
		}*/
		
		Controller controller = new Controller();

		Graph model = new Graph();
		controller.setModel(model);
		
		MainFrame view = new MainFrame(controller);
		controller.setView(view);
	}
}
