package ak.quiz.quiz.controller;

import ak.quiz.quiz.model.*;
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
            modelMap.put("questionNr", score.getCounter() + 1);
            modelMap.put("question", questions.get(score.getCounter()));
            Answer myAnswer = answerRepository.getAnswerById((List<Answer>) answerRepository.findAll(), questions.get(score.getCounter()).getAnswersId());
            myAnswer.randomize();
            modelMap.put("answer", myAnswer);
            modelMap.put("score", score.getPoint());
        } else {
            modelMap.put("score", score.getPoint());
            modelMap.put("questionCount", questions.size());
            System.out.println(user.getAnswers());
            score.setCounter(0);
            score.setPoint(0);
            if (emailSender.sendEmail(user.getEmail(), "From Quiz", "Błędne odpowiedzi to: " + user.getAnswers().replaceAll("//","<br>")) == true){
                modelMap.put("emailResult", "Na Twój email została wysłana wiadomość w której dowiesz się które odpowiedzi były błędne");
            }
            return "result";
        }
        return "home";
    }

    @GetMapping("/add")
    public String add(ModelMap modelMap) {
        return "add";
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

    @PostMapping(path = "/add") // Map ONLY GET Requests
    public String addNewQuestion(@RequestParam String question
            , @RequestParam String answA
            , @RequestParam String answB
            , @RequestParam String answC
            , @RequestParam String answD, ModelMap modelMap) {
        answerRepository.save(new Answer(answA, answB, answC, answD));
        int answerId = answerRepository.getLastId((List<Answer>) answerRepository.findAll());
        questionRepository.save(new Question(question, answA, answerId));
        return "redirect:/";
    }

}
