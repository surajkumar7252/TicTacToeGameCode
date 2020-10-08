import java.util.Scanner;

public class TicTacToeGame {
	static Scanner sc=new Scanner(System.in);
	public static void createBoard() {
		 char[] Board= new char[10];
	     for(int index=0;index<10;index++){
	    	Board[index]=' ';
        	}
	  }
	
	public static char choice() {
		System.out.println("Enter you input(X/O):");
		char option = sc.next().charAt(0); 
		if(option=='X')
		{
			System.out.println("you chose X");
		}
		if(option=='O')
		{
			System.out.println("you chose X");
		}
		else
		{
			System.out.println("Please enter the valid character");
		}
		return option;
	}
     public static void main(String[] args) {
	    createBoard();
	    char optedChoice=choice();
	    
	    System.out.println("chosen option "+ optedChoice);
		}
    }
