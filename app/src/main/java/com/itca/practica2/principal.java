package com.itca.practica2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class principal extends AppCompatActivity {
    private EditText ettitulo, etdescripcion, etautor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        ettitulo = findViewById(R.id.ettitulo);
        etdescripcion = findViewById(R.id.etdescripcion);
        etautor =  findViewById(R.id.etautor);
    }

    public void guardarnota(View view) {

        try {
            ConexionSQLite admin = new ConexionSQLite(this);
            SQLiteDatabase db = admin.getWritableDatabase();

            String titulo = ettitulo.getText().toString();
            String descripcion = etdescripcion.getText().toString();
            String autor = etautor.getText().toString();

            ContentValues registro = new ContentValues();
            registro.put("id", (Integer) null);
            registro.put("titulo", titulo);
            registro.put("descripcion", descripcion);
            registro.put("autor", autor);

            int result = (int) db.insert("tb_bloc", null, registro);
            db.close();

            if (result > 0) {
                Toast.makeText(this, "Se guardo el registro", Toast.LENGTH_SHORT).show();
                ettitulo.setText(" ");
                etdescripcion.setText(" ");
                etautor.setText(" ");
            } else {
                Toast.makeText(this, "No Se guardo el registro", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            String msg = e.toString();
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }

    }



    public void notas(View view) {
        Intent i = new Intent(principal.this, MainActivity2.class);
        startActivity(i);
    }
}