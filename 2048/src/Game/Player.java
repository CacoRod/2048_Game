package Game;

import java.awt.event.KeyEvent;
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
			
	}
//	public void movementt(KeyEvent e) {
//		
//		int keyCode = e.getKeyCode();
//		
//		if (keyCode == KeyEvent.VK_W) {
//			moves.moveUp();
//			System.out.println("\n");
//			moves.consoleRender();	
//		}
//	}
	
	public void movement() {
		
		
		while (!moves.gameLost()) {
	    
				Scanner scanner = new Scanner(System.in);
				String movement = scanner.nextLine();
				
				switch (movement) {
				
				case "w":
					if (!moves.checkSumVerticalMoves()) {
					moves.moveUp();
					System.out.println("\n");
					}
					moves.consoleRender();
					break;

				case "a":
					if (!moves.checkSumHorizontalMoves()) {
					moves.moveLeft();
					System.out.println("\n");
					}
					moves.consoleRender();
					break;
					
				case "d":
					if (!moves.checkSumAntiHorizontalMoves()) {
					moves.moveRight();
					System.out.println("\n");
<<<<<<< HEAD
					}
					moves.consoleRender();
					break;

				case "s":
					if (!moves.checkSumAntiVerticalMoves()) {
=======
					moves.consoleRender();	
				}
		
				if (movement.equals("s")) {
>>>>>>> origin
					moves.moveDown();
					System.out.println("\n");
					}
					moves.consoleRender();
					break;
					
				default:
					break;
				}
				
//				
//				if (movement.equals("w")) {
//					moves.moveUp();
//					System.out.println("\n");
//					moves.consoleRender();	
//				}
//				
//				if (movement.equals("a")) {
//					moves.moveLeft();
//					System.out.println("\n");
//					moves.consoleRender();	
//				}
//				
//				if (movement.equals("d")) {
//					moves.moveRight();
//					System.out.println("\n");
//					moves.consoleRender();	
//				}
//				
//				if (movement.equals("s")) {
//					moves.moveDown();
//					System.out.println("\n");
//					moves.consoleRender();	
//					}
			}
		System.out.println("Perdiste");
		}
}
