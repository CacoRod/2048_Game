package Game;

public class Main {

	public static void main(String[] args) {

		Board test = new Board();
		test.getField(0, 1).setValue(8);
		test.getField(0, 2).setValue(8);
		test.getField(0, 3).setValue(8);
		test.getField(1, 1).setValue(8);
		test.getField(1, 3).setValue(8);
		test.getField(1, 0).setValue(2);
		test.getField(2, 1).setValue(2);
		test.getField(2, 3).setValue(2);
		test.consoleRender();
		test.moveLeft();
		System.out.println("\n");
		test.consoleRender();

	}

}
