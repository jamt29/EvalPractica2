package com.itca.practica2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ConexionSQLite extends SQLiteOpenHelper {
    public ConexionSQLite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table tb_bloc(id integer NOT NULL PRIMARY KEY AUTOINCREMENT ," + "titulo varchar NOT NULL, descripcion varchar NOT NULL, autor varchar NOT NULL)");
        db.execSQL("insert into tb_bloc(titulo, descripcion, autor)values( 'Cosas que hacer','Trabajdar en el modulo de comportamiento laboral','Byron')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("create table tb_bloc(id integer NOT NULL PRIMARY KEY AUTOINCREMENT ," + "titulo varchar NOT NULL, descripcion varchar NOT NULL, autor varchar NOT NULL)");
        db.execSQL("insert into tb_bloc(titulo, descripcion, autor)values( 'Cosas que hacer','Trabajdar en el modulo de comportamiento laboral','Byron')");
    }
}
