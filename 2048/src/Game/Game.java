package Game;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;
import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;




//tiene dos jugador. Tiene que poder iniciar una nueva partida, restartearla, cargar y guardar
public class Game

{
	
	private Player player1;
	private Player player2;
	private boolean forfeit;
	int turn;
	int language = -1;
	int score = 0;
	boolean score_vis;
	String win_mes;
	String inv_mes;
	String goa_mes;
	String goa_inv;
	String tie_mes;
	String lb_mes;
	String mode;
	String sav_mes;
	String sav_err;
	String sav_suc;
	String loa_mes;
	String loa_err;
	String loa_suc;
	String gam_mod_a;
	String gam_mod_b;
	String sco_thr;

	
	String help_en = "INSTRUCTIONS: \n"
			+ "\nHow to Play:\n"
			+ "\nTwo players, each with its board(4x4) must move the fields inside it. Each time a Player makes a move,\n"
			+ "a new field spawns in his board.Should two fields of the same value clash, they'll sum up into a single\n"
			+ "field of greater value. Only fields of same value can do this.\n"
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
			+"\nGame Modes:\n"
			+ "Move game: the game ends when one of the players can't make any more moves. Meaning the other player won\n"
			+ "Score game: the player with the highest score wins. After passing a certain score (determined by the\n"
			+ "players beforehand) you won't be allowed to make any more moves. The other player then enters LAST\n"
			+ "BREATH, where he has three moves to try and beat your score\n"	
			+ "\nControls:\n"
			+ "\nw - move up\n"
			+ "s - move down\n"
			+ "a - move left\n"
			+ "d - move right\n"
			+ "h - press h at any time to bring up this menu\n"
			+ "q - change language\n"
			+ "x - save game\n"
			+ "l - load game\n"
			+ "n - start a new game\n";
	String help_es = "INSTRUCCIONES: \n"
			+ "\nComo jugar:\n"
			+ "\nDos jugadores, cada uno con su tablero(4x4), deben mover los campos. Cada vez que un jugador hace un movimiento,\n"
			+ "un nuevo campo aparece en su tablero.Si dos campos colicionan, ambos seran combinados en un campo de mayor valor. \n"
			+ "Solo campos del mismo valor pueden hacer eso.\n"
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
			+"\nGame Modes:\n"
			+ "Movimiento: el juego termina cuando un jugador no puede hacer mas movimientos. Significando que el otro gano\n"
			+ "Puntaje: el jugador con el mayor puntaje gana. Al sobrepasar cierto puntaje (determinado por los jugadores\n"
			+ "antes de empezar) no podras hacer mas movimientos. El otro jugador entonces entra en ULTIMO ALIENTO,\n"
			+ "donde tiene solo tres movimientos para intentar vencer tu puntaje\n"
			+ "\nControles:\n"
			+ "\nw - mover hacia arriba\n"
			+ "s - mover hacia abajo\n"
			+ "a - mover hacia la izquierda\n"
			+ "d - mover hacia la derecha\n"
			+ "h - presiona en cualquier momento para traer este menu\n"
			+ "q - cambiar de idioma\n"
			+ "x - guardar juego\n"
			+ "l - cargar juego\n"
			+ "n - nuevo juego\n";
	
	private Scanner scanner;

	public Game() 
	{
		player1 = new Player(this);
		player1.setName(player1.getName() + " 1");
		player2 = new Player(this);
		player2.setName(player2.getName() + " 2");
		changeLanguage();
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
					+ "1 - el puntaje mas alto es el ganador\n"
					+ "2 - el primero en quedarse sin movimientos es el perdedor";
			goa_mes = "ingrese un valor entre 100 y 1000";
			goa_inv = "el valor debe ser entre 100 y 1000";
			tie_mes = "EMPATE";
			lb_mes = "ULTIMO ALIENTO!";
			sav_mes = "guardar Partida con el nombre:";
			sav_err = "no se admiten espacios";
			sav_suc = "partida guardada!";
			loa_mes = "nombre del guardado:";
			loa_err = "no hay tal guardado";
			loa_suc = "partida cargada!";
			gam_mod_a ="PARTIDA POR MOVIMIENTO";
			gam_mod_b ="PARTIDA POR PUNTAJE";
			sco_thr = "PUNTAJE DE BARRERA: ";
		}
		else {
			player1.setName("Player 1");
			player1.getMoves().setLanguage();
			player2.setName("Player 2");
			player2.getMoves().setLanguage();
			win_mes = " HAS WON THE GAME";
			inv_mes = "invalid input";
			mode= "GAME MODES: \n"
					+ "1 - highest score is the winner\n"
					+ "2 - first to run out of moves is the looser";
			goa_mes = "imput a value between 100 and 1000";
			goa_inv = "value must be between 100 and 1000";
			tie_mes = "TIE";
			lb_mes = "LAST BREATH!";
			sav_mes = "save game with name:";
			sav_err = "no spaces allowed";
			sav_suc = "game saved!";
			loa_mes = "name of the save:";
			loa_err = "no such save";
			loa_suc = "game loaded!";
			gam_mod_a ="MOVE GAME";
			gam_mod_b ="SCORE GAME";
			sco_thr = "SCORE THRESHHOLD: ";
		}
	}
	
	public void gamePlay_a() 
	{
		score_vis = false;
		score = 0;
		while ((!player1.getMoves().gameLost() && !player2.getMoves().gameLost()) && !isForfeit()) 
		{
				turn = turn*-1;
				if (turn == 1) player1.movement();
				else player2.movement();
				
			}
		if (!isForfeit()) {
			if (player1.getMoves().gameLost()) System.out.println(player2.getName().toUpperCase() + win_mes);
			else System.out.println(player1.getName().toUpperCase() + win_mes);
		}
		else gameMode();
	}
	
	public void gamePlay_b(int goal) 
	{
		System.out.println(sco_thr + goal);
		score_vis = true;
		score = goal;
		while ((player1.getScore()<goal && player2.getScore()<goal) && !isForfeit()) 
		{
				turn = turn*-1;
				if (turn == 1) player1.movement();
				else player2.movement();
				
			}
		if (!isForfeit()) {
			System.out.println(lb_mes);
			if (player1.getScore()>= goal) lastBreath(player2);
			else lastBreath(player1);
			player1.scoreMes();
			player2.scoreMes();
			if (player1.getScore() == player2.getScore()) System.out.println(tie_mes);
			else {
				if (player1.getScore()>=player2.getScore()) System.out.println(player1.getName().toUpperCase() + win_mes);
				else System.out.println(player2.getName().toUpperCase() + win_mes);
			}
		}
		else gameMode();
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
				    + "                                                                                     ver 1.18";
		
		
		System.out.println(menu);
		help(language);;
		boolean done = false;
		boolean done2 = false;
		while (!done) {
			scanner = new Scanner(System.in);
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
			
			if (movement == 'l') {
				loadGame();
				done2 = true;
			}
			if (!done2) System.out.println(inv_mes);
		}
	}
	
	public void gameMode()
	{
		player1.reset();
		player2.reset();
		setForfeit(false);
		turn = -1;
		System.out.println(mode);
		boolean done = false;
		boolean done2 = false;
		while (!done) {
			scanner = new Scanner(System.in);
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
			scanner = new Scanner(System.in);
			String scan = scanner.nextLine().toLowerCase();
			if (scan.isEmpty()) scan = "100";
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

	public void lastBreath(Player player) {
		for (int a = 0; a<3 ; a++) {
			player.movement();
		}
		
	}

	public boolean isForfeit() {
		return forfeit;
	}

	public void setForfeit(boolean forfeit) {
		this.forfeit = forfeit;
	}
	
	
	
	public void saveGame()
	
	{
		System.out.println(sav_mes);
		scanner = new Scanner(System.in);
		String scan = scanner.nextLine().toLowerCase();
		
		JsonObject save = new JsonObject();


		JsonArray play1 = new JsonArray();
		for (int a = 0 ; a<4 ; a ++) {
			for (int b = 0; b<4 ; b ++) {
				play1.add(player1.getMoves().getTable()[a][b].toString());
			}
		}
		
		JsonArray play2 = new JsonArray();
		for (int a = 0 ; a<4 ; a ++) {
			for (int b = 0; b<4 ; b ++) {
				play2.add(player2.getMoves().getTable()[a][b].toString());
			}
		}
		
		save.put("player 1", play1);
		save.put("player 2", play2);
		save.put("p1_score", player1.getScore());
		save.put("p2_score", player2.getScore());
		save.put("turn", turn);
		save.put("goal", score);
		
		
		

			
		try {
			FileWriter file = new FileWriter(scan +".txt");
			file.write(save.toJson());
			file.flush();
			file.close();
		} catch (IOException e) {
			System.out.println(sav_err);
		}

			System.out.println(sav_suc);
		
	}
	
	public void loadGame() 
	{
	boolean err = false;
	System.out.println(loa_mes);
	scanner = new Scanner(System.in);
	String scan = scanner.nextLine().toLowerCase();
	
	{
    try {
    	Object list = Jsoner.deserialize(new FileReader(scan + ".txt"));
    	JsonObject obt = (JsonObject) list;
    	player1.setScore(((BigDecimal) obt.get("p1_score")).intValue());
    	player2.setScore(((BigDecimal) obt.get("p2_score")).intValue());
    	turn = ((BigDecimal) obt.get("turn")).intValue()*-1;
    	score = ((BigDecimal) obt.get("goal")).intValue();
    	JsonArray play1_board = (JsonArray) obt.get("player 1");
    	JsonArray play2_board = (JsonArray) obt.get("player 2");
        int pos = -1;
        player1.reset();
        player2.reset();
        
        for (int a = 0; a <4 ; a++) {
        	for (int b = 0; b<4 ; b++) {
        		pos +=1;
        		
        		String full_1 = play1_board.getString(pos);
        		String[] split_1 = full_1.split(" ");
        		if (split_1[0].equals("B")) player1.getMoves().getTable()[a][b] = new PBlockedField(Integer.parseInt(split_1[1]), split_1[2]);
        			else player1.getMoves().getTable()[a][b] = new Field(Integer.parseInt(split_1[0]),player1.getMoves(), split_1[1]);
        		
        		String full_2 = play2_board.getString(pos);
        		String[] split_2 = full_2.split(" ");
        		if (split_2[0].equals("B")) player2.getMoves().getTable()[a][b] = new PBlockedField(Integer.parseInt(split_2[1]), split_2[2]);
        			else player2.getMoves().getTable()[a][b] = new Field(Integer.parseInt(split_2[0]),player2.getMoves(), split_2[1]);
        		} 	
        	}    
        }

	     catch (Exception e) {
	        System.out.println(loa_err);
	        err = true;
    }
    
    if (!err) {
	    if (score != 0) {
	    	System.out.println(loa_suc);
	    	System.out.println(gam_mod_b);
	    	System.out.println(player1.getName() +": " + player1.getScore());
	    	System.out.println(player2.getName() +": " + player2.getScore());
	    	gamePlay_b(score);
	
	    }
	    else {
	    	System.out.println(loa_suc);
	    	System.out.println(gam_mod_a);
	    	gamePlay_a();}
    }
}

}
}
