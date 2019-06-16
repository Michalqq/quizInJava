package ak.quiz.quiz.model;

import javax.persistence.*;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "Question")
    private String question;

    @Column(name = "Correct_Answer")
    private String correctAnswer;

    @Column(name = "Answers_Id")
    private Integer answersId;

    public Question(){}

    public Question(String question, String correctAnswer, Integer answersId) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.answersId = answersId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public Integer getAnswersId() {
        return answersId;
    }

    public void setAnswersId(Integer answersId) {
        this.answersId = answersId;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", correctAnswer='" + correctAnswer + '\'' +
                ", answersId=" + answersId +
                '}';
    }

}
