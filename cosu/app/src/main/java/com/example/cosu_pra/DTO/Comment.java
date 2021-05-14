package com.example.cosu_pra.DTO;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Comment {
    private String writer;
    private String date;
    private String content;

    public Comment(){}

    public Comment(String user, String content){
        writer = user;
        this.content = content;
        date = new SimpleDateFormat("yyyy/ MM / dd / HH:mm:ss").
                format(Calendar.getInstance().getTime());
    }

    // getter
    public String getWriter(){
        return writer;
    }

    public String getDate(){
        return date;
    }

    public String getContent() {
        return content;
    }

    // setter
    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }
}
