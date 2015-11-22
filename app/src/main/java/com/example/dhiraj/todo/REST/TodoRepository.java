package com.example.dhiraj.todo.REST;

import com.google.common.collect.ImmutableMap;
import com.strongloop.android.loopback.ModelRepository;
import com.strongloop.android.loopback.callbacks.JsonArrayParser;
import com.strongloop.android.loopback.callbacks.ListCallback;

/**
 * Created by dhiraj on 22/11/15.
 */
public class TodoRepository extends ModelRepository<Todo>{

    public TodoRepository() {
        super("Todo","Todos",Todo.class);
    }

    public void filter(int uID, final ListCallback<Todo> callback) {
        invokeStaticMethod("all",
                ImmutableMap.of("filter",
                        ImmutableMap.of("where",
                                ImmutableMap.of("uID", uID))),
                new JsonArrayParser<Todo>(this, callback));
    }
}
