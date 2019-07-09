package ak.quiz.quiz.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "Answer_A")
    private String answerA;

    @Column(name = "Answer_B")
    private String answerB;

    @Column(name = "Answer_C")
    private String answerC;

    @Column(name = "Answer_D")
    private String answerD;

    public Answer() {
    }

    public Answer(String answerA, String answerB, String answerC, String answerD) {
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAnswerA() {
        return answerA;
    }

    public void setAnswerA(String answerA) {
        this.answerA = answerA;
    }

    public String getAnswerB() {
        return answerB;
    }

    public void setAnswerB(String answerB) {
        this.answerB = answerB;
    }

    public String getAnswerC() {
        return answerC;
    }

    public void setAnswerC(String answerC) {
        this.answerC = answerC;
    }

    public String getAnswerD() {
        return answerD;
    }

    public void setAnswerD(String answerD) {
        this.answerD = answerD;
    }
//
//    public void randomize() {
//        List<String> answers = new ArrayList<>();
//        answers.add(answerA);
//        answers.add(answerB);
//        answers.add(answerC);
//        answers.add(answerD);
//        int random = new Random().nextInt(answers.size());
//        this.setAnswerA(answers.get(random));
//        answers.remove(random);
//        random = new Random().nextInt(answers.size());
//        this.setAnswerB(answers.get(random));
//        answers.remove(random);
//        random = new Random().nextInt(answers.size());
//        this.setAnswerC(answers.get(random));
//        answers.remove(random);
//        random = new Random().nextInt(answers.size());
//        this.setAnswerD(answers.get(random));
//        answers.remove(random);
//    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", answerA='" + answerA + '\'' +
                ", answerB='" + answerB + '\'' +
                ", answerC='" + answerC + '\'' +
                ", answerD='" + answerD + '\'' +
                '}';
    }
}
