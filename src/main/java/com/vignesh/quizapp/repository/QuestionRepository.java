package com.vignesh.quizapp.repository;

import com.vignesh.quizapp.domain.Question;
import org.hibernate.id.factory.internal.SequenceGenerationTypeStrategy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    public List<Question> findByCategory(String category);

    @Query(value = "select * from question q where q.category=:category order by q.id limit :numQn", nativeQuery = true)
    List<Question> findRandomByCategoryAndLimit(@Param(value = "category") String category, @Param(value = "numQn") int numQn);
}
