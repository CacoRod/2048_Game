package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import Game.Field;
import Game.Game;
import Game.Player;
import Game.PowerUp;
import Game.PowerUpDivide;

class GameTest {

	@BeforeClass
	void setUp() throws Exception {
		Field[][] table = 
			  { {new Field(2,null),new Field(2,null),new Field(4,null),new Field(0,null)},
				{new Field(2,null),new Field(0,null),new Field(0,null),new Field(0,null)},
				{new Field(4,null),new Field(0,null),new Field(4,null),new Field(0,null)},
				{new Field(0,null),new Field(0,null),new Field(8,null),new Field(8,null)}
			};
		gameTest = new Game();
		gameTest.getPlayer1().getMoves().setTable(table);
		gameTest.getPlayer2().getMoves().setTable(table);
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Test
	void applyDebufftest() {
		Field[][] table = 
			  { {new Field(2,null),new Field(2,null),new Field(4,null),new Field(0,null)},
				{new Field(2,null),new Field(0,null),new Field(0,null),new Field(0,null)},
				{new Field(4,null),new Field(0,null),new Field(4,null),new Field(0,null)},
				{new Field(0,null),new Field(0,null),new Field(8,null),new Field(8,null)}
			};
		gameTest.powerUpTrigger(new PowerUpDivide(), gameTest.getPlayer1());
		assertNotEquals(gameTest.getPlayer1().getMoves().getTable(), table);
	}
	@Test
	void powerUpTriggertest() {
		Field[][] table = 
			  { {new Field(2,null),new Field(2,null),new Field(4,null),new Field(0,null)},
				{new Field(2,null),new Field(0,null),new Field(0,null),new Field(0,null)},
				{new Field(4,null),new Field(0,null),new Field(4,null),new Field(0,null)},
				{new Field(0,null),new Field(0,null),new Field(8,null),new Field(8,null)}
			};
		gameTest.powerUpTrigger(new PowerUpDivide(), gameTest.getPlayer1());
		assertNotEquals(gameTest.getPlayer2().getMoves().getTable(), table);
	}
	
	
	Game gameTest;
	
	
	
	
	

}
