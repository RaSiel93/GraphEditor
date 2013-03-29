package shell;

import static java.awt.event.InputEvent.CTRL_DOWN_MASK;
import static java.awt.event.InputEvent.SHIFT_DOWN_MASK;

import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import main.Controller;


public class MenuPanel extends JMenuBar {
    @SuppressWarnings("deprecation")
    public MenuPanel(Controller controller) {
	JMenu fileMenu = new JMenu("����");

	JMenuItem newFile = new JMenuItem("�����");
	newFile.addActionListener(controller.getMethod("CREATE"));
	newFile.setAccelerator(KeyStroke.getKeyStroke('N', CTRL_DOWN_MASK));
	JMenuItem loadFile = new JMenuItem("�������");
	loadFile.addActionListener(controller.getMethod("OPEN"));
	loadFile.setAccelerator(KeyStroke.getKeyStroke('O', CTRL_DOWN_MASK));
	JMenuItem saveFile = new JMenuItem("���������");
	saveFile.setAccelerator(KeyStroke.getKeyStroke('S', CTRL_DOWN_MASK));
	saveFile.addActionListener(controller.getMethod("SAVE"));
	JMenuItem exitItem = new JMenuItem("�����");
	exitItem.addActionListener(controller.getMethod("EXIT"));

	fileMenu.add(newFile);
	fileMenu.add(loadFile);
	fileMenu.add(saveFile);
	fileMenu.add(exitItem);

	add(fileMenu);
	// -----------------------------------
	JMenu toolsMenu = new JMenu("�����������");

	JMenuItem toolVertex = new JMenuItem("����");
	toolVertex.addActionListener(controller.getMethod("VERTEX_MODE"));
	toolVertex.setAccelerator(KeyStroke.getKeyStroke('V', SHIFT_DOWN_MASK));
	JMenuItem toolEdge = new JMenuItem("����");
	toolEdge.addActionListener(controller.getMethod("EDGE_MODE"));
	toolEdge.setAccelerator(KeyStroke.getKeyStroke('A', SHIFT_DOWN_MASK));
	JMenuItem editNameOrLenght = new JMenuItem("��� - �����");
	editNameOrLenght.addActionListener(controller.getMethod("EDIT_MODE"));
	editNameOrLenght.setAccelerator(KeyStroke.getKeyStroke('E',
		SHIFT_DOWN_MASK));

	toolsMenu.add(toolVertex);
	toolsMenu.add(toolEdge);
	toolsMenu.add(editNameOrLenght);

	add(toolsMenu);
	// -----------------------------------
	JMenu editMenu = new JMenu("��������������");

	JMenuItem removeItem = new JMenuItem("�������");
	removeItem.addActionListener(controller.getMethod("REMOVE"));
	removeItem
		.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));

	JMenuItem selectAllItem = new JMenuItem("�������� ���");
	selectAllItem.addActionListener(controller.getMethod("SELECT_ALL"));
	selectAllItem.setAccelerator(KeyStroke
		.getKeyStroke('A', CTRL_DOWN_MASK));

	editMenu.add(removeItem);
	editMenu.add(selectAllItem);
	add(editMenu);
	// -----------------------------------
	JMenu algoMenu = new JMenu("��������");
	JMenuItem runAlgo = new JMenuItem("���������");
	runAlgo.addActionListener(controller.getMethod("RUN_ALGO"));
	runAlgo.setAccelerator(KeyStroke.getKeyStroke('R', CTRL_DOWN_MASK));
	JMenuItem stepAlgo = new JMenuItem("����������..");
	stepAlgo.setEnabled(false);
	stepAlgo.addActionListener(controller.getMethod("STEP_ALGO"));
	stepAlgo.setAccelerator(KeyStroke.getKeyStroke('W', CTRL_DOWN_MASK));
	JMenuItem breakAlgo = new JMenuItem("��������");
	breakAlgo.addActionListener(controller.getMethod("BREAK_ALGO"));
	breakAlgo.setAccelerator(KeyStroke.getKeyStroke('T', CTRL_DOWN_MASK));
	breakAlgo.setEnabled(false);

	// controller.addItem(runAlgo, stepAlgo, breakAlgo);

	algoMenu.add(runAlgo);
	algoMenu.add(stepAlgo);
	algoMenu.add(breakAlgo);
	add(algoMenu);
    }
}
