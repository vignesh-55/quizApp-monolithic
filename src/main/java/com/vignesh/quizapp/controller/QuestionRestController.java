package com.vignesh.quizapp.controller;

import com.vignesh.quizapp.domain.Question;
import com.vignesh.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionRestController {

    @Autowired
    QuestionService questionService;
    @GetMapping("allquestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return new ResponseEntity<>(questionService.getAllQuestion(), HttpStatus.OK);
    }

    @GetMapping("category/{category}")
    public List<Question> getAllQuestionsByCategory(@PathVariable String category){
        return questionService.getAllQuestionsByCategory(category);
    }

    @PostMapping("add")
    public Long addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }
}
