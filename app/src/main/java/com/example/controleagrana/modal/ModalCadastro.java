package com.example.controleagrana.modal;

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
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState){
        super.onCreateView(inflater, container, saveInstanceState);
        Log.i("Script", "onCreate()");

        View view = inflater.inflate(R.layout.modal_add_user, container, false);
        FloatingActionButton btExit = view.findViewById(R.id.exitModal);
        btExit.setOnClickListener(view1 -> dismiss());

        inputNome = view.findViewById(R.id.edit_name);
        inputCPF = view.findViewById(R.id.edit_cpf);
        inputSalario = view.findViewById(R.id.edit_salary);

        Button btCadastrar = view.findViewById(R.id.btcadastrar);
        btCadastrar.setOnClickListener(view12 -> {
            if(!inputNome.getText().toString().matches("") &&
                    !inputCPF.getText().toString().matches("") &&
                    !inputSalario.getText().toString().matches("") &&
                    inputSalario.getText().toString().matches("^\\d+.?\\d{1,2}$")
            ) {
                ((MainActivity) requireActivity()).addUserName(inputNome.getText().toString());
                ((MainActivity) requireActivity()).addUserObject(inputNome.getText().toString(),
                        inputCPF.getText().toString(),
                        Float.parseFloat(inputSalario.getText().toString()));

                dismiss();
            }else{
                Toast.makeText(view12.getContext(), "Coloque todos os dados!", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }


    @Override
    public void onCancel(@NonNull DialogInterface dialog) {
        super.onCancel(dialog);
    }

}
