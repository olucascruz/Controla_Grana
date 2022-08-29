package com.example.controleagrana.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.controleagrana.R;
import com.example.controleagrana.modal.ModalCadastro;
import com.example.controleagrana.usuarios.Usuario;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private ListView listUsers;
    private FloatingActionButton btOpenCadastro;
    private ArrayList<Usuario> usersObject = new ArrayList<Usuario>();
    private ArrayList<String> nameUsers = new ArrayList<String>();
    private static Usuario usuarioSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listUsers = findViewById(R.id.listUsers);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_list_item_activated_1,
                android.R.id.text1, nameUsers

        );

        listUsers.setAdapter(adapter);

        listUsers.setOnItemClickListener(new AdapterView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                String nomeSelecionado = listUsers.getItemAtPosition(i).toString();
                usuarioSelecionado = usersObject.get(i);
                startActivity(new Intent(MainActivity.this, UsuarioActivity.class));
                Toast.makeText(getApplicationContext(), nomeSelecionado, Toast.LENGTH_SHORT).show();
            }
        });

        btOpenCadastro = findViewById(R.id.opencadastro);
        btOpenCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ModalCadastro modalCadastro = new ModalCadastro();
                modalCadastro.show(getSupportFragmentManager(), "myModal");
            }
        });


    }


    public void addUserName(String value){
        nameUsers.add(value);
    };

    public void addUserObject(String _nome, String _cpf, float _salario){
        usersObject.add(new Usuario(_nome, _cpf, _salario));
        Toast.makeText(getApplicationContext(), "Usuario criado", Toast.LENGTH_SHORT).show();
    };

    public static Usuario getUsusarioSelecionado(){
        return usuarioSelecionado;
    }
}