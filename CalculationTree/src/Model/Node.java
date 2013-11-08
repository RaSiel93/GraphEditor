package Model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

abstract class Node extends DefaultMutableTreeNode {
	abstract public Double execute();
	
	abstract public String getExpression();
	
	abstract public void close();

	abstract public void open();
	
	abstract public boolean isOpen();
	
	abstract public List<Node> getOperands();
	
	static Node createNode(String expression) {
		Node node = null;
		boolean bracket;
		if (bracket = isBracket(expression)) {
			expression = expression.substring(1, expression.length() - 1);
		}
		if (isNumber(expression)) {
			node = new NodeNumber(Double.parseDouble(expression));
		} else {
			int index = analyze(expression);
			if (index != -1) {
				switch (expression.charAt(index)) {
				case '+':
					node = (Node) new NodePlus(
							expression.substring(0, index),
							expression.substring(index + 1, expression.length()),
							bracket);
					break;
				case '-':
					node = (Node) new NodeMinus(
							expression.substring(0, index),
							expression.substring(index + 1, expression.length()),
							bracket);
					break;
				case '*':
					node = (Node) new NodeMultiply(expression.substring(0,
							index), expression.substring(index + 1,
							expression.length()), bracket);
					break;
				case '/':
					node = (Node) new NodeDivide(
							expression.substring(0, index),
							expression.substring(index + 1, expression.length()),
							bracket);
					break;
				}
			} else {

			}
		}

		return node;
	}

	private static boolean isBracket(String expression) {
		int inside = 0;
		int position = -1;

		if (expression.charAt(0) == '(') {
			for (char symbol : expression.toCharArray()) {
				position++;
				if (symbol == '(') {
					inside++;
				} else if (symbol == ')') {
					inside--;
					if (inside == 0) {
						if (position == expression.length() - 1) {
							return true;
						} else {
							return false;
						}
					}
				}
			}
		}
		return false;
	}

	private static int analyze(String expression) {
		List<String> priority = new ArrayList<String>();
		priority.add("+-");
		priority.add("*/");

		int position = -1;

		int deep = -1;
		for (String operations : priority) {
			for (char operation : operations.toCharArray()) {
				for (int index = expression.length() - 1, inside = 0; index > 0; index--) {
					if (expression.charAt(index) == operation) {
						if (inside < deep || deep == -1) {
							deep = inside;
							position = index;
						}
					} else if (expression.charAt(index) == '(') {
						inside--;
					} else if (expression.charAt(index) == ')') {
						inside++;
					}
				}
			}
		}
		return position;
	}

	private static boolean isNumber(String expression) {
		try {
			Double.parseDouble(expression);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
