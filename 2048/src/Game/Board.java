package Game;

public class Board{
	
	private Field[][] table = { {new Field(0,this),new Field(0,this),new Field(0,this),new Field(0,this)},
								{new Field(0,this),new Field(0,this),new Field(0,this),new Field(0,this)},
								{new Field(0,this),new Field(0,this),new Field(0,this),new Field(0,this)},
								{new Field(0,this),new Field(0,this),new Field(0,this),new Field(0,this)}
							};

	private Player player;
	
	
	public Board() 
	{
		fieldSpawnerFirstRound();
		fieldSpawnerFirstRound();
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

	
	

	public void sumLeft() 
	{
		for (int fila=0; fila<=table.length - 1; fila++) {
			for (int columna=0; columna<=table[fila].length - 2;columna++) {
				for (int siguiente=columna+1;siguiente<=table.length-1;siguiente++) {
					if (!table[fila][siguiente].checkSum(table[fila][columna]) && getFieldValue(fila,siguiente) != 0) break;
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
					if (getFieldValue(fila,columna-1) == 0) {
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
					if (!table[fila][siguiente].checkSum(table[fila][columna]) && getFieldValue(fila,siguiente) != 0) break;
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
					if (getFieldValue(fila,columna+1) == 0) {
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
					if (!table[siguiente][columna].checkSum(table[fila][columna]) && getFieldValue(siguiente,columna) != 0) break;
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
					if (getFieldValue(fila+1,columna) == 0) {
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
					if (!table[siguiente][columna].checkSum(table[fila][columna]) && getFieldValue(siguiente,columna) != 0) break;
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
					if (getFieldValue(fila-1,columna) == 0) {
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
				if (getFieldValue(fila, columna) == 0) rend += "-";
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
		
				if (getFieldValue(a, b) == 0 && !(table[a][b] instanceof PBlockedField)) {
					if (c == 0)	{
						table[a][b] = new Field(2,this, randomPowerUp());
						System.out.println("a FIELD has SPAWNED at [" + a + "][" + b + "] with a POWER UP!\n");
					}
					else {
						table[a][b] = new Field(2,this);
						System.out.println("a FIELD has SPAWNED at [" + a + "][" + b + "]\n");
					}
					done = true;
				}
			}
		}
	}
	
	private void fieldSpawnerFirstRound()
	{
			boolean done = false;
			while (!done) {
				int a = (int) ((Math.random()*Math.random() * 4));
				int b = (int) ((Math.random()*Math.random() * 4));
				if (getFieldValue(a, b) == 0) {
					table[a][b] = new Field(2,this);
					done = true;
			}
		}
	}
	public boolean isFull() 
	{
		for (int fila = 0; fila<=table.length-1; fila++) {
			for (int columna = 0; columna<=table.length-1; columna++) {
				if (getFieldValue(fila,columna) == 0) return false;
			}
		}
		return true;
	}
	
	public boolean checkSumHorizontal()
	{
		for (int fila=0; fila<=table.length - 1; fila++) {
			for (int columna=0; columna<=table[fila].length - 2;columna++) {
					if (table[fila][columna+1].checkSum(table[fila][columna])) return true; 
				}
			}
		return false;
	}
	
	public boolean checkSumVertical() 
	{
		for (int columna=0; columna<=table.length - 1; columna++) {
			for (int fila=0; fila<=table[fila].length - 2;fila++) {
					if (table[fila+1][columna].checkSum(table[fila][columna])) return true; 
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
				table[a][b] = new PBlockedField(getFieldValue(a, b));
				System.out.println("FIELD [" + a + "][" + b +"] from " + player.getName().toUpperCase() + " has been BLOCKED\n");
				done = true;
			}
		}
	}
	
	public void revertBlockedField()
	{
		for (int fila = 0; fila<=table.length-1; fila++) {
			for (int columna = 0; columna<=table.length-1; columna++) {
				if (table[fila][columna] instanceof PBlockedField) {
					table[fila][columna] = new Field(getFieldValue(fila,columna), this);
					System.out.println("FIELD [" + fila + "][" + columna + "] from " + player.getName().toUpperCase() + " has been UNBLOCKED\n");
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
		System.out.println("ROW [" + a +"] from " + player.getName().toUpperCase() + " has MOVED in the opossite direction\n");
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
		System.out.println("ROW [" + a +"] from " + player.getName().toUpperCase() + " has MOVED in the opossite direction\n");
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
		System.out.println("COLUMN [" + a +"] from " + player.getName().toUpperCase() + " has MOVED in the opossite direction\n");
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
		System.out.println("COLUMN [" + a +"] from " + player.getName().toUpperCase() + " has MOVED in the opossite direction\n");
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
				System.out.println("FIELD [" + a + "][" + b +"] from " + player.getName().toUpperCase() + " has been DIVIDED\n");
				done = true;
			}
			if (getFieldValue(a,b) == 2){
				table[a][b].setValue(0);
				table[a][b].setBuff(null);
				System.out.println("FIELD [" + a + "][" + b +"] from " + player.getName().toUpperCase() + " has been DIVIDED...\n"
						+ "since the value was 2, it has been REMOVED\n");
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
				System.out.println("FIELD [" + a + "][" + b +"] from " + player.getName().toUpperCase() + " has been REMOVED\n");
				done = true;
			}
		}
	}

}
