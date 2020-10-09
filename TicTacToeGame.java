import java.util.Scanner;

public class TicTacToeGame {
	
	public static final char EMPTY =' ';
	public static final char X_CHARACTER ='X';
	public static final char O_CHARACTER ='O';
	public static final int IS_COMPUTER_TURN=1;
	public static final int IS_USER_TURN=0;
	public enum Players{
		COMPUTERTURN,USERTURN;
	}
	public enum IsPlayingForward{
		 MATCHDRAW, PLAYERWINS, OTHERSTURN;
	}
	static Scanner sc=new Scanner(System.in);
	
	
	/**
	 * UC1
	 * @return
	 */
	public static char[] createBoard() {
		 char[] Board= new char[10];
	     for(int index=1;index<10;index++){
	    	Board[index]=' ';
        	}
	     return Board;
	  }
	
	
	
	/**
	 * UC2
	 * @return
	 */
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
	
	/**
	 * UC3
	 * @param assignedBoard
	 */
	public static void showBoard(char[] assignedBoard)
	{
	 for(int row=1;row<=3;row++){
		 for(int column=0;column<=3;column++) {
			 System.out.print(assignedBoard[row+column]+ "--"); 
		 }
		 
		 System.out.println();
	 }
	}

	/**
	 * UC4
	 * @param index
	 * @param assignedBoard
	 * @return
	 */
	public static boolean isEmpty(int index,char[] assignedBoard)
	{     
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
	/**
	 * UC4
	 * @param assignedBoard
	 * @return
	 */
	public static int desiredLocation(char[] assignedBoard)
	{     
	      char[] Board= new char[10];
           Board=assignedBoard;
           boolean goAhead;
           System.out.println("Enter your desired index:(between 1 to 9) " );
   		   int desiredIndex = sc.nextInt();
   		   goAhead=isEmpty(desiredIndex,Board);
          if(goAhead)
          {
        	  System.out.println("Your selected index in vacant. You can mark here"); 
        	  
          }
          else
  		{
  			System.out.println("index not available");
  		}return desiredIndex;
	}
	/**
	 * UC5
	 * @param desiredIndex
	 * @param assignedBoard
	 * @return
	 */
	public static char[] makeMove(int desiredIndex,char[] assignedBoard)
	{   char[] Board= new char[10];
	     Board=assignedBoard;
	     char choice;
	     do {
			System.out.println("enter you input (X/O)");
			char desiredInput = sc.next().charAt(0);
			Board[desiredIndex]=desiredInput;
		    System.out.println("do you want to perform again (Y/N:)");
		choice = sc.next().charAt(0);
	     }while(choice=='Y');
		return Board;
	}
	/**
	 * UC6
	 * @return
	 */
	public static Players firstPlayer() {
		int a= (int)Math.floor(Math.random()*10)%2; 
		if(a==IS_COMPUTER_TURN){
			return Players.COMPUTERTURN ;
			}
		else {
			return Players.USERTURN  ;
		}
	}
	
	
	/**
	 * UC7
	 * @param optedChoice
	 * @param assignedBoard
	 */
	public static IsPlayingForward isPlayForwardPossible (char optedChoice,char[] assignedBoard)
	{  
          if(assignedBoard[1]==optedChoice && assignedBoard[2]==optedChoice && assignedBoard[3]==optedChoice ||
        	 assignedBoard[4]==optedChoice && assignedBoard[5]==optedChoice && assignedBoard[6]==optedChoice ||
        	 assignedBoard[7]==optedChoice && assignedBoard[8]==optedChoice && assignedBoard[9]==optedChoice||
        	 assignedBoard[1]==optedChoice && assignedBoard[4]==optedChoice && assignedBoard[7]==optedChoice||
        	 assignedBoard[2]==optedChoice && assignedBoard[5]==optedChoice && assignedBoard[8]==optedChoice||
        	 assignedBoard[3]==optedChoice && assignedBoard[6]==optedChoice && assignedBoard[9]==optedChoice||
        	 assignedBoard[1]==optedChoice && assignedBoard[5]==optedChoice && assignedBoard[9]==optedChoice||
        	 assignedBoard[3]==optedChoice && assignedBoard[5]==optedChoice && assignedBoard[7]==optedChoice){
          
			return IsPlayingForward.PLAYERWINS;
		}
          
          else{ 
        	  int counter=0;
        	  for(int index=1;index<10;index++) {
        		 if(assignedBoard[index]!=' ') {
        			 counter++;
        		  }
        	    }
        	 if(counter==9) 
        		 return IsPlayingForward.MATCHDRAW;
        	
        	 else 
        	     return IsPlayingForward.OTHERSTURN;
        	 }
        	 
          }
          
	/**
	 * UC8
	 * @param optedChoice
	 * @param assignedBoard
	 * @return
	 */
	public static int computerPlaying (char optedChoice,char[] assignedBoard)
	{   IsPlayingForward processedChoice;
		int desiredCell=0;
	    char[] Board= new char[10];
        Board=assignedBoard;
        for(int index=1;index<10;index++)
        {
        	if(Board[index]== EMPTY) {
        		Board[index]=optedChoice;
        		processedChoice=isPlayForwardPossible(optedChoice,Board);
        		if(processedChoice==IsPlayingForward.PLAYERWINS) {
        			desiredCell=index;
        		}
        		
        	}
        }
		return desiredCell;
	}
     public static void main(String[] args) {
	    char[] assignedBoard = new char[10];
	    char repeat;
	    int desiredComputerWinIndex;
	    assignedBoard=createBoard();
	    Players firstPlay=firstPlayer();
	    
	    System.out.println("first Turn goes to " + firstPlay);
	    
	    do {
	    showBoard(assignedBoard);
	    char optedChoice=choice();
	    showBoard(assignedBoard);
	    
		int indexDesired=desiredLocation(assignedBoard);
		assignedBoard=makeMove(indexDesired,assignedBoard);
		IsPlayingForward forwardPlay=isPlayForwardPossible(optedChoice,assignedBoard);
		desiredComputerWinIndex=computerPlaying(optedChoice,assignedBoard);
		System.out.println("Desired index for the computer to win "+desiredComputerWinIndex);
		System.out.println("Game Status " + forwardPlay);
	    System.out.println("chosen option "+ optedChoice);
	    System.out.println("do you want to perform again (Y/N:)");
		repeat = sc.next().charAt(0);
		}while(repeat=='Y');
     }
    }
