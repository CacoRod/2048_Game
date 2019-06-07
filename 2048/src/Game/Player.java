package Game;

import java.util.Scanner;

public class Player
{
	
	private Board moves;
	private String name;
	private Game game;
	private boolean moveAffected;
	
	
	
	public boolean isMoveAffected(){
		return moveAffected;
	}
	
	public void setMoveAffected(boolean debuff) 
	{
		this.moveAffected = debuff;
	}
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
		System.out.println(getName().toUpperCase());
		moves.consoleRender();
		boolean done = false;
		
		while (!done) {
			Scanner scanner = new Scanner(System.in);
			String scan = scanner.nextLine();
			if (scan.isEmpty()) scan = "fff";
			char movement = scan.charAt(0);
					
			if (movement == 'h') {
				game.help();
				movement();	
				done = true;
			}
					
			if (movement == 'w') {
				getMoves().moveUp();
				System.out.println("\n");
				done = true;
				if (isMoveAffected()) game.moveDebuffUp(getMoves(), this);
			}
					
			if (movement == 'a') {
				getMoves().moveLeft();
				System.out.println("\n");
				done = true;
				if (isMoveAffected()) game.moveDebuffLeft(getMoves(), this);
			}
					
			if (movement == 'd') {
				getMoves().moveRight();
				System.out.println("\n");
				done = true;
				if (isMoveAffected()) game.moveDebuffRight(getMoves(), this);
			}
			
			if (movement == 's') {
				getMoves().moveDown();
				System.out.println("\n");
				done = true;
				if (isMoveAffected()) game.moveDebuffDown(getMoves(), this);
				}
		}
		game.revertBlockedField(getMoves(), this);
		setMoveAffected(false);
		moves.fieldSpawner();
		moves.consoleRender();
		System.out.println("===============================================\n"
				+ "===============================================\n"
				+ "===============================================\n"
				+ "===============================================\n"
				+ "===============================================");
	}

	public void applyPowerUp(PowerUp buff)
	{
		game.powerUpTrigger(buff, this);
	}
	
}
