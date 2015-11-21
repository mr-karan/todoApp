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

public class LoginFragment extends android.support.v4.app.Fragment {
    EditText emailId,password;
    Button login;
    TextView loginTitle;
    public LoginFragment() {
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

        View view = inflater.inflate(R.layout.fragment_login, container, false);
        loginTitle = (TextView) view.findViewById(R.id.title);
        emailId = (EditText) view.findViewById(R.id.email);
        password = (EditText) view.findViewById(R.id.password);
        login = (Button) view.findViewById(R.id.btn_login);
        //done with the inflation for the fragment

        //creating the onClickListener for the button login
        login.setOnClickListener(new View.OnClickListener() {
            //local variable for validation
            boolean res;
            @Override
            public void onClick(View v) {
                //calling the validate input before actually checking for Credentials
                res = validateInput();
                if(res)
                    //set your check for Credentials here!!
                    Toast.makeText(getActivity(),"Valid Format",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getActivity(), "Invalid Format", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private boolean validateInput(){
        boolean result = false;
        if(emailId.getText().toString().trim() !="" && password.getText().toString().trim() != "") {
            result = true;
        }
        return result;
    }
}
