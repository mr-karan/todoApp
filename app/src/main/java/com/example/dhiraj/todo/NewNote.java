package com.example.dhiraj.todo;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dhiraj.todo.REST.Todo;
import com.example.dhiraj.todo.REST.TodoRepository;
import com.google.common.collect.ImmutableMap;
import com.strongloop.android.loopback.RestAdapter;
import com.strongloop.android.loopback.callbacks.VoidCallback;

import java.math.BigInteger;

public class NewNote extends AppCompatActivity {
    Toolbar toolbar;
    EditText todo;
    Todo object;
    RestAdapter adapter ;
    TodoRepository repository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        todo = (EditText) findViewById(R.id.content);
        adapter= new RestAdapter(getApplicationContext(), "http://tagdoapi.herokuapp.com/api");
        repository = adapter.createRepository(TodoRepository.class);
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
                    object =repository.createObject(ImmutableMap.of("content", todo.getText().toString(),"uID",new BigInteger("43")));
                    object.save(new VoidCallback() {
                        @Override
                        public void onSuccess() {
                            Toast.makeText(getBaseContext(),"Todo kicked into cloud",Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onError(Throwable t) {
                            Toast.makeText(getBaseContext(),"Launch failed.",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}