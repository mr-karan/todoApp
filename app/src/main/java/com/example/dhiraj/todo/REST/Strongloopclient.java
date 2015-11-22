package com.example.dhiraj.todo.REST;
import android.content.Context;

import com.strongloop.android.loopback.RestAdapter;
import com.strongloop.android.remoting.adapters.RestContractItem;

public class Strongloopclient {

    private Context context;
    private RestAdapter adapter;

    public Strongloopclient(Context contxt) {
        context = contxt;
    }

    public RestAdapter getLoopBackAdapter(String transaction, String operation) {
        if (adapter == null) {
            adapter = new RestAdapter(context, "https://tagdoapi.herokuapp.com/api");
            adapter.getContract().addItem(
                    new RestContractItem("/" + transaction, operation),
                    transaction);
        }
        return adapter;
    }
}