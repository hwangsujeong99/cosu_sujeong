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

import java.util.Map;

/**
 * 내가 만들어야 하는 함수가......
 * 7. 새 글 올라오면 알려주는 함수? --> 이건 공부가 더 필요할 듯
 * 8. 검색기능
 */
public class HelpPosting {
    public static final String PROJECT = "Projects";
    public static final String STUDY = "Studies";
    public static final String QNA = "QnA";
    public static final String FROM_POST = "posting";
    public static final String MSG_DONE = "done";


    private FirebaseFirestore db;
    private Context context;


    public HelpPosting(Context context) {
        db = FirebaseFirestore.getInstance();
        this.context = context;
    }

    /**
     * post
     * posting a post
     *
     * @param collection: path of post, use static final values
     *                    PROJECT, STUDY, QNA
     * @param post:       post to write
     */
    public void post(String collection, Post post) {
        db.collection(collection)
                .add(post)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("Firebase", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("Firebase", "Error adding document", e);
                    }
                });
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
        db.collection(collection).document(postID)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("test", "DocumentSnapshot successfully deleted!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("test", "Error deleting document", e);
                    }
                });
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
        DocumentReference documentRef = db.collection(collection).document(postID);

        documentRef.update("comments", FieldValue.arrayUnion(comment));
    }

    /**
     * addUser
     * add user to user list in post
     * NOTICE: please check user list is not full
     *
     * @param collection: path of post,use static final values
     *                    PROJECT, STUDY
     * @param postID:     id of post
     * @param userID:     id of user that add in user list
     */
    public void addUser(String collection,String postID, String userID) {
        // 일단 인원수가 비었는지 확인
        DocumentReference documentRef = db.collection(collection).document(postID);
        documentRef.update("users", FieldValue.arrayUnion(userID));
    }


    /**
     * getPostsQuery
     *
     * @param collection: path of post,use static final values
     *                        PROJECT, STUDY, QNA
     * @return Task<QuerySnapshot>: use addOnCompleteListener method instead Thread
     *
     * example)
     * getAllPost(.....).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
     *      @Override
     *      public void onComplete(@NonNull Task<QuerySnapshot> task) {
     *          if (task.isSuccessful()) {
     *              for (QueryDocumentSnapshot document : tt.getResult()) {
     *                  posts.put(document.getId(), document.toObject(ProjectPost.class)); // 맵으로 넣는 방법
     *              }
     *          }
     *      }
     * });
     */
    public Task<QuerySnapshot> getAllPosts(String collection) {
        return db.collection(collection).get();
    }

    /**
     * getPost
     * get a post from firebase
     *
     * @param collection: path of post,use static final values
     *                        PROJECT, STUDY, QNA
     * @param postID: id of post
     * @return Task<DocumentSnapshot>: use addOnSuccessListener method instead Thread
     *
     * example)
     * getPost(....).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
     *             @Override
     *             public void onSuccess(DocumentSnapshot documentSnapshot) {
     *                  post = documentSnapshot.toObject(ProjectPost.class); // post는 원하는 post 객체를 사용하세요
     *            }
     *         });
     */
    public Task<DocumentSnapshot> getPost(String collection, String postID){
        return db.collection("Projects").document(postID).get();
    }

    public Task<QuerySnapshot> searchPostByCategory(String collection,String category){
        CollectionReference postsRef = db.collection("collection");

        postsRef.whereArrayContains("category", "west_coast");
        return null;
    }

}
