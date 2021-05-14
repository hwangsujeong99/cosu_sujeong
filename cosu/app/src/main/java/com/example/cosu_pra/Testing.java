package com.example.cosu_pra;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.cosu_pra.DTO.Category;
import com.example.cosu_pra.DTO.Comment;
import com.example.cosu_pra.DTO.ProjectPost;
import com.example.cosu_pra.DTO.QnAPost;
import com.example.cosu_pra.DTO.StudyPost;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.components.Component;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class Testing {
    HelpPosting pst;

    Testing() {
        pst = new HelpPosting();
        readPost();
    }

    // ok
    private void writePost() {

        // Project

        ProjectPost pp1 = new ProjectPost("first project post", "god",
                "This is content. it maybe changed", 3);
        ProjectPost pp2 = new ProjectPost("second project post", "dog",
                "This is content. it maybe changed. but i do not want to change it", 7);
        ProjectPost pp3 = new ProjectPost("third project post", "cat",
                "please help me", 6);
        pst.addPost(pst.PROJECT, pp1);
        pst.addPost(pst.PROJECT, pp2);
        pst.addPost(pst.PROJECT, pp3);

        // Study
        StudyPost sp1 = new StudyPost("first study post", "lion",
                "This is content. it maybe changed", 3);
        StudyPost sp2 = new StudyPost("second study post", "tiger",
                "This is content. it maybe changed. but i do not want to change it", 7);
        StudyPost sp3 = new StudyPost("third study post", "horse",
                "please help me", 6);
        pst.addPost(pst.STUDY, sp1);
        pst.addPost(pst.STUDY, sp2);
        pst.addPost(pst.STUDY, sp3);

        // QnA
        QnAPost qq1 = new QnAPost("first study post", "queen",
                "This is content. it maybe changed");
        QnAPost qq2 = new QnAPost("how can i get good score?", "rabbit",
                "This is content. it maybe changed");
        QnAPost qq3 = new QnAPost("help firesoter", "king",
                "fire store help");
        pst.addPost(pst.QNA, qq1);
        pst.addPost(pst.QNA, qq2);
        pst.addPost(pst.QNA, qq3);
    }

    // ok
    private void addComment() {
        Comment com1 = new Comment("bear", "i am groot");
        Comment com2 = new Comment("human", "i like tuna");
        Comment com3 = new Comment("john", "i like pizza");
        Comment com4 = new Comment("alphaGo", "netflix");

        pst.addComment(pst.PROJECT, "5mCaTT8j2qmoXkFxnywY", com1);
        pst.addComment(pst.PROJECT, "5mCaTT8j2qmoXkFxnywY", com2);
        pst.addComment(pst.PROJECT, "5mCaTT8j2qmoXkFxnywY", com3);
        pst.addComment(pst.PROJECT, "C7eP94NWsG5TFQbNkSmY", com4);

    }

    // ok
    private void readProjectPosts() {
        pst.getAllPosts(pst.PROJECT).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    Map<String, ProjectPost> posts = new HashMap<String, ProjectPost>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        posts.put(document.getId(), document.toObject(ProjectPost.class)); // 맵으로 넣는 방법
                    }
                    for (ProjectPost pp : posts.values()) {
                        Log.d("test", pp.getContent());
                        Log.d("test", pp.getWriter());
                    }
                }
            }
        });
    }

    // ok
    private void deletePost() {
        pst.deletePost(pst.PROJECT, "09CMhGxz19E7hU1Y75BI");
    }

    // ok
    private void deleteComment() {
        pst.deleteComment(pst.PROJECT, "C7eP94NWsG5TFQbNkSmY", "7TL4F42yX0odDLmbZ8lL");
    }

    // ok
    private void readPost() {
        // get post
        pst.getPost(pst.PROJECT, "5mCaTT8j2qmoXkFxnywY")
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                       ProjectPost post = documentSnapshot.toObject(ProjectPost.class); // post는 원하는 post 객체를 사용하세요
                        Log.d("test",post.getWriter());
                        Log.d("test",post.getContent());
                        Log.d("test",post.getTitle());
                        Log.d("test",post.getMax() +"");
                        Log.d("test",post.getUsers() +"");
                    }
                });

        // get post's comments
        pst.getComments(pst.PROJECT, "5mCaTT8j2qmoXkFxnywY")
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            Map<String, Comment> comments = new HashMap<String, Comment>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                comments.put(document.getId(), document.toObject(Comment.class)); // 맵으로 넣는 방법
                            }
                            for (Comment cmt : comments.values()) {
                                Log.d("test", cmt.getContent());
                                Log.d("test", cmt.getWriter());
                            }
                        }
                    }
                });
    }
}
