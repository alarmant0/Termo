class Util {

	public static void paintIcon(ColorImage bg, int x, int y, Color estado, char c) {
		if (bg == null) {
			throw new NullPointerException("BG CANNOT BE NULL!");
		}
		for(int i = 0;i<Constantes.ICON_SIZE;i++) {
			for(int j = 0;j<Constantes.ICON_SIZE;j++) {
				bg.setColor(x + i,y + j, estado);
			}
		}
		bg.drawCenteredText( (Constantes.ICON_SIZE/2) + x,  (Constantes.ICON_SIZE/2) + y, String.valueOf(c).toUpperCase(), Constantes.LETTER_SIZE , new Color(255,255,255));
	}
	
	public static void paintGrelhaVazia(ColorImage img) {
		int x = Constantes.SETUP;
		int y = 35;
		Color c = Constantes.NOT_IN_WORD_BG;
		for(int i = 0;i<Constantes.TRIES;i++) {
			for(int j = 0;j<Constantes.WORD_LENGTH;j++) {
				if (i == 0) {
					paintSpecialIcon(img, x, y );
				}
				else {
					paintIcon(img, x , y, c, ' ');
				}
				x+= Constantes.ICON_SIZE + 5;
			}
			x = Constantes.SETUP;
			y += Constantes.ICON_SIZE + Constantes.ICON_SPACING;
		}
	}
	
	public static void paintSpecialIcon(ColorImage bg, int x, int y) {
		if (x < 0 || y < 0) {
			throw new IllegalArgumentException("X/Y CANNOT BE LESS THAN 0");
		}
		for(int i = 0;i<Constantes.ICON_SIZE;i++) {
			for(int j = 0;j<Constantes.ICON_SIZE;j++) {
				if (i > 5 && i < Constantes.ICON_SIZE - 5 && j > 5 && j < Constantes.ICON_SIZE - 5) {
					bg.setColor(x + i, y + j ,Constantes.DEFAULT_BG);
				}
				else {
					bg.setColor(x + i,y + j ,Constantes.EMPTY_BG);
				}
			}
		}
	}
	
	public static void paintGrelha(ColorImage img, char[][] matrix, String word, int [] v, String secret) {
		int x = Constantes.SETUP;
		String str = word.toUpperCase();
		for(int i = 0;i<Constantes.TRIES;i++) {
			if (matrix[i][0] != ' ') {
				continue;
			}
			for(int j = 0;j<Constantes.WORD_LENGTH;j++) {
				int y = (Constantes.ICON_SIZE * i) + (Constantes.ICON_SPACING * i) + 35;
				Color c = check(str.charAt(j),  secret , j , v);
				paintIcon(img, x , y, c, str.charAt(j));
				matrix[i][j] = str.charAt(j);
				x+= Constantes.ICON_SIZE + 5;
				if (i != Constantes.TRIES - 1) {
					paintSpecialIcon(img, x - Constantes.ICON_SIZE - 5, (Constantes.ICON_SIZE * (i+1) + (Constantes.ICON_SPACING * (i+1)) + 35));
				}
			}
			return;
		}
	}
	
	public static void paintKeyboard(ColorImage bg, char[][] teclado, int [] codigos) {
		int x = 130;
		int y = 400;
		for(int i = 0;i<teclado.length;i++) {
			x = 130 + (i*30);
			for(int j = 0;j<teclado[i].length;j++) {
				Color c = findColor(findIndex(teclado[i][j]), codigos);
				paintIcon(bg, x, y, c, teclado[i][j]);
				x+= Constantes.ICON_SIZE + 5;
			}
			y += Constantes.ICON_SIZE + Constantes.ICON_SPACING;
		}
	}
	
	private static int findIndex(Character c) {
		int aux = 0;
		for(int i = 0;i<Constantes.QWERTY.length;i++) {
			for(int j = 0;j<Constantes.QWERTY[i].length;j++) {
				if (Constantes.QWERTY[i][j] == c) {
					return aux;
				}
				aux++;
			}
		}
		return aux;
	}
	
	private static Color findColor(int index, int [] v) {
		switch(v[index]) {
			case 0:
				return Constantes.EMPTY_BG;
			case 1:
				return Constantes.NOT_IN_WORD_BG;
			case 2:
				return Constantes.EXISTS_BG;
			case 3:
				return Constantes.CORRECT_BG;
		}
		return Constantes.ERROR_BG;
	}
	
	public static String getWordSimple(Dictionary dic, String guess) {
		return dic.getOriginalWord(guess);
	}
	
	private static Color check(char c , String secret, int index, int [] v) { // int [] v //EM VEZ DE CHAR C STRING // CRIAR FUNCAO VERIFICAR SE JA FOI ENCONTRADA
		int x = findIndex(c);
		for(int i = 0;i<secret.length();i++) {
			if (secret.charAt(i) == c) {
				if (index == i) {
					v[x] = Constantes.CORRECT_POSITION;
					return Constantes.CORRECT_BG;
				}
				else {
					if (v[x] != 3) {
						v[x] = Constantes.EXISTS;
						return Constantes.EXISTS_BG;
					}
				}
			}
		}
		if (v[x] != 3) {
			v[x] = Constantes.NOT_EXISTS;
		}
		return Constantes.NOT_IN_WORD_BG;
	}
	
}