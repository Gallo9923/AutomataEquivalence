package model;

public class Node {
	
	private char value;
	private Node left;
	private Node right;
	
	public Node(char value) {
		this.value = value;
		left = null;
		right = null;
	}

	// Getters && Setters
	
	public char getValue() {
		return value;
	}

	public void setValue(char value) {
		this.value = value;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}
	
	
	
	
}
