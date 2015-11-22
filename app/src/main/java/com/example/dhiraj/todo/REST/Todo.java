package com.example.dhiraj.todo.REST;

import com.strongloop.android.loopback.Model;

import java.math.BigInteger;

/**
 * Created by dhiraj on 22/11/15.
 */
public class Todo extends Model {
    String content;
    public BigInteger uID;

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public Todo() {
        content = "This is your todo. Click to edit";
        uID = new BigInteger("55");
    }

    public Todo(String todo,int uID) {
        this.content = todo;
        this.uID = new BigInteger(Integer.toString(uID));
    }
}
