package com.example.cosu_pra.DTO;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Comment {
    private String writer;
    private String date;
    private String m_contents;

    public Comment(){}

    public Comment(String user, String content){
        writer = user;
        m_contents = content;
        date = new SimpleDateFormat("yyyy/ MM / dd / HH:mm:ss").
                format(Calendar.getInstance().getTime());
    }

    public String getWriter(){
        return writer;
    }

    public String getDate(){
        return date;
    }

    public String getContents() {
        return m_contents;
    }

}
