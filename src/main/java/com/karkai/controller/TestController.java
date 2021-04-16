package com.karkai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import net.minidev.json.parser.ParseException;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.karkai.modal.Question;
import com.karkai.modal.Id;
import com.karkai.modal.Test;
import com.karkai.service.TestService;

@RestController
@RequestMapping(value = "test")
public class TestController {

    @Autowired
    private TestService testService;


    //  get questions by exam
    @PostMapping("/getByExam")
    public List<Test> getTestByExam(@RequestBody Id id) throws ExecutionException, InterruptedException{
        return testService.getTestsByExam(id.getId());
    }

    //  get questions
    @PostMapping("/getQuestions")
    public List<Question> getAllMaterials(@RequestBody Id id) throws ExecutionException, InterruptedException, IOException, ParseException {
        return testService.getTestQuestionByJsonLink(id.getId());
    }

    
}
