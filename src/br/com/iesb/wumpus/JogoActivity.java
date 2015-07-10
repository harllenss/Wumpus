package br.com.iesb.wumpus;

import br.com.iesb.wumpus.game.Direcao;
import br.com.iesb.wumpus.game.MundoWumpus;
import br.com.iesb.wumpus.game.MundoWumpusBuilder;
import br.com.iesb.wumpus.game.Resultado;
import android.app.Activity;
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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jogo);
		
		int quantidadeSalasPorLado = 4;
        int quantidadeDeWumpus = 1;
        int quantidadeCova = 3;
        int quantidadeFlecha = 1;
        int quantidadeOuro = 1;
        
        textView = (TextView) findViewById(R.id.textView);
        
        mundoWumpus = new MundoWumpusBuilder().quantidadeSalasPorLado(quantidadeSalasPorLado)
                .quantidadeDeWumpus(quantidadeDeWumpus).quantidadeCova(quantidadeCova).quantidadeFlecha(quantidadeFlecha)
                .quantidadeOuro(quantidadeOuro).criar();
	
	
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
	        	
	        	
	        	/*
	            if (resultado.movimentoValido()) {
	                System.out.println("Resultado do Movimento: " + resultado);
	            } else {
	                System.err.print("Movimento invalido!\n\n");
	            }
	            */
	        	
	        	if(resultado.gameOver()){
	        		textView.setText("Você perdeu");
	        		
	        	}else if(resultado.venceu()){
	        		textView.setText("Você ganhou");
	        		
	        	}
	        	else{
	        		textView.setText(" " + resultado.getMessage());
	     
	        	}

	        	
	        	jogoAdapter.setItem(posicaoJogador, R.drawable.white1);
	        	jogoAdapter.setItem(position, R.drawable.newgoku);
	        	posicaoJogador = position;
	        	
	        	jogoAdapter.notifyDataSetChanged();
	        	
	        	
	        }
	    });
	}

}