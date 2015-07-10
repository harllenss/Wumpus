package br.com.iesb.wumpus.game;

import java.util.List;

public interface Resultado {

	List<Item> itensDaSala();
	boolean movimentoValido();
	boolean gameOver();
	boolean venceu();
	
	public String getMessage();

}
