package Game;



public class Field
{
	// casillero. Contiene un valor y posiblemente un powerup
	private int value;
	private PowerUp buff;
	private Board board;
	private boolean sum;
	
	
	private Player getPlayer() 
	{
		return board.getPlayer();
	}
	private Game getGame() 
	{
		return board.getPlayer().getGame();
	}
	public Board getBoard()
	{
		return board;
	}

	public void setBoard(Board board) 
	{
		this.board = board;
	}

	public void setBuff(PowerUp buff) 
	{
		this.buff = buff;
	}
	
	public int getValue() 
	{
		return value;
	}
	
	public void setValue(int value) 
	{
		this.value = value;
	}
	
	public PowerUp getBuff()
	{
		return buff;
	}
	
	
	public Field(int value,Board board, PowerUp buff) 
	{
		setValue(value);
		setBuff(buff);
		setBoard(board);
		setSum(true);
		
	}
	
	public Field (int value, Board board) 
	{
		setValue(value);
		setBoard(board);
		setSum(true);
		
	}
	
	public Field (int value, Board board,String buff) 
	{
		setValue(value);
		setBoard(board);
		
		switch (buff) {
		
		  case "null":
			  	setBuff(null);
			    break;
		  case "R":
			  	setBuff(new PowerUpRemove());
			    break;
		  case "D":
			  setBuff(new PowerUpDivide());
			  	break;
		  case "M":
			  setBuff(new PowerUpMove());
			  	break;
		  case "B":
			  setBuff(new PowerUpBlock());
			  break;
	
		}
	}


	public void sum(Field other)
	{
		if (!(other instanceof PBlockedField) && (((this.canSum() && other.canSum()) && (value == other.value)) || (!other.hasValue() || !hasValue()))) {
			
			if (hasPowerUp() && other.hasValue()) {
				getGame().powerUpTrigger(getBuff(), getPlayer());
				setBuff(null);
			}
			if (other.hasPowerUp() && hasValue()) { 
				getGame().powerUpTrigger(other.getBuff(), getPlayer());
				other.setBuff(null);
			}
			if (other.hasValue() && hasValue()) {
				setSum(false);
				getPlayer().sumScore(value + other.value);
			}
			doSum(other);
		}
	}
	
	public void doSum(Field other) 
	{
		if (!hasValue()) setBuff(other.getBuff());
		setValue(value += other.value);
		other.setBuff(null);
		other.setValue(0);
	}
	
	
	
	public boolean hasValue() {
		return (value != 0);
	}
	
	public boolean checkSum(Field other) 
	{
		return ((value == other.value) && other.hasValue() && !(this instanceof PBlockedField)); 
	}
	
	public boolean checkSum_b(Field other) 
	{
		return (((value == other.value) || !other.hasValue()) && !(this instanceof PBlockedField) && !(other instanceof PBlockedField)); 
	}
	
	public boolean hasPowerUp()
	{
		return (this.getBuff() != null);
	}
	
	public boolean canSum() {
		return sum;
	}
	public void setSum(boolean sum) {
		this.sum = sum;
	}
	
	public String toString() {
		String buff = "null";
		String data = String.valueOf(value);
		if (getBuff() != null) buff = getBuff().render();
		
		return data + " " + buff;
		
	}
}
