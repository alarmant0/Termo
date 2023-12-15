public class Game {

	private ColorImage bg;
	private Dictionary dic;
	private char[][] grelha; 
	private Stats stats;
	private String word;
	private int [] codigoDasLetras = new int[26];
	private int [] jogosStats = new int[4]; // [0] = jogosJogados, [1] = jogosGanhos, [2] = seqdevitorias, [3] = melhorseq
	private int [] jogosGanhos = new int [6]; 
	private int tentativas = 0;
	private boolean won = false;
	
	public Game(ColorImage bg) {
		jogosStats[0] = 1;
		this.stats = new Stats(jogosStats, jogosGanhos);
		this.bg = bg;
		resetGrelha();
		createEstados();
		createDictionary();
		paintKeyboardVazio(this.bg, codigoDasLetras);
		this.word = generateWord();
		Util.paintGrelhaVazia(this.bg);
	}
	
	public Game(Dictionary dic) {
		jogosStats[0] = 1;
		this.stats = new Stats(jogosStats, jogosGanhos);
		resetGrelha();
		this.dic = dic;
		this.word = generateWord();
		createEstados();
		Util.paintGrelhaVazia(this.bg);
		paintKeyboardVazio(this.bg, codigoDasLetras);
	}
	
	private void resetGrelha() {
		this.grelha = new char[6][6];
		for(int i = 0;i<grelha.length;i++) {
			for(int j = 0;j<grelha[i].length;j++) {
				this.grelha[i][j] = ' ';
			}
		}
	}
	
	public Stats getStats() {
		return this.stats;
	}
	
	private void paintKeyboardVazio(ColorImage img, int [] v) {
		Util.paintKeyboard(this.bg, Constantes.QWERTY, codigoDasLetras);
	}
	
	public String getSecretWord() {
		return this.word;
	}	
	
	public ColorImage getBg() {
		return this.bg;
	}
	
	private void createEstados() {
		for(int x = 0;x<codigoDasLetras.length;x++) {
			this.codigoDasLetras[x] = Constantes.UNTESTED;
		}
	}
	
	private void createDictionary() {
		this.dic = new Dictionary("./pt_br.txt");
	}
	
	private String generateWord() {
		return this.dic.generateSecretWord(6);
	}
	
	public void leaveAndseeStats() {
		if (tentativas == 6) {
			if (!won) {
				jogosStats[2] = 0;
			}
			this.stats.paintStats(this.bg);
		}
	}
	
	public void resetBg() {
		
	}
	
	public void playAgain() {
		if (tentativas == 6) {
			resetBg();
			won = false;
			jogosStats[0]++;
			resetGrelha();
			createEstados();
			paintKeyboardVazio(this.bg, codigoDasLetras);
			Util.paintGrelhaVazia(this.bg);
			this.word = generateWord();
			tentativas = 0;
		}
	}
	
	public void tryGuess(String guess) {
		if(guess.length() != 6 || tentativas == 6 || !dic.exists(guess)) return;
		this.tentativas++;
		guess = Util.getWordSimple(this.dic, guess);
		Util.paintGrelha(getBg(), this.grelha, guess, codigoDasLetras, word);
		updateKeyboard(guess, this.word);
		if (guess.equals(this.word)) {
			won = true;
			jogosStats[1] ++;
			jogosStats[2] ++;
			jogosStats[3] = jogosStats[2];
			jogosGanhos[tentativas-1]++;
			tentativas = 6;
		}
		return;
	}
	
	private void updateKeyboard(String guess, String word) {
		Util.paintKeyboard(this.bg, Constantes.QWERTY, this.codigoDasLetras);
	}
	
}