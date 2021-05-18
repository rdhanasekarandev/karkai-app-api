package com.karkai.service;


import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import com.karkai.modal.Exam;
import com.karkai.modal.Material;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Component
public class ExamService {

    // get all materials
    public List<Exam> getAllExams()throws ExecutionException, InterruptedException{
        Firestore dbFireStore= FirestoreClient.getFirestore();
        List<Exam> exams=new ArrayList<>();

        ApiFuture<QuerySnapshot> future = dbFireStore.collection("Exams").get();
        List<QueryDocumentSnapshot> documentSnapshots = future.get().getDocuments();
        for (QueryDocumentSnapshot queryDocumentSnapshot:documentSnapshots){
            exams.add(queryDocumentSnapshot.toObject(Exam.class));
        }
        return exams;
    }
}
