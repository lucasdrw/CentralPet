package com.cursoandroid.centralpet.centralpet.activity.dominio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;

import com.cursoandroid.centralpet.centralpet.activity.dominio.entidades.Compromissos;

import java.util.Date;

/**
 * Created by L Moraes on 28/04/2017.
 */
public class RepositorioComp {

    private SQLiteDatabase conn;
    public RepositorioComp (SQLiteDatabase conn){ this.conn = conn; }


    private ContentValues preencheContentValues(Compromissos compromissos){

        ContentValues values = new ContentValues();

        values.put("NOME", compromissos.getNome());
        values.put("COMPROMISSO", compromissos.getCompromisso());
        values.put("TIPO", compromissos.getTipo());
        values.put("OUTRO", compromissos.getOutro());
        //values.put("DATA", compromissos.getData().getTime());
        values.put("DATA", "22");
        values.put("HORARIO", "22");
        values.put("LOCAL", compromissos.getLocal());
        values.put("OBS", compromissos.getObs());

        return values;
    }

    public void excluir(long id){
        conn.delete("COMPROMISSO", " _id = ? ", new String[]{ String.valueOf(id)});
    }

    public void alterar(Compromissos compromissos){

        ContentValues values = preencheContentValues(compromissos);
        conn.update("COMPROMISSO", values, " _id = ? ", new String[]{ String.valueOf( compromissos.getId())});
    }

    public void inserir(Compromissos compromissos){

        ContentValues values = preencheContentValues(compromissos);
        conn.insertOrThrow("COMPROMISSO", null, values);
    }

    public ArrayAdapter<Compromissos> buscaComp(Context context){

        ArrayAdapter<Compromissos> adpComp = new ArrayAdapter<Compromissos>(context, android.R.layout.simple_list_item_1);


        Cursor cursor = conn.query("COMPROMISSO", null, null, null, null, null, null);

        if(cursor.getCount() > 0 ){

            cursor.moveToFirst();

            do {

                Compromissos compromissos = new Compromissos();

                compromissos.setId(cursor.getLong(0));
                compromissos.setNome(cursor.getString(1));
                compromissos.setCompromisso(cursor.getString(2));
                compromissos.setTipo(cursor.getString(3));
                compromissos.setOutro(cursor.getString(4));
                compromissos.setData( new Date(cursor.getLong(5)));
                compromissos.setHorario(null);
                compromissos.setLocal(cursor.getString(7));
                compromissos.setObs(cursor.getString(8));

                adpComp.add(compromissos);

            }while (cursor.moveToNext());
        }
        return adpComp;
    }
}
