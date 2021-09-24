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
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
    public ListView list;
    public TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        list = findViewById(R.id.notesList);
        text = findViewById(R.id.texView);

        ArrayList<String> valor = new ArrayList<>();


        ConexionSQLite admin = new ConexionSQLite(this);
        SQLiteDatabase db = admin.getWritableDatabase();

        Cursor fila = db.rawQuery("select titulo from tb_bloc", null);
        if (fila.moveToFirst()) {
            do {
                valor.add(fila.getString(0));
            } while (fila.moveToNext());
        }
        db.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, valor);
        list.setAdapter(adapter);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(view.getContext(), MainActivity3.class);
                //String t = valor.get(position);
                String t = (String) list.getItemAtPosition(position);

                intent.putExtra("valorTitle", t);
                startActivity(intent);
            }
        });

    }

    public void regresar(View view) {
        Intent i = new Intent(MainActivity2.this, principal.class);
        startActivity(i);
    }
}