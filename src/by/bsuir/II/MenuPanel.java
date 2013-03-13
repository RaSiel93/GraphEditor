package by.bsuir.II;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

class MenuPanel extends JMenuBar{
	public MenuPanel(final Controller controller){
		JMenu fileMenu = new JMenu("����");
		fileMenu.add(new JMenuItem("�����"));
		JMenuItem exitItem = new JMenuItem("�����");
		exitItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){System.exit(0);}
		});		
		fileMenu.add(exitItem);
		add(fileMenu);
		//-----------------------------------
		JMenu toolsMenu = new JMenu("�����������");
		toolsMenu.add(new JMenuItem("����"));
		toolsMenu.add(new JMenuItem("����"));
		add(toolsMenu);
		//-----------------------------------
		JMenu editMenu = new JMenu("��������������");
		JMenuItem cutItem = new JMenuItem("��������");
		//cutItem.addActionListener(new ActionListener(){
			//public void actionPerformed(ActionEvent event){controller.cut();}
		//});
		editMenu.add(cutItem);
		editMenu.add(new JMenuItem("����������"));
		editMenu.add(new JMenuItem("��������"));
		//editMenu.add(new JMenuItem("�������"));
		JMenuItem removeItem = new JMenuItem("�������");
		removeItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){controller.removeSelectedObjects();}
		});
		editMenu.add(removeItem);
		JMenuItem selectAllItem = new JMenuItem("�������� ���");
		selectAllItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){controller.selectAllObject();}
		});
		editMenu.add(selectAllItem);
		add(editMenu);
		//-----------------------------------
		JMenu algoMenu = new JMenu("��������");
		algoMenu.add(new JMenuItem("���������"));
		algoMenu.add(new JMenuItem("����������"));
		
		/*Action exitAction = new AbstractAction("exit"){
			public void actionPerformed(ActionEvent event){
				System.exit(0);
			}
		};*/	
		add(algoMenu);	
	}
}
