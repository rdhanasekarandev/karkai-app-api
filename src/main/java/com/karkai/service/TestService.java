package com.karkai.service;

import org.springframework.stereotype.Component;

import net.minidev.json.parser.ParseException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.karkai.modal.Folder;
import com.karkai.modal.Penta;
import com.karkai.modal.Question;
import com.karkai.modal.Test;
import com.karkai.modal.TestData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Component
public class TestService {

    // get test by subject
    public List<Test> getTestsBySubject(String subject, String exam, String language)
            throws ExecutionException, InterruptedException, IOException, ParseException {
        Firestore dbFireStore = FirestoreClient.getFirestore();

        DocumentReference docRef = dbFireStore.collection(exam + "Tests").document(subject + language);
        ApiFuture<DocumentSnapshot> future = docRef.get();

        DocumentSnapshot document = future.get();

        Folder Folder = document.toObject(Folder.class);

        org.json.JSONArray jsonArray = (org.json.JSONArray) new JsonService().jsonParse(Folder.getJsonLink())
                .get("test");

        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        List<Test> tests = new ArrayList<Test>();
        tests = objectMapper.readValue(jsonArray.toString(), new TypeReference<List<Test>>() {
        });
        System.out.println(tests);
        return tests;
    }

    // get questions
    public List<Question> getTestQuestions(String jsonLink)
            throws ExecutionException, InterruptedException, IOException, ParseException {

        org.json.JSONArray jsonArray = (org.json.JSONArray) new JsonService().jsonParse(jsonLink).get("question");

        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        List<Question> questions = new ArrayList<Question>();
        questions = objectMapper.readValue(jsonArray.toString(), new TypeReference<List<Question>>() {
        });
        System.out.println(questions);
        return questions;
    }

    // get Test data
    public TestData getTestData(String exam, String language)
            throws ExecutionException, InterruptedException, IOException, ParseException {
        Firestore dbFireStore = FirestoreClient.getFirestore();

        DocumentReference docRef = dbFireStore.collection(exam + "Tests").document(language + "data");
        ApiFuture<DocumentSnapshot> future = docRef.get();

        DocumentSnapshot document = future.get();
        
        Penta penta = document.toObject(Penta.class);

        TestData testData = new TestData();

        testData.setExam(penta.getExam());
        testData.setNoOfTests(Arrays.asList(penta.getNoOfTests().split("%")));
        testData.setChapters(Arrays.asList(penta.getChapters().split("%")));
        testData.setSubjects(Arrays.asList(penta.getSubjects().split("%")));

        return testData;
    }

}
