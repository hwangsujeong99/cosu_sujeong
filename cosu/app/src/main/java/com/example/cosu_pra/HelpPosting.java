package com.example.cosu_pra;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.cosu_pra.DTO.Comment;
import com.example.cosu_pra.DTO.ProjectPost;
import com.example.cosu_pra.DTO.QnAPost;
import com.example.cosu_pra.DTO.StudyPost;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

/**
 * 내가 만들어야 하는 함수가......
 * 1. 글을 올려주는 함수 --> 완료
 * 2. 코멘트 올려주는 함수 --> 완료
 * 3. 타이틀 가져와서 arraylist 리턴해주는 함수 --> 에타는 타이틀이 없는데 어케하지?
 * 4. 글 들어가면 전체 데이터 넘겨주는 함수 --> 걍 get 때리면 되는데 이거 unpacking을 프런트해서 해줄까?
 * 5. 글 삭제하는 기능(젤 나중에하자)
 * 6. 인원을 추가해주는 함수 --> 완료
 * 7. 새 글 올라오면 알려주는 함수? --> 이건 공부가 더 필요할 듯
 * 8. 검색기능
 */
public class HelpPosting {
    FirebaseFirestore db;

    public HelpPosting() {
        db = FirebaseFirestore.getInstance();
    }

    public void postProject(ProjectPost post) {
        db.collection("Projects")
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

    public void getProject(String postID){
        DocumentReference docRef = db.collection("Projects").document(postID);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                ProjectPost post = documentSnapshot.toObject(ProjectPost.class);
                Log.d("test","dddd: "+ post.getDate());
            }
        });
    }

    public void getProjects() {
        db.collection("Projects")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("test", document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.w("test", "Error getting documents.", task.getException());
                        }
                    }
                });
    }


    public void updateProjectComment(Comment comment, String postID) {
        DocumentReference documentRef = db.collection("Projects").document(postID);

        documentRef.update("comments", FieldValue.arrayUnion(comment));
    }

    public void updateProjectUser(String userID, String postID) {
        DocumentReference documentRef = db.collection("Projects").document(postID);

        documentRef.update("users", FieldValue.arrayUnion(userID));
    }

    public boolean isProjectFull(String postID) {
        return true;
    }

    public void postStudy(StudyPost post) {
        db.collection("Studies")
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

    public void getStudies() {
        db.collection("Studies")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("test", document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.w("test", "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    public void updateStudyComment(Comment comment, String postID) {
        DocumentReference documentRef = db.collection("Studies").document(postID);

        documentRef.update("comments", FieldValue.arrayUnion(comment));
    }

    public void updateStudyUser(String userID, String postID) {
        DocumentReference documentRef = db.collection("Studies").document(postID);

        documentRef.update("users", FieldValue.arrayUnion(userID));
    }


    public void postQnA(QnAPost post) {
        db.collection("QnA")
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

    public void getQnAs() {
        db.collection("QnA")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("test", document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.w("test", "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    public void updateQnAComment(Comment comment, String postID) {
        DocumentReference documentRef = db.collection("QnA").document(postID);

        documentRef.update("comments", FieldValue.arrayUnion(comment));
    }


}
