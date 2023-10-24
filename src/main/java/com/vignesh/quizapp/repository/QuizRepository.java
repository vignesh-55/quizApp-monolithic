package com.vignesh.quizapp.repository;

import com.vignesh.quizapp.domain.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

}
