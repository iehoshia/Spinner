package com.holamundo.app.holamundo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;


public class CycleActivity extends AppCompatActivity {

    ListView lDatos;
    public ArrayList<Integer> valores;
    ArrayAdapter aLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cycle);
        lDatos=(ListView)findViewById(R.id.listView);
        valores=new ArrayList<>();
        aLista=new ArrayAdapter(this, android.R.layout.simple_list_item_1, valores);
        lDatos.setAdapter(aLista);


    }
    public boolean esPrimo (int num)
    {
        boolean es_primo=true;
        for (int i=2; i<num;i++)
        {
            if(num % i==0)
            {
                es_primo=false;
                break;

            }
        }
        return es_primo;
    }

    public void mostrarNumeros(View view){


        EditText etNumero;
        etNumero=(EditText)findViewById(R.id.editText10);

        String sNumero;
        sNumero=etNumero.getText().toString();

        int iNum;
        iNum=Integer.parseInt(sNumero);

        ArrayList<String>valores=new ArrayList<>();


        for (int i=2;i<iNum;i++){
            boolean es_primo=esPrimo(i);
            if (es_primo){


                String sNu;
                sNu=String.valueOf(i);

                valores.add(sNu);


                //Toast.makeText(this,String.valueOf(i), Toast.LENGTH_SHORT).show();
            }
        }

        ArrayAdapter aLista=new ArrayAdapter(this, android.R.layout.simple_list_item_1, valores);
        lDatos.setAdapter(aLista);

    }

    public void mostrarTablas(View view)
    {
        EditText etNumeroTablas;
        etNumeroTablas=(EditText)findViewById(R.id.editText10);

        String sNumeroTablas;
        sNumeroTablas=etNumeroTablas.getText().toString();

        int iNum;
        iNum=Integer.parseInt(sNumeroTablas);

        ArrayList<String>valores=new ArrayList<>();
        for(int i=0; i<10; i++){
            int res=i*iNum;

            String sNu;
            sNu=String.valueOf(res);

            String rResultado=String.valueOf(i)+"x"+iNum+"="+res;
            valores.add(rResultado);

            Toast.makeText(this,rResultado, Toast.LENGTH_SHORT).show();


        }
        ArrayAdapter aLista=new ArrayAdapter(this, android.R.layout.simple_list_item_1, valores);
        lDatos.setAdapter(aLista);

    }

    public  void mostrarIngresar(View view){
        EditText etNumeroIngresar;
        etNumeroIngresar=(EditText)findViewById(R.id.editText10);
        int sNumeroIngresar;


        sNumeroIngresar=Integer.parseInt(etNumeroIngresar.getText().toString());
        valores.add(sNumeroIngresar);
        Collections.sort(valores);
        aLista.notifyDataSetChanged();

    }

    public  void mostrarUsuarioActivity(View view){
        Intent in=new Intent(CycleActivity.this,UsuarioActivity.class);
        startActivity(in);
    }


}