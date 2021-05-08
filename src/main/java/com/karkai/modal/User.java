package com.karkai.modal;

import com.sun.org.apache.xpath.internal.operations.Bool;

public class User {

    String id;
    String fName;
    String lName;
    String email;
    String phone;
    String image;
    String std;
    String exam;
    String isPrimeUser;
    String language;
    int totalScore;
    int correct;
    int wrong;
    int skipped;
    int timeUp;
    String city;


    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }

    public int getWrong() {
        return wrong;
    }

    public void setWrong(int wrong) {
        this.wrong = wrong;
    }

    public int getSkipped() {
        return skipped;
    }

    public void setSkipped(int skipped) {
        this.skipped = skipped;
    }

    public int getTimeUp() {
        return timeUp;
    }

    public void setTimeUp(int timeUp) {
        this.timeUp = timeUp;
    }

    public String getIsPrimeUser() {
        return isPrimeUser;
    }

    public void setIsPrimeUser(String isPrimeUser) {
        this.isPrimeUser = isPrimeUser;
    }


    public String getStd() {
        return std;
    }

    public void setStd(String std) {
        this.std = std;
    }

    public String getExam() {
        return exam;
    }

    public void setExam(String exam) {
        this.exam = exam;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
