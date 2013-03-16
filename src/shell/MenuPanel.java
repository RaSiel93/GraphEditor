package shell;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import main.Controller;


class MenuPanel extends JMenuBar{
	public MenuPanel(final Controller controller, final EditionPanel editionPanel){
		JMenu fileMenu = new JMenu("Файл");
		JMenuItem newFile = new JMenuItem("Новый");
		newFile.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				controller.selectAllObject(); 
				controller.removeSelectedObjects();}
		});
		fileMenu.add(newFile);
		JMenuItem exitItem = new JMenuItem("Выход");
		exitItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){System.exit(0);}
		});		
		fileMenu.add(exitItem);
		add(fileMenu);
		//-----------------------------------
		JMenu toolsMenu = new JMenu("Инструменты");
		
		JMenuItem toolVertex = new JMenuItem("Узел");
		toolVertex.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){editionPanel.enableVertexMode();}
		});
		toolsMenu.add(toolVertex);
		
		JMenuItem toolEdge = new JMenuItem("Дуга");
		toolEdge.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){editionPanel.enableEdgeMode();}
		});
		toolsMenu.add(toolEdge);
		
		add(toolsMenu);
		//-----------------------------------
		JMenu editMenu = new JMenu("Редактирование");
		//JMenuItem cutItem = new JMenuItem("Вырезать");
		//cutItem.addActionListener(new ActionListener(){
			//public void actionPerformed(ActionEvent event){controller.cut();}
		//});
		//editMenu.add(cutItem);
		//editMenu.add(new JMenuItem("Копировать"));
		//editMenu.add(new JMenuItem("Вставить"));
		JMenuItem removeItem = new JMenuItem("Удалить");
		removeItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){controller.removeSelectedObjects();}
		});
		editMenu.add(removeItem);
		JMenuItem selectAllItem = new JMenuItem("Выделить все");
		selectAllItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){controller.selectAllObject();}
		});
		editMenu.add(selectAllItem);
		add(editMenu);
		//-----------------------------------
		JMenu algoMenu = new JMenu("Алгоритм");
		algoMenu.add(new JMenuItem("Запустить"));
		algoMenu.add(new JMenuItem("Остановить"));
		
		/*Action exitAction = new AbstractAction("exit"){
			public void actionPerformed(ActionEvent event){
				System.exit(0);
			}
		};*/	
		add(algoMenu);
	}
}
