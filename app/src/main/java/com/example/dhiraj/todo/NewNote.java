package com.example.dhiraj.todo;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.Calendar;

public class NewNote extends AppCompatActivity {
    Toolbar toolbar;
    EditText todo;
    Calendar calendar;
    int hours,mins;
    TextView edit;
    Spinner priority;
    private String [] typeString = { "High" , "Medium" , "Low" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        todo = (EditText) findViewById(R.id.content);
        calendar = Calendar.getInstance();
        hours = calendar.get(Calendar.HOUR_OF_DAY);
        mins = calendar.get(Calendar.MINUTE);
        edit = (TextView) findViewById(R.id.editTime);
        edit.setText("Edited "+ hours + ":" + mins);
        priority = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapterType = new ArrayAdapter<>(getBaseContext(),android.R.layout.simple_spinner_dropdown_item,typeString);
        adapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        priority.setAdapter(adapterType);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                String t = todo.getText().toString();
                if(t.equals("")){
                    Toast.makeText(getBaseContext(),"Empty todo discarded",Toast.LENGTH_SHORT).show();
                }
                else{
                    final ParseUser currentUser = ParseUser.getCurrentUser();
                    ParseObject todoObject = new ParseObject("Todo");
                    todoObject.put("content",todo.getText().toString());
                    todoObject.put("Priority",priority.getSelectedItem().toString());
                    todoObject.put("author",currentUser);
                    todoObject.saveEventually();
                    Toast.makeText(getBaseContext(),"Todo saved",Toast.LENGTH_SHORT).show();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}