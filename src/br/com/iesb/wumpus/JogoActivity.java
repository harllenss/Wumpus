package br.com.iesb.wumpus;

import br.com.iesb.wumpus.game.Direcao;
import br.com.iesb.wumpus.game.MundoWumpus;
import br.com.iesb.wumpus.game.MundoWumpusBuilder;
import br.com.iesb.wumpus.game.Resultado;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class JogoActivity extends Activity {
	
	private int[] lista = {R.drawable.white1,R.drawable.white1,R.drawable.white1,R.drawable.white1,
            R.drawable.white1,R.drawable.white1,R.drawable.white1,R.drawable.white1,
            R.drawable.white1,R.drawable.white1,R.drawable.white1,R.drawable.white1,
            R.drawable.newgoku,R.drawable.white1,R.drawable.white1,R.drawable.white1};
    MundoWumpus mundoWumpus;

    int posicaoJogador = 12;
    TextView textView;

    int quantidadeSalasPorLado = 4;
    int quantidadeDeWumpus = 1;
    int quantidadeCova = 3;
    int quantidadeFlecha = 1;
    int quantidadeOuro = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo);



        textView = (TextView) findViewById(R.id.textView);

        mundoWumpus = new MundoWumpusBuilder().quantidadeSalasPorLado(quantidadeSalasPorLado)
                .quantidadeDeWumpus(quantidadeDeWumpus).quantidadeCova(quantidadeCova).quantidadeFlecha(quantidadeFlecha)
                .quantidadeOuro(quantidadeOuro).criar(this);


        GridView gv = (GridView) findViewById(R.id.tela_tabuleiro);
        final JogoAdapter jogoAdapter = new JogoAdapter(this, lista,new GridView.LayoutParams(180, 180));
        gv.setAdapter(jogoAdapter);

        gv.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                Direcao d = Direcao.MOVIMENTO_INVALIDO;
                if(posicaoJogador - 4 == position){
                    d = Direcao.CIMA;
                } else if (posicaoJogador + 4 == position){
                    d = Direcao.BAIXO;
                } else if (posicaoJogador + 1 == position){
                    d = Direcao.DIREITA;
                } else if (posicaoJogador - 1 == position){
                    d = Direcao.ESQUERDA;
                }

                Resultado resultado = mundoWumpus.andarPara(d);

                if(! resultado.movimentoValido()) return;



                if(resultado.gameOver()){
                    textView.setText("Você perdeu");
                    jogoAdapter.setItem(posicaoJogador, R.drawable.white1);
                    jogoAdapter.setItem(12, R.drawable.newgoku);
                    posicaoJogador = 12;
                    jogoAdapter.notifyDataSetChanged();

                    mundoWumpus = new MundoWumpusBuilder().quantidadeSalasPorLado(quantidadeSalasPorLado)
                            .quantidadeDeWumpus(quantidadeDeWumpus).quantidadeCova(quantidadeCova).quantidadeFlecha(quantidadeFlecha)
                            .quantidadeOuro(quantidadeOuro).criar(v.getContext());

                    Intent i = new Intent(JogoActivity.this, PontuacaoActivity.class);
                    startActivity(i);

                    return;

                }else if(resultado.venceu()){
                    textView.setText("Você ganhou");
                    jogoAdapter.setItem(posicaoJogador, R.drawable.white1);
                    jogoAdapter.setItem(12, R.drawable.newgoku);
                    posicaoJogador = 12;
                    jogoAdapter.notifyDataSetChanged();

                    mundoWumpus = new MundoWumpusBuilder().quantidadeSalasPorLado(quantidadeSalasPorLado)
                            .quantidadeDeWumpus(quantidadeDeWumpus).quantidadeCova(quantidadeCova).quantidadeFlecha(quantidadeFlecha)
                            .quantidadeOuro(quantidadeOuro).criar(v.getContext());

                    Intent i = new Intent(JogoActivity.this, PontuacaoActivity.class);
                    startActivity(i);

                    return;

                }
                else{
                    textView.setText(" " + resultado.getMessage() + "\n" + mundoWumpus.retornaPontuacao());

                }


                jogoAdapter.setItem(posicaoJogador, R.drawable.white1);
                jogoAdapter.setItem(position, R.drawable.newgoku);
                posicaoJogador = position;

                jogoAdapter.notifyDataSetChanged();


            }
        });
    }

}