package br.com.iesb.wumpus.game;

import java.util.List;

public class MundoWumpusImpl implements MundoWumpus {

	private Malha malha;
	private int quantidadeFlecha;
	private int wumpusDerrotados = 0;
	private int ourosEncontrados = 0;

	public MundoWumpusImpl(Malha malha, int quantidadeFlecha) {
		this.malha = malha;
		this.quantidadeFlecha = quantidadeFlecha;
	}

	@Override
	public Resultado andarPara(Direcao direcaoInformada) {
		List<Item> itensDaSala = malha.moverAgentePara(direcaoInformada);
		boolean movimentoValido = (itensDaSala != null);
		if (!movimentoValido) {
			return new ResultadoImpl(movimentoValido);
		} else {
			boolean gameOver = itensDaSala.contains(Item.COVA)
					|| itensDaSala.contains(Item.WUMPUS)
					|| itensDaSala.contains(Item.OURO);
			boolean venceu = itensDaSala.contains(Item.OURO);
			return new ResultadoImpl(movimentoValido, itensDaSala, gameOver,
					venceu);
		}
	}

	@Override
	public Resultado atirarPara(Direcao direcaoInformada) {
		return null;
	}

	@Override
	public String exibirParcial() {
		StringBuilder sb = new StringBuilder();
		sb.append(malha.exibirParcial());
//		sb.append(pontuacao());
		return sb.toString();
	}

	private String pontuacao() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n\nQuantidade Flecha: ").append(quantidadeFlecha)
				.append("\n");
		sb.append("Wumpus Derrotados: ").append(wumpusDerrotados).append("\n");
		sb.append("Ouros Encontrados: ").append(ourosEncontrados).append("\n");
		return sb.toString();
	}

	@Override
	public String exibirTudo() {
		StringBuilder sb = new StringBuilder();
		sb.append(malha.exibirTudo());
//		sb.append(pontuacao());
		return sb.toString();
	}

	@Override
	public String toString() {
		return exibirTudo();
	}

}
