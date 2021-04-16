package com.karkai.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import net.minidev.json.parser.ParseException;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.cloud.FirestoreClient;
import com.karkai.modal.Material;
import com.karkai.modal.MaterialFolder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Component
public class MaterialService {

    // get all materials
    public List<Material> getAllMaterial()throws ExecutionException, InterruptedException{
        Firestore dbFireStore= FirestoreClient.getFirestore();
        List<Material> materials=new ArrayList<>();

        ApiFuture<QuerySnapshot> future = dbFireStore.collection("Materials").get();
        List<QueryDocumentSnapshot> documentSnapshots = future.get().getDocuments();
        for (QueryDocumentSnapshot queryDocumentSnapshot:documentSnapshots){
            materials.add(queryDocumentSnapshot.toObject(Material.class));
        }
        return materials;
    }

    // get subject material
    public List<Material> getMaterialBySubject(String subject,String exam,String language) throws ExecutionException, InterruptedException, IOException, ParseException {
        Firestore dbFireStore= FirestoreClient.getFirestore();

        DocumentReference docRef = dbFireStore.collection(exam+"Materials").document(subject+language);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        
        DocumentSnapshot document = future.get();

        MaterialFolder materialFolder = document.toObject(MaterialFolder.class);

        JSONObject jsonObject = new JsonService().jsonParse(materialFolder.getJsonLink());

            String jsonString = jsonObject.toString();

            ObjectMapper objectMapper = new ObjectMapper();

            objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
            List<Material> materials=new ArrayList<Material>();

            try{
                materials = objectMapper.readValue(jsonString, List.class);
                System.out.print(materials);
            }catch(Exception e){
                e.printStackTrace();
            }
        return materials;
    }

    // add new material in firebase
    public String addMaterial(MaterialFolder material) {
        Firestore firestore= FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture=firestore.collection(material.getExam()+"Materials").document(material.getSubject()+material.getLanguage()).set(material);
        return "success";
    }

}
