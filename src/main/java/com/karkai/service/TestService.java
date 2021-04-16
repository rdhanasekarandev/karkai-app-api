package com.karkai.service;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import net.minidev.json.parser.ParseException;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.karkai.modal.Question;
import com.karkai.modal.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


@Component
public class TestService {

        // get  All Tests by exam
        public List<Test> getTestsByExam(String exam)throws ExecutionException, InterruptedException{
            Firestore dbFireStore= FirestoreClient.getFirestore();
            List<Test> tests=new ArrayList<>();
    
            ApiFuture<QuerySnapshot> future = dbFireStore.collection("Test").get();
            List<QueryDocumentSnapshot> documentSnapshots = future.get().getDocuments();
            for (QueryDocumentSnapshot queryDocumentSnapshot:documentSnapshots){
                Test test = queryDocumentSnapshot.toObject(Test.class);
                if(test.getExam().equals(exam)){
                    tests.add(test);
                }
            }
            return tests;
        }
    
        // get  Test Question by jsonLink
        public List<Question> getTestQuestionByJsonLink(String jsonLink)throws ExecutionException, InterruptedException, IOException, ParseException{
            
            JSONObject jsonObject = new JsonService().jsonParse(jsonLink);

            String jsonString = jsonObject.get("question1").toString();

            ObjectMapper objectMapper = new ObjectMapper();

            objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
            List<Question> questions=new ArrayList<Question>();

            try{
                questions = objectMapper.readValue(jsonString, List.class);
                System.out.print(questions);
            }catch(Exception e){
                e.printStackTrace();
            }

            return questions;
        }
    
}
