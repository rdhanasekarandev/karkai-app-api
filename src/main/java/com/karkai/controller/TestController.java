package com.karkai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import net.minidev.json.parser.ParseException;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.karkai.modal.Question;
import com.karkai.modal.Duo;
import com.karkai.modal.GetMaterial;
import com.karkai.modal.Id;
import com.karkai.modal.Test;
import com.karkai.modal.TestData;
import com.karkai.service.TestService;

@RestController
@RequestMapping(value = "test")
public class TestController {

    @Autowired
    private TestService testService;

    // get Test data
    @PostMapping("/getTestData")
    public TestData getAllMaterials(@RequestBody GetMaterial getMaterial)
            throws ExecutionException, InterruptedException, IOException, ParseException {
        return testService.getTestData(getMaterial.getExam(), getMaterial.getLanguage());
    }

    // get tests
    @PostMapping("/getTests")
    public List<Test> getTests(@RequestBody GetMaterial getMaterial)
            throws ExecutionException, InterruptedException, IOException, ParseException {
        return testService.getTestsBySubject(getMaterial.getSubject(), getMaterial.getExam(),
                getMaterial.getLanguage());
    }

    // get question
    @PostMapping("/getQuestion")
    public List<Question> getQuestions(@RequestBody Id id)
            throws ExecutionException, InterruptedException, IOException, ParseException {
        return testService.getTestQuestions(id.getId());
    }

}
