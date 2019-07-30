package Game;

public class PBlockedField extends Field{

	public PBlockedField(int value, PowerUp buff) {
		super(value,null, buff);

	}
	
	public PBlockedField (int value,String buff) 
	{
		super(value,null, buff);
	}
	
	public void sum(Field other) {
		
	}
	
	public String toString() {
		String buff = "null";
		String data = String.valueOf(getValue());
		if (getBuff() != null) buff = getBuff().render();
		
		return "B " + data + " " + buff;
		
	}

}
