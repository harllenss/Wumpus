package br.com.iesb.wumpus;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class PontuacaoActivity extends Activity {

    ListView pontuacao;
    private ArrayList<String> list;
    private ArrayAdapter<String> scores;

    private PontuacaoUtil pontuacaoUtil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pontuacao);

        pontuacao = (ListView) findViewById(R.id.listaPontuacao);

        pontuacaoUtil = new PontuacaoUtil(this);

        final List<PontuacaoVO> scoreList = pontuacaoUtil.retornarPontuacoes();

        list = new ArrayList<String>();
        for(PontuacaoVO pontuacaoVO : scoreList) {
            list.add(" " + pontuacaoVO.getPontuacao());
        }

        scores = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);

        pontuacao.setAdapter(scores);

    }
}
