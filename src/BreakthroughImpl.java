
public class BreakthroughImpl implements Breakthrough 
{	
	private int[][] board;
	private int boardSize=8;
	private PlayerType playerInTurn=PlayerType.WHITE;
	
	public BreakthroughImpl()
	{
		board=new int[boardSize][boardSize];
		for(int i=0;i<boardSize;i++)
		{
			for(int j=0;j<boardSize;j++)
			{
				if(i<2)
				{
					board[i][j]=1;
				}
				else if(i>5)
				{
					board[i][j]=2;
				}
				else
				{
					board[i][j]=0;
				}
			}
		}
	}
	
	public PieceType getPieceAt( int row, int column )
	{
		int piece=board[row][column];
		if(piece==1)
		{
			return PieceType.BLACK;
		}
		else if(piece==2)
		{
			return PieceType.WHITE;
		}
		return PieceType.NONE;
	}

	public PlayerType getPlayerInTurn() 
	{
		return playerInTurn;
	}

	public PlayerType getWinner() 
	{
		for(int i=0;i<boardSize;i++)
		{
			if(getPieceAt(0,i)==PieceType.WHITE)
			{
				return PlayerType.WHITE;
			}
			else if(getPieceAt(boardSize-1,i)==PieceType.BLACK)
			{
				return PlayerType.BLACK;
			}
		}
		return null;
	}

	public boolean isMoveValid(int fromRow, int fromColumn,
			int toRow, int toColumn)
	{
		if(!boardLimits(fromRow,fromColumn,toRow,toColumn))
		{
			return false;
		}
		if(!movementDirection(fromRow,fromColumn,toRow,toColumn))
		{
			return false;
		}
		if(!movingOwnPiece(fromRow,fromColumn,toRow,toColumn)){
			return false;
		}
		if(!distance(fromRow,fromColumn,toRow,toColumn)){
			return false;
		}
		if(!sideMoves(fromRow,fromColumn,toRow,toColumn)){
			return false;
		}
		if(!straightPieceTake(fromRow,fromColumn,toRow,toColumn)){
			return false;
		}
		if(getWinner()!=null){
			return false;
		}
		return true;
		
	}
	
	/**
	 * Move a piece from a given location (fromRow, fromColumn) to a new
	 * location (toRow, toColumn). PRECONDITION: the move is valid on the given
	 * board, that is, a previous call to isMoveValid was true.
	 */
	public void move(int fromRow, int fromColumn,
			int toRow, int toColumn)// throws IllegalMoveException
	{
			if(isMoveValid(fromRow,fromColumn,toRow,toColumn))
			{
			board[toRow][toColumn]=board[fromRow][fromColumn];
			board[fromRow][fromColumn]=0;
			playerInTurn=playerInTurn==PlayerType.BLACK?PlayerType.WHITE:PlayerType.BLACK;
			}
	}
	
	/**
	 * Checks if any of the arguments exceed the board limits
	 * @return true if arguments are in the board limits false otherwise
	 */
	private boolean boardLimits(int fromRow,int fromColumn,int toRow,int toColumn)
	{
		if(toColumn>boardSize-1||toRow>boardSize-1 || fromColumn>boardSize-1||fromRow>boardSize-1)
		{
			return false;
		}
		else if(toColumn<0||toRow<0 || fromColumn<0||fromRow<0)
		{
			return false;	
		}
		return true;
	}
	
	/**
	 * Check if player if moving his own piece
	 * @return false if black player moves white or white player moves black
	 * true otherwise
	 */
	private boolean movingOwnPiece(int fromRow,int fromColumn,int toRow,int toColumn)
	{
		if(playerInTurn==PlayerType.WHITE&&getPieceAt(fromRow, fromColumn)!=PieceType.WHITE)
		{
			return false;
		}
		if(playerInTurn==PlayerType.BLACK&&getPieceAt(fromRow, fromColumn)!=PieceType.BLACK)
		{
			return false;
		}
		return true;
	}
	
	/**
	 * Check if the player is moving in the right direction 
	 * i.e.,Both players should move forward
	 * @return true if players are moving forward, false otherwise
	 */
	private boolean movementDirection(int fromRow,int fromColumn,int toRow,int toColumn)
	{
		if(playerInTurn==PlayerType.BLACK&& toRow<fromRow)
		{
			return false;
		}
		if(playerInTurn==PlayerType.WHITE&&toRow>fromRow)
		{
			return false;
		}
		return true;
	}
	
	/**
	 * Check if the distance of the move is exactly 1 either vertically or diagonally 
	 * @return true if the distance is 1 false otherwise
	 */
	private boolean distance(int fromRow,int fromColumn,int toRow,int toColumn)
	{
		if(Math.abs(fromRow-toRow)>1)
		{
			return false;
		}
		if(Math.abs(fromColumn-toColumn)>1)
		{
			return false;
		}
		return true;
	}
	
	/**
	 * Check if there is any side move i.e, move in same row
	 * @return false if there is a side move, true otherwise
	 */
	private boolean sideMoves(int fromRow,int fromColumn,int toRow,int toColumn)
	{
		if(Math.abs(fromColumn-toColumn)>0 && (fromRow-toRow)==0)
			return false;
		return true;
	}
	
	
	/**
	 * Check if the player takes pieces vertically also 
	 * i.e., A player can only take piece of opponent diagonally only
	 * @return false if player takes the pieces vertically  is 1 false otherwise
	 */
	private boolean straightPieceTake(int fromRow,int fromColumn,int toRow,int toColumn)
	{
		if(toColumn==fromColumn)
		{
			return board[toRow][toColumn]==0;
		}
		return true;
	}
	
}
