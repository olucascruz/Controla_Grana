package com.example.controleagrana.modal;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import com.example.controleagrana.R;
import com.example.controleagrana.activities.UsuarioActivity;
import com.example.controleagrana.contas.Conta;
import com.example.controleagrana.usuarios.Usuario;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



public class ModalAddConta extends DialogFragment {
    private EditText inputCod;
    private EditText inputDescricao;
    private EditText inputValor;
    private EditText inputValidade;
    private Usuario user;
    String _cod;
    String _descr;
    String _valor;
    Date _validade = null;
    String _validadeString;


    @Override
    public void onCreate(Bundle savedStanceState) {
        super.onCreate(savedStanceState);

        Log.i("Script", "onCreate()");
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState){
        super.onCreateView(inflater, container, saveInstanceState);
        Log.i("Script", "onCreate()");

        View view = inflater.inflate(R.layout.modal_add_conta, container, false);
        FloatingActionButton btExit = view.findViewById(R.id.exitModalConta);
        btExit.setOnClickListener(view1 -> dismiss());

        inputCod = view.findViewById(R.id.edit_cod);
        inputDescricao = view.findViewById(R.id.edit_descricao);
        inputValor = view.findViewById(R.id.edit_valor);
        inputValidade = view.findViewById(R.id.edit_validade);

        String cadastro_or_edit = ((UsuarioActivity) requireActivity()).getCadOrEdit();
        TextView title_cad_or_edit = view.findViewById(R.id.CadOrEdit);
        @SuppressLint("CutPasteId") Button bt_cad_or_edit = view.findViewById(R.id.btcadoredit);
        if(cadastro_or_edit.matches("Cadastrar")){
            title_cad_or_edit.setText("Cadastrar Conta");
            bt_cad_or_edit.setText("Cadastrar");
        }else{
            title_cad_or_edit.setText("Editar Conta");
            bt_cad_or_edit.setText("Editar");
        }

        @SuppressLint("CutPasteId") Button btCadastrar = view.findViewById(R.id.btcadoredit);
        btCadastrar.setOnClickListener(view12 -> {
            user = ((UsuarioActivity) requireActivity()).getUser();

            if(valideDados()){
                if(cadastro_or_edit.matches("Cadastrar")) {
                    user.setConta(Integer.parseInt(_cod), Float.parseFloat(_valor), _descr, _validade);

                }else{
                   Conta EditConta =((UsuarioActivity) requireActivity()).getEditUser();
                   EditConta.setCodigo(Integer.parseInt(_cod));
                   EditConta.setDescricao(_descr);
                   EditConta.setValor(Float.parseFloat(_valor));
                   EditConta.setValidade(_validade);
                   ((UsuarioActivity) requireActivity()).setCadOrEdit("Cadastrar");

                }
                dismiss();


                float numberDespesas = 0;

                for (int i = 0; i < user.getQntContas(); i++) {
                    numberDespesas += user.getConta(i).getValor();
                }
                ((UsuarioActivity) requireActivity()).setDespesas(numberDespesas);
                ((UsuarioActivity) requireActivity()).setRestante(user.getSalario() - numberDespesas);
                ((UsuarioActivity) requireActivity()).setAviso(numberDespesas > user.getSalario());

            }else{
                Toast.makeText(view12.getContext(), "Coloque todos os dados!", Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }

    private boolean valideDados() {
        boolean retorno = false;
        _cod = inputCod.getText().toString();
        _descr = inputDescricao.getText().toString();
        _valor = inputValor.getText().toString();
        _validadeString = inputValidade.getText().toString();

        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
        formatter.setLenient(false);
        try {
            _validade = formatter.parse(_validadeString);
        } catch (ParseException e) {
            _validadeString = "";
            e.printStackTrace();
        }
        if(!_cod.matches("")){
            retorno = true;
        }else{
            inputCod.setError("*");
            inputCod.requestFocus();
            return false;
        }
        if(!_valor.matches("") && _valor.matches("^\\d+.?\\d{1,2}$")){
            retorno = true;
        }else{
            inputValor.setError("*");
            inputValor.requestFocus();
            return false;
        }
        if(!_descr.matches("")){
            retorno = true;
        }else{
            inputDescricao.setError("*");
            inputDescricao.requestFocus();
            return false;
        }
        if(!_validadeString.matches("") && _validadeString.length() == 8){
            retorno = true;
        }else{
            inputValidade.setError("*");
            inputValidade.requestFocus();
            return false;
        }

        return retorno;
    }


    @Override
    public void onCancel(@NonNull DialogInterface dialog) {
        super.onCancel(dialog);
    }

}
