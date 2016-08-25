import static org.junit.Assert.*;

import org.junit.Test;

//import BreakthroughImpl.PieceType;
//import BreakthroughImpl.PlayerType;
import org.junit.*;
/**
 * Initial test case class for Breakthrough
 * 
 * This source code is from the book "Flexible, Reliable Software: Using
 * Patterns and Agile Development" published 2010 by CRC Press. Author: Henrik B
 * Christensen Computer Science Department Aarhus University
 * 
 * This source code is provided WITHOUT ANY WARRANTY either expressed or
 * implied. You may study, use, modify, and distribute it for non-commercial
 * purposes. For any commercial use, see http://www.baerbak.com/
 */
public class TestBreakthrough {
	Breakthrough game;

	/** Fixture */
	@Before
	public void setUp() {
		game = new BreakthroughImpl();
	//	BreakthroughImpl.PlayerType playerInTurn=BreakthroughImpl.PlayerType.WHITE;
		//Breakthrough.PlayerType Player;
	}
		
	
//Should Have Black Pawn on 00
	@Test
	public void shouldHaveBlackPawnOn00(){
		assertEquals( "Black has pawn on (0,0)",
				BreakthroughImpl.PieceType.BLACK, game.getPieceAt(0,0) );
	}
	
//Should Have White Pawn on 77
	@Test
	public void shouldHaveWhitePawnOn77(){
		assertEquals( "White has pawn on (7,7)",
				BreakthroughImpl.PieceType.WHITE, game.getPieceAt(7,7) );
	}
	
//Shouldn't have pawn on 55
	@Test
	public void shouldHaveNonePawnOn55()
	{
		assertEquals( "None on (5,5)",
				BreakthroughImpl.PieceType.NONE, game.getPieceAt(5,5) );
	}
			
//Player In Turn should be White Player.	
	@Test
	public void shouldBeginWithWhitePlayer()
	{   
	    assertEquals(BreakthroughImpl.PlayerType.WHITE, game.getPlayerInTurn());
	}
	
	
//Can Move One Piece One Square vertically Towards Home Row	  
	  @Test
	  public void straightMove()
	  {  
			game.move(6, 0, 5, 0);
			assertTrue(game.getPieceAt(6, 0) == BreakthroughImpl.PieceType.NONE);
			assertTrue(game.getPieceAt(5, 0) == BreakthroughImpl.PieceType.WHITE);
			game.move(1, 4, 2, 4);
			assertTrue(game.getPieceAt(2, 4) == BreakthroughImpl.PieceType.BLACK);
			assertTrue(game.getPieceAt(1, 4) == BreakthroughImpl.PieceType.NONE);
	  }
	  
//Can Move One Piece One Square Diagonally Right Towards Home Row	  
	  @Test
	  public void diagonalRightMove() 
	  {
	    game.move(6, 0, 5, 1); //Move from (6,0) to (5,1)
	    assertEquals( "Piece should have moved from (6,0).",
	            BreakthroughImpl.PieceType.NONE, game.getPieceAt(6,0) );
	    assertEquals( "Piece should have moved to (5,1).",
	            BreakthroughImpl.PieceType.WHITE, game.getPieceAt(5,1) );

	  }

//Can Move One Piece One Square Diagonally Left Towards Home Row
	  @Test
	  public void diagonalLeftMove()
	  {
	    game.move(6, 1, 5, 0); //Move from (6,1) to (5,0)
	    assertEquals( "Piece should have moved from (6,1)",
	    		BreakthroughImpl.PieceType.NONE, game.getPieceAt(6,1) );
	    assertEquals( "Piece should have moved to (5,0).",
	            BreakthroughImpl.PieceType.WHITE, game.getPieceAt(5,0) );
	  }
	  
//Should Only Move One Square Per Turn
	  @Test 
	  public void shouldOnlyMoveOneSquarePerTurn()
	  {	  
	    //Cannot move more than one square
		assertFalse(game.isMoveValid(6, 0, 4, 0));
	    
	  }
	
//Should Not Allow The Move To Sideways
	 @Test 
	  public void shouldNotAllowMoveSideways() 
	  {
		game.move(6, 0, 5, 0); //White's turn
		game.move(1, 1, 2, 1); //Black's turn
		//Moving in same row is invalid
		assertFalse(game.isMoveValid(5, 0, 5, 1)); //White's turn
	  }  
				
//Should Switch From White To Black Player
		@Test
		public void shouldSwitchFromWhiteToBlackPlayer()
		{
			assertTrue(game.getPlayerInTurn() == BreakthroughImpl.PlayerType.WHITE);
			game.move(6, 7, 5, 7);
			assertTrue(game.getPlayerInTurn() == BreakthroughImpl.PlayerType.BLACK);
		}

//Should Switch Player After Each Move
		@Test
		public void shouldSwitchPlayerAfterEachMove() 
		{
			assertTrue(game.getPlayerInTurn() == BreakthroughImpl.PlayerType.WHITE);
			game.move(6, 7, 5, 7);
			assertTrue(game.getPlayerInTurn() == BreakthroughImpl.PlayerType.BLACK);
			game.move(1, 0, 2, 0);
			assertTrue(game.getPlayerInTurn() == BreakthroughImpl.PlayerType.WHITE);
			game.move(5, 7, 4, 7);
			assertTrue(game.getPlayerInTurn() == BreakthroughImpl.PlayerType.BLACK);
		}	

// Player Should Not Move Empty Piece i.e.,there should be a piece in from grid
		@Test
		public void playerShouldNotMoveEmptyPiece()
		{
			assertTrue(game.getPieceAt(5,0) == BreakthroughImpl.PieceType.NONE);
			assertFalse(game.isMoveValid(5, 0, 4, 0));
		}

//Each Player Can Move Only Piece Of His Color
		@Test
		public void eachPlayerCanMoveOnlyPieceOfHisColor() 
		{
			assertTrue(game.getPlayerInTurn() == BreakthroughImpl.PlayerType.WHITE);
			game.move(6, 4, 5, 4);
			assertTrue(game.getPlayerInTurn() == BreakthroughImpl.PlayerType.BLACK);
			game.move(1, 2, 2, 2);
		}
		
//Black Player Should Not Move To Field With Black Piece
		@Test 
		public void blackPlayerShouldNotMoveToBlackPiece()
		{
			assertFalse(game.isMoveValid(0, 0, 1, 0));
		}	
		
//White Player Should Not Move To Field With white Piece
		@Test 
		public void whitePlayerShouldNotMoveToWhitePiece() 
		{
			assertFalse(game.isMoveValid(7, 7, 6, 7));
		}

		
//White Player Should Not Go Backwards
		@Test
		public void whitePlayerShouldNotGoBackwards()
		{
			assertFalse(game.isMoveValid(5, 0, 6, 0));
		}

//Black Player Should Not Go Backwards
		@Test
		public void blackPlayerShouldNotGoBackwards() 
		{
			assertFalse(game.isMoveValid(1, 2, 0, 2));
		}

//White Player Cannot Make More Than One Step Move
		@Test 
		public void whitePlayerCannotMakeMoreThanOneStepMove() 
		{
			game.move(6, 0, 4, 0);
			assertTrue(game.getPieceAt(6, 0) == BreakthroughImpl.PieceType.WHITE);
			assertFalse(game.getPieceAt(4, 0) == BreakthroughImpl.PieceType.WHITE);
			assertTrue(game.getPieceAt(4, 0) == BreakthroughImpl.PieceType.NONE);
		}
		
//Black Player Should Not Make More Than One Step Move
		@Test 
		public void blackPlayerShouldNotMakeMoreThanOneStepMove()
		{
			assertFalse(game.isMoveValid(0, 2, 1, 2));
			game.move(1, 1, 3, 1);
			assertTrue(game.getPieceAt(1, 1) == BreakthroughImpl.PieceType.BLACK);
			assertFalse(game.getPieceAt(3, 1) == BreakthroughImpl.PieceType.BLACK);
			assertTrue(game.getPieceAt(3, 1) == BreakthroughImpl.PieceType.NONE);
			
		}
//white pawn Should Be The Winner	
		@Test
		public void whiteShouldBeTheWinner() {	
			assertEquals(game.getWinner(), null);
			game.move(6, 0, 5, 0);
			assertEquals(game.getWinner(), null);
			game.move(1, 1, 2, 2);
			assertEquals(game.getWinner(), null);
			game.move(5, 0, 4, 0);
			assertEquals(game.getWinner(), null);
			game.move(0, 0, 1, 1);
			assertEquals(game.getWinner(), null);
			game.move(4, 0, 3, 0);
			assertEquals(game.getWinner(), null);
			game.move(1, 0, 2, 1);
			assertEquals(game.getWinner(), null);
			game.move(3, 0, 2, 0);
			assertEquals(game.getWinner(), null);
			game.move(1, 7, 2, 7);
			assertEquals(game.getWinner(), null);
			game.move(2, 0, 1, 0);
			assertEquals(game.getWinner(), null);
			game.move(2, 7, 3, 7);
			assertEquals(game.getWinner(), null);
			game.move(1, 0, 0, 0);
			assertEquals(game.getWinner(), BreakthroughImpl.PlayerType.WHITE);
	//move should be invalid after we get winner
			assertFalse(game.isMoveValid(3,7,4,7));
		}
		
//Arguments cannot cross the board limits
		@Test
		public void shouldNotCrossBoardLimits() 
		{	
			assertEquals(game.getWinner(), null);
			game.move(6, 0, 5, 0);
			assertFalse(game.isMoveValid(0, 0, -1, 0));	
		}
		
		
//White Piece Should Capture Black piece Piece Diagonally	
		@Test
		public void whitePieceShouldCaptuerBlackPieceDiagonally()
		{
			
			game.move(6, 0, 5, 0);		//white player first
			game.move(1, 0, 2, 0); 		//black player's turn
			game.move(5, 0, 4, 0);		//white player's turn
			game.move(2, 0, 3, 1);		//black player's turn
			assertTrue(game.getPieceAt(4, 0) == BreakthroughImpl.PieceType.WHITE);
			assertTrue(game.getPieceAt(3, 1) == BreakthroughImpl.PieceType.BLACK);
			game.move(4, 0, 3, 1);
		//After taking the pawn white piece will be moved to black's position.			assertTrue(game.getPieceAt(3, 1) == BreakthroughImpl.PieceType.WHITE);
			assertEquals(game.getPieceAt(4, 0), BreakthroughImpl.PieceType.NONE);
			int count=0;
		//After taking the pawn black piece will be removed from the board
			for(int i=0;i<8;i++)
			{
				for(int j=0;j<8;j++)
				{
					if(game.getPieceAt(i, j)== BreakthroughImpl.PieceType.BLACK)
					count++;  // counting the number of blacks on the board
				}}
			assertEquals(count,8+8-1);
		}
		
//White Piece Should Not Capture Black Piece Vertically	
		@Test
		public void whitePieceShouldNotCaptureBlackPieceVertically() 
		{
			game.move(6, 0, 5, 0);		//white player first
			game.move(1, 0, 2, 0); 		//black player's turn
			game.move(5, 0, 4, 0);		//white player's turn
			game.move(2, 0, 3, 0);		//black player's turn
			assertTrue(game.getPieceAt(4, 0) == BreakthroughImpl.PieceType.WHITE);
			assertTrue(game.getPieceAt(3, 0) == BreakthroughImpl.PieceType.BLACK);
			game.move(4, 0, 3, 0);		//black player's turn
		//pawns are not moved as white player cannot take black pawn vertically
			assertTrue(game.getPieceAt(4, 0) == BreakthroughImpl.PieceType.WHITE);
			assertTrue(game.getPieceAt(3, 0) == BreakthroughImpl.PieceType.BLACK);
		}

//Toggle players only after a valid move
		@Test
		public void toggleOnlyAfterValidMove()
		{
			game.move(6, 0, 5, 0);		//white player first
			game.move(1, 0, 2, 0); 		//black player's turn
			game.move(5, 0, 5, 1);		//white player's turn
		//Invalid move so player should be white again
			assertTrue(game.getPlayerInTurn()==Breakthrough.PlayerType.WHITE);
			game.move(5,1,4,0);		//white player's turn
		//Invalid move as player should continue from (5,0)
			assertTrue(game.getPlayerInTurn()==Breakthrough.PlayerType.WHITE);
			game.move(5,0,4,0);		//white player's turn
		//Should toggle the player now 	
			assertTrue(game.getPlayerInTurn()==Breakthrough.PlayerType.BLACK);


			
		}
		
	}



