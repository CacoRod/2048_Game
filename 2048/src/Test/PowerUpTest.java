package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Game.PowerUp;

class PowerUpTest {

	@BeforeClass
	void setUp() throws Exception {
		test = new PowerUp();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void renderTest() {
		assertEquals(test.render(), "P");
		assertNotNull(test.render());
	}
	
	PowerUp test;

}
