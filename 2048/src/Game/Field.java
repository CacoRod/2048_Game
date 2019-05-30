package Game;

public class Field{
	// casillero. Contiene un valor y posiblemente un powerup
	private int value;
	private IPowerUp buff;
	
	
	
	
	
	
	
	
	public void setBuff(IPowerUp buff) {
		this.buff = buff;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public IPowerUp getBuff() {
		return buff;
	}
	
	
	public Field(int value, IPowerUp buff) {
		setValue(value);
		setBuff(buff);
		
	}
	public Field (int value) {
		setValue(value);
		
	}
	
	
	public void sum(Field other) {
		if (!(this instanceof PBlockedField) && !(other instanceof PBlockedField)) {
			setValue(value += other.value);
			other.setValue(0);
		}
	}
	public boolean checkSum(Field other) {
		return (value == other.value); 
	}
	public boolean hasPowerUp() {
		return (this.getBuff() != null);
	}
}
