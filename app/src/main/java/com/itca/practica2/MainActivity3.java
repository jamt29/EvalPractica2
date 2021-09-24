package com.itca.practica2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {
    public String x;
    public EditText v;
    public EditText titulo, desc, autor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        titulo = findViewById(R.id.ettitulo);
        desc = findViewById(R.id.etdescripcion);
        autor = findViewById(R.id.etautor);

        Bundle bundle = new Bundle();

        String dato = getIntent().getStringExtra("valorTitle");

        v = findViewById(R.id.ettitulo);
        v.setText(dato);

        x = v.getText().toString();


        try {

            ConexionSQLite admin = new ConexionSQLite(this);
            SQLiteDatabase db = admin.getWritableDatabase();

            Cursor fila = db.rawQuery("select descripcion, autor from tb_bloc where titulo = '" + x + "'"  , null);
            if(fila.moveToFirst()) {
                desc.setText(fila.getString(0));
                autor.setText(fila.getString(1));
            }


        } catch (Exception e) {
            Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show();
        }


    }



    public void delete(View view) {
        ConexionSQLite admin = new ConexionSQLite(this);
        SQLiteDatabase db = admin.getWritableDatabase();
        String t = titulo.getText().toString();
        int cant = db.delete("tb_bloc", "titulo = '" + x + "'", null);
        db.close();
        titulo.setText("");
        desc.setText("");
        autor.setText("");

        Toast.makeText(this, "Su nota a sido eliminada!", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(MainActivity3.this, principal.class);
        startActivity(i);
    }


    public void update(View view) {
        ConexionSQLite admin = new ConexionSQLite(this);
        SQLiteDatabase db = admin.getWritableDatabase();
        String t = titulo.getText().toString();
        String d = desc.getText().toString();
        String a = autor.getText().toString();


        ContentValues registro = new ContentValues();
        registro.put("titulo", t);
        registro.put("descripcion", d);
        registro.put("autor", a);

        int cant = db.update("tb_bloc", registro, "titulo = '" + x + "'", null);
        db.close();
        if (cant == 1) {
            Toast.makeText(this, "Su nota a sido actualizada", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(MainActivity3.this, principal.class);
            startActivity(i);
        } else {
            Toast.makeText(this, "Su nota no pudo ser actualizada", Toast.LENGTH_SHORT).show();
        }
    }
}