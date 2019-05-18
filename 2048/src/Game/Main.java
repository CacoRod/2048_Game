package Game;

public class Main {

	public static void main(String[] args) {

		Board test = new Board();
		test.getField(0, 0).setValue(2);
		test.getField(0, 1).setValue(2);
		test.getField(0, 2).setValue(0);
		test.getField(0, 3).setValue(2);
		test.getField(1, 0).setValue(4);
		test.getField(1, 1).setValue(4);
		test.getField(1, 2).setValue(4);
		test.getField(1, 3).setValue(0);
		test.getField(2, 0).setValue(4);
		test.getField(2, 1).setValue(4);
		test.getField(2, 2).setValue(2);
		test.getField(2, 3).setValue(2);
		test.consoleRender();
		test.moveUp();
		System.out.println("\n");
		test.consoleRender();
		test.moveRight();
		System.out.println("\n");
		test.consoleRender();
		
	
	}

}
