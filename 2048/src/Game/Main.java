package Game;

public class Main {

	public static void main(String[] args) {

		Board test = new Board();
		test.getTable()[0][0].setValue(2);
		test.getTable()[0][1].setValue(0);
		test.getTable()[0][2].setValue(0);
		test.getTable()[0][3].setValue(2);
		test.getTable()[1][0].setValue(4);
		test.getTable()[1][1].setValue(4);
		test.getTable()[1][2].setValue(4);
		test.getTable()[1][3].setValue(0);
		test.getTable()[2][0].setValue(4);
		test.getTable()[2][1].setValue(4);
		test.getTable()[2][2].setValue(2);
		test.getTable()[2][3].setValue(2);
		test.consoleRender();
		System.out.println(test.getScore());
		test.moveLeft();
		test.consoleRender();
		System.out.println(test.getScore());
		test.moveDown();
		test.consoleRender();
		System.out.println(test.getScore());
	}
}
