package com.karkai.controller;

import com.karkai.modal.Exam;
import com.karkai.service.ExamService;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(value = "exam")
public class ExamController {
    @Autowired
    private ExamService examService;

    //  create new User
    @GetMapping("/get")
    public List<Exam> getAllExams() throws ExecutionException, InterruptedException, IOException, ParseException {
        return examService.getAllExams();
    }
}
