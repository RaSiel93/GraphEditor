package Model;

import java.util.List;

public class NodeNumber extends Node {
	private Double number;

	NodeNumber(Double number) {
		this.number = number;
	}

	public Double execute(){
		return number;
	}
	
	public String toString() {
		return String.valueOf(execute());
	}

	public void close(){}

	public void open(){}
	

	public List<Node> getOperands() {
		return null;
	}

	public String getExpression() {
		return String.valueOf(execute());
	}
	
	public boolean isOpen() {
		return false;
	}
}
