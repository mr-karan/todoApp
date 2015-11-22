package com.example.dhiraj.todo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.dhiraj.todo.REST.Todo;
import com.example.dhiraj.todo.REST.TodoRepository;
import com.strongloop.android.loopback.ModelRepository;
import com.strongloop.android.loopback.RestAdapter;
import com.strongloop.android.loopback.callbacks.ListCallback;
import com.strongloop.android.loopback.callbacks.VoidCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.view.CardListView;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    FloatingActionButton fab;

    ArrayList<Card> cards;
    Card card;
    CardHeader header;
    CardArrayAdapter myArrayAdapter;
    CardListView listView;
    HashMap<Integer,Todo> map = new HashMap();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //working with the inflation
        toolbar = (Toolbar) findViewById(R.id.toolbarMain);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        fab = (FloatingActionButton) findViewById(R.id.addFAB);

        //working with the cards
        cards = new ArrayList<>();

        //working with objects
        RestAdapter adapter  = new RestAdapter(getBaseContext(),"http://tagdoapi.herokuapp.com/api");
        TodoRepository repo;
        repo = adapter.createRepository(TodoRepository.class);
        //repo.findAll(new ListCallback<Todo>() {
        repo.filter(43,new ListCallback<Todo>() {
            @Override
            public void onSuccess(List<Todo> objects) {
                for(Todo object : objects){
                    map.put((Integer) object.getId(),object);
                    card = new Card(MainActivity.this);
                    card.setTitle(object.getContent());
                    card.setId(Integer.toString((Integer) object.getId()));
                    card.setClickable(true);
                    card.setOnClickListener(new Card.OnCardClickListener() {
                        @Override
                        public void onClick(Card card, View view) {
                            Toast.makeText(getBaseContext(), card.getId(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    card.setSwipeable(true);
                    card.setOnSwipeListener(new Card.OnSwipeListener() {
                        @Override
                        public void onSwipe(Card card) {
                            map.get(Integer.parseInt(card.getId())).destroy(new VoidCallback() {
                                @Override
                                public void onSuccess() {
                                    Toast.makeText(getBaseContext(), "Why you kiill me!", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onError(Throwable t) {
                                    Toast.makeText(getBaseContext(), "I am not going anywhere fucker", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                    header = new CardHeader(MainActivity.this);
                    cards.add(card);
                }

                myArrayAdapter = new CardArrayAdapter(MainActivity.this,cards);
                listView = (CardListView) findViewById(R.id.myList);
                if(listView != null)
                    listView.setAdapter(myArrayAdapter);
            }
            @Override
            public void onError(Throwable t) {

            }
        });



        //FAB onclick listener
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
