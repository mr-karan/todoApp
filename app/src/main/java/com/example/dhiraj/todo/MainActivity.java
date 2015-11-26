package com.example.dhiraj.todo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

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
    HashMap<String,ParseObject> map = new HashMap();
    CardListView listView;

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
        final ParseUser currentUser = ParseUser.getCurrentUser();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Todo");
        query.whereEqualTo("author",currentUser);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    for (ParseObject object : objects) {
                        map.put(object.getObjectId(), object);
                        card = new Card(MainActivity.this);
                        card.setTitle(object.getString("content") + " With " + object.getString("Priority") + " Priority");
                        card.setId(object.getObjectId());
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
                                map.get(card.getId()).deleteEventually();
                                Toast.makeText(getBaseContext(), "Why you kill me?", Toast.LENGTH_SHORT).show();
                            }
                        });
                        header = new CardHeader(MainActivity.this);
                        cards.add(card);
                    }
                    myArrayAdapter = new CardArrayAdapter(MainActivity.this, cards);
                    listView = (CardListView) findViewById(R.id.myList);
                    if (listView != null)
                        listView.setAdapter(myArrayAdapter);
                } else {
                    Toast.makeText(getBaseContext(), "Server \"get\" error", Toast.LENGTH_SHORT).show();
                }
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


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }

}
