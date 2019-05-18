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

	public Field getField(int a, int b) {
		return table[a][b];
	}

	public int getFieldValue(int a, int b) {
		return table[a][b].getValue();
		}

	
	
	

	
	

	public void checkSumLeft() {
		for (int fila=0; fila<=table.length - 1; fila++) {
			for (int columna=0; columna<=table[fila].length - 2;columna++) {
				for (int siguiente=columna+1;siguiente<=3;siguiente++) {
					if (!getField(fila,siguiente).checkSum(getField(fila,columna)) && getFieldValue(fila,siguiente) != 0) break;
					if (getField(fila,siguiente).checkSum(getField(fila,columna))) {
						getField(fila,columna).sum(getField(fila,siguiente));
						getField(fila,siguiente).setValue(0);
						break;
					}
					}
				}
			}
		}

	public void moveLeft() {
		checkSumLeft();
		for (int fila=0; fila<=3; fila++) {
			for (int contador=1; contador<=4; contador ++ ) {
				for (int columna=3; columna>0;columna--) {
					if (getFieldValue(fila,columna-1) == 0) {
						getField(fila, columna-1).sum(getField(fila, columna));
						getField(fila, columna).setValue(0);	
				}
			}
		}
	}
}
	public void checkSumRight() {
		for (int fila=0; fila<=3; fila++) {
			for (int columna=3; columna>=1;columna--) {
				for (int siguiente=columna-1;siguiente>=0;siguiente--) {
					if (getFieldValue(fila,siguiente) != getFieldValue(fila,columna) && getFieldValue(fila,siguiente) != 0) break;
					if (getFieldValue(fila,siguiente) == getFieldValue(fila,columna)) {
						getField(fila,columna).sum(getField(fila,siguiente));
						getField(fila,siguiente).setValue(0);
						break;
					}
					}
				}
			}
		}


	public void moveRight() {
		checkSumRight();
		for (int fila=0; fila<=3; fila++) {
			for (int contador=1; contador<=4; contador++ ) {
				for (int columna=0; columna<3;columna++) {
					if (getFieldValue(fila,columna+1) == 0) {
						getField(fila, columna+1).sum(getField(fila, columna));
						getField(fila, columna).setValue(0);	
				}
			}
		}
	}
}
	public void checkSumDown() {
		for (int columna=0; columna<=3; columna++) {
			for (int fila=3; fila>=1;fila--) {
				for (int siguiente=fila-1;siguiente>=0;siguiente--) {
					if (getFieldValue(siguiente,columna) != getFieldValue(fila,columna) && getFieldValue(siguiente,columna) != 0) break;
					if (getFieldValue(siguiente,columna) == getFieldValue(fila,columna)) {
						getField(fila,columna).sum(getField(siguiente,columna));
						getField(siguiente,columna).setValue(0);
						break;
					}
					}
				}
			}
		}
	public void moveDown() {
		checkSumDown();
		for (int columna=0; columna<=3; columna++) {
			for (int contador=1; contador<=4; contador++ ) {
				for (int fila=0; fila<3;fila++) {
					if (getFieldValue(fila+1,columna) == 0) {
						getField(fila+1, columna).sum(getField(fila, columna));
						getField(fila, columna).setValue(0);	
				}
			}
		}
	}
}
	public void checkSumUp() {
		for (int columna=0; columna<=3; columna++) {
			for (int fila=0; fila<=2;fila++) {
				for (int siguiente=fila+1;siguiente<=3;siguiente++) {
					if (getFieldValue(siguiente,columna) != getFieldValue(fila,columna) && getFieldValue(siguiente,columna) != 0) break;
					if (getFieldValue(siguiente,columna) == getFieldValue(fila,columna)) {
						getField(fila,columna).sum(getField(siguiente,columna));
						getField(siguiente,columna).setValue(0);
						break;
					}
					}
				}
			}
		}
	public void moveUp() {
		checkSumUp();
		for (int columna=0; columna<=3; columna++) {
			for (int contador=1; contador<=4; contador++ ) {
				for (int fila=3; fila>0;fila--) {
					if (getFieldValue(fila-1,columna) == 0) {
						getField(fila-1, columna).sum(getField(fila, columna));
						getField(fila, columna).setValue(0);	
				}
			}
		}
	}
}
	public void consoleRender() {
		for (int fila = 0; fila<=3; fila++) {
			String rend = "";
			for (int columna = 0; columna<=3; columna++) {
				rend += (getFieldValue(fila, columna) + ",");
			}
			System.out.println(rend);
		}
	}

}
