package com.itca.practica2;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.itca.practica2.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private EditText ettitulo, etdescripcion, etautor;
    private Cursor fila;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ettitulo = (EditText) findViewById(R.id.ettitulo);
        etdescripcion = (EditText) findViewById(R.id.etdescripcion);
        etautor = (EditText) findViewById(R.id.etautor);
    


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

       /* NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration); */

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, MainActivity2.class );
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void guardarnota(View view) {
        ConexionSQLite admin = new ConexionSQLite(this, "EvalBlocNotas", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String t = ettitulo.getText().toString();
        String d = etdescripcion.getText().toString();
        String a= etautor.getText().toString();

        ContentValues values = new ContentValues();

        values.put("titulo", t);
        values.put("descripcion", d);
        values.put("autor", a);

        if (t.isEmpty()) {
            ettitulo.setError("Campo Obligatorio");

        } else if (d.isEmpty()) {
            etdescripcion.setError("Campo Obligatorio");

        } else if (a.isEmpty()) {
            etautor.setError("Campo Obligatorio");
        } else {

            db.insert("tb_bloc", null, values);
            db.close();

            Toast.makeText(this, "Nota guardada correctamente", Toast.LENGTH_SHORT).show();

            ettitulo.setText(null);
            etdescripcion.setText(null);
            etautor.setText(null);

       }



    }

    public void onClick(View view) {
        Intent i = new Intent(MainActivity.this, MainActivity2.class );
        startActivity(i);
    }



   /* @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    } */
}

//https://github.com/jamt29/MomotoApp/blob/master/app/src/main/java/com/example/momotoapp/newaccount.java