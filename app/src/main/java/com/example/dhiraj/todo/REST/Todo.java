package com.example.dhiraj.todo.REST;

import com.strongloop.android.loopback.Model;

/**
 * Created by dhiraj on 22/11/15.
 */
public class Todo extends Model {
    private String content;
    private int id;
    private int uID;

    public void setContent(String content) {
        this.content = content;
    }


    public int getid() {
        return id;
    }

    public String getContent() {
        return content;
    }
}