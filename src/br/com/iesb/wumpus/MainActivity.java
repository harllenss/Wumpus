package br.com.iesb.wumpus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void selecionarOpcao(View view) {
		switch (view.getId()) {
		case R.id.tela_jogo:
			startActivity(new Intent(this, JogoActivity.class));
			break;

		default:
			break;
		}
	}
}
