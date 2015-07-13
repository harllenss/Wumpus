package br.com.iesb.wumpus.game;

public interface MundoWumpus {

	Resultado andarPara(Direcao direcaoInformada);

	Resultado atirarPara(Direcao direcaoInformada);

	String exibirParcial();

	String exibirTudo();

	int retornaPontuacao();

}
