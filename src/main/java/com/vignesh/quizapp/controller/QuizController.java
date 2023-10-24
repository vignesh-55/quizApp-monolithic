package com.vignesh.quizapp.controller;

import com.vignesh.quizapp.domain.Question;
import com.vignesh.quizapp.domain.QuestionWrapper;
import com.vignesh.quizapp.domain.Response;
import com.vignesh.quizapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    QuizService quizService;
    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam(name = "category") String category, @RequestParam(name = "numQn") int numQn, @RequestParam(name = "title") String title){
        return quizService.createQuiz(category, numQn, title);
    }

    @GetMapping("get/{quizId}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Long quizId){
        return quizService.getQuizQuestions(quizId);
    }

    @PostMapping("submit/{quizId}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Long quizId, @RequestBody List<Response> responses){
        return quizService.submitQuiz(quizId, responses);
    }
}
