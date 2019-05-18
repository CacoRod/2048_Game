package Game;

public class Board {
	
	private Field[][] table = { {new Field(0),new Field(0),new Field(0),new Field(0)},
								{new Field(0),new Field(0),new Field(0),new Field(0)},
								{new Field(0),new Field(0),new Field(0),new Field(0)},
								{new Field(0),new Field(0),new Field(0),new Field(0)}
							};
		
	public Field[][] getTable() {
		return table;
	}

	public int getFieldValue(int a, int b) {
		return table[a][b].getValue();
		}

	

	
	

	public void checkSumLeft() {
		for (int fila=0; fila<=table.length - 1; fila++) {
			for (int columna=0; columna<=table[fila].length - 2;columna++) {
				for (int siguiente=columna+1;siguiente<=table.length-1;siguiente++) {
					if (!table[fila][siguiente].checkSum(table[fila][columna]) && getFieldValue(fila,siguiente) != 0) break;
					if (table[fila][siguiente].checkSum(table[fila][columna])) {
						table[fila][columna].sum(table[fila][siguiente]);
						table[fila][siguiente].setValue(0);
						break;
					}
					}
				}
			}
		}
	

	public void moveLeft() {
		checkSumLeft();
		for (int fila=0; fila<=table.length - 1; fila++) {
			for (int contador=1; contador<=4; contador ++ ) {
				for (int columna=table[fila].length-1; columna>0;columna--) {
					if (getFieldValue(fila,columna-1) == 0) {
						table[fila][columna-1].sum(table[fila][columna]);
						table[fila][columna].setValue(0);	
					}
				}
			}
		}
		fieldSpawner();
	}
	
	
	public void checkSumRight() {
		for (int fila=0; fila<=table.length-1; fila++) {
			for (int columna=3; columna>table.length-table.length;columna--) {
				for (int siguiente=columna-1;siguiente>=table.length-table.length;siguiente--) {
					if (getFieldValue(fila,siguiente) != getFieldValue(fila,columna) && getFieldValue(fila,siguiente) != 0) break;
					if (getFieldValue(fila,siguiente) == getFieldValue(fila,columna)) {
						table[fila][columna].sum(table[fila][siguiente]);
						table[fila][siguiente].setValue(0);
						break;
						}
					}
				}
			}
		}


	public void moveRight() {
		checkSumRight();
		for (int fila=0; fila<=table.length-1; fila++) {
			for (int contador=1; contador<=table.length; contador++ ) {
				for (int columna=0; columna<table.length-1;columna++) {
					if (getFieldValue(fila,columna+1) == 0) {
						table[fila][columna+1].sum(table[fila][columna]);
						table[fila][columna].setValue(0);	
					}
				}
			}
		}
		fieldSpawner();
	}
	
	
	public void checkSumDown() {
		for (int columna=0; columna<=table.length -1; columna++) {
			for (int fila=3; fila>table.length - table.length;fila--) {
				for (int siguiente=fila-1;siguiente>=table.length-table.length;siguiente--) {
					if (getFieldValue(siguiente,columna) != getFieldValue(fila,columna) && getFieldValue(siguiente,columna) != 0) break;
					if (getFieldValue(siguiente,columna) == getFieldValue(fila,columna)) {
						table[fila][columna].sum(table[siguiente][columna]);
						table[siguiente][columna].setValue(0);
						break;
						}
					}
				}
			}
		}
	
	
	public void moveDown() {
		checkSumDown();
		for (int columna=0; columna<=table.length -1; columna++) {
			for (int contador=1; contador<=table.length; contador++ ) {
				for (int fila=0; fila<table.length - 1;fila++) {
					if (getFieldValue(fila+1,columna) == 0) {
						table[fila+1][columna].sum(table[fila][columna]);
						table[fila][columna].setValue(0);	
					}
				}
			}
		}
		fieldSpawner();
	}
	
	
	public void checkSumUp() {
		for (int columna=0; columna<=table.length-1; columna++) {
			for (int fila=0; fila<=table.length-2;fila++) {
				for (int siguiente=fila+1;siguiente<=table.length-1;siguiente++) {
					if (getFieldValue(siguiente,columna) != getFieldValue(fila,columna) && getFieldValue(siguiente,columna) != 0) break;
					if (getFieldValue(siguiente,columna) == getFieldValue(fila,columna)) {
						table[fila][columna].sum(table[siguiente][columna]);
						table[siguiente][columna].setValue(0);
						break;
						}
					}
				}
			}
		}
	
	
	public void moveUp() {
		checkSumUp();
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
		
		
		while (done == false) {
			int a = (int) ((Math.random()*Math.random() * 4));
			int b = (int) ((Math.random()*Math.random() * 4));
			if (getFieldValue(a, b) == 0) {
				table[a][b].setValue(2);
				done = true;
			}
		}
	}
			

	public void consoleRender() {
		for (int fila = 0; fila<=table.length-1; fila++) {
			String rend = "";
			for (int columna = 0; columna<=table.length-1; columna++) {
				rend += (getFieldValue(fila, columna) + ",");
			}
			System.out.println(rend);
			}
		}
	}
