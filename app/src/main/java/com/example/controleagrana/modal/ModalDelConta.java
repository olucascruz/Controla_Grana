package com.example.controleagrana.modal;

import android.content.DialogInterface;
import android.os.Bundle;
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
import com.example.controleagrana.activities.UsuarioActivity;
import com.example.controleagrana.usuarios.Usuario;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ModalDelConta extends DialogFragment {

    private EditText cod;
    private Usuario user;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.modal_del_conta, container, false);

        FloatingActionButton btExit = (FloatingActionButton) view.findViewById(R.id.exitModalDelConta);
        btExit.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view){
                dismiss();

            }
        });

        user = ((UsuarioActivity)getActivity()).getUser();


        cod = view.findViewById(R.id.editCodDel);
        Button btDeletar = (Button) view.findViewById(R.id.btDeletar);
        btDeletar.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view){
                if(user.DelConta(Integer.parseInt(cod.getText().toString()))){
                    Toast.makeText(view.getContext(), "Conta deletada", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(view.getContext(), "Conta não encontrada", Toast.LENGTH_SHORT).show();
                }

                dismiss();
            }
        });

        return view;
    }

    @Override
    public void onCancel(@NonNull DialogInterface dialog) {
        super.onCancel(dialog);
    }
}
