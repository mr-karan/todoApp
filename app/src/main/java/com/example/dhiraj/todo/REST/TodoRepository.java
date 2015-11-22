package com.example.dhiraj.todo.REST;

import com.strongloop.android.loopback.ModelRepository;

/**
 * Created by dhiraj on 22/11/15.
 */
public class TodoRepository extends ModelRepository<Todo>{

    public TodoRepository() {
        super("Todo",Todo.class);
    }
}
