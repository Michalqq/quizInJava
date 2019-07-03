package ak.quiz.quiz.model.Randomize;

import ak.quiz.quiz.model.Answer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Randomize implements Randomizable {
    @Override
    public Answer randomize(Answer answer) {
        List<String> answers = new ArrayList<>();
        answers.add(answer.getAnswerA());
        answers.add(answer.getAnswerB());
        answers.add(answer.getAnswerC());
        answers.add(answer.getAnswerD());
        int random = new Random().nextInt(answers.size());
        answer.setAnswerA(answers.get(random));
        answers.remove(random);
        random = new Random().nextInt(answers.size());
        answer.setAnswerB(answers.get(random));
        answers.remove(random);
        random = new Random().nextInt(answers.size());
        answer.setAnswerC(answers.get(random));
        answers.remove(random);
        random = new Random().nextInt(answers.size());
        answer.setAnswerD(answers.get(random));
        answers.remove(random);
        return answer;
    }

}
