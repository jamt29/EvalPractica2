package com.itca.practica2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

        ArrayList <String> rank = new ArrayList<>();

        ConexionSQLite admin = new ConexionSQLite(this);
        SQLiteDatabase bd = admin.getWritableDatabase();

        Cursor fila = bd.rawQuery("select titulo from tb_bloc", null);
        if(fila.moveToFirst()) {
            do {
                rank.add(fila.getString(0) + " - " + fila.getString(1));
            } while (fila.moveToNext());
        }
        bd.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, rank);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(view.getContext(), MainActivity3.class);
                //String t = valor.get(position);
                String t = (String) listView.getItemAtPosition(position);

                intent.putExtra("valorTitle", t);
                startActivity(intent);
            }
        });


    }
}