package model;

public class Moore {

	public static final char FIRST_STATE_NAME = 'A';
	
	// Attributes
	
	private char[] states1;
	private char[] states2;
	private int[] outputAlphabet;
	private char[][] stateTableM1;
	private char[][] stateTableM2;
	private char[][] directSumStateTable;
	private char[] directSumStates;
	
	public Moore(char[] states1, char[] states2, int[] outputAlphabet, char[][] stateTableM1, char[][] stateTableM2) {
		
		this.states1 = states1;
		this.states2 = states2;
		this.outputAlphabet = outputAlphabet;
		this.stateTableM1 = stateTableM1;
		this.stateTableM2 = stateTableM2;
		this.directSumStateTable = null;
	}
	
	
	//Methods
	
	public void mooreAlgorithm() {
		
		// Step 1: Eliminate inaccessible states from the initial state for each machine M1 and M2. 
		eliminateInaccessibleStates(stateTableM1, states1, 1);
		eliminateInaccessibleStates(stateTableM2, states2, 2);
		
		// Step 2: Rename states
		renameStates();
		
		// Step 3: create the directSum of the two machines M1 and M2
		directSum();
		
		// Step 4: 
		
		
		
	}
	
	public void directSum() {
		
		displayArray(states1);
		displayArray(states2);
		
		int rows = (states1.length + states2.length);
		int columns = outputAlphabet.length + 1;
		
		
		System.out.println("StateTable 1");
		displayMatrix(stateTableM1);
		
		System.out.println("StateTable 2");
		displayMatrix(stateTableM2);
		
		directSumStateTable = new char[rows][columns];
		
		for(int i=0; i < rows; i++) {
			for(int j=0; j < columns; j++){
				
			
				if(i < states1.length) {
					directSumStateTable[i][j] = stateTableM1[i][j];
				}else {
					directSumStateTable[i][j] = stateTableM2[i-states1.length][j];
				}
				
				
			}
		}
		
		System.out.println("Direct Sum");
		displayMatrix(directSumStateTable);
		
	}
	
	public void renameStates() {
		
		//Rename the set of states of the machine M14
		for(int k=0; k<states1.length; k++) {
			char temp = states1[k];
			states1[k] = (char)(FIRST_STATE_NAME+k);
			
			for(int i=0; i<stateTableM1.length; i++) {
				for(int j=0; j<stateTableM1[0].length - 1; j++) {
					
					if(stateTableM1[i][j] == temp) {
						stateTableM1[i][j] = states1[k];
					}
					
				}
			}
			
			
		}
		
		//Rename the set of states of the machine M2
		for(int k=0; k<states2.length; k++) {
			char temp = states2[k];
			states2[k] = (char)(FIRST_STATE_NAME+states1.length+k);
			
			for(int i=0; i<stateTableM2.length; i++) {
				for(int j=0; j<stateTableM2[0].length - 1; j++) {
					
					if(stateTableM2[i][j] == temp) {
						stateTableM2[i][j] = states2[k];
					}
					
				}
			}
			
		}
		
	}
	
	public void eliminateInaccessibleStates(char[][] stateTable, char[] states, int M) {
		
		BinaryTree bt = new BinaryTree();
		
		
		
		//Add the initial state to the root of the binary tree
		bt.add(states[0], states[0]);
		
		// Adds all the states accessible from the initial state to the binary tree
		for(int i=0; i<stateTable.length; i++) {
			
			for(int j=0; j<stateTable[0].length-1; j++) {
				
				//System.out.println("M" + M +" " + states[i] + " " + stateTable[i][j]);
				bt.add(states[i], stateTable[i][j]);
				
			}	
		}
		
		//Finds the inaccessible states from the initial state
		char[] statesToRemove = new char[states.length];
		
		for(int k=0; k<states.length; k++) {
			
			if(!bt.containsNodeRecursive(bt.getRoot(), states[k])) {
				
				statesToRemove[k] = states[k]; 
			}
			
		}
		
		
		// Deletes the inaccessible states from the initial state of the stateTable
		stateTable = deleteMatrixRows(statesToRemove, stateTable);
		
		
		
		// Deletes the inaccessible states from the initial state of states of the machine
		states = deleteStates(statesToRemove, states);
		
		// Updates values
		if(M == 1) {
			states1 = states;
			stateTableM1 = stateTable;
		}else {
			states2 = states;
			stateTableM2 = stateTable;
		}
		
	
	}
	
	public char[] deleteStates(char[] statesToRemove, char[] states) {
		 
		int newStatesSize = 0;
		
		for(int i=0; i<statesToRemove.length; i++) {
			
			if(statesToRemove[i] == '\0') {
				newStatesSize++;
			}
			
		}
		
		
		char[] newStates = new char[newStatesSize];
		
		int i=0;
		for(int k=0; k<states.length; k++) {
			
			if(states[k] == statesToRemove[k]) 
				continue;
			
			newStates[i] = states[k];
			i++;
			
		}
		
		return newStates;
	}
	
	public char[][] deleteMatrixRows(char[] statesToRemove, char[][] matrix){
		
		int rowsSize = matrix.length;
		
		for(int k=0; k<statesToRemove.length; k++) {
			if(statesToRemove[k] != '\0') {
				rowsSize--;
			}
		}
		
		char[][] newMatrix = new char[rowsSize][matrix[0].length];
		
		int p=0;
		for(int i=0; i<matrix.length; i++) {
			for(int j=0; j<matrix[0].length; j++) {
				

				if(statesToRemove[i] != '\0') 
					continue;
					
				newMatrix[p][j] = matrix[i][j];
			}
			p++;
		}
				
		return newMatrix;
		
	}
	
	
	
	// Debugging Methods
	
	public void displayArray(char[] a) {
		for(int i=0; i<a.length; i++) {
			
			if(a[i] == '\0') {
				System.out.print("X" + " ");
			}else {
				System.out.print(a[i] + " ");
			}
			
		}
		
		System.out.println();
	}
	
	public void displayMatrix(char[][] m) {
	
		for(int i=0; i<m.length; i++) {
			for(int j=0; j<m[0].length; j++) {
				System.out.print(m[i][j] + " ");
			}
			
			System.out.println();
		}
	
	}
}
