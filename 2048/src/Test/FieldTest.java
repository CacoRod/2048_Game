package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import Game.Field;
import Game.PBlockedField;

class FieldTest {

	@BeforeClass
	void setUp() throws Exception {
		test = new Field(2,null);
		test2 = new Field(2,null);
		test3= new Field(4,null);
		test4= new PBlockedField(2, null);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void checkSumTest() {
		assertTrue(test.checkSum(test2));
		assertFalse(test.checkSum(test3));
		assertFalse(test.checkSum(test4));
		
	}
	@Test
	void SumTest() {
		test.sum(test2);
		assertTrue(test.getValue() == 4);
		assertTrue(test2.getValue() == 0);
		
	}
	
	
	Field test;
	Field test2;
	Field test3;
	Field test4;
	
	
	
	
}
