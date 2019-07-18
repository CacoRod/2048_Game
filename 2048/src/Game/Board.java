package Game;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;


public class Board{
	
	public void setTable(Field[][] table) {
		this.table = table;
	}

	private Field[][] table = { {new Field(0,this),new Field(0,this),new Field(0,this),new Field(0,this)},
								{new Field(0,this),new Field(0,this),new Field(0,this),new Field(0,this)},
								{new Field(0,this),new Field(0,this),new Field(0,this),new Field(0,this)},
								{new Field(0,this),new Field(0,this),new Field(0,this),new Field(0,this)}
							};

	private Player player;
	private String field_mes;
	private String from_mes;
	private String move_mes;
	private String row_mes;
	private String col_mes;
	private String bl_mes;
	private String unbl_mes;
	private String spawn_mes;
	private String spawn_fol;
	private String div_mes;
	private String rem_mes;
	private String div_fol;

	
	public Board(Player player) 
	{
		setPlayer(player);
		setLanguage();
		fieldSpawnerFirstRound();
		fieldSpawnerFirstRound();
	}
	
	public Board(JsonArray jsonArray) 
	{
		loadBoard(jsonArray);
	}


	public Field[][] getTable() 
	{
		return table;
	}

	public int getFieldValue(int a, int b) 
	{
		return table[a][b].getValue();
	}


	public Player getPlayer()
	{
		return player;
	}


	public void setPlayer(Player player) 
	{
		this.player = player;
	}

	public void setLanguage()
	{
		field_mes = "FIELD [";
		from_mes = "] from ";
		move_mes = " has MOVED in the opossite direction\n";
		row_mes = "ROW [";
		col_mes = "COLUMN [";
		bl_mes =" has been BLOCKED\n";
		unbl_mes = " has been UNBLOCKED\n";
		spawn_mes = "a FIELD has SPAWNED at [";
		spawn_fol = "] with a POWER UP!\n";
		div_mes = " has been DIVIDED\n";
		rem_mes = " has been REMOVED \n";
		div_fol = "...since the value was 2," + rem_mes;
		
		if (getPlayer().getGame().language != 1) {
		field_mes = "CAMPO [";
		from_mes = "] de ";
		move_mes = " se a MOVIDO en la direccion opuesta\n";
		row_mes = "FILA [";
		col_mes = "COLUMNA [";
		bl_mes =" fue BLOQUEADO\n";
		unbl_mes = "fue DESBLOQUEADO\n";
		spawn_mes = "un CAMPO ha APARECIDO [";
		spawn_fol = "] con un POWER UP!\n";
		div_mes = " fue DIVIDIDO\n";
		rem_mes = " fue REMOVIDO \n";
		div_fol = "...como su valor era 2," + rem_mes;
		}

	}
	
	public void sumLeft() 
	{
		for (int fila=0; fila<=table.length - 1; fila++) {
			for (int columna=0; columna<=table[fila].length - 2;columna++) {
				for (int siguiente=columna+1;siguiente<=table.length-1;siguiente++) {
					if (!table[fila][siguiente].checkSum(table[fila][columna]) && table[fila][siguiente].hasValue()) break;
					if (table[fila][siguiente].checkSum(table[fila][columna])) {
						table[fila][columna].sum(table[fila][siguiente]);
						break;
					}
				}
			}
		}
	}
	

	public void moveLeft() 
	{
		sumLeft();
		for (int fila=0; fila<=table.length - 1; fila++) {
			for (int contador=1; contador<=4; contador ++ ) {
				for (int columna=table[fila].length-1; columna>0;columna--) {
					if (!table[fila][columna-1].hasValue()) {
						table[fila][columna-1].sum(table[fila][columna]);
					}
				}
			}
		}
	}
	
	
	public void sumRight() 
	{
		for (int fila=0; fila<=table.length-1; fila++) {
			for (int columna=3; columna>table.length-table.length;columna--) {
				for (int siguiente=columna-1;siguiente>=table.length-table.length;siguiente--) {
					if (!table[fila][siguiente].checkSum(table[fila][columna]) && table[fila][siguiente].hasValue()) break;
					if (table[fila][siguiente].checkSum(table[fila][columna])) {
						table[fila][columna].sum(table[fila][siguiente]);
						break;
					}
				}
			}
		}
	}


	public void moveRight() 
	{
		sumRight();
		for (int fila=0; fila<=table.length-1; fila++) {
			for (int contador=1; contador<=table.length; contador++ ) {
				for (int columna=0; columna<table.length-1;columna++) {
					if (!table[fila][columna+1].hasValue()) {
						table[fila][columna+1].sum(table[fila][columna]);
					}
				}
			}
		}
	}
	
	
	public void sumDown() 
	{
		for (int columna=0; columna<=table.length -1; columna++) {
			for (int fila=3; fila>table.length - table.length;fila--) {
				for (int siguiente=fila-1;siguiente>=table.length-table.length;siguiente--) {
					if (!table[siguiente][columna].checkSum(table[fila][columna]) && table[siguiente][columna].hasValue()) break;
					if (table[siguiente][columna].checkSum(table[fila][columna])) {
						table[fila][columna].sum(table[siguiente][columna]);
						break;
					}
				}
			}
		}
	}
	
	
	public void moveDown() 
	{
		sumDown();
		for (int columna=0; columna<=table.length -1; columna++) {
			for (int contador=1; contador<=table.length; contador++ ) {
				for (int fila=0; fila<table.length - 1;fila++) {
					if (!table[fila+1][columna].hasValue()) {
						table[fila+1][columna].sum(table[fila][columna]);	
					}
				}
			}
		}
	}
	
	
	public void sumUp() 
	{
		for (int columna=0; columna<=table.length-1; columna++) {
			for (int fila=0; fila<=table.length-2;fila++) {
				for (int siguiente=fila+1;siguiente<=table.length-1;siguiente++) {
					if (!table[siguiente][columna].checkSum(table[fila][columna]) && table[siguiente][columna].hasValue()) break;
					if (table[siguiente][columna].checkSum(table[fila][columna])) {
						table[fila][columna].sum(table[siguiente][columna]);
						break;
					}
				}
			}
		}
	}
	
	
	public void moveUp() 
	{
		sumUp();
		for (int columna=0; columna<=table.length-1; columna++) {
			for (int contador=1; contador<=table.length; contador++ ) {
				for (int fila=3; fila>table.length-table.length;fila--) {
					if (!table[fila-1][columna].hasValue()) {
						table[fila-1][columna].sum(table[fila][columna]);
						table[fila][columna].setValue(0);	
					}
				}
			}
		}
	}
	
	public void consoleRender() {
		for (int fila = 0; fila<=table.length-1; fila++) {
			String rend = "\t";
			for (int columna = 0; columna<=table.length-1; columna++) {
				if (table[fila][columna] instanceof PBlockedField) rend += "B(" +getFieldValue(fila, columna) + ")\t";
				else{
				if (!table[fila][columna].hasValue()) rend += "-";
				else rend += getFieldValue(fila, columna);
				if (table[fila][columna].hasPowerUp()) rend += table[fila][columna].getBuff().render();
				rend += "\t";
				}
			}
			rend += "\n";
			System.out.println(rend);
			
			}
	}
	
	
	public void fieldSpawner()
	{
		if (!isFull()) {
			boolean done = false;
			int c = (int) ((Math.random()*Math.random() * 20));
			while (!done) {
				int a = (int) ((Math.random()*Math.random() * 4));
				int b = (int) ((Math.random()*Math.random() * 4));
		
				if (!(table[a][b].hasValue()) && !(table[a][b] instanceof PBlockedField)) {
					if (c == 0)	{
						table[a][b] = new Field(2,this, randomPowerUp());
						System.out.println(spawn_mes + a + "][" + b + spawn_fol);
					}
					else {
						table[a][b] = new Field(2,this);
						System.out.println( spawn_mes + a + "][" + b + "]\n");
					}
					done = true;
				}
			}
		}
	}
	
	private void fieldSpawnerFirstRound()
	{
				int a = (int) ((Math.random()*Math.random() * 4));
				int b = (int) ((Math.random()*Math.random() * 4));
					table[a][b] = new Field(2,this);

	}
	public boolean isFull() 
	{
		for (int fila = 0; fila<=table.length-1; fila++) {
			for (int columna = 0; columna<=table.length-1; columna++) {
				if (!table[fila][columna].hasValue()) return false;
			}
		}
		return true;
	}
	
	public boolean checkSumHorizontal()
	{
		for (int fila=0; fila<=table.length - 1; fila++) {
			for (int columna=0; columna<=table[fila].length - 1;columna++) {
					if (table[fila][columna+1].checkSum(table[fila][columna])) return true; 
				}
			}
		return false;
	}
	public boolean checkSumVertical() 
	{
		for (int columna=0; columna<=table.length - 1; columna++) {
			for (int fila=0; fila<=table[fila].length - 1;fila++) {
					if (table[fila+1][columna].checkSum(table[fila][columna])) return true; 
				}
			}
		return false;
	}
	
	public boolean canMoveLeft()
	{
		for (int fila=0; fila<=table.length - 1; fila++) {
			for (int columna=1; columna<=table[fila].length - 1;columna++) {
					if (table[fila][columna].hasValue() && table[fila][columna].checkSum(table[fila][columna-1])) return true; 
				}
			}
		return false;
	}
	
	public boolean canMoveUp()
	{
		for (int fila=1; fila<=table.length - 1; fila++) {
			for (int columna=0; columna<=table[fila].length - 1;columna++) {
				if (table[fila][columna].hasValue() && table[fila][columna].checkSum(table[fila-1][columna])) return true; 
			}
		}
		return false;
	}
	
	public boolean canMoveRight()
	{
		for (int fila=0; fila<=table.length - 1; fila++) {
			for (int columna=0; columna<=table[fila].length - 2;columna++) {
				if (table[fila][columna].hasValue() && table[fila][columna].checkSum(table[fila][columna+1])) return true; 
			}
		}
		return false;
	}
	
	public boolean canMoveDown()
	{
		for (int fila=0; fila<=table.length - 1; fila++) {
			for (int columna=0; columna<=table[fila].length - 1;columna++) {
				if (table[fila][columna].hasValue() && table[fila][columna].checkSum(table[fila+1][columna])) return true; 
			}
		}
		return false;
	}
	
	public boolean gameLost()
	{
		return (isFull() && !checkSumHorizontal() && !checkSumVertical());
	}

	public PowerUp randomPowerUp() 
	{
		int a = (int) ((Math.random()*Math.random() * 4));
		if (a == 0) return new PowerUpBlock();
		if (a == 1) return new PowerUpRemove();
		if (a == 2) return new PowerUpMove();
		if (a == 3) return new PowerUpDivide();
		return null;
	}
	
	
	public void blockField() 
	{
		boolean done = false;
		while (done == false) {
			int a = (int) ((Math.random()*Math.random() * table.length));
			int b = (int) ((Math.random()*Math.random() * table.length));
			if (getFieldValue(a,b) != 0) {
				table[a][b] = new PBlockedField(getFieldValue(a, b), table[a][b].getBuff());
				System.out.println(field_mes + a + "][" + b + from_mes + player.getName().toUpperCase() + bl_mes);
				done = true;
			}
		}
	}
	
	public void revertBlockedField()
	{
		for (int fila = 0; fila<=table.length-1; fila++) {
			for (int columna = 0; columna<=table.length-1; columna++) {
				if (table[fila][columna] instanceof PBlockedField) {
					table[fila][columna] = new Field(getFieldValue(fila,columna), this, table[fila][columna].getBuff());
					System.out.println(field_mes + fila + "][" + columna + from_mes + player.getName().toUpperCase() + unbl_mes);
				}
			}
		}
	}
	
	public void moveDebuffUp() 
	{
		boolean done = false;
		while (done == false) {
			int a = (int) ((Math.random()*Math.random() * table.length));
			for (int contador=1; contador<=4; contador ++ ) {
				for (int fila=0; fila< table.length - 1;fila++) {
					if (getFieldValue(fila+1,a) == 0) {
						table[fila+1][a].sum(table[fila][a]);	
					}
				}
			}
		System.out.println(row_mes + a + from_mes + player.getName().toUpperCase() + move_mes);
		done = true;
		}
	}
	
	public void moveDebuffDown() 
	{
		boolean done = false;
		while (done == false) {
			int a = (int) ((Math.random()*Math.random() * table.length));
			for (int contador=1; contador<=4; contador ++ ) {
				for (int fila=table.length - 1; fila>0;fila--) {
					if (getFieldValue(fila-1,a) == 0) {
						table[fila-1][a].sum(table[fila][a]);	
					}
				}
			}
		System.out.println(row_mes + a + from_mes + player.getName().toUpperCase() + move_mes);
		done = true;
		}
	}
	
	public void moveDebuffLeft() 
	{
		boolean done = false;
		while (done == false) {
			int a = (int) ((Math.random()*Math.random() * table.length));
			for (int contador=1; contador<=4; contador ++ ) {
				for (int columna=0; columna< table.length - 1;columna++) {
					if (getFieldValue(a,columna+1) == 0) {
						table[a][columna+1].sum(table[a][columna]);	
					}
				}
			}
		System.out.println(col_mes + a + from_mes + player.getName().toUpperCase() + move_mes);
		done = true;
		}
	}
	
	public void moveDebuffRight() 
	{
		boolean done = false;
		while (done == false) {
			int a = (int) ((Math.random()*Math.random() * table.length));
			for (int contador=1; contador<=4; contador ++ ) {
				for (int columna=table.length - 1; columna>0;columna--) {
					if (getFieldValue(a,columna-1) == 0) {
						table[a][columna-1].sum(table[a][columna]);	
					}
				}
			}
		System.out.println(col_mes + a + from_mes + player.getName().toUpperCase() + move_mes);
		done = true;
		}
	}

	public void divideField() 
	{
		boolean done = false;
		while (done == false) {
			int a = (int) ((Math.random()*Math.random() * table.length));
			int b = (int) ((Math.random()*Math.random() * table.length));
			if (getFieldValue(a,b) != 0 && getFieldValue(a,b) != 2 ) {
				table[a][b].setValue(getFieldValue(a,b)/2);;
				System.out.println(field_mes + a + "][" + b + from_mes + player.getName().toUpperCase() + div_mes);
				done = true;
			}
			if (getFieldValue(a,b) == 2){
				table[a][b].setValue(0);
				table[a][b].setBuff(null);
				System.out.println(field_mes + a + "][" + b + from_mes + player.getName().toUpperCase() + div_mes
						+ div_fol);
				done = true;
			}
		}
	}
	
	public void removeField() 
	{
		boolean done = false;
		while (done == false) {
			int a = (int) ((Math.random()*Math.random() * table.length));
			int b = (int) ((Math.random()*Math.random() * table.length));
			if (getFieldValue(a,b) != 0 && !(table[a][b] instanceof PBlockedField)) {
				table[a][b].setValue(0);;
				table[a][b].setBuff(null);
				System.out.println(field_mes + a + "][" + b + from_mes + player.getName().toUpperCase() + rem_mes);
				done = true;
			}
		}
  }
	
	com.github.cliftonlabs.json_simple.JsonObject obj = new com.github.cliftonlabs.json_simple.JsonObject();
	
	public JsonArray saveBoard() {
		
		JsonArray jsonBoard = new JsonArray();
		for (int i = 0; i < table.length; i++) {
			JsonArray jsonArray = new JsonArray();
			for (int j = 0; j < table.length; j++) {
				obj.put("field", getFieldValue(i, j));
				jsonArray.add(obj);
			}
			jsonBoard.add(jsonArray);
		}

		
		return jsonBoard;
	}
	
	public void loadBoard(JsonArray array) {
		for (int i = 0; i < array.size(); i++) {
			Object[] fields = array.getCollection(i).toArray();			
			for (int j = 0; j < fields.length; j++) {
				
				this.table[i][j] = new Field(((JsonObject) fields[j]).get("field"));
				this.table[i][j].setBoard(this);
			}
		}
		
	}
	


}
