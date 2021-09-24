package com.itca.practica2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ConexionSQLite extends SQLiteOpenHelper {
    public ConexionSQLite(@Nullable Context context) {
        super(context,  "EvalBlocNotas.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table tb_bloc(id integer not null primary key autoincrement, titulo text, descripcion text, autor text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
            db.execSQL("drop table if exists tb_bloc");
            onCreate(db);
          }
}
