package com.vignesh.quizapp.service;

import com.vignesh.quizapp.domain.Question;
import com.vignesh.quizapp.domain.QuestionWrapper;
import com.vignesh.quizapp.domain.Quiz;
import com.vignesh.quizapp.domain.Response;
import com.vignesh.quizapp.repository.QuestionRepository;
import com.vignesh.quizapp.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    QuizRepository quizRepository;

    public ResponseEntity<String> createQuiz(String category, int numQn, String title){
        Quiz quiz = new Quiz();
        List<Question> questionList = questionRepository.findRandomByCategoryAndLimit(category, numQn);
        quiz.setTitle(title);
        quiz.setQuestions(questionList);
        quizRepository.save(quiz);

        return new ResponseEntity<>("Quiz Created", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Long id) {
        Optional<Quiz> quizOptional = quizRepository.findById(id);
        List<Question> questionList = quizOptional.get().getQuestions();
        List<QuestionWrapper> questionWrappers = new ArrayList<>();
        for(Question question : questionList){
            QuestionWrapper questionWrapper = new QuestionWrapper(question.getId(), question.getQuestionTitle(), question.getOption1(), question.getOption2(), question.getOption3(), question.getOption4());
            questionWrappers.add(questionWrapper);
        }
        return new ResponseEntity<>(questionWrappers, HttpStatus.OK);
    }

    public ResponseEntity<Integer> submitQuiz(Long quizId, List<Response> responses) {
        Optional<Quiz> quizOptional = quizRepository.findById(quizId);
        List<Question> questionList = quizOptional.get().getQuestions();
        Integer right = 0, i = 0;
        for(Response response : responses){
            if(response.getUserAnswer().equalsIgnoreCase(questionList.get(i).getRightAnswer())){
                right++;
            }
            i++;
        }
        return new ResponseEntity<>(right, HttpStatus.ACCEPTED);
    }
}
