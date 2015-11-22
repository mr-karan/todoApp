package com.example.dhiraj.todo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.dhiraj.todo.REST.Strongloopclient;
import com.example.dhiraj.todo.REST.Todo;
import com.example.dhiraj.todo.REST.TodoRepository;
import com.google.common.collect.ImmutableMap;
import com.strongloop.android.loopback.Model;
import com.strongloop.android.loopback.ModelRepository;
import com.strongloop.android.loopback.RestAdapter;
import com.strongloop.android.loopback.callbacks.ListCallback;
import com.strongloop.android.loopback.callbacks.ObjectCallback;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    FloatingActionButton fab;
    List<Todo> allTodos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            RestAdapter adapter  = new RestAdapter(getBaseContext(),"http://tagdoapi.herokuapp.com/api");
            ModelRepository repo;
            try {
                repo = adapter.createRepository(TodoRepository.class);
                repo.findAll(new ListCallback() {
                    @Override
                    public void onSuccess(List objects) {

                    }

                    @Override
                    public void onError(Throwable t) {

                    }
                });
            }
            catch (Exception e) {
                Toast.makeText(getBaseContext(),"Repo fail",Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e) {
            Toast.makeText(getBaseContext(),"Adapter fail",Toast.LENGTH_SHORT).show();
        }
        toolbar = (Toolbar) findViewById(R.id.toolbarMain);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);




        fab = (FloatingActionButton) findViewById(R.id.addFAB);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create a bundle of all the contents in the note and pass it with the intent
                Intent intent = new Intent(getBaseContext(), NewNote.class);
                startActivity(intent);
            }
        });
    }

}
