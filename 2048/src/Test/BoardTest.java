package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Game.Board;
import Game.Field;
import Game.Player;

class BoardTest {
	
	
	@BeforeEach
	void setUp() throws Exception {
		Field[][] table = 
			  { {new Field(2,board),new Field(2,board),new Field(4,board),new Field(0,board)},
				{new Field(2,board),new Field(0,board),new Field(0,board),new Field(0,board)},
				{new Field(4,board),new Field(0,board),new Field(4,board),new Field(0,board)},
				{new Field(0,board),new Field(0,board),new Field(8,board),new Field(8,board)}
			};
		board = new Board(player);
		board.setTable(table);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testSumLeft() {
		 Field[][] table = 
			  { {new Field(4,board),new Field(0,board),new Field(4,board),new Field(0,board)},
				{new Field(2,board),new Field(0,board),new Field(0,board),new Field(0,board)},
				{new Field(8,board),new Field(0,board),new Field(0,board),new Field(0,board)},
				{new Field(0,board),new Field(0,board),new Field(16,board),new Field(0,board)}
			};
		board.sumLeft();
		assertEquals(board.getTable(), table);
		
	}
	@Test
	void testSumRight() {
		Field[][] table = 
			{ 		{new Field(0,board),new Field(4,board),new Field(4,board),new Field(0,board)},
					{new Field(2,board),new Field(0,board),new Field(0,board),new Field(0,board)},
					{new Field(0,board),new Field(0,board),new Field(8,board),new Field(0,board)},
					{new Field(0,board),new Field(0,board),new Field(0,board),new Field(16,board)}
			};
		board.sumRight();
		assertEquals(board.getTable(), table);
		
	}
	@Test
	void testSumUp() {
		Field[][] table = 
			{ 		{new Field(4,board),new Field(2,board),new Field(8,board),new Field(0,board)},
					{new Field(0,board),new Field(0,board),new Field(0,board),new Field(0,board)},
					{new Field(4,board),new Field(0,board),new Field(0,board),new Field(0,board)},
					{new Field(0,board),new Field(0,board),new Field(8,board),new Field(8,board)}
			};
		board.sumUp();
		assertEquals(board.getTable(), table);
		
	}
	@Test
	void testSumDown() {
		Field[][] table = 
			  { {new Field(0,board),new Field(2,board),new Field(0,board),new Field(0,board)},
				{new Field(4,board),new Field(0,board),new Field(0,board),new Field(0,board)},
				{new Field(4,board),new Field(0,board),new Field(8,board),new Field(0,board)},
				{new Field(0,board),new Field(0,board),new Field(8,board),new Field(8,board)}
			};
		board.sumDown();
		assertEquals(board.getTable(), table);
	}
	
	@Test
	void testMoveLeft() {
		 Field[][] table = 
			  { {new Field(4,board),new Field(4,board),new Field(0,board),new Field(0,board)},
				{new Field(2,board),new Field(0,board),new Field(0,board),new Field(0,board)},
				{new Field(8,board),new Field(0,board),new Field(0,board),new Field(0,board)},
				{new Field(16,board),new Field(0,board),new Field(0,board),new Field(0,board)}
			};
		board.moveLeft();
		assertEquals(board.getTable(), table);
		
	}
	@Test
	void testMoveRight() {
		Field[][] table = 
			{ 		{new Field(0,board),new Field(0,board),new Field(4,board),new Field(4,board)},
					{new Field(0,board),new Field(0,board),new Field(0,board),new Field(2,board)},
					{new Field(0,board),new Field(0,board),new Field(0,board),new Field(8,board)},
					{new Field(0,board),new Field(0,board),new Field(0,board),new Field(16,board)}
			};
		board.moveRight();
		assertEquals(board.getTable(), table);
		
	}
	@Test
	void testMoveUp() {
		Field[][] table = 
			{ 		{new Field(4,board),new Field(2,board),new Field(8,board),new Field(8,board)},
					{new Field(4,board),new Field(0,board),new Field(8,board),new Field(0,board)},
					{new Field(0,board),new Field(0,board),new Field(0,board),new Field(0,board)},
					{new Field(0,board),new Field(0,board),new Field(0,board),new Field(0,board)}
			};
		board.moveUp();
		assertEquals(board.getTable(), table);
		
	}
	@Test
	void testMoveDown() {
		Field[][] table = 
			  { {new Field(0,board),new Field(0,board),new Field(0,board),new Field(0,board)},
				{new Field(0,board),new Field(0,board),new Field(0,board),new Field(0,board)},
				{new Field(4,board),new Field(0,board),new Field(8,board),new Field(0,board)},
				{new Field(4,board),new Field(2,board),new Field(8,board),new Field(8,board)}
			};
		board.moveDown();
		assertEquals(board.getTable(), table);
	}
	@Test
	void fieldSpawnerTest() {
		 Field[][] table = 
			  { {new Field(2,board),new Field(2,board),new Field(4,board),new Field(0,board)},
				{new Field(2,board),new Field(0,board),new Field(0,board),new Field(0,board)},
				{new Field(4,board),new Field(0,board),new Field(4,board),new Field(0,board)},
				{new Field(0,board),new Field(0,board),new Field(8,board),new Field(8,board)}
			};
		board.fieldSpawner();
		assertNotEquals(board.getTable(), table);
	}
	@Test
	void isFullTest() {
		 Field[][] table = 
			  { {new Field(2,board),new Field(2,board),new Field(4,board),new Field(8,board)},
				{new Field(2,board),new Field(2,board),new Field(4,board),new Field(8,board)},
				{new Field(2,board),new Field(2,board),new Field(4,board),new Field(8,board)},
				{new Field(2,board),new Field(2,board),new Field(8,board),new Field(8,board)}
			};
		 board.setTable(table);
		assertTrue(board.isFull());
	}
	@Test
	void checkSumHorizontalTest() {
		assertTrue(board.checkSumHorizontal());
		Field[][] table = 
			{ 		{new Field(2,board),new Field(8,board),new Field(4,board),new Field(0,board)},
					{new Field(2,board),new Field(0,board),new Field(0,board),new Field(0,board)},
					{new Field(4,board),new Field(0,board),new Field(8,board),new Field(0,board)},
					{new Field(0,board),new Field(0,board),new Field(8,board),new Field(8,board)}
			};
		board.setTable(table);
		assertFalse(board.checkSumHorizontal());
	}
	@Test
	void checkSumVerticalTest() {
		assertTrue(board.checkSumVertical());
		Field[][] table = 
			{ 		{new Field(8,board),new Field(8,board),new Field(4,board),new Field(0,board)},
					{new Field(2,board),new Field(0,board),new Field(0,board),new Field(0,board)},
					{new Field(4,board),new Field(0,board),new Field(2,board),new Field(0,board)},
					{new Field(0,board),new Field(0,board),new Field(8,board),new Field(8,board)}
			};
		board.setTable(table);
		assertFalse(board.checkSumVertical());
	}
	
	@Test
	void gameLostTest() {
		assertTrue(board.checkSumVertical());
		Field[][] table = 
			{ 		{new Field(23,board),new Field(87,board),new Field(44,board),new Field(9,board)},
					{new Field(24,board),new Field(8,board),new Field(11,board),new Field(2,board)},
					{new Field(45,board),new Field(9,board),new Field(25,board),new Field(1,board)},
					{new Field(6,board),new Field(10,board),new Field(84,board),new Field(81,board)}
			};
		board.setTable(table);
		assertTrue(board.gameLost());
		Field[][] table2 = 
			{ 		{new Field(23,board),new Field(87,board),new Field(44,board),new Field(9,board)},
					{new Field(24,board),new Field(8,board),new Field(11,board),new Field(2,board)},
					{new Field(45,board),new Field(9,board),new Field(25,board),new Field(1,board)},
					{new Field(6,board),new Field(0,board),new Field(84,board),new Field(81,board)}
			};
		board.setTable(table2);
		assertFalse(board.gameLost());
	}
	@Test
	void randomPowerUpTest() {
		assertNotNull(board.randomPowerUp());
	}
	@Test
	void blockTest() {
		Field[][] table = 
			  { {new Field(2,board),new Field(2,board),new Field(4,board),new Field(0,board)},
				{new Field(2,board),new Field(0,board),new Field(0,board),new Field(0,board)},
				{new Field(4,board),new Field(0,board),new Field(4,board),new Field(0,board)},
				{new Field(0,board),new Field(0,board),new Field(8,board),new Field(8,board)}
			};
		board.blockField();
		assertNotEquals(board.getTable(), table);
		board.revertBlockedField();
		assertEquals(board.getTable(), table);
	}
	
	@Test
	void divideFieldTest() {
		Field[][] table = 
			  { {new Field(2,board),new Field(2,board),new Field(4,board),new Field(0,board)},
				{new Field(2,board),new Field(0,board),new Field(0,board),new Field(0,board)},
				{new Field(4,board),new Field(0,board),new Field(4,board),new Field(0,board)},
				{new Field(0,board),new Field(0,board),new Field(8,board),new Field(8,board)}
			};
		board.divideField();
		assertNotEquals(board.getTable(), table);
	}
	
	@Test
	void removeFieldTest() {
		Field[][] table = 
			  { {new Field(2,board),new Field(2,board),new Field(4,board),new Field(0,board)},
				{new Field(2,board),new Field(0,board),new Field(0,board),new Field(0,board)},
				{new Field(4,board),new Field(0,board),new Field(4,board),new Field(0,board)},
				{new Field(0,board),new Field(0,board),new Field(8,board),new Field(8,board)}
			};
		board.removeField();
		assertNotEquals(board.getTable(), table);
	}
	
	@Test
	void moveDebuffUpTest() {
		Field[][] table = 
			  { {new Field(2,board),new Field(2,board),new Field(4,board),new Field(0,board)},
				{new Field(2,board),new Field(0,board),new Field(0,board),new Field(0,board)},
				{new Field(4,board),new Field(0,board),new Field(4,board),new Field(0,board)},
				{new Field(0,board),new Field(0,board),new Field(8,board),new Field(8,board)}
			};
		board.moveDebuffUp();
		assertNotEquals(board.getTable(), table);
	}
	@Test
	void moveDebuffDownTest() {
		Field[][] table = 
			  { {new Field(2,board),new Field(2,board),new Field(4,board),new Field(0,board)},
				{new Field(2,board),new Field(0,board),new Field(0,board),new Field(0,board)},
				{new Field(4,board),new Field(0,board),new Field(4,board),new Field(0,board)},
				{new Field(0,board),new Field(0,board),new Field(8,board),new Field(8,board)}
			};
		board.moveDebuffDown();
		assertNotEquals(board.getTable(), table);
	}
	@Test
	void moveDebuffLeftTest() {
		Field[][] table = 
			  { {new Field(2,board),new Field(2,board),new Field(4,board),new Field(0,board)},
				{new Field(2,board),new Field(0,board),new Field(0,board),new Field(0,board)},
				{new Field(4,board),new Field(0,board),new Field(4,board),new Field(0,board)},
				{new Field(0,board),new Field(0,board),new Field(8,board),new Field(8,board)}
			};
		board.moveDebuffLeft();
		assertNotEquals(board.getTable(), table);
	}
	@Test
	void moveDebuffRightTest() {
		Field[][] table = 
			  { {new Field(2,board),new Field(2,board),new Field(4,board),new Field(0,board)},
				{new Field(2,board),new Field(0,board),new Field(0,board),new Field(0,board)},
				{new Field(4,board),new Field(0,board),new Field(4,board),new Field(0,board)},
				{new Field(0,board),new Field(0,board),new Field(8,board),new Field(8,board)}
			};
		board.moveDebuffRight();
		assertNotEquals(board.getTable(), table);
	}
	
	Board board;
	Player player;
}
