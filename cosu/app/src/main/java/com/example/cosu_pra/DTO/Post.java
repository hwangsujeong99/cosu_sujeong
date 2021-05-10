package com.example.cosu_pra.DTO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class Post {
    private String title;
    private String writer;
    private String date;
    private String content;
    private Category category;
    private List<Comment> comments;

    public Post() {
        date = new SimpleDateFormat("yyyy/ MM / dd / HH:mm:ss").
                format(Calendar.getInstance().getTime());
        comments = new ArrayList<Comment>();
    }

    public Post(String title, String writer, String content, Category category) {
        this(writer, content, category);
        this.title = title;
    }

    public Post(String writer, String content, Category category) {
        this();
        this.writer = writer;
        this.content = content;
        this.category = category;

    }


    // getter
    public String getWriter() {
        return writer;
    }

    public String getDate() {
        return date;
    }

    public String getContent() {
        return content;
    }

    public Category getCategory(){
        return category;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public String getTitle() {
        return title;
    }

    // setter
    public void setTitle(String title) {
        this.title = title;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
