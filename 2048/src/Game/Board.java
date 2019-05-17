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
		for (int fila=0; fila<=3; fila++) {
			for (int columna=0; columna<=2;columna++) {
				for (int siguiente=columna+1;siguiente<=3;siguiente++) {
					if (getFieldValue(fila,siguiente) != getFieldValue(fila,columna) && (getFieldValue(fila,siguiente) != 0)) siguiente = 3;
						getField(fila,columna).sum(getField(fila,siguiente));
						getField(fila,siguiente).setValue(0);
						siguiente = 4;
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
