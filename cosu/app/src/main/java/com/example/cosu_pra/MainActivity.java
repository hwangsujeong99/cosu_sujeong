package com.example.cosu_pra;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.cosu_pra.DTO.Category;
import com.example.cosu_pra.DTO.Comment;
import com.example.cosu_pra.DTO.ProjectPost;
import com.example.cosu_pra.DTO.QnAPost;
import com.example.cosu_pra.DTO.StudyPost;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String title = "it's a new title";
        String writer = "test_writer 123";
        String content = "this is content, check firebase operate well";
        Category category = new Category(true, true);
        int maxUser = 8;


        /* Example */
        HelpPosting helpPost = new HelpPosting();

        // Post Project --> Study and QnA also use postStudy and postQnA
//        ProjectPost projectPost = new ProjectPost(title, writer, content, category, maxUser);
//        helpPost.postProject(projectPost);

        // Update comment
//        Comment comment1 = new Comment("user1","this post is great!!!");
//        helpPost.updateProjectComment(comment1,"7fyYktEOHsmVDeDzTr2e"); // you must get postID

        // Read a post
        helpPost.getProject("7fyYktEOHsmVDeDzTr2e");
    }
}