package Game;

import java.util.Scanner;

public class Player {
	
	//registro de jugador. Su puntaje, su juego y sus controles
	
	private Board moves;
	

	public void setMoves(Board moves) {
		this.moves = moves;
	}
	public Player() { 
		
		setMoves(new Board());
		moves.consoleRender();
		
//		int key = 1;
//		//int key = e.getKeyCode();
//
//		    if (key == KeyEvent.VK_L) {
//		        moves.moveLeft();
//				moves.consoleRender();
//		    }
//
//		    if (key == KeyEvent.VK_D) {
//		    	moves.moveRight();
//				moves.consoleRender();
//		    }
//
//		    if (key == KeyEvent.VK_W) {
//		    	moves.moveUp();
//				moves.consoleRender();
//		    }
//
//		    if (key == KeyEvent.VK_S) {
//		    	moves.moveDown();
//				moves.consoleRender();
//		    }
			
	}
	public void movement() {
		
		
		while (!moves.gameLost()) {
	    
				Scanner scanner = new Scanner(System.in);
				String movement = scanner.nextLine();
				
				
				if (movement.equals("w")) {
					moves.moveUp();
					System.out.println("\n");
					moves.consoleRender();	
				}
				
				if (movement.equals("a")) {
					moves.moveLeft();
					System.out.println("\n");
					moves.consoleRender();	
				}
				
				if (movement.equals("d")) {
					moves.moveRight();
					System.out.println("\n");
					moves.consoleRender();	
				}
		
				if (movement.equals("s")) {
					moves.moveDown();
					System.out.println("\n");
					moves.consoleRender();	
					}
			}
		System.out.println("Perdiste");
		}
}
