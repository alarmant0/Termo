class Stats {

	private int [] stats;
	private int [] jogosGanhos;
	
	public Stats(int [] vitorias, int [] jogosGanhos) {
		this.stats = vitorias;
		this.jogosGanhos = jogosGanhos;
	}
	
	public int [] getStats() {
		return this.stats;
	}
	
	public int [] getJogosGanhos() {
		return this.jogosGanhos;
	}
	
	public void addVictory() {
		this.stats[1]++;
	}
	
	public void paintStats(ColorImage img) {
		for(int i = 0;i<img.getWidth();i++) {
			for(int j = 0;j<img.getHeight();j++) {
				img.setColor(i,j, Constantes.DEFAULT_BG);
			}
		}
		//ESTATISTICAS
		img.drawText(Constantes.DEFAULT_WIDTH/2 - 140, 30, "ESTATÍSTICAS", Constantes.LETTER_SIZE + 10, Constantes.LETTER_COLOR);
		// Jogos Jogados
		img.drawText(Constantes.DEFAULT_WIDTH/2 - 240, 90, Integer.toString(stats[0]) , Constantes.LETTER_SIZE + 20, Constantes.LETTER_COLOR);
		img.drawText(Constantes.DEFAULT_WIDTH/2 - 280, 140, "Jogados", Constantes.LETTER_SIZE - 8, Constantes.LETTER_COLOR);
		//Percentagem de vitoria
		img.drawText(Constantes.DEFAULT_WIDTH/2 - 140, 90, Integer.toString((stats[1]*100)/stats[0]), Constantes.LETTER_SIZE + 20, Constantes.LETTER_COLOR);
		img.drawText(Constantes.DEFAULT_WIDTH/2 - 170, 140, "% Vitórias", Constantes.LETTER_SIZE - 8, Constantes.LETTER_COLOR);
		// Sequencia de vitorias
		img.drawText(Constantes.DEFAULT_WIDTH/2 - 20, 90, Integer.toString(stats[2]), Constantes.LETTER_SIZE + 20, Constantes.LETTER_COLOR);
		img.drawText(Constantes.DEFAULT_WIDTH/2 - 50, 140, "Sequencias", Constantes.LETTER_SIZE - 8, Constantes.LETTER_COLOR);
		img.drawText(Constantes.DEFAULT_WIDTH/2 - 50, 160, "de vitorias", Constantes.LETTER_SIZE - 8, Constantes.LETTER_COLOR);
		// Melhor Sequencia
		img.drawText(Constantes.DEFAULT_WIDTH/2 + 100, 90, Integer.toString(stats[3]), Constantes.LETTER_SIZE + 20, Constantes.LETTER_COLOR);
		img.drawText(Constantes.DEFAULT_WIDTH/2 + 90, 140, "Melhor", Constantes.LETTER_SIZE - 8, Constantes.LETTER_COLOR);
		img.drawText(Constantes.DEFAULT_WIDTH/2 + 80, 160, "Sequencia", Constantes.LETTER_SIZE - 8, Constantes.LETTER_COLOR);
		// Distribuiçao de tentativas
		img.drawText(Constantes.DEFAULT_WIDTH/2 - 210, Constantes.DEFAULT_HEIGHT/2 - 60, "Distribuiçao de tentativas", Constantes.LETTER_SIZE + 10, Constantes.LETTER_COLOR);
		// Grelha da distribuiçao de tentativas

		int x = Constantes.DEFAULT_WIDTH / 2 - 100;
		int y = Constantes.DEFAULT_HEIGHT / 2;
		for(int i = 0;i<6;i++) {
			Util.paintIcon(img, x, y, Constantes.DEFAULT_BG, (char)(i + 1 + 48));
			Util.paintIcon(img, x + 30, y, Constantes.DEFAULT_BG, '>');
			Util.paintIcon(img, x + 60, y, Constantes.DEFAULT_BG, (char)(jogosGanhos[i] + 48));
			y += 40;
		}
		Util.paintIcon(img, x, y, Constantes.DEFAULT_BG, 'X');
		Util.paintIcon(img, x + 30, y, Constantes.DEFAULT_BG, '>');
		Util.paintIcon(img, x + 60, y, Constantes.DEFAULT_BG, (char)(stats[0]-stats[1] + 48));

	}
	
//	void drawCenteredText(int textX, int textY, String text, int textSize, Color textColor) {
//		drawText(textX, textY, text, textSize, textColor, true);
//	}
	
}