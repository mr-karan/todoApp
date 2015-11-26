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
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

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
                ParseUser user = new ParseUser();
                user.setUsername(name.getText().toString());
                user.setPassword(password.getText().toString());
                user.setEmail(email.getText().toString());

                // other fields can be set just like with ParseObject
                //user.put();
                ParseUser currentUser = ParseUser.getCurrentUser();
                currentUser.logOut();
                user.signUpInBackground(new SignUpCallback() {
                    public void done(ParseException e) {
                        if (e == null) {
                            // Hooray! Let them use the app now.
                            Toast.makeText(getActivity(),"Registration Succesful",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            startActivity(intent);

                        } else {
                            Toast.makeText(getActivity(),e.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                            // Sign up didn't succeed. Look at the ParseException
                            // to figure out what went wrong
                            //Toast
                        }
                    }
                });
            }
        });
        return view;
    }
}
