package Game;

import java.util.Scanner;




public class Player
{
	
	private Board moves;
	private String name;
	private Game game;
	private boolean moveAffected;
	private int score;
	private Scanner scanner;
	
	public void languageMes() {
		if (getGame().language != 1) System.out.println("lenguage cambiado a ESPAÑOL");
		else System.out.println("language set to ENGLISH");
	}
	
	public void scoreMes() {
		String mes;
		if (getGame().language != 1) mes = "\tPUNTAJE:";
		else mes = "\tSCORE:";
		
		System.out.println(mes + score);
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
		setScore(0);
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
		if (getGame().score_vis) scoreMes();
		boolean done = false;
		boolean done2 = false;
		
		while (!done) {
			scanner = new Scanner(System.in);
			String scan = scanner.nextLine().toLowerCase();
			if (scan.isEmpty()) scan = "fff";
			char movement = scan.charAt(0);
					
			if (movement == 'h') {
				game.help(getGame().language);
				movement();	
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
			
			if (movement == 'n'){
				done = true;
				done2 = true;
				getGame().setForfeit(true);
				}
			
			if (movement == 'x'){
				getGame().saveGame();
				done2 = true;
				}
			
			if (movement == 'l'){
				getGame().loadGame();
				getMoves().consoleRender();
				done2 = true;
				done = true;
				getGame().setForfeit(true);
				}
			
			if (!done2) invalidMes();

		}
		if (!getGame().isForfeit()) {
			moves.revertBlockedField();
			setMoveAffected(false);
			moves.fieldSpawner();
			System.out.println("\t////////" + getName().toUpperCase() + "\\\\\\\\\\\\\\\\\\");
			moves.consoleRender();
			if (getGame().score_vis) scoreMes();
		}
		System.out.println(
				  "===============================================\n"
				+ "===============================================\n"
				+ "===============================================\n"
				+ "===============================================\n"
				+ "===============================================");
	}

	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public void sumScore(int value) {
		score += value;
	}
	
	public void reset() {
		setMoveAffected(false);
		setScore(0);
		setMoves(new Board(this));
	}
	


}
