package br.com.iesb.wumpus;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class JogoAdapter extends BaseAdapter {
	private Context context;
	private final int[] lista;
	private final android.view.ViewGroup.LayoutParams params;
	
	public JogoAdapter(Context context, int[] lista, 
			android.view.ViewGroup.LayoutParams params) {
		this.context = context;
		this.lista = lista;
		this.params = params;
	};
	
	public void setItem(int p, int image) {
		this.lista[p] = image;
	}
	
	@Override
	public int getCount() {
		return lista.length;
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		 ImageView imagem = new ImageView(context);
		 imagem.setImageResource(lista[position]);
			imagem.setAdjustViewBounds(true);
			imagem.setLayoutParams(params);
	        return imagem;
	}

}
