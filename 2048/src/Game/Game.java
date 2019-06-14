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

	public Game() 
	{
		player1 = new Player("player 1");
		player1.setGame(this);
		player2 = new Player("player 2");
		player2.setGame(this);

	}
	public void gamePlay() 
	{
		
		while (!player1.getMoves().gameLost() || !player2.getMoves().gameLost()) 
		{
				turn = turn*-1;
				if (turn == 1) player1.movement();
				else player2.movement();
				
			}
		System.out.println("Perdiste");
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

	public void help() 
	{
		String help = "INSTRUCTIONS: \n"
				+ "\nHow to Play:\n"
				+ "\nTwo players, each with its board(4x4) must move the fields inside it. Each time a Player makes a move,\n"
				+ "a new field spawns in his board.Should two fields of the same value clash, they'll add up into a single\n"
				+ "field of greater value. Only fields of same value can add up. The game is done once a player can't make\n"
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
				+ "n - start a new game\n"
				+ "l - load game saved\n";
				
		System.out.println(help);
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
				    + "/////////////////////////////////////////////////////////////////////////////////////////////\n";
		System.out.println(menu);
		help();
		boolean done = false;
		while (!done) {
			Scanner scanner = new Scanner(System.in);
			String scan = scanner.nextLine().toLowerCase();
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
			if (movement == 'l') {
				loadGame(stringToJson());	
				gamePlay();
			}
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
	
	public void loadGame(JsonObject obj) {
		try {
			this.setPlayer1(new Player((JsonObject) obj.get("player 1")));
			this.setPlayer2(new Player((JsonObject) obj.get("player 2")));
			this.getPlayer1().setGame(this);
			this.getPlayer2().setGame(this);
			this.getPlayer1().setName("player 1");
			this.getPlayer2().setName("player 2");
		} catch (Exception e) {
			System.out.println("game roto: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
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
