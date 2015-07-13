package br.com.iesb.wumpus;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class PontuacaoUtil {

    DatabaseHelper dbHelper;

    public PontuacaoUtil(Context c){
        dbHelper = new DatabaseHelper(c);
    }

    public long salvarPontuacao(int score){
        ContentValues values = new ContentValues();
        values.put("pontuacao", score);


        SQLiteDatabase db = dbHelper.getWritableDatabase();

        long result = db.insert("pontuacao", null,values);

        return result;

    }

    public List<PontuacaoVO> retornarPontuacoes() {
        List<PontuacaoVO> entities = new ArrayList<PontuacaoVO>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT _id, pontuacao FROM pontuacao", null);
        cursor.moveToFirst();

        for(int i = 0; i < cursor.getCount(); i++){
            PontuacaoVO pontuacao = new PontuacaoVO();
            pontuacao.setId(cursor.getInt(0));
            pontuacao.setPontuacao(cursor.getInt(1));
            entities.add(entities.size(), pontuacao);
            cursor.moveToNext();
        }

        return entities;
    }

    public void limparPontuacao() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        db.delete("score", null, null);
    }
}

