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

import java.util.List;

@Controller
public class MainController {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    Score score;

    private User user;

    private Counter counter;

    public MainController(Counter counter, User user) {
        this.counter = counter;
        this.user = user;
    }

    @GetMapping("")
    public String home(ModelMap modelMap) {
        return "start";
    }

    @GetMapping("/test")
    public String test(ModelMap modelMap, @RequestParam String userName, @RequestParam String userEmail) {
        List<Question> questions = (List<Question>) questionRepository.findAll();
        if (counter.getCounter() < questions.size()) {
            if (user.getName() == null){
                user.setName(userName);
                user.setEmail(userEmail);
                System.out.println(user.getName());
            }
            modelMap.put("questionNr", counter.getCounter() + 1);
            modelMap.put("question", questions.get(counter.getCounter()));
            Answer answer = answerRepository.getAnswerById((List<Answer>) answerRepository.findAll(), questions.get(counter.getCounter()).getAnswersId());
            answer.randomize();
            modelMap.put("answer", answer);
            modelMap.put("score", score.getPoint());
        } else {
            modelMap.put("score", score.getPoint());
            modelMap.put("questionCount", questions.size());
            counter.setCounter(0);
            score.setPoint(0);
            return "result";
        }
        return "home";
    }

    @GetMapping("/add")
    public String add(ModelMap modelMap) {
        return "add";
    }

    @PostMapping(path = "/")
    public String sendAnswer(@RequestParam String answer, @RequestParam int questionId, ModelMap modelMap) {
        if (answer != null) {
            if (questionRepository.checkIfCorrectAnswer(questionRepository.findById(questionId), answer))
                score.addPoint();
        }
        counter.addCounter();
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
