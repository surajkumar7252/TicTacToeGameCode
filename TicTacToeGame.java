import java.util.Scanner;

public class TicTacToeGame {
	
	public static final char EMPTY =' ';
	public static final char X_CHARACTER ='X';
	public static final char O_CHARACTER ='O';
	
	static Scanner sc=new Scanner(System.in);
	public static char[] createBoard() {
		 char[] Board= new char[10];
	     for(int index=1;index<10;index++){
	    	Board[index]=' ';
        	}
	     return Board;
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
	 for(int row=1;row<=3;row++){
		 for(int column=0;column<=3;column++) {
			 System.out.print(assignedBoard[row+column]+ "--"); 
		 }
		 
		 System.out.println();
	 }
	}

	public static boolean move(int index,char[] assignedBoard)
	{     boolean fact;
	      char[] Board= new char[10];
           Board=assignedBoard;
           if(Board[index]!=' ')
           {
        	  return false; 
           }
           else
           {
        	   return true;
           }
		 
	}
	
	public static char[] userDefinedInput(char[] assignedBoard)
	{   char[] Board= new char[10];
	     Board=assignedBoard;
	     char choice;
	     boolean goAhead;
	     do
	     {
		System.out.println("Enter your desired index:(between 1 to 9) " );
		int desiredIndex = sc.nextInt();
		goAhead=move(desiredIndex,assignedBoard);
		if(goAhead){
			System.out.println("Your selected index in vacant. You can mark here");
			System.out.println("enter you input (X/O");
			char desiredInput = sc.next().charAt(0);
			Board[desiredIndex]=desiredInput;
		 }
		else
		{
			System.out.println("index not available");
		}
		System.out.println("do you want to perform again (Y/N:");
		choice = sc.next().charAt(0);
	     }while(choice=='Y');
		return Board;
	}
	
     public static void main(String[] args) {
	    char[] assignedBoard = new char[10];
	    assignedBoard=createBoard();
	    showBoard(assignedBoard);
	    char optedChoice=choice();
	    assignedBoard= userDefinedInput(assignedBoard);
	    showBoard(assignedBoard);
		
	    System.out.println("chosen option "+ optedChoice);
		}
    }
