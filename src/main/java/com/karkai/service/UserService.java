package com.karkai.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.karkai.modal.User;
import net.minidev.json.parser.ParseException;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Component
public class UserService {

    private JsonService jsonService = new JsonService();

    // get all user details in firebase
    public List<User> getAllUser() throws ExecutionException, InterruptedException, IOException, ParseException {
        Firestore dbFireStore= FirestoreClient.getFirestore();
        List<User> user=new ArrayList<>();
        jsonService.jsonParse("https://firebasestorage.googleapis.com/v0/b/karkai-ac679.appspot.com/o/format.json?alt=media&token=b481d53f-227b-463b-8013-0437d523739f");
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

}
