package Model;

import java.util.ArrayList;
import java.util.List;

public class NodeMultiply extends Node {
	private Node operand1;
	private Node operand2;
	private boolean bracket;
	private boolean status;

	NodeMultiply(String expression1, String expression2, boolean bracket) {
		operand1 = createNode(expression1);
		operand2 = createNode(expression2);
		add(operand1);
		add(operand2);
		this.bracket = bracket;
	}

	public Double execute(){
		return operand1.execute() * operand2.execute();
	}
	
	public String toString() {
		if (status) {
			return "+";
		}
		return String.valueOf(execute());
	}
	
	public void close() {
		status = false;
		operand1.close();
		operand2.close();
	}

	public void open() {
		status = true;
	}

	public List<Node> getOperands() {
		List<Node> nodes = new ArrayList<Node>();
		nodes.add(operand1);
		nodes.add(operand2);
		return nodes;
	}

	public String getExpression() {
		if(!isOpen()){
			return String.valueOf(execute());
		}
		String expression = operand1.getExpression() + "*" + operand2.getExpression();
		if(bracket){
			return "(" + expression + ")";
		}
		return expression;
	}
	
	public boolean isOpen() {
		return status;
	}
}
