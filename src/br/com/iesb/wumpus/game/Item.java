package br.com.iesb.wumpus.game;

public enum Item {

	FEDOR('F', null, true, false), BRISA('B', null, true, false), WUMPUS('W', FEDOR, false, false), COVA(
			'C', BRISA, false, false), OURO('O', null, false, false), AGENTE('A', null, true, true);

	private char codigo;
	private Item efeito;
	private boolean exibicaoParcial;
	private boolean sempreVisivel;

	Item(char codigo, Item efeito, boolean exibicaoParcial, boolean sempreVisivel) {
		this.codigo = codigo;
		this.efeito = efeito;
		this.exibicaoParcial = exibicaoParcial;
		this.sempreVisivel = sempreVisivel;
	}

	public char getCodigo() {
		return codigo;
	}

	public Item getEfeito() {
		return this.efeito;
	}

	public boolean isExibicaoParcial() {
		return exibicaoParcial;
	}

	public boolean isSempreVisivel() {
		return sempreVisivel;
	}

}
