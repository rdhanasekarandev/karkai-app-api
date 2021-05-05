package com.karkai.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.karkai.modal.Folder;
import com.karkai.modal.Material;
import com.karkai.modal.User;
import com.sun.org.apache.xpath.internal.operations.Bool;
import net.minidev.json.parser.ParseException;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Component
public class UserService {

    private JsonService jsonService = new JsonService();

    public User getUserById(String id) throws ExecutionException, InterruptedException, IOException, ParseException {
        Firestore dbFireStore = FirestoreClient.getFirestore();

        DocumentReference docRef = dbFireStore.collection("Users").document(id);
        ApiFuture<DocumentSnapshot> future = docRef.get();

        DocumentSnapshot document = future.get();

        User user = document.toObject(User.class);
        return  user;
    }


    // get all user details in firebase
    public List<User> getAllUser() throws ExecutionException, InterruptedException, IOException, ParseException {
        Firestore dbFireStore= FirestoreClient.getFirestore();
        List<User> user=new ArrayList<>();
        ApiFuture<QuerySnapshot> future = dbFireStore.collection("Users").get();
        List<QueryDocumentSnapshot> documentSnapshots = future.get().getDocuments();
        for (QueryDocumentSnapshot queryDocumentSnapshot:documentSnapshots){
            user.add(queryDocumentSnapshot.toObject(User.class));
        }
        return user;
    }

    // add new user in firebase
    public String addUser(User user) {
        Firestore firestore= FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture=firestore.collection("Users").document(user.getId()).set(user);
        return "success";
    }

    // save user details in firebase
    public String updateUser(String id,String field,String value) {
        Firestore firestore= FirestoreClient.getFirestore();
        Map update = new HashMap();
        update.put(field,value);
        ApiFuture collectionApiFuture=firestore.collection("Users").document(id).update(update);
        return "success";
    }

}
