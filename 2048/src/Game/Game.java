package Game;

import java.util.Scanner;

//tiene dos jugador. Tiene que poder iniciar una nueva partida, restartearla, cargar y guardar
public class Game
{

	private Player player1;
	private Player player2;

	public Game() 
	{
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
				System.out.println("FIELD [" + a + "][" + b +"] from " + player.getName() + " has been BLOCKED");
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
					System.out.println("FIELD [" + fila + "][" + columna + "] from " + player.getName() + " has been UNBLOCKED");
				}
			}
		}
	}
	
	public void moveDebuffUp(Board board, Player player) 
	{
		boolean done = false;
		while (done == false) {
			int a = (int) ((Math.random()*Math.random() * board.getTable().length));
			for (int contador=1; contador<=4; contador ++ ) {
				for (int fila=0; fila< board.getTable().length - 1;fila++) {
					if (board.getFieldValue(fila+1,a) == 0) {
						board.getTable()[fila+1][a].sum(board.getTable()[fila][a]);	
					}
				}
			}
		System.out.println("ROW [" + a +"] from " + player.getName() + " has MOVED in the opossite direction");
		done = true;
		}
	}

	public void divideField(Board board, Player player) 
	{
		boolean done = false;
		while (done == false) {
			int a = (int) ((Math.random()*Math.random() * board.getTable().length));
			int b = (int) ((Math.random()*Math.random() * board.getTable().length));
			if (board.getFieldValue(a,b) != 0 && board.getFieldValue(a,b) != 2 ) {
				board.getTable()[a][b].setValue(board.getFieldValue(a,b)/2);;
				System.out.println("FIELD [" + a + "][" + b +"] from " + player.getName() + " has been DIVIDED");
				done = true;
			}
		}
	}
	
	public void removeField(Board board, Player player) 
	{
		boolean done = false;
		while (done == false) {
			int a = (int) ((Math.random()*Math.random() * board.getTable().length));
			int b = (int) ((Math.random()*Math.random() * board.getTable().length));
			if (board.getFieldValue(a,b) != 0 && !(board.getTable()[a][b] instanceof PBlockedField)) {
				board.getTable()[a][b].setValue(0);;
				System.out.println("FIELD [" + a + "][" + b +"] from " + player.getName() + " has been REMOVED");
				done = true;
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
		if (debuff instanceof PowerUpDivide) divideField(player.getMoves(), player);
		if (debuff instanceof PowerUpRemove) removeField(player.getMoves(), player);
		if (debuff instanceof PowerUpMove) player.setMoveAffected(true);
	}

	public void help() 
	{
		String help = "INSTRUCTIONS: \n"
				+ "\nHow to Play:\n"
				+ "\nTwo players, each with its board(4x4) must move the fields inside it. Each time a Player moves,\n"
				+ "a new field spawns in the board.Should two fields of the same value clash, they'll add up into\n"
				+ "a single field of greater value. Only fields of same value can add up. The game is done once a player\n"
				+ "can't make any more moves, signaling the other player's victory\n"
				+ "8 - a field is represented by it's current value\n"
				+ "B(4) - this is a blocked field: It can't be moved or summed no matter it's value:\n"
				+"\nPowerups:\n"
				+ "\nIf a field has a powerup it will show up as a letter next to the number. "
				+ "If a player sums a field containing one, it will apply a debuff on the enemy player\n"
				+ "R - remove a random field from the enemy player\n"
				+ "B - block a random field from the enemy player, preventing it from being summed or moved\n"
				+ "D - divide a random field from the enemy player, cutting it's value in half\n"
				+ "M - should the enemy player move, a random file/row will move in the oposite direction\n"
				+ "\nControls:\n"
				+ "\nw - move up\n"
				+ "s - move down\n"
				+ "a - move left\n"
				+ "d - move right\n"
				+ "h - press h at any time to bring up this menu\n"
				+ "n - start a new game\n";
				
		System.out.println(help);
	}

	public void menu()
	{
		String menu = "/////////////////////////////////////////////////////////////////////////////////////////////\n"
					+ "///////////           //////////            ///////  ///////  //////////            /////////\n"
				    + "///////////  ////////  /////////  ////////  ///////  ///////  //////////  ////////  /////////\n"
				    + "/////////////////////  /////////  ////////  ///////  ///////  //////////  ////////  /////////\n"
				    + "///////////////////   //////////  ////////  ///////  ///////  //////////            /////////\n"
				    + "////////////////    ////////////  ////////  ///////           //////////            /////////\n"
				    + "/////////////   ////////////////  ////////  ////////////////  //////////  ////////  /////////\n"
				    + "//////////   ///////////////////  ////////  ////////////////  //////////  ////////  /////////\n"
				    + "/////////               ////////            ////////////////  //////////            /////////\n"
				    + "/////////////////////////////////////////////////////////////////////////////////////////////\n";
		System.out.println(menu);
		help();
		boolean done = false;
		while (!done) {
			Scanner scanner = new Scanner(System.in);
			String scan = scanner.nextLine();
			if (scan.isEmpty()) scan = "fff";
			char movement = scan.charAt(0);
			
			if (movement == 'h') {
				menu();
				done = true;
			}
			if (movement == 'n') {
				gamePlay();
				done = true;
			}
		}
	}
}
