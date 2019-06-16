package ak.quiz.quiz.repository;

import ak.quiz.quiz.model.Answer;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public interface AnswerRepository extends CrudRepository<Answer, Integer> {

    default int getLastId(List<Answer> answers) {
        int id = 0;
        for (Answer answer:answers){
            if (answer.getId()>id) id = answer.getId();
        }
        return id;
    }

    default Answer getAnswerById(List<Answer> answers, int id){
        for (Answer answer:answers){
            if (answer.getId().equals(id)) return answer;
        }
        return new Answer();
    }

    default List<Answer> getAllUnsorted(List<Answer> answers){
        List<Answer> newAnswers = new ArrayList<>();
        for (int i = 0; i < answers.size(); i++) {
            int random = new Random().nextInt(answers.size());
            newAnswers.add(answers.get(random));
        }
        return newAnswers;
    }
}
