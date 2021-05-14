package com.example.cosu_pra;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.cosu_pra.DTO.Category;
import com.example.cosu_pra.DTO.Comment;
import com.example.cosu_pra.DTO.Post;
import com.example.cosu_pra.DTO.ProjectPost;
import com.example.cosu_pra.DTO.QnAPost;
import com.example.cosu_pra.DTO.StudyPost;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * HelpPosting 사용설명서
 * <p>
 * - 역할
 * 글 올리기 -> 완료
 * 글 지우기 ->완료
 * 글 수정하기
 * 댓글 올리기 -> 완료
 * 댓글 지우기 -> 완료
 * 팀원으로 참여하기
 * 팀원으로 참여한것 취소하기
 * 검색기능 -글쓴이 -> 완료
 * 검색기능 -카테고리
 * 검색기능 -내용
 * 신고기능
 * 관심 유저 등록하기
 * 관심 글 등록하기
 */
public class HelpPosting {
    public static final String PROJECT = "Projects";
    public static final String STUDY = "Studies";
    public static final String QNA = "QnA";
    public static final String COMMENTS = "comments";

    private FirebaseFirestore db;

    public HelpPosting() {
        db = FirebaseFirestore.getInstance();
    }

    /**
     * post
     * posting a post
     * --> testing is complete
     *
     * @param collection: path of post, use static final values
     *                    PROJECT, STUDY, QNA
     * @param post:       post to write
     */
    public void addPost(String collection, Post post) {
        db.collection(collection).add(post);
    }

    /**
     * getPost
     * get a post from firebase
     *
     * @param collection: path of post,use static final values
     *                    PROJECT, STUDY, QNA
     * @param postID:     id of post
     * @return Task<DocumentSnapshot>: use addOnSuccessListener method instead Thread
     */
    public Task<DocumentSnapshot> getPost(String collection, String postID) {
        return db.collection(collection).document(postID).get();
    }

    /**
     * deletePost
     * delete a post in collection path which id is postID
     *
     * @param collection: path of post, use static final values
     *                    PROJECT, STUDY, QNA
     * @param postID:     id of post
     */
    public void deletePost(String collection, String postID) {
        // delete sub collection
        db.collection(collection).document(postID)
                .collection(COMMENTS).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                deleteComment(collection, postID, document.getId());
                            }
                        }
                    }
                });

        // delete post
        db.collection(collection).document(postID).delete();
    }

    /**
     * getAllPosts
     * --> test complete
     *
     * @param collection: path of post,use static final values
     *                    PROJECT, STUDY, QNA
     * @return Task<QuerySnapshot>: use addOnCompleteListener method instead Thread
     */
    public Task<QuerySnapshot> getAllPosts(String collection) {
        return db.collection(collection).get();
    }


    /**
     * addComment
     * add a comment on post which id is postID
     *
     * @param collection: path of post,use static final values
     *                    PROJECT, STUDY, QNA
     * @param postID:     id of post
     * @param comment:    comment to add
     */
    public void addComment(String collection, String postID, Comment comment) {
        db.collection(collection).document(postID)
                .collection(COMMENTS).add(comment);
    }

    public void deleteComment(String collection, String postID, String commentID) {
        db.collection(collection).document(postID)
                .collection(COMMENTS).document(commentID).delete();
    }

    public Task<QuerySnapshot> getComments(String collection, String postID) {
        return db.collection(collection).document(postID).collection(COMMENTS).get();
    }


    /**
     * searchPostByWriter
     * <p>
     * search post which contains search word
     *
     * @param collection: path of post,use static final values(PROJECT, STUDY, QNA)
     * @param writer:     writer
     * @return Task<QuerySnapshot>:
     * use addOnCompleteListener method instead Thread
     */
    public Task<QuerySnapshot> searchPostByWriter(String collection, String writer) {
        return db.collection(collection).whereEqualTo("writer", writer).get();
    }


}
