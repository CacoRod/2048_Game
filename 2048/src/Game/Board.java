package Game;

public class Board{
	
	private Field[][] table = { {new Field(0),new Field(2),new Field(0),new Field(0)},
								{new Field(0),new Field(2),new Field(0),new Field(0)},
								{new Field(0),new Field(4),new Field(0),new Field(0)},
								{new Field(0),new Field(0),new Field(0),new Field(0)}
							};
	
	PBlockedField test = new PBlockedField(2);
	private int score;
	
	
	public Board() {
		setScore(0);
		fieldSpawner();
		fieldSpawner();
	}
	
		
	public int getScore() {
		return score;
	}

	public void sumScore(int value) {
		setScore(getScore()+value);
	}

	public void setScore(int score) {
		this.score = score;
	}


	public Field[][] getTable() {
		return table;
	}

	public int getFieldValue(int a, int b) {
		return table[a][b].getValue();
		}

	

	
	

	public void sumLeft() {
		for (int fila=0; fila<=table.length - 1; fila++) {
			for (int columna=0; columna<=table[fila].length - 2;columna++) {
				for (int siguiente=columna+1;siguiente<=table.length-1;siguiente++) {
					if (!table[fila][siguiente].checkSum(table[fila][columna]) && getFieldValue(fila,siguiente) != 0) break;
					if (table[fila][siguiente].checkSum(table[fila][columna])) {
						table[fila][columna].sum(table[fila][siguiente]);
						sumScore(getFieldValue(fila,columna));
						break;
					}
					}
				}
			}
		}
	

	public void moveLeft() {
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
		fieldSpawner();
	}
	
	
	public void sumRight() {
		for (int fila=0; fila<=table.length-1; fila++) {
			for (int columna=3; columna>table.length-table.length;columna--) {
				for (int siguiente=columna-1;siguiente>=table.length-table.length;siguiente--) {
					if (!table[fila][siguiente].checkSum(table[fila][columna]) && getFieldValue(fila,siguiente) != 0) break;
					if (table[fila][siguiente].checkSum(table[fila][columna])) {
						table[fila][columna].sum(table[fila][siguiente]);
						sumScore(getFieldValue(fila,columna));
						break;
						}
					}
				}
			}
		}


	public void moveRight() {
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
		fieldSpawner();
	}
	
	
	public void sumDown() {
		for (int columna=0; columna<=table.length -1; columna++) {
			for (int fila=3; fila>table.length - table.length;fila--) {
				for (int siguiente=fila-1;siguiente>=table.length-table.length;siguiente--) {
					if (!table[siguiente][columna].checkSum(table[fila][columna]) && getFieldValue(siguiente,columna) != 0) break;
					if (table[siguiente][columna].checkSum(table[fila][columna])) {
						table[fila][columna].sum(table[siguiente][columna]);
						sumScore(getFieldValue(fila,columna));
						break;
						}
					}
				}
			}
		}
	
	
	public void moveDown() {
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
		fieldSpawner();
	}
	
	
	public void sumUp() {
		for (int columna=0; columna<=table.length-1; columna++) {
			for (int fila=0; fila<=table.length-2;fila++) {
				for (int siguiente=fila+1;siguiente<=table.length-1;siguiente++) {
					if (!table[siguiente][columna].checkSum(table[fila][columna]) && getFieldValue(siguiente,columna) != 0) break;
					if (table[siguiente][columna].checkSum(table[fila][columna])) {
						table[fila][columna].sum(table[siguiente][columna]);
						sumScore(getFieldValue(fila,columna));
						break;
						}
					}
				}
			}
		}
	
	
	public void moveUp() {
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
		fieldSpawner();
	}
	
	
	private void fieldSpawner() {
		boolean done = false;
		while (!done) {
			int a = (int) ((Math.random()*Math.random() * 4));
			int b = (int) ((Math.random()*Math.random() * 4));
			if (getFieldValue(a, b) == 0) {
				table[a][b] = new Field(2);
				done = true;
			}
		}
	}
	public boolean isFull() {
		for (int fila = 0; fila<=table.length-1; fila++) {
			for (int columna = 0; columna<=table.length-1; columna++) {
				if (getFieldValue(fila,columna) == 0) return false;
			}
		}
		return true;
	}
	
	public boolean checkSumHorizontal() {
		for (int fila=0; fila<=table.length - 1; fila++) {
			for (int columna=0; columna<=table[fila].length - 2;columna++) {
					if (table[fila][columna+1].checkSum(table[fila][columna])) return true; 
				}
			}
		return false;
		
	}
	
	public boolean checkSumVertical() {
		for (int columna=0; columna<=table.length - 1; columna++) {
			for (int fila=0; fila<=table[fila].length - 2;fila++) {
					if (table[fila+1][columna].checkSum(table[fila][columna])) return true; 
				}
			}
		return false;
		
	}
	public boolean gameLost() {
		return (isFull() && !checkSumHorizontal() && !checkSumVertical());
	}
	
	}
