package ak.quiz.quiz.controller;

import ak.quiz.quiz.model.Answer;
import ak.quiz.quiz.model.Question;
import ak.quiz.quiz.repository.AnswerRepository;
import ak.quiz.quiz.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class AddQuestionController {

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    QuestionRepository questionRepository;

    @GetMapping("/add")
    public String add(ModelMap modelMap) {
        return "add";
    }


    @PostMapping(path = "/add") // Map ONLY GET Requests
    public String addNewQuestion(@RequestParam String question
            , @RequestParam String answA
            , @RequestParam String answB
            , @RequestParam String answC
            , @RequestParam String answD, RedirectAttributes redirectAttributes) {
        answerRepository.save(new Answer(answA, answB, answC, answD));
        int answerId = answerRepository.getLastId((List<Answer>) answerRepository.findAll());
        questionRepository.save(new Question(question, answA, answerId));
        redirectAttributes.addFlashAttribute("succesfulAdded","You succesfully added a new question");
        return "redirect:/add";
    }
}
