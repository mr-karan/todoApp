package com.example.dhiraj.todo.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dhiraj.todo.MainActivity;
import com.example.dhiraj.todo.R;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

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
                Toast.makeText(getActivity(),"Logging in",Toast.LENGTH_SHORT).show();
                //calling the validate input before actually checking for Credentials
                ParseUser currentUser = ParseUser.getCurrentUser();
                currentUser.logOut();
                ParseUser.logInInBackground(emailId.getText().toString(), password.getText().toString(), new LogInCallback() {
                    public void done(ParseUser user, ParseException e) {
                        if (user != null) {
                            Toast.makeText(getActivity(),"Logged in",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getActivity(),e.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                            // Signup failed. Look at the ParseException to see what happened.
                            //Toast
                        }
                    }
                });

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
