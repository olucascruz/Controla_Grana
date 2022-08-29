package com.example.controleagrana.modal;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
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
import androidx.fragment.app.DialogFragment;

import com.example.controleagrana.R;
import com.example.controleagrana.activities.MainActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ModalCadastro extends DialogFragment {
    private EditText inputNome;
    private EditText inputCPF;
    private EditText inputSalario;



    @Override
    public void onCreate(Bundle savedStanceState) {
        super.onCreate(savedStanceState);

        Log.i("Script", "onCreate()");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState){
        super.onCreateView(inflater, container, saveInstanceState);
        Log.i("Script", "onCreate()");

        View view = inflater.inflate(R.layout.modal_add_user, container, false);
        FloatingActionButton btExit = (FloatingActionButton) view.findViewById(R.id.exitModal);
        btExit.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view){
                dismiss();

            }
        });

        inputNome = (EditText) view.findViewById(R.id.edit_name);
        inputCPF = (EditText) view.findViewById(R.id.edit_cpf);
        inputSalario = (EditText) view.findViewById(R.id.edit_salary);

        Button btCadastrar = (Button) view.findViewById(R.id.btcadastrar);
        btCadastrar.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view){
                if(!inputNome.getText().toString().matches("") &&
                        !inputCPF.getText().toString().matches("") &&
                        !inputSalario.getText().toString().matches("")
                ) {
                    ((MainActivity) getActivity()).addUserName(inputNome.getText().toString());
                    ((MainActivity) getActivity()).addUserObject(inputNome.getText().toString(),
                            inputCPF.getText().toString(),
                            Float.parseFloat(inputSalario.getText().toString()));

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
