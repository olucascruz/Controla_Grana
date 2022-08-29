package com.example.controleagrana.modal;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.controleagrana.R;
import com.example.controleagrana.activities.UsuarioActivity;
import com.example.controleagrana.contas.Conta;
import com.example.controleagrana.usuarios.Usuario;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;

public class ModalRelatorio extends DialogFragment {

    private ListView listContas;
    private Usuario user;
    private ArrayList<String> contaObject = new ArrayList<String>();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.modal_relatorio, container, false);

        FloatingActionButton btExit = (FloatingActionButton) view.findViewById(R.id.exitModalRelatorio);
        btExit.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view){
                dismiss();

            }
        });


        user = ((UsuarioActivity)getActivity()).getUser();
        for(int i = 0; i < user.getQntContas(); i++){
           Conta conta = user.getConta(i);
            String dataFormatada = new SimpleDateFormat("dd/MM/yyyy").format(conta.getValidade());
            String str = "Codigo: "+ conta.getCodigo() +
                   "\n"+ "Descrição: "+ conta.getDescricao()+
                   "\n"+ "Valor: "+ conta.getValor()+
                   "\n"+ "Validade: " + dataFormatada + "\n\n";
           contaObject.add(str);
        }

        listContas = (ListView) view.findViewById(R.id.listContas);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                view.getContext(),
                android.R.layout.simple_list_item_activated_1,
                android.R.id.text1, contaObject

        );

        listContas.setAdapter(adapter);

        return view;
    }


    @Override
    public void onCancel(@NonNull DialogInterface dialog) {
        super.onCancel(dialog);
    }
}
