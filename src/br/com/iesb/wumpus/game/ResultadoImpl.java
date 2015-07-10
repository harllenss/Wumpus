package br.com.iesb.wumpus.game;

import java.util.LinkedList;
import java.util.List;

public class ResultadoImpl implements Resultado {

	private boolean movimentoValido;
	private List<Item> itensDaSala = new LinkedList<Item>();
	private boolean gameOver = false;
	private boolean venceu = false;

	public ResultadoImpl(boolean movimentoValido, List<Item> itensDaSala,
			boolean gameOver, boolean venceu) {
		this.movimentoValido = movimentoValido;
		this.itensDaSala = itensDaSala;
		this.gameOver = gameOver;
		this.venceu = venceu;
	}

	public ResultadoImpl(boolean movimentoValido) {
		this.movimentoValido = movimentoValido;
	}

	@Override
	public boolean movimentoValido() {
		return movimentoValido;
	}

	@Override
	public List<Item> itensDaSala() {
		return itensDaSala;
	}

	@Override
	public boolean gameOver() {
		return gameOver;
	}

	@Override
	public boolean venceu() {
		return venceu;
	}

	@Override
	public String toString() {
		return "[movimentoValido=" + movimentoValido + ", itensDaSala="
				+ itensDaSala + ", gameOver=" + gameOver + ", venceu=" + venceu
				+ "]";
	}
	
	public String getMessage() {
		String message = "";
		for(Item i : itensDaSala) {
			if(i.equals(Item.BRISA)) {
				message += "Estou sentindo uma brisa";
				
			}
			if(i.equals(Item.FEDOR)) {
				message += "\nEstou sentindo uma cheiro ruim";
			}
		}
		
		return message;
	}

}
