package com.example.cosu_pra.DTO;

import android.location.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudyPost extends Post{
    private int max;
    private List<String> users;
    private Map<String,Double> location;

    public StudyPost() {}

    public StudyPost(String title,String writer_id, String contents, Category category, int max_users) {
        super(title, writer_id, contents, category);

        max = max_users;
        users = new ArrayList<String>();
        users.add(writer_id);
    }

    public StudyPost(String title, String writer_id, String contents, Category category,
                     int max_users, Location location) {
        this(title, writer_id, contents, category,max_users);

        this.location = new HashMap<>();
        this.location.put("altitude",location.getAltitude());
        this.location.put("latitude",location.getLatitude());
    }

    public int getMax() {
        return max;
    }

    public List<String> getUsers() {
        return users;
    }

    public Map<String,Double> getLocation(){
        return location;
    }
}
