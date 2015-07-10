package br.com.iesb.wumpus.game;

import java.util.List;
import java.util.Random;

public class Malha {

    private Random random = new Random();

    private Sala[][] salas;
    private Sala salaDoAgente;

    private int quantidadeSalasPorLado;

    public Malha(int quantidadeSalasPorLado, int quantidadeWumpus, int quantidadeCova, int quantidadeOuro) {
        inicializarMalha(quantidadeSalasPorLado);
        inicializarAgente();
        inicializarOuro(quantidadeOuro);
        inicializarWumpus(quantidadeWumpus);
        inicializarCova(quantidadeCova);
    }

    private void inicializarMalha(int quantidadeSalasPorLado) {
        this.quantidadeSalasPorLado = quantidadeSalasPorLado;
        this.salas = new Sala[quantidadeSalasPorLado][quantidadeSalasPorLado];
        inicializarSala();
        inicializarVizinhos();
        this.salaDoAgente = this.salas[quantidadeSalasPorLado - 1][0];
    }

    private void inicializarSala() {
        for (int y = 0; y < salas.length; y++) {
            for (int x = 0; x < salas.length; x++) {
                salas[y][x] = new Sala();
            }
        }
    }

    private void inicializarVizinhos() {
        for (int y = 0; y < salas.length; y++) {
            for (int x = 0; x < salas.length; x++) {
                salas[y][x].setVizinhoDeCima(vizinhoDeCima(y, x));
                salas[y][x].setVizinhoDeBaixo(vizinhoDeBaixo(y, x));
                salas[y][x].setVizinhoDaEsquerda(vizinhoDaEsquerda(y, x));
                salas[y][x].setVizinhoDaDireita(vizinhoDaDireita(y, x));
            }
        }
    }

    private Sala vizinhoDeCima(int y, int x) {
        return (y > 0) ? this.salas[y - 1][x] : null;
    }

    private Sala vizinhoDeBaixo(int y, int x) {
        return (y < quantidadeSalasPorLado - 1) ? this.salas[y + 1][x] : null;
    }

    private Sala vizinhoDaEsquerda(int y, int x) {
        return (x > 0) ? this.salas[y][x - 1] : null;
    }

    private Sala vizinhoDaDireita(int y, int x) {
        return (x < quantidadeSalasPorLado - 1) ? this.salas[y][x + 1] : null;
    }

    private void inicializarAgente() {
        this.salaDoAgente.adicionarItem(Item.AGENTE);
    }

    private void inicializarOuro(int quantidadeOuro) {
        preencherNSalasCom(quantidadeOuro, Item.OURO, new Item[] {});
    }

    private void inicializarWumpus(int quantidadeWumpus) {
        preencherNSalasCom(quantidadeWumpus, Item.WUMPUS, new Item[] { Item.OURO, Item.COVA });
    }

    private void inicializarCova(int quantidadeCova) {
        preencherNSalasCom(quantidadeCova, Item.COVA, new Item[] { Item.OURO, Item.WUMPUS });
    }

    private void preencherNSalasCom(int quantidadeASerPreenchida, Item item, Item[] restricoes) {
        int quantidadePreenchida = 0;
        do {
            Sala sala = preencherSalaCom(item, restricoes);
            if (sala.temItem(item)) {
                quantidadePreenchida++;
            }
        } while (quantidadePreenchida < quantidadeASerPreenchida);
    }

    private Sala preencherSalaCom(Item item, Item[] restricoes) {
        Sala sala;
        int x = random.nextInt(quantidadeSalasPorLado);
        int y = random.nextInt(quantidadeSalasPorLado);
        sala = this.salas[y][x];
        if (!sala.equals(salaDoAgente) && !sala.equals(salaDoAgente.getVizinhoDeCima()) && !sala.equals(salaDoAgente.getVizinhoDaDireita())
                && !sala.temAlgumDestesItens(restricoes)) {
            sala.adicionarItem(item);
        }
        return sala;
    }

    public String exibirParcial() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < salas.length; y++) {
            for (int x = 0; x < salas.length; x++) {
                sb.append(salas[y][x].exibirParcial() + '\t');
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    public String exibirTudo() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < salas.length; y++) {
            for (int x = 0; x < salas.length; x++) {
                sb.append(salas[y][x].exibirTudo() + '\t');
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return exibirTudo();
    }

    public List<Item> moverAgentePara(Direcao direcao) {
        Sala proximaSala = null;
        switch (direcao) {
        case CIMA:
            proximaSala = salaDoAgente.getVizinhoDeCima();
            break;
        case ESQUERDA:
            proximaSala = salaDoAgente.getVizinhoDaEsquerda();
            break;
        case BAIXO:
            proximaSala = salaDoAgente.getVizinhoDeBaixo();
            break;
        case DIREITA:
            proximaSala = salaDoAgente.getVizinhoDaDireita();
            break;
        default:
            proximaSala = null;
            break;
        }
        if (proximaSala != null) {
            salaDoAgente.removerItem(Item.AGENTE);
            proximaSala.adicionarItem(Item.AGENTE);
            proximaSala.setExplorado(true);
            salaDoAgente = proximaSala;
            return proximaSala.getItems();
        }
        return null;
    }

}
