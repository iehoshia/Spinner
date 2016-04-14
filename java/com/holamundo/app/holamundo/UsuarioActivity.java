package com.holamundo.app.holamundo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.view.View;

import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

public class UsuarioActivity extends AppCompatActivity {
    ListView lDatos;
    public ArrayList<String> valores;
    ArrayAdapter aLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);
        lDatos=(ListView)findViewById(R.id.listView2);
        valores=new ArrayList<>();
        aLista=new ArrayAdapter(this, android.R.layout.simple_list_item_1, valores);
        lDatos.setAdapter(aLista);
    }
    public  void mostrarPersona(View view){

        EditText etNombre;
        etNombre=(EditText)findViewById(R.id.editText11);
        String sNombre;
        sNombre=etNombre.getText().toString();

        EditText etApellido;
        etApellido=(EditText)findViewById(R.id.editText12);
        String sApellido;
        sApellido=etApellido.getText().toString();

        String sCompleto;
        sCompleto=sNombre+sApellido;

        valores.add(sCompleto);
        Collections.sort(valores);
        aLista.notifyDataSetChanged();

    }



}
