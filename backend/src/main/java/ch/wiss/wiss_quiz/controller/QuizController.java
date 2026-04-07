package ch.wiss.wiss_quiz.controller;

import ch.wiss.wiss_quiz.model.Question;
import ch.wiss.wiss_quiz.service.QuizService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/quiz")
public class QuizController {
    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping(path = "")
    public List<Question> getQuizQuestions(@RequestParam Integer catId) {
        return quizService.getQuizQuestionsByCategoryId(catId);
    }
}