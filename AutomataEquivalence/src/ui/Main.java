package ui;

import java.io.*;
import java.util.*;

import model.Moore;

public class Main {

	public static final String MOORE_M1 = "data" + File.separator + "MOORE_M1.txt";
	public static final String MOORE_M2 = "data" + File.separator + "MOORE_M2.txt";
	
	public static final String MEALY_M1 = "data" + File.separator + "MEALY_M1.txt";
	public static final String MEALY_M2 = "data" + File.separator + "MEALY_M2.txt";
	
	private Scanner sc;
	private Moore moore;
	
	
	public static void main(String[] args) {
		
		Main obj = new Main();
		obj.menu();
		
		
		
				
			
		
		
	}
	
	public Main() {
		
		 sc = new Scanner(System.in);
		
	}
	
	public void menu() {
		
		boolean menu = true;
		
		while(menu == true) {

			System.out.println("-------------- MENU --------------");
			System.out.println("1: Moore Machine \n" + 
							   "2: Mealy Machine \n" +
							   "3: Quit program");
			int option = Integer.parseInt(sc.nextLine());
			
			switch(option) {
				case 1:
					mooreMachine();
					break;
				case 2:
					break;
				case 3:
					menu = false;
					System.out.println("Program Exit");
					break;
			}
			
		}
		
		
	}
	
	
	public void mooreMachine() {
		
		readMooreMachineFile();
		moore.mooreAlgorithm();
	}
	
	public void readMooreMachineFile() {
		
		try {
			// ----------------------
			// Machine 1
			// ----------------------
			
			BufferedReader br = new BufferedReader(new FileReader(new File(MOORE_M1)));
			
			String line = br.readLine();
			int counter = 0;
			
			
			char[] states1 = new char[0];
			int[] outputAlphabet1 = new int[0];
			
			while(line != null && counter < 2) {
				
				if(counter == 0) {
					
					String[] ia1 = line.split(" ");
					states1 = new char[ia1.length];
					
					for(int i=0; i<ia1.length; i++) {
						states1[i] = ia1[i].charAt(0);
						
					}
				}else if(counter == 1) {
					
					String[] oa1 = line.split(" ");
					outputAlphabet1 = new int[oa1.length];
					
					for(int i=0; i<oa1.length; i++) {
						outputAlphabet1[i] = Integer.parseInt(oa1[i]);

					}
					
				}
				
				counter++;
				line = br.readLine();
			}
			
			char[][] stateTableM1 = new char[states1.length][outputAlphabet1.length+1];
			
			counter = 0;
			
			while(line != null && counter < states1.length) {
				
				String[] row = line.split(" "); 
				
				for(int j = 0; j<stateTableM1[0].length; j++) {
					stateTableM1[counter][j] = row[j].charAt(0);
					
					
				}
				
				counter++;
				line = br.readLine();
			}
			
			// ----------------------
			// Machine 2
			// ----------------------
			
			br = new BufferedReader(new FileReader(new File(MOORE_M2)));
			
			line = br.readLine();
			counter = 0;
			
		
			char[] states2 = new char[0];
			int[] outputAlphabet2 = new int[0];
			
			while(line != null && counter < 2) {
				
				if(counter == 0) {
					
					String[] ia2 = line.split(" ");
					states2 = new char[ia2.length];
					
					for(int i=0; i<ia2.length; i++) {
						states2[i] = ia2[i].charAt(0);
					}
				}else if(counter == 1) {
					
					String[] oa2 = line.split(" ");
					outputAlphabet2 = new int[oa2.length];
					
					for(int i=0; i<oa2.length; i++) {
						outputAlphabet2[i] = Integer.parseInt(oa2[i]);
					}
					
				}
				
				counter++;
				line = br.readLine();
			}
			
			
			char[][] stateTableM2 = new char[states2.length][outputAlphabet2.length+1];
			
			counter = 0;
			
			while(line != null) {
				
				String[] row = line.split(" "); 
				
				for(int j = 0; j<stateTableM2[0].length; j++) {
					stateTableM2[counter][j] = row[j].charAt(0);
				}
				
				counter++;
				line = br.readLine();
			}
			
			// ----------------------
			// Initilize Moore
			// ----------------------
			
			
			
			if (arrayEquals(outputAlphabet1,outputAlphabet2)){
			
				moore = new Moore(states1, states2, outputAlphabet1, stateTableM1, stateTableM2);
				
			}else {
				System.out.println("The output alphabet is not the same for both machines");
			}
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	public boolean arrayEquals(int[] a1, int[] a2) {
		
		boolean equal = true;
		
		for(int i=0; i<a1.length; i++) {
			if(a1[i] != a2[i]) {
				equal = false;
				break;
			}
		}
		
		return equal;
	}
	
	//Debuggin Methods
	
	public void displayMatrix(char[][] m) {
		
		for(int i=0; i<m.length; i++) {
			for(int j=0; j<m[0].length; j++) {
				System.out.print(m[i][j] + " ");
			}
			
			System.out.println();
		}
	
	}
	
}
