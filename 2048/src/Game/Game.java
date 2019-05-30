package Game;

import java.util.Scanner;

//tiene dos jugador. Tiene que poder iniciar una nueva partida, restartearla, cargar y guardar
public class Game {
	
	private Player player1;
	private Player player2;
	
	
	public Game() {
		player1 = new Player("player 1",'w','s','a','d');
		player2 = new Player("player 2", 'i','k','j','l');
	}
	public void movement() {
		
		
		while (!player1.getMoves().gameLost() || !player2.getMoves().gameLost()) {
	    
				Scanner scanner = new Scanner(System.in);
				String scan = scanner.nextLine();
				char movement = scan.charAt(0);
				
				
				if (movement == player1.getUp()) {
					player1.getMoves().moveUp();
					System.out.println("\n");
					player1.consoleRender();	
				}
				
				if (movement == player1.getLeft()) {
					player1.getMoves().moveLeft();
					System.out.println("\n");
					player1.consoleRender();	
				}
				
				if (movement == player1.getRight()) {
					player1.getMoves().moveRight();
					System.out.println("\n");
					player1.consoleRender();	
				}
		
				if (movement == player1.getDown()) {
					player1.getMoves().moveDown();
					System.out.println("\n");
					player1.consoleRender();	
					}
				
				
				
				
				if (movement == player2.getUp()) {
					player2.getMoves().moveUp();
					System.out.println("\n");
					player2.consoleRender();	
				}
				
				if (movement == player2.getLeft()) {
					player2.getMoves().moveLeft();
					System.out.println("\n");
					player2.consoleRender();	
				}
				
				if (movement == player2.getRight()) {
					player2.getMoves().moveRight();
					System.out.println("\n");
					player2.consoleRender();	
				}
		
				if (movement == player2.getDown()) {
					player2.getMoves().moveDown();
					System.out.println("\n");
					player2.consoleRender();	
					}
			}
		System.out.println("Perdiste");
		}

	
	public void blockedField(Board board) {
		boolean done = false;
		while (done == false) {
			int a = (int) ((Math.random()*Math.random() * board.getTable().length));
			int b = (int) ((Math.random()*Math.random() * board.getTable().length));
			if (board.getFieldValue(a,b) != 0) {
				board.getTable()[a][b] = new PBlockedField(board.getFieldValue(a, b));
				done = true;
			}
		}
	}
	
	public void revertBlockedField(Board board) {
		for (int fila = 0; fila<=board.getTable().length-1; fila++) {
			for (int columna = 0; columna<=board.getTable().length-1; columna++) {
				if (board.getTable()[fila][columna] instanceof PBlockedField) {
					board.getTable()[fila][columna] = new Field(board.getFieldValue(fila,columna));
				}
			}
		}
	}
	


}
