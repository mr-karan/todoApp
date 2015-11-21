package com.example.dhiraj.todo.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dhiraj.todo.R;

public class RegisterFragment extends android.support.v4.app.Fragment {
    EditText name,email,password;
    TextView registerTitle;
    Button register;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        registerTitle = (TextView) view.findViewById(R.id.title);
        name = (EditText) view.findViewById(R.id.name);
        email = (EditText) view.findViewById(R.id.email);
        password = (EditText) view.findViewById(R.id.password);
        register = (Button) view.findViewById(R.id.btn_register);

        //creating on click listener for the register button
        register.setOnClickListener(new View.OnClickListener() {
            boolean res;

            @Override
            public void onClick(View v) {
                res = validateInput();
                if(res)
                    //push the values to the server here
                    Toast.makeText(getActivity(), "Valid Format", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getActivity(), "Invalid Format", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    private boolean validateInput(){
        boolean result = false;
        if(email.getText().toString().trim() !="" && password.getText().toString().trim() != "" && name.getText().toString().trim() != "")
            result = true;
        return result;
    }
}
