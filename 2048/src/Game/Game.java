package Game;

import java.util.Scanner;

//tiene dos jugador. Tiene que poder iniciar una nueva partida, restartearla, cargar y guardar
public class Game {
	
	private Player player1;
	private Player player2;
	
	
	public Game() {
		player1 = new Player("player 1");
		player1.setGame(this);
		player2 = new Player("player 2");
		player2.setGame(this);

	}
	public void gamePlay() 
	{
		int turn = -1;
		
		while (!player1.getMoves().gameLost() || !player2.getMoves().gameLost()) 
		{
				turn = turn*-1;
				if (turn == 1) player1.movement();
				else player2.movement();
				
			}
		System.out.println("Perdiste");
		}

	
	public void blockedField(Board board) 
	{
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
	
	public void revertBlockedField(Board board)
	{
		for (int fila = 0; fila<=board.getTable().length-1; fila++) {
			for (int columna = 0; columna<=board.getTable().length-1; columna++) {
				if (board.getTable()[fila][columna] instanceof PBlockedField) {
					board.getTable()[fila][columna] = new Field(board.getFieldValue(fila,columna), board);
				}
			}
		}
	}

	public void powerUpTrigger(PowerUp buff, Player player) 
	{
		if (player != player1) applyDebuff(player2);
		else applyDebuff(player1);
	}
	
	public void applyDebuff(Player player) 
	{
		
	}

}
