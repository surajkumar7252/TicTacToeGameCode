import java.util.Scanner;

public class TicTacToeGame {
	
	public static final char EMPTY =' ';
	public static final char X_CHARACTER ='X';
	public static final char O_CHARACTER ='O';
	
	static Scanner sc=new Scanner(System.in);
	public static char[] createBoard() {
		 char[] Board= new char[10];
	     for(int index=0;index<10;index++){
	    	Board[index]=' ';
        	}
	     return Board;
	  }
	
	public static void showBoard() {
		 char[] Board= new char[10];
	     for(int index=0;index<10;index++){
	    	Board[index]=' ';
       	}
	  }
	
	public static char choice() {
		System.out.println("Enter you input(X/O):");
		char option = sc.next().charAt(0); 
		char computerOption;
		if(option=='X')
		{
			computerOption= X_CHARACTER;
			
		}
		if(option=='O')
		{
			computerOption= O_CHARACTER;
		}
		else
		{
			System.out.println("Please enter the valid character");
		}
		return option;
	}
	
	public static void showBoard(char[] assignedBoard)
	{
	 for(int index=0;index<10;index++)
	 {
		 System.out.println(assignedBoard[index]+ "--");
	 }
	}
     public static void main(String[] args) {
	    char[] assignedBoard = new char[10];
	    assignedBoard=createBoard();
	    showBoard(assignedBoard);
	    char optedChoice=choice();
	    
		
	    System.out.println("chosen option "+ optedChoice);
		}
    }
