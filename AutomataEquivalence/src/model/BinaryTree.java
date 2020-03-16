package model;

public class BinaryTree {
	
	Node root;
	
	//Adds a value given its parent value to the binary tree if a recursive way
	public Node addRecursive(Node current, char parentValue, char value) {
		
		
		if(current == null) {
			return new Node(value);
		}else if(current.getLeft() == null && current.getValue() == parentValue) {
			
			current.setLeft(addRecursive(current.getLeft(), parentValue, value));
			
		}else if(current.getRight() == null && current.getValue() == parentValue) {
			
			current.setRight(addRecursive(current.getRight(), parentValue, value));
			
		} else if(containsNodeRecursive(current.getLeft(), parentValue)){
			addRecursive(current.getLeft(), parentValue, value);
			
		}else if(containsNodeRecursive(current.getRight(), parentValue)){
			addRecursive(current.getRight(), parentValue, value);
			
		}else {
			System.out.println("The node couldnt be added");  //The value already exist
		}
		
		
		return current;
		
	}
	
	
	public void add(char parentValue, char value) {
		
		root = addRecursive(root, parentValue, value);
	}
	
	
	public boolean containsNodeRecursive(Node current, char value) {
	
		if(current == null) {
			return false;
			
		}else if(current.getValue() == value) {
			return true;
			
		} else if(containsNodeRecursive(current.getLeft(), value)) {
			return true;
			
		} else if(containsNodeRecursive(current.getRight(), value)) {			
			return true;
			
		} else {
			return false;
		}
		
	}
	
	public Node getRoot() {
		return root;
	}
	
	/*
	
	public static void main(String[] args) {
		
		BinaryTree bt = new BinaryTree();
		
		bt.add('A', 'A');
		bt.add('A', 'C');
		bt.add('A', 'B');
		bt.add('B', 'D');
		bt.add('D', 'K');
		
		
		
	}
	*/
}
