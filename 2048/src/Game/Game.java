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

	
	public void blockField(Board board, Player player) 
	{
		boolean done = false;
		while (done == false) {
			int a = (int) ((Math.random()*Math.random() * board.getTable().length));
			int b = (int) ((Math.random()*Math.random() * board.getTable().length));
			if (board.getFieldValue(a,b) != 0) {
				board.getTable()[a][b] = new PBlockedField(board.getFieldValue(a, b));
				System.out.println("Field [" + a + "][" + b +"] from " + player.getName() + " has been blocked");
				done = true;
			}
		}
	}
	
	public void revertBlockedField(Board board, Player player)
	{
		for (int fila = 0; fila<=board.getTable().length-1; fila++) {
			for (int columna = 0; columna<=board.getTable().length-1; columna++) {
				if (board.getTable()[fila][columna] instanceof PBlockedField) {
					board.getTable()[fila][columna] = new Field(board.getFieldValue(fila,columna), board);
					System.out.println("Field [" + fila + "][" + columna + "] from " + player.getName() + " has been unblocked");
				}
			}
		}
	}

	public void powerUpTrigger(PowerUp buff, Player player) 
	{
		if (player != player1) applyDebuff(player1,buff);
		else applyDebuff(player2,buff);
	}
	
	public void applyDebuff(Player player, PowerUp debuff) 
	{
		if (debuff instanceof PowerUpBlock) blockField(player.getMoves(), player);
	}

}
