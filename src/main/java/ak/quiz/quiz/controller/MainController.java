package ak.quiz.quiz.controller;

import ak.quiz.quiz.model.*;
import ak.quiz.quiz.model.Randomize.Randomizable;
import ak.quiz.quiz.model.Randomize.Randomize;
import ak.quiz.quiz.repository.AnswerRepository;
import ak.quiz.quiz.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.jws.WebParam;
import java.util.List;
import java.util.Optional;

@Controller
public class MainController {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    AnswerRepository answerRepository;


    private EmailSender emailSender;

    private User user;

    private Score score;

    public MainController(Score score, User user, EmailSender emailSender) {
        this.score = score;
        this.user = user;
        this.emailSender = emailSender;
    }

    @GetMapping("")
    public String home(ModelMap modelMap) {
        return "start";
    }
    @GetMapping("/start")
    public String start(ModelMap modelMap, @RequestParam String userName, @RequestParam String userEmail){
        user.setName(userName);
        user.setEmail(userEmail);
        user.setAnswers("");
        return "redirect:/test";
    }
    @GetMapping("/sendAnswer")
    public String sendAnswer(ModelMap modelMap, @RequestParam int questionId, @RequestParam String answer){
        sendAnswer(answer, questionId);
        return "redirect:/test";
    }
    @GetMapping("/test")
    public String test(ModelMap modelMap) {
        List<Question> questions = (List<Question>) questionRepository.findAll();
        if (score.getCounter() < questions.size()) {
            getHome(questions, modelMap);
        } else {
            getResult(questions, modelMap);
            return "result";
        }
        return "home";
    }
    public String getHome(List<Question> questions, ModelMap modelMap){
        modelMap.put("questionNr", score.getCounter() + 1);
        modelMap.put("question", questions.get(score.getCounter()));
        Answer myAnswer = answerRepository.getAnswerById((List<Answer>) answerRepository.findAll(), questions.get(score.getCounter()).getAnswersId());
        Randomizable randomize = new Randomize();
        myAnswer = randomize.randomize(myAnswer);
        modelMap.put("answer", myAnswer);
        modelMap.put("score", score.getPoint());
        return "home";
    }
    public void getResult(List<Question> questions, ModelMap modelMap){
        modelMap.put("score", score.getPoint());
        modelMap.put("questionCount", questions.size());
        score.setCounter(0);
        score.setPoint(0);
        if (emailSender.sendEmail(user.getEmail(), "From Quiz", "Błędne odpowiedzi to: " + user.getAnswers().replaceAll("//","<br>")) == true){
            modelMap.put("emailResult", "Na Twój email została wysłana wiadomość w której dowiesz się które odpowiedzi były błędne");
        }
    }

    @PostMapping(path = "/")
    public String sendAnswer(@RequestParam String answer, @RequestParam int questionId) {
        if (answer != null) {
            if (questionRepository.checkIfCorrectAnswer(questionRepository.findById(questionId), answer))
                score.addPoint();
            else {
                user.setAnswers(user.getAnswers() + "//" + questionRepository.findById(questionId).get().getQuestion());
            }
        }
        score.addCounter();
        return "redirect:/";
    }



}
