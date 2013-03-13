package by.bsuir.II;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

class MenuPanel extends JMenuBar{
	public MenuPanel(final Controller controller){
		JMenu fileMenu = new JMenu("Файл");
		fileMenu.add(new JMenuItem("Новый"));
		JMenuItem exitItem = new JMenuItem("Выход");
		exitItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){System.exit(0);}
		});		
		fileMenu.add(exitItem);
		add(fileMenu);
		//-----------------------------------
		JMenu toolsMenu = new JMenu("Инструменты");
		toolsMenu.add(new JMenuItem("Узел"));
		toolsMenu.add(new JMenuItem("Дуга"));
		add(toolsMenu);
		//-----------------------------------
		JMenu editMenu = new JMenu("Редактирование");
		JMenuItem cutItem = new JMenuItem("Вырезать");
		//cutItem.addActionListener(new ActionListener(){
			//public void actionPerformed(ActionEvent event){controller.cut();}
		//});
		editMenu.add(cutItem);
		editMenu.add(new JMenuItem("Копировать"));
		editMenu.add(new JMenuItem("Вставить"));
		//editMenu.add(new JMenuItem("Удалить"));
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
