package ak.quiz.quiz.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    @Column(name = "name")
    private String name;

    @Column(name = "score")
    private String score;

    @Column(name = "email")
    private String email;

    @Column(name = "score_percentage")
    private double scorePercentage;

    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;

    public UserEntity(){}

    public UserEntity(Integer id, @NotNull String name, String score, double scorePercentage, Date date, String email) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.scorePercentage = scorePercentage;
        this.date = date;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public double getScorePercentage() {
        return scorePercentage;
    }

    public void setScorePercentage(double scorePercentage) {
        this.scorePercentage = scorePercentage;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
