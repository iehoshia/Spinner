package com.holamundo.app.holamundo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void mostrarDatos(View view){

        EditText etNombre;
        etNombre=(EditText) findViewById(R.id.editText);

        String sNombre;
        sNombre=etNombre.getText().toString();
        //Toast.makeText(this, sNombre, Toast.LENGTH_SHORT).show();

        EditText etApellido;
        etApellido=(EditText)findViewById(R.id.editText2);

        String sApellido;
        sApellido=etApellido.getText().toString();
        Toast.makeText(this,sNombre+sApellido,Toast.LENGTH_SHORT).show();

        EditText etDireccion,etTelefono,etEmail;
        etDireccion=(EditText) findViewById(R.id.editText3);
        etTelefono=(EditText) findViewById(R.id.editText4);
        etEmail=(EditText) findViewById(R.id.editText5);

        String sDireccion,sTelefono,sEmail;
        sDireccion=etDireccion.getText().toString();
        sTelefono=etTelefono.getText().toString();
        sEmail=etEmail.getText().toString();

        Toast.makeText(this,sDireccion+sTelefono+sEmail,Toast.LENGTH_SHORT).show();

        EditText etContraseña,etContraseñaN;
        etContraseña=(EditText)findViewById( R.id.editText6);
        etContraseñaN=(EditText)findViewById( R.id.editText7);

        String sContraseña,sContraseñaN;
        sContraseña=etContraseña.getText().toString();
        sContraseñaN=etContraseñaN.getText().toString();

        Toast.makeText(this,sContraseña+sContraseñaN,Toast.LENGTH_SHORT).show();

    }

    public void mostrarCiclos(View view) {

        Intent in= new Intent(MainActivity.this,CycleActivity.class);
        startActivity(in);


    }

    public void mostrarEstudents(View view) {

        Intent in= new Intent(MainActivity.this,StudentActivity.class);
        startActivity(in);


    }

}
