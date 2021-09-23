package com.itca.practica2;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        listView = (ListView)findViewById(R.id.rview);

        ArrayList <String> ranking = new ArrayList<>();

        ConexionSQLite admin = new ConexionSQLite(this, "EvalBlocNotas", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        Cursor fila = bd.rawQuery("select titulo from tb_bloc", null);
        if(fila.moveToFirst()) {
            do {
                ranking.add(fila.getString(0) + " - " + fila.getString(1));
            } while (fila.moveToNext());
        }
        bd.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        listView.setAdapter(adapter);


    }
}