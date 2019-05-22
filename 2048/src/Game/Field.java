package Game;

public class Field {
	// casillero. Contiene un valor y posiblemente un powerup
	private int value;
	private PowerUp buff;
	
	
	
	
	
	
	
	
	public void setBuff(PowerUp buff) {
		this.buff = buff;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public PowerUp getBuff() {
		return buff;
	}
	
	
	public Field(int value, PowerUp buff) {
		setValue(value);
		setBuff(buff);
		
	}
	public Field (int value) {
		setValue(value);
		
	}
	
	
	public void sum(Field other) {
		setValue(value += other.value);
		other.setValue(0);
	}
	public boolean checkSum(Field other) {
		return (value == other.value); 
	}
	
}
