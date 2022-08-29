package com.example.controleagrana.modal;

import android.content.DialogInterface;
import android.os.Bundle;
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
import com.example.controleagrana.usuarios.Usuario;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

public class ModalBuscarConta extends DialogFragment {

    private EditText cod;
    private Usuario user;
    private String DeleteOrString;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.modal_buscar_conta, container, false);

        FloatingActionButton btExit = (FloatingActionButton) view.findViewById(R.id.exitModalDelConta);
        btExit.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view){
                dismiss();

            }
        });

        user = ((UsuarioActivity)getActivity()).getUser();
        DeleteOrString = ((UsuarioActivity)getActivity()).DeleteOrEdit();

        TextView textDelOrEdit = view.findViewById(R.id.textDelOrEdit);
        Button btDelOrEdit = view.findViewById(R.id.btDelOrEdit);
        if(DeleteOrString.matches("Delete")){
            textDelOrEdit.setText("Delete uma conta");
            btDelOrEdit.setText("Deletar");
        }else{
            textDelOrEdit.setText("Edite uma conta");
            btDelOrEdit.setText("Editar");

        }
        cod = view.findViewById(R.id.editCodDel);

        btDelOrEdit.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view){
                if(!cod.getText().toString().matches("")) {
                    if(DeleteOrString.matches("Delete")){

                        if (user.DelConta(Integer.parseInt(cod.getText().toString()))) {
                            Toast.makeText(view.getContext(), "Conta deletada", Toast.LENGTH_SHORT).show();
                            dismiss();
                        } else {
                            Toast.makeText(view.getContext(), "Conta não encontrada", Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        ((UsuarioActivity)getActivity()).setCadOrEdit("Editar");
                        ((UsuarioActivity) requireActivity()).setEditUser(user.getContaCad(Integer.parseInt(cod.getText().toString())));
                        ((UsuarioActivity)getActivity()).openModalAddConta();
                        dismiss();
                    }
                }

                else{
                    Toast.makeText(view.getContext(), "Coloque um código.", Toast.LENGTH_SHORT).show();
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
