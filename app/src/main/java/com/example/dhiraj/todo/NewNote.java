package com.example.dhiraj.todo;

import android.content.Intent;
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

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.Calendar;

public class NewNote extends AppCompatActivity {
    Toolbar toolbar;
    EditText todo;
    Calendar calendar;
    int hours,mins;
    TextView edit;
    Spinner priority;
    String tempEdited;
    private String [] typeString = { "HIGH" , "MEDIUM" , "LOW" };
    String todoId;
    ParseObject todoOb;

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
        if(hours < 10 || mins < 10){

            if (hours < 10 && mins < 10){
                tempEdited = "Edited " + "0" +hours + ":" + "0" + mins;
                edit.setText(tempEdited);
            }
            else if (hours < 10){
                tempEdited = "Edited " + "0" + hours + ":" + mins;
                edit.setText(tempEdited);
            }
            else if (mins < 10){
                tempEdited = "Edited " + hours + ":" + "0" + mins;
                edit.setText(tempEdited);
            }
        }
        else {
            tempEdited = "Edited " + hours + ":" + mins;
            edit.setText(tempEdited);
        }
        priority = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapterType = new ArrayAdapter<>(getBaseContext(),android.R.layout.simple_spinner_dropdown_item,typeString);
        adapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        priority.setAdapter(adapterType);
        if (getIntent().hasExtra("ID")) {
            todoId = getIntent().getExtras().getString("ID");
            ParseQuery<ParseObject> query = ParseQuery.getQuery("Todo");
            try {
                todoOb=query.get(todoId);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            todo.setText(todoOb.getString("content"));
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        saveNote();
    }

    public void saveNote(){
        String t = todo.getText().toString();
        if(t.equals("")){
            Toast.makeText(getBaseContext(),"Empty todo discarded",Toast.LENGTH_SHORT).show();
        }
        else if(todoId==null){
            final ParseUser currentUser = ParseUser.getCurrentUser();
            ParseObject todoObject = new ParseObject("Todo");
            todoObject.put("content",todo.getText().toString());
            todoObject.put("Priority",priority.getSelectedItem().toString());
            todoObject.put("author",currentUser);
            todoObject.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    Intent intent = new Intent(getBaseContext(),MainActivity.class);
                    startActivity(intent);
                }
            });
            Toast.makeText(getBaseContext(),"Todo saved",Toast.LENGTH_SHORT).show();
        }
        else {
            todoOb.put("content",todo.getText().toString());
            todoOb.put("Priority",priority.getSelectedItem().toString());
            todoOb.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    Intent intent = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(intent);
                }
            });
            Toast.makeText(getBaseContext(),"Todo saved",Toast.LENGTH_SHORT).show();
        }
    }

}