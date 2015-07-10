package br.com.iesb.wumpus.game;

public class MundoWumpusBuilder {

	private int quantidadeSalasPorLado = 4;
	private int quantidadeDeWumpus = 1;
	private int quantidadeCova = 3;
	private int quantidadeFlecha = 1;
	private int quantidadeOuro = 1;

	public MundoWumpusBuilder quantidadeSalasPorLado(int quantidadeSalasPorLado) {
		this.quantidadeSalasPorLado = quantidadeSalasPorLado;
		return this;
	}

	public MundoWumpusBuilder quantidadeDeWumpus(int quantidadeDeWumpus) {
		this.quantidadeDeWumpus = quantidadeDeWumpus;
		return this;
	}

	public MundoWumpusBuilder quantidadeCova(int quantidadeCova) {
		this.quantidadeCova = quantidadeCova;
		return this;
	}

	public MundoWumpusBuilder quantidadeFlecha(int quantidadeFlecha) {
		this.quantidadeFlecha = quantidadeFlecha;
		return this;
	}

	public MundoWumpusBuilder quantidadeOuro(int quantidadeOuro) {
		this.quantidadeOuro = quantidadeOuro;
		return this;
	}

	public MundoWumpus criar() {
		Malha malha = new Malha(quantidadeSalasPorLado, quantidadeDeWumpus,
				quantidadeCova, quantidadeOuro);
		return new MundoWumpusImpl(malha, quantidadeFlecha);
	}

}
