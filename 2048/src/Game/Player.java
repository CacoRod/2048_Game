package Game;

import java.util.Scanner;

public class Player {
	
	//registro de jugador. Su puntaje, su juego y sus controles
	
	private Board moves;
	private String name;
	private char up;
	private char down;
	private char left;
	private char right;
	
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Board getMoves() {
		return moves;
	}
	
	public char getUp() {
		return up;
	}

	public char getDown() {
		return down;
	}

	public char getLeft() {
		return left;
	}

	public char getRight() {
		return right;
	}

	public void setUp(char up) {
		this.up = up;
	}
	public void setDown(char down) {
		this.down = down;
	}
	public void setLeft(char left) {
		this.left = left;
	}
	public void setRight(char right) {
		this.right = right;
	}
	public void setMoves(Board moves) {
		this.moves = moves;
	}
	public Player(String name,char up, char down, char left, char right) { 
		
		setName(name);
		setUp(up);
		setDown(down);
		setLeft(left);
		setRight(right);
		setMoves(new Board());
		consoleRender();
	}
	public void consoleRender() {
		System.out.println(getName());
		for (int fila = 0; fila<=moves.getTable().length-1; fila++) {
			String rend = "";
			for (int columna = 0; columna<=moves.getTable().length-1; columna++) {
				rend += (moves.getFieldValue(fila, columna) + "\t");
			}
			System.out.println(rend);
			
			}
		System.out.println("\n" + moves.getScore());
		}

}
