package com.cursoandroid.centralpet.centralpet.activity.bancodados;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.cursoandroid.centralpet.centralpet.activity.bancodados.ScriptSQL;

/**
 * Created by L Moraes on 28/04/2017.
 */
public class DataBase extends SQLiteOpenHelper{

    public DataBase(Context context){
        super(context, "AGENDA", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(ScriptSQL.getCreateAgenda());

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
