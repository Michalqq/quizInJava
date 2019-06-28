package ak.quiz.quiz.repository;

import ak.quiz.quiz.model.Question;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends CrudRepository<Question, Integer> {

    default boolean checkIfCorrectAnswer(Optional<Question> question, String answer){
        if (question.get().getCorrectAnswer().equals(answer)) return true;
        return false;
    }

}
