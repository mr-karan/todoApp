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
import com.strongloop.android.loopback.RestAdapter;
import com.strongloop.android.loopback.callbacks.ListCallback;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    FloatingActionButton fab;
    List<Todo> allTodos;
    Strongloopclient strc = new Strongloopclient(getBaseContext());
    RestAdapter adapter = strc.getLoopBackAdapter("todos","GET");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            TodoRepository repo = adapter.createRepository(TodoRepository.class);
            repo.findAll(new ListCallback<Todo>() {
                @Override
                public void onSuccess(List<Todo> objects) {
                    System.out.println(objects);
                    Toast.makeText(getBaseContext(), "Worked", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onError(Throwable t) {

                }
            });
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
