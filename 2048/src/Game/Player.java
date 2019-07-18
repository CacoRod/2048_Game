package Game;

import java.util.Scanner;

import com.github.cliftonlabs.json_simple.JsonObject;



public class Player
{
	
	private Board moves;
	private String name;
	private Game game;
	private boolean moveAffected;
	
	public void languageMes() {
		if (getGame().language != 1) System.out.println("lenguage cambiado a ESPAÑOL");
		else System.out.println("language set to ENGLISH");
	}
	                          
	public void invalidMes() {
		if (getGame().language != 1) System.out.println("comando invalido");
		else System.out.println("invalid input");
	}
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
	
	public Player(Game game) 
	{ 
		if (game.language != 1) setName("Jugador");
		else setName("Player");
		setGame(game);
		setMoves(new Board(this));
	}
	
//	public Player(JsonObject object) 
//	{ 
//		setName((String) object.get("nombre"));
//		setMoves(new Board((JsonArray) object.get("board")));
//		getMoves().setPlayer(this);
//	}
//	
	public void movement() 
	{
		System.out.println("\t////////" + getName().toUpperCase() + "\\\\\\\\\\\\\\\\\\");
		moves.consoleRender();
		boolean done = false;
		boolean done2 = false;
		
		while (!done) {
			Scanner scanner = new Scanner(System.in);
			String scan = scanner.nextLine().toLowerCase();
			if (scan.isEmpty()) scan = "fff";
			char movement = scan.charAt(0);
					
			if (movement == 'h') {
				game.help(getGame().language);
				movement();	
				done = true;
				done2 = true;
			}
					
			if ((movement == 'w') && (getMoves().canMoveUp())) {
				getMoves().moveUp();
				System.out.println("\n");
				done = true;
				done2 = true;
				if (isMoveAffected()) moves.moveDebuffUp();
			}
					
			if ((movement == 'a') && (getMoves().canMoveLeft())) {
				getMoves().moveLeft();
				System.out.println("\n");
				done = true;
				done2 = true;
				if (isMoveAffected()) moves.moveDebuffLeft();
			}
					
			if ((movement == 'd') && (getMoves().canMoveRight())) {
				getMoves().moveRight();
				System.out.println("\n");
				done = true;
				done2 = true;
				if (isMoveAffected()) moves.moveDebuffRight();
			}
			
			if ((movement == 's') && (getMoves().canMoveDown())) {
				getMoves().moveDown();
				System.out.println("\n");
				done = true;
				done2 = true;
				if (isMoveAffected()) moves.moveDebuffDown();
				}
			
			if (movement == 'q'){
				getGame().changeLanguage();
				languageMes();
				movement();
				done = true;
				done2 = true;
				}
			
			
			if (!done2) invalidMes();

		}
		moves.revertBlockedField();
		setMoveAffected(false);
		moves.fieldSpawner();
		moves.consoleRender();
		System.out.println(
				  "===============================================\n"
				+ "===============================================\n"
				+ "===============================================\n"
				+ "===============================================\n"
				+ "===============================================");
	}

	
	
	com.github.cliftonlabs.json_simple.JsonObject obj = new com.github.cliftonlabs.json_simple.JsonObject();
	
	public JsonObject savePlayer() {
		obj.put("board", moves.saveBoard());
		obj.put("name", getName());
		return obj;
	}
	
	public JsonObject loadPlayer() {
		obj.get("name");
		return obj;
	}


}
