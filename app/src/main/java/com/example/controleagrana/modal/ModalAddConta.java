package com.example.controleagrana.modal;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;

import com.example.controleagrana.R;
import com.example.controleagrana.activities.MainActivity;
import com.example.controleagrana.activities.UsuarioActivity;
import com.example.controleagrana.usuarios.Usuario;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

public class ModalAddConta extends DialogFragment {
    private EditText inputCod;
    private EditText inputDescricao;
    private EditText inputValor;
    private EditText inputValidade;
    private Usuario user;


    @Override
    public void onCreate(Bundle savedStanceState) {
        super.onCreate(savedStanceState);

        Log.i("Script", "onCreate()");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState){
        super.onCreateView(inflater, container, saveInstanceState);
        Log.i("Script", "onCreate()");

        View view = inflater.inflate(R.layout.modal_add_conta, container, false);
        FloatingActionButton btExit = (FloatingActionButton) view.findViewById(R.id.exitModalConta);
        btExit.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view){
                dismiss();

            }
        });

        inputCod = (EditText) view.findViewById(R.id.edit_cod);
        inputDescricao = (EditText) view.findViewById(R.id.edit_descricao);
        inputValor = (EditText) view.findViewById(R.id.edit_valor);
        inputValidade = (EditText) view.findViewById(R.id.edit_validade);
        Button btCadastrar = (Button) view.findViewById(R.id.btcadastrarconta);
        btCadastrar.setOnClickListener(new Button.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view){
                user = ((UsuarioActivity) getActivity()).getUser();
                String _cod = inputCod.getText().toString();
                String _descr = inputDescricao.getText().toString();
                String _valor = inputValor.getText().toString();
                String _validadeString = inputValidade.getText().toString();

                SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
                Date _validade = null;

                try {
                    _validade = formatter.parse(_validadeString);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if(!_cod.matches("") && !_valor.matches("") && !_descr.matches("") && !_validadeString.matches("")){
                user.setConta(Integer.parseInt(_cod),  Float.parseFloat(_valor), _descr, _validade);

                float numberDespesas = 0;

                for(int i = 0; i < user.getQntContas(); i++){
                    numberDespesas += user.getConta(i).getValor();
                }
                ((UsuarioActivity) requireActivity()).setDespesas(numberDespesas);
                ((UsuarioActivity) requireActivity()).setRestante(user.getSalario()-numberDespesas);


                dismiss();
                }else{
                    Toast.makeText(view.getContext(), "Coloque todos os dados!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        return view;
    }


    @Override
    public void onCancel(@NonNull DialogInterface dialog) {
        super.onCancel(dialog);
    }

}
