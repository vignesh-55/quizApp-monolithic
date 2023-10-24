package com.vignesh.quizapp.service;

import com.vignesh.quizapp.repository.QuestionRepository;
import com.vignesh.quizapp.domain.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionRepository questionRepository;

    public List<Question> getAllQuestion() {
        return questionRepository.findAll();
    }

    public List<Question> getAllQuestionsByCategory(String category) {
        return questionRepository.findByCategory(category);
    }

    public Long addQuestion(Question question) {
//        questionRepository.save(question);
        return questionRepository.save(question).getId();
    }
}
