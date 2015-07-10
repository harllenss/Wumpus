package br.com.iesb.wumpus.game;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Sala {

    private Set<Item> items = new LinkedHashSet<Item>();
    private Sala vizinhoDeCima;
    private Sala vizinhoDeBaixo;
    private Sala vizinhoDaEsquerda;
    private Sala vizinhoDaDireita;
    private boolean explorado;

    public Sala getVizinhoDeCima() {
        return vizinhoDeCima;
    }

    public void setVizinhoDeCima(Sala vizinhoDeCima) {
        this.vizinhoDeCima = vizinhoDeCima;
    }

    public Sala getVizinhoDeBaixo() {
        return vizinhoDeBaixo;
    }

    public void setVizinhoDeBaixo(Sala vizinhoDeBaixo) {
        this.vizinhoDeBaixo = vizinhoDeBaixo;
    }

    public Sala getVizinhoDaEsquerda() {
        return vizinhoDaEsquerda;
    }

    public void setVizinhoDaEsquerda(Sala vizinhoDaEsquerda) {
        this.vizinhoDaEsquerda = vizinhoDaEsquerda;
    }

    public Sala getVizinhoDaDireita() {
        return vizinhoDaDireita;
    }

    public void setVizinhoDaDireita(Sala vizinhoDaDireita) {
        this.vizinhoDaDireita = vizinhoDaDireita;
    }

    public void adicionarItem(Item item) {
        items.add(item);
        Item efeito = item.getEfeito();
        if (efeito != null) {
            if (vizinhoDeCima != null) {
                vizinhoDeCima.adicionarItem(efeito);
            }
            if (vizinhoDeBaixo != null) {
                vizinhoDeBaixo.adicionarItem(efeito);
            }
            if (vizinhoDaEsquerda != null) {
                vizinhoDaEsquerda.adicionarItem(efeito);
            }
            if (vizinhoDaDireita != null) {
                vizinhoDaDireita.adicionarItem(efeito);
            }
        }
    }

    public void removerItem(Item item) {
        items.remove(item);
    }

    public List<Item> getItems() {
        Set<Item> unmodifiableSet = Collections.unmodifiableSet(items);
        List<Item> list = new LinkedList<Item>();
        list.addAll(unmodifiableSet);
        return list;
    }

    public boolean temOuro() {
        return temItem(Item.OURO);
    }

    public boolean temWumpus() {
        return temItem(Item.WUMPUS);
    }

    public boolean temCova() {
        return temItem(Item.COVA);
    }

    public boolean temItem(Item item) {
        boolean resultado = false;
        for (Item i : items) {
            if (i.equals(item)) {
                resultado = true;
            }
        }
        return resultado;
    }

    public boolean isExplorado() {
        return explorado;
    }

    public boolean temAlgumDestesItens(Item[] restricoes) {
        boolean resultado = false;
        for (int i = 0; i < restricoes.length; i++) {
            if (temItem(restricoes[i])) {
                resultado = true;
                break;
            }
        }
        return resultado;
    }

    public void setExplorado(boolean explorado) {
        this.explorado = explorado;
    }

    public String exibirParcial() {
        StringBuilder sb = new StringBuilder();
        for (Item item : items) {
            if ((this.explorado && item.isExibicaoParcial()) || (item.isSempreVisivel())) {
                sb.append(item.getCodigo());
            }
        }
        salaNaoExplorada(sb);
        return sb.toString();
    }

    private void salaNaoExplorada(StringBuilder sb) {
        if (sb.length() == 0) {
            sb.append(".");
        }
    }

    public String exibirTudo() {
        StringBuilder sb = new StringBuilder();
        for (Item item : items) {
            sb.append(item.getCodigo());
        }
        salaNaoExplorada(sb);
        return sb.toString();
    }

    @Override
    public String toString() {
        return exibirTudo();
    }

}
