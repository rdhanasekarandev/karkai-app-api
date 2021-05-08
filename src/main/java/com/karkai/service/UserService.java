package com.karkai.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.karkai.modal.*;
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




    // get all user details in firebase
    public ScorePosition getScorePosition(String id) throws ExecutionException, InterruptedException, IOException, ParseException {
        Firestore dbFireStore= FirestoreClient.getFirestore();
        List<User> users=new ArrayList<>();
        ApiFuture<QuerySnapshot> future = dbFireStore.collection("Users").get();
        List<QueryDocumentSnapshot> documentSnapshots = future.get().getDocuments();
        for (QueryDocumentSnapshot queryDocumentSnapshot:documentSnapshots){
            users.add(queryDocumentSnapshot.toObject(User.class));
        }
        return findScorePosition(users,id);
    }

    ScorePosition findScorePosition(List<User> users, String id){
        int total = 0;
        User maxUser = new User();
        User currentUser = new User();
        ScorePosition scorePosition = new ScorePosition();
        maxUser.setTotalScore(0);

        for(User user:users) {
            if (user.getId() ==id) {
                currentUser = user;
            }
        }
        scorePosition.setCurrent(currentUser);
        for(User user:users) {
            if (user.getTotalScore() > maxUser.getTotalScore()) {
                maxUser = user;
            }
        }
        scorePosition.setUser1(maxUser);
        User user2 = findMaxPosition(users,maxUser);
        scorePosition.setUser2(user2);
        User user3 = findMaxPosition(users,user2);
        scorePosition.setUser3(user3);
        User user4 = findMaxPosition(users,user3);
        scorePosition.setUser4(user4);
        User user5 = findMaxPosition(users,user4);
        scorePosition.setUser5(user5);
        scorePosition.setPosition(findCurrentUserPositon(users,currentUser));

        return  scorePosition;
    }

    int findCurrentUserPositon(List<User> users,User cUser){
        int i = users.size();
        for(User user:users){
            if(user.getTotalScore()<cUser.getTotalScore()){
                i = i-1;
            }
        }

        return  i;
    }

    User findMaxPosition(List<User> users,User max){
        int total = 0;
        User maxUser = new User();
        maxUser.setTotalScore(0);
        for(User user:users){
            if(user.getTotalScore()>maxUser.getTotalScore() && user.getTotalScore()<max.getTotalScore()){
                maxUser = user;
            }
        }

        return  maxUser;
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

    public String updateScore(Score score) {
        Firestore firestore= FirestoreClient.getFirestore();
        Map update = new HashMap();
        update.put("total",score.getTotal());
        update.put("correct",score.getCorrect());
        update.put("wrong",score.getWrong());
        update.put("skipped",score.getSkipped());
        update.put("timeUp",score.getTimeup());
        ApiFuture collectionApiFuture=firestore.collection("Users").document(score.getId()).update(update);
        return "success";
    }

}
