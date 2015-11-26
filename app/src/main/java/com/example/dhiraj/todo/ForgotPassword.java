package com.example.dhiraj.todo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;

public class ForgotPassword extends AppCompatActivity {
    android.support.v7.widget.Toolbar toolbar;
    TextView title;
    EditText email;
    Button enter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        email = (EditText) findViewById(R.id.email);
        title = (TextView) findViewById(R.id.title);
        enter = (Button) findViewById(R.id.enter);

        enter.setOnClickListener(new View.OnClickListener() {
            // Do the necessary things for changing the password.
            @Override
            public void onClick(View v) {

                ParseUser.requestPasswordResetInBackground(email.getText().toString(), new RequestPasswordResetCallback() {
                    public void done(ParseException e) {
                        if (e == null) {
                            Toast.makeText(getBaseContext(),"Email with reset instructions has been sent",Toast.LENGTH_SHORT).show();
                            // An email was successfully sent with reset instructions.
                        } else {
                            // Something went wrong. Look at the ParseException to see what's up.
                            Toast.makeText(getBaseContext(),e.getLocalizedMessage(),Toast.LENGTH_SHORT).show();

                        }
                    }
                });

            }
        });
    }

}
