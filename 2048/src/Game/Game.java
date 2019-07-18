package Game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;


//tiene dos jugador. Tiene que poder iniciar una nueva partida, restartearla, cargar y guardar
public class Game
{

	private Player player1;
	private Player player2;
	int turn = -1;
	int language = 1;
	boolean score_vis;
	String win_mes = " HAS WON THE GAME";
	String inv_mes = "invalid input";
	String goa_mes = "imput a value between 100 and 1000";
	String goa_inv = "value must be between 100 and 1000";
	String tie_mes = "TIE";
	String mode= "GAME MODES: \n"
			+ "1 - first to reach a certain ammount of points points is the winner\n"
			+ "2 - first to run out of moves is the looser";
	
	String help_en = "INSTRUCTIONS: \n"
			+ "\nHow to Play:\n"
			+ "\nTwo players, each with its board(4x4) must move the fields inside it. Each time a Player makes a move,\n"
			+ "a new field spawns in his board.Should two fields of the same value clash, they'll sum up into a single\n"
			+ "field of greater value. Only fields of same value can do this. The game is done once a player can't make\n"
			+ "any more moves, meaning the other player won\n"
			+ "\n"
			+ "\t2\t-\t-\t-\n"
			+ "\t2M\t-\t-\t-\n"
			+ "\t-\tB(4)\t-\t-\n"
			+ "\t64\t64\t-\t16\n"
			+ "\n"
			+ "2 - a field is represented by it's current value\n"
			+ "2M - a letter next means that field contains a power up\n"
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
			+ "q - change language\n"
			+ "n - start a new game\n";
	String help_es = "INSTRUCCIONES: \n"
			+ "\nComo jugar:\n"
			+ "\nDos jugadores, cada uno con su tablero(4x4), deben mover los campos. Cada vez que un jugador hace un movimiento,\n"
			+ "un nuevo campo aparece en su tablero.Si dos campos colicionan, ambos seran combinados en un campo de mayor valor. \n"
			+ "Solo campos del mismo valor pueden hacer eso. El juego termina una ves que un jugador no puede hacer mas \n"
			+ "movimientos, significando su derrota\n"
			+ "\n"
			+ "\t2\t-\t-\t-\n"
			+ "\t2M\t-\t-\t-\n"
			+ "\t-\tB(4)\t-\t-\n"
			+ "\t64\t64\t-\t16\n"
			+ "\n"
			+ "2 - un campo es representado por su valor\n"
			+ "2M - una letra al lado de su valor significa que ese campo tiene un POWER UP\n"
			+ "B(4) - este es un campo bloqueado, no puede ser movido ni sumado:\n"
			+"\nPowerups:\n"
			+ "\nSi un campo contiene una letra, significa que tiene un POWER UP. "
			+ "Si el jugador suma uno, le aplicara un efecto negativo al enemigo\n"
			+ "R - remueve un campo del tablero\n"
			+ "B - bloquea un campo del tablero, impdiendo ser sumado o movido\n"
			+ "D - divide el valor de un campo por la mitad\n"
			+ "M - cuando el jugador enemigo haga un movimiento, uno de sus campos se movera en direccion contraria\n"
			+ "\nControles:\n"
			+ "\nw - mover hacia arriba\n"
			+ "s - mover hacia abajo\n"
			+ "a - mover hacia la izquierda\n"
			+ "d - mover hacia la derecha\n"
			+ "h - presiona en cualquier momento para traer este menu\n"
			+ "q - cambiar de idioma\n"
			+ "n - nuevo juego\n";

	public Game() 
	{
		player1 = new Player(this);
		player1.setName(player1.getName() + " 1");
		player2 = new Player(this);
		player2.setName(player2.getName() + " 2");
		menu();
	}
	
	public void changeLanguage() {
		language = language*-1;
		if (language != 1) {
			player1.setName("Jugador 1");
			player1.getMoves().setLanguage();
			player2.setName("Jugador 2");
			player2.getMoves().setLanguage();
			win_mes = " A GANADO EL JUEGO";
			inv_mes = "comando invalido";
			mode= "MODOS DE JUEGO: \n"
					+ "1 - el primero en juntar cierta cantidad de puntos es el ganador\n"
					+ "2 - el primero en quedarse sin movimientos es el perdedor";
			goa_mes = "ingrese un valor entre 100 y 1000";
			goa_inv = "el valor debe ser entre 100 y 1000";
			tie_mes = "EMPATE";
		}
		else {
			player1.setName("Player 1");
			player1.getMoves().setLanguage();
			player2.setName("Player 2");
			player2.getMoves().setLanguage();
			win_mes = " HAS WON THE GAME";
			inv_mes = "invalid input";
			mode= "GAME MODES: \n"
					+ "1 - first to reach a certain ammount of points is the winner\n"
					+ "2 - first to run out of moves is the looser";
			goa_mes = "imput a value between 100 and 1000";
			goa_inv = "value must be between 100 and 1000";
			tie_mes = "TIE";
		}
	}
	
	public void gamePlay_a() 
	{
		score_vis = false;
		player1 = new Player(this);
		player1.setName(player1.getName() + " 1");
		player2 = new Player(this);
		player2.setName(player2.getName() + " 2");
		while (!player1.getMoves().gameLost() && !player2.getMoves().gameLost()) 
		{
				turn = turn*-1;
				if (turn == 1) player1.movement();
				else player2.movement();
				
			}
		if (player1.getMoves().gameLost()) System.out.println(player2.getName().toUpperCase() + win_mes);
		else System.out.println(player1.getName().toUpperCase() + win_mes);
		}
	
	public void gamePlay_b(int goal) 
	{
		score_vis = true;
		player1 = new Player(this);
		player1.setName(player1.getName() + " 1");
		player2 = new Player(this);
		player2.setName(player2.getName() + " 2");
		while (player1.getScore()<=goal && player2.getScore()<=goal) 
		{
				turn = turn*-1;
				if (turn == 1) player1.movement();
				else player2.movement();
				
			}
		if (turn == -1) player2.movement();
		if (player1.getScore()>=goal && player2.getScore()>=goal ) {
			System.out.println(tie_mes);
		}
		else {
		if (player1.getScore()>=goal) System.out.println(player1.getName().toUpperCase() + win_mes);
		else System.out.println(player2.getName().toUpperCase() + win_mes);
		}
	}
	
	
	
	public void powerUpTrigger(PowerUp buff, Player player) 
	{
		if (player != player1) applyDebuff(player1,buff);
		else applyDebuff(player2,buff);
	}
	
	public void applyDebuff(Player player, PowerUp debuff) 
	{
		if (debuff instanceof PowerUpBlock) player.getMoves().blockField();
		if (debuff instanceof PowerUpDivide) player.getMoves().divideField();
		if (debuff instanceof PowerUpRemove) player.getMoves().removeField();
		if (debuff instanceof PowerUpMove) player.setMoveAffected(true);
	}

	public void help(int lan) 
	{	
		if (lan != 1) System.out.println(help_es);
		else System.out.println(help_en);
	}

	public void menu()
	{
		String menu = "\n\n\n"
				    + "/////////////////////////////////////////////////////////////////////////////////////////////\n"
					+ "///////////           //////////            ///////  ///////  //////////            /////////\n"
				    + "///////////  ////////  /////////  ////////  ///////  ///////  //////////  ////////  /////////\n"
				    + "/////////////////////  /////////  ////////  ///////  ///////  //////////  ////////  /////////\n"
				    + "///////////////////   //////////  ////////  ///////  ///////  //////////            /////////\n"
				    + "////////////////    ////////////  ////////  ///////           //////////            /////////\n"
				    + "/////////////   ////////////////  ////////  ////////////////  //////////  ////////  /////////\n"
				    + "//////////   ///////////////////  ////////  ////////////////  //////////  ////////  /////////\n"
				    + "/////////               ////////            ////////////////  //////////            /////////\n"
				    + "/////////////////////////////////////////////////////////////////////////////////////////////\n"
				    + "                                                                                     ver 1.16";
		System.out.println(menu);
		help(language);;
		boolean done = false;
		boolean done2 = false;
		while (!done) {
			Scanner scanner = new Scanner(System.in);
			String scan = scanner.nextLine().toLowerCase();
			if (scan.isEmpty()) scan = "fff";
			char movement = scan.charAt(0);
			
			if (movement == 'h') {
				menu();
				done = true;
				done2 = true;
			}
			if (movement == 'n') {
				gameMode();
				done = true;
				done2 = true;
			}
			
			if (movement == 'q') {
				changeLanguage();
				menu();
				done = true;
				done2 = true;
			}
			if (!done2) System.out.println(inv_mes);
		}
	}
	
	public void gameMode()
	{
		System.out.println(mode);
		boolean done = false;
		boolean done2 = false;
		while (!done) {
			Scanner scanner = new Scanner(System.in);
			String scan = scanner.nextLine().toLowerCase();
			if (scan.isEmpty()) scan = "fff";
			char movement = scan.charAt(0);
			
			if (movement == '1') {
				setGoal();
				done = true;
				done2 = true;
			}
			if (movement == '2') {
				gamePlay_a();
				done = true;
				done2 = true;
			}
			if (movement == 'q') {
				changeLanguage();
				gameMode();
				done = true;
				done2 = true;
			}
			if (!done2) System.out.println(inv_mes);
		}
	}
	
	public void setGoal()
	{
		System.out.println(goa_mes);
		boolean done = false;
		boolean done2 = false;
		while (!done) {
			Scanner scanner = new Scanner(System.in);
			String scan = scanner.nextLine().toLowerCase();
			char movement = scan.charAt(0);
			int goal = Integer.parseInt(scan);
			if (scan.isEmpty()) scan = "250";
			
			if (movement == 'q') {
				changeLanguage();
				setGoal();
				done = true;
				done2 = true;
			}
			
			if (goal >= 100 && goal <=1000) {
				gamePlay_b(goal);
				done = true;
				done2 = true;
			}

			if (!done2) System.out.println(goa_inv);
		}
	}
	
	public JsonObject stringToJson() {
		
		JsonObject j = null;
		try {
		j = (JsonObject)Jsoner.deserialize(readText());
		} catch(Exception e)  {
			System.out.println("puto " + e.getMessage());
		}
		return j;
		}
	
	public void guardarArchivo() {
		try (PrintWriter out = new PrintWriter("partida.txt")) {
		    out.println(saveGame());
		} catch (FileNotFoundException e) {
			System.out.println("no se pudo guardar el archivo");
		}
	}
	
	public Player getPlayer1() {
		return player1;
	}
	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}
	public Player getPlayer2() {
		return player2;
	}
	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}
	
	
	public String saveGame() {
		JsonObject obj = new JsonObject();
		obj.put("player 1", player1.savePlayer());
		obj.put("player 2", player2.savePlayer());
		return Jsoner.serialize(obj);
	}
	
//	public void loadGame(JsonObject obj) {
//		try {
//			this.setPlayer1(new Player((JsonObject) obj.get("player 1")));
//			this.setPlayer2(new Player((JsonObject) obj.get("player 2")));
//			this.getPlayer1().setGame(this);
//			this.getPlayer2().setGame(this);
//			this.getPlayer1().setName("player 1");
//			this.getPlayer2().setName("player 2");
//		} catch (Exception e) {
//			System.out.println("game roto: " + e.getMessage());
//			e.printStackTrace();
//		}
//	}
	
	public String readText() {
		File file = new File("partida.txt"); 
		  
		  BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			System.out.println("no se encuentra el archivo");
		} 
		  
		  String st = ""; 
		  try {
			while ((st = br.readLine()) != null) 
			    System.out.println(st);
		} catch (IOException e) {
			System.out.println("no se encuentra el archivo");
		}  
		  return st;
	}
	
	
	
	
	
	
	
	
	
}
