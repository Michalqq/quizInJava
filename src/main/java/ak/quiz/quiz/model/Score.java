package ak.quiz.quiz.model;

import org.springframework.stereotype.Component;

@Component
public class Score {
    private int point;

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
    public void addPoint(){
        this.point = point + 1;
    }
}
