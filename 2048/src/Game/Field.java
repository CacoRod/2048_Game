package Game;

public class Field
{
	// casillero. Contiene un valor y posiblemente un powerup
	private int value;
	private PowerUp buff;
	private Board board;
	
	
	
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
		
	}
	
	public Field (int value, Board board) 
	{
		setValue(value);
		setBoard(board);
		
	}
	public void sum(Field other)
	{
		if (!(this instanceof PBlockedField) && !(other instanceof PBlockedField)) {
			if (hasPowerUp() && other.value != 0) {
				getGame().powerUpTrigger(getBuff(), getPlayer());
				setBuff(null);
			}
			if (other.hasPowerUp() && value != 0) { 
				getGame().powerUpTrigger(other.getBuff(), getPlayer());
				other.setBuff(null);
			}
			setValue(value += other.value);
			setBuff(other.getBuff());
			other.setValue(0);
			other.setBuff(null);
		}
	}
	
	
	public boolean checkSum(Field other) 
	{
		return (value == other.value && !(this instanceof PBlockedField)); 
	}
	
	public boolean hasPowerUp()
	{
		return (this.getBuff() != null);
	}

}
