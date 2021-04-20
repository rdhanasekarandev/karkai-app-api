package com.karkai.modal;

import java.util.ArrayList;
import java.util.List;

public class TestData {
    String exam;
    List<String> subjects;
    List<String> noOfTests;
    List<String> chapters;
    
    public String getExam() {
        return exam;
    }

    public void setExam(String exam) {
        this.exam = exam;
    }

    public List<String> getSubjcets() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }
    public List<String> getNoOfTests() {
        return noOfTests;
    }

    public void setNoOfTests(List<String> list) {
        this.noOfTests = list;
    }
    public List<String> getChapters() {
        return chapters;
    }

    public void setChapters(List<String> chapters) {
        this.chapters = chapters;
    }
    
}
