package com.example.controleagrana.modal;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

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

public class ModalRelatorio extends DialogFragment {
    @SuppressWarnings("FieldCanBeLocal")
    private ListView listContas;
    @SuppressWarnings("FieldCanBeLocal")
    private Usuario user;
    @SuppressWarnings("FieldCanBeLocal")
    private final ArrayList<String> contaObject = new ArrayList<>();
    @SuppressWarnings("FieldCanBeLocal")
    private TextView total;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.modal_relatorio, container, false);

        FloatingActionButton btExit = view.findViewById(R.id.exitModalRelatorio);
        btExit.setOnClickListener(view1 -> dismiss());


        user = ((UsuarioActivity)requireActivity()).getUser();
        for(int i = 0; i < user.getQntContas(); i++){
           Conta conta = user.getConta(i);
            @SuppressLint("SimpleDateFormat") String dataFormatada = new SimpleDateFormat("dd/MM/yyyy").format(conta.getValidade());
            String str = "Codigo: "+ conta.getCodigo() +
                   "\n"+ "Descrição: "+ conta.getDescricao()+
                   "\n"+ "Valor: "+ conta.getValor()+
                   "\n"+ "Validade: " + dataFormatada + "\n\n";
           contaObject.add(str);
        }

        listContas = view.findViewById(R.id.listContas);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                view.getContext(),
                android.R.layout.simple_list_item_activated_1,
                android.R.id.text1, contaObject

        );

        listContas.setAdapter(adapter);
        total = view.findViewById(R.id.RelatorioTotal);
        float totalFloat = ((UsuarioActivity) requireActivity()).getDespesasValue();
        total.setText("Total: R$"+totalFloat);
        return view;
    }


    @Override
    public void onCancel(@NonNull DialogInterface dialog) {
        super.onCancel(dialog);
    }
}
