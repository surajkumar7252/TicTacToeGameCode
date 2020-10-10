import java.util.Scanner;

public class TicTacToeGame {
	
	public static final char EMPTY =' ';
	public static final char X_CHARACTER ='X';
	public static final char O_CHARACTER ='O';
	public static final int IS_COMPUTER_TURN=1;
	public static final int IS_USER_TURN=0;
	private static final int[] sidePositions = new int[] { 1, 3, 5, 7 };
	private static final char[] board = new char[10];
	
	static char playerLetterChoice, computerLetterChoice;
	static Players firstPlay;
	static IsPlayingForward isPlayingForward ;
	
	public enum Players{
		COMPUTERTURN,USERTURN;
	}
	public enum IsPlayingForward{
		 COMPUTERWINS,MATCHDRAW, PLAYERWINS, OTHERSTURN;
	}
	static Scanner sc=new Scanner(System.in);
	
	
	/**
	 * UC1
	 * @return
	 */
	public static char[] createBoard() {
		 
	     for(int index=1;index<10;index++){
	    	board[index]=' ';
        	}
	     return board;
	  }
	
	
	
	/**
	 * UC2
	 * @return
	 */
	public static char choice() {
		System.out.println("Enter you input(X/O):");
		playerLetterChoice = sc.next().charAt(0); 
		
		if(playerLetterChoice=='X')
		{
			computerLetterChoice= O_CHARACTER;
			
		}
		if(playerLetterChoice=='O')
		{
			computerLetterChoice= X_CHARACTER;
		}
		else
		{
			System.out.println("Please enter the valid character");
		}
		return playerLetterChoice;
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
           Board=assignedBoard.clone();
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
           Board=assignedBoard.clone();
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
	     Board=assignedBoard.clone();
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
	public static char[] computerPlaying (char optedChoice,char[] assignedBoard)
	{   IsPlayingForward processedChoice;
		int desiredCell=0;
	    char[] board= new char[10];
        board=assignedBoard.clone();
        for(int index=1;index<10;index++)
        {
        	if(board[index]== EMPTY) {
        		board[index]=optedChoice;
        		processedChoice=isPlayForwardPossible(optedChoice,board);
        		if(processedChoice==IsPlayingForward.PLAYERWINS) {
        			desiredCell=index;
        		}
        		
        	}
        }
        desiredCell=cornerPlayForComputer(assignedBoard);
        if (desiredCell != -1) {
			board = makeMove(desiredCell, board);
		}
        desiredCell = subsequentChoices(board);
		if (desiredCell != -1) {
			board = makeMove(desiredCell, board);
		}
		return board;
	}
	
	/**
	 * UC9
	 * @param optedChoice
	 * @param assignedBoard
	 * @return
	 */
	public static int computerBlocksPosition (char optedChoice,char[] assignedBoard) {
		
		char[] board=assignedBoard.clone();
		for(int position=0;position<board.length-1;position++) {
			if(isEmpty(position, board)) {
				board=makeMove(position,board);
				if(isPlayForwardPossible(optedChoice, board)==IsPlayingForward.PLAYERWINS) {
					return position;
				}else {
					board=board.clone();
				}
			}
		}
		return -1;
		
	}
     
	/**
	 * UC10
	 * @param assignedBoard
	 * @return
	 */
	public static int cornerPlayForComputer(char[] assignedBoard) {
		int freePositionAtCorner = 0;
		if(assignedBoard[1] == EMPTY)
			freePositionAtCorner = 1;
		else if(assignedBoard[3] == EMPTY)
			freePositionAtCorner = 3;
		else if(assignedBoard[7] == EMPTY)
			freePositionAtCorner = 7;
		else if(assignedBoard[9] == EMPTY)
			freePositionAtCorner = 9;
		return freePositionAtCorner;
	}
	
	/**
	 * UC11
	 * @param board
	 * @return
	 */
	public static int subsequentChoices(char[] board) {
		int centralPosition=5;
		if (isEmpty(centralPosition, board)) {
			return centralPosition;
		}
		for (int position : sidePositions) {
			if (isEmpty(position, board)) {
				return position;
			}
		}
		return -1;
	}
	
	
	/**
	 * UC12
	 * 
	 */
	public static void assignedValidLetter() {
		char playerChoice = choice();
		
		if (playerChoice == 'X') {
			playerLetterChoice = 'X';
			computerLetterChoice = 'O';
		} else {
			playerLetterChoice = 'O';
			computerLetterChoice = 'X';
		}
	}

	
	/**
	 * UC12
	 * @param board
	 */
	public static void fullGamePlay(char[] board) {
		
		while (true) {
			IsPlayingForward gameStatus;
			if (firstPlay == Players.USERTURN) {
				showBoard(board);
				int desiredPosition = desiredLocation( board);
				board = makeMove(desiredPosition, board);
				gameStatus = isPlayForwardPossible(playerLetterChoice, board);
			} else {
				showBoard(board);
				board = computerPlaying(computerLetterChoice, board);
				gameStatus = isPlayForwardPossible(computerLetterChoice, board);
			}
			if (isPlayingForward == IsPlayingForward.PLAYERWINS) {
				System.out.println("Player wins.");
				showBoard(board);
				break;
			} else if (isPlayingForward == IsPlayingForward.COMPUTERWINS) {
				System.out.println("Computer wins.");
				showBoard(board);
				break;
			} else if (isPlayingForward == IsPlayingForward.OTHERSTURN) {
				if (firstPlay == Players.USERTURN) {
					firstPlay = Players.COMPUTERTURN;
				} else {
					firstPlay = Players.USERTURN;
				}
			} else if (isPlayingForward == IsPlayingForward.MATCHDRAW) {
				System.out.println("Match Tie.");
				
				showBoard(board);
				break;
			}
		}
	}
    /**
     * UC13
     * @param args
     */
    public static void main(String[] args) {
    	char newGame=' ';
    	do{
    		createBoard();
    		choice();
    		firstPlay=firstPlayer();
    		fullGamePlay(board);
	    System.out.println("Do you wanna play a new game (Y/N)");
	    newGame = sc.next().charAt(0);
    	}while(newGame=='Y' || newGame=='y');
     }
    }
