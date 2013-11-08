package Model;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.ExpandVetoException;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import Main.FrameMain;

public class TreeCalculation extends JPanel implements TreeSelectionListener {
	private FrameMain frameMain;
	private JTree tree;
	private JButton buttonPrev;
	private JButton buttonNext;
	static int index = -1;
	private List<TreePath> history;

	public TreeCalculation(String expression, FrameMain frameMain) {
		this.frameMain = frameMain;
		history = new ArrayList<TreePath>();
		tree = new JTree(Node.createNode(expression));
		Node root = (Node) tree.getModel().getRoot();
		generateHistory(root, history);
		frameMain.inDisplay(root.getExpression());
		root.close();
		tree.collapseRow(0);
		//tree.setEnabled(false);
		index = 0;

		tree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.addTreeSelectionListener(this);
		tree.addTreeExpansionListener(new SwitchTree());
		JScrollPane treeView = new JScrollPane(tree);

		JPanel navigation = new JPanel();
		buttonPrev = new JButton("<=");
		buttonPrev.addActionListener(new PrevHistory());
		buttonNext = new JButton("=>");
		buttonNext.addActionListener(new NextHistory());
		navigation.add(buttonPrev);
		navigation.add(buttonNext);
		
		
		setLayout(new BorderLayout());
		add(treeView, BorderLayout.CENTER);
		add(navigation, BorderLayout.SOUTH);
	}

	private void generateHistory(Node parent, List<TreePath> his) {
		if (parent.getClass() == NodeNumber.class) {
			return;
		}
		his.add(new TreePath(parent.getPath()));
		for (Node child : parent.getOperands()) {
			generateHistory(child, his);
		}
	}

	private class PrevHistory implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			for (int number = index; number < history.size(); number++) {
				Node node = (Node) history.get(number).getLastPathComponent();
				if (!node.isOpen()) {
					tree.expandPath((history.get(number)));
					break;
				}
			}
		}
	}

	private class NextHistory implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			for (int number = index; number >= 0; number--) {
				Node node = (Node) history.get(number).getLastPathComponent();
				if (node.isOpen()) {
					tree.collapsePath((history.get(number)));
					break;
				}
			}
		}
	}

	public void valueChanged(TreeSelectionEvent arg0) {
		// Node node = (Node) tree.getLastSelectedPathComponent();
		// if (node.isLeaf()) {
		// int x = 0;
		// }
		// if (node.getAllowsChildren()) {
		// int x = 0;
		// }
		// revalidate();
		// repaint();
		// Node nodeInfo = (Node) node.getUserObject();
	}
	
	private class SwitchTree implements TreeExpansionListener {
		public void treeCollapsed(TreeExpansionEvent arg0) {
			Node node = (Node) arg0.getPath().getLastPathComponent();
			node.close();
			reIndex(node);
			updateDisplay();
			tree.updateUI();
		}

		public void treeExpanded(TreeExpansionEvent arg0) {
			Node node = (Node) arg0.getPath().getLastPathComponent();
			node.open();
			reIndex(node);
			updateDisplay();
			tree.updateUI();
		}
		
		private void reIndex(Node node) {
			for(int number = 0; number < history.size(); number++){
				if( node == (Node) history.get(number).getLastPathComponent()){
					index = number;
					return;
				}
			}
		}
	}

	void updateDisplay() {
		Node root = (Node) tree.getModel().getRoot();
		frameMain.inDisplay(root.getExpression());
	}
}
