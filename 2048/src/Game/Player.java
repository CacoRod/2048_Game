package Game;

import java.util.Scanner;

public class Player
{
	
	private Board moves;
	private String name;
	private Game game;
	
	
	public Game getGame() 
	{
		return game;
	}

	public void setGame(Game game) 
	{
		this.game = game;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public Board getMoves() 
	{
		return moves;
	}

	public void setMoves(Board moves)
	{
		this.moves = moves;
	}
	
	public Player(String name) 
	{ 
		
		setName(name);
		setMoves(new Board());
		getMoves().setPlayer(this);
	}
	
	public void movement() 
	{
		System.out.println(getName());
		moves.consoleRender();
		
		Scanner scanner = new Scanner(System.in);
		String scan = scanner.nextLine();
		char movement = scan.charAt(0);
				
		if (movement == 'h') {
			game.help();
			movement();	
		}
				
				
		if (movement == 'w') {
			getMoves().moveUp();
			System.out.println("\n");	
		}
				
		if (movement == 'a') {
			getMoves().moveLeft();
			System.out.println("\n");	
		}
				
		if (movement == 'd') {
			getMoves().moveRight();
			System.out.println("\n");
		}
		
		if (movement == 's') {
			getMoves().moveDown();
			System.out.println("\n");	
			}
		game.revertBlockedField(getMoves(), this);
		moves.consoleRender();
		System.out.println("===============================================");
	}

	public void applyPowerUp(PowerUp buff)
	{
		game.powerUpTrigger(buff, this);
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
		}

}
