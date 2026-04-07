package ch.wiss.wiss_quiz.controller;

import ch.wiss.wiss_quiz.model.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // This means that this class is a Controller
@RequestMapping(path="/question") // This means URL's start with /question (after Application path)
public class QuestionController {
  private final QuestionRepository questionRepository;
  private final CategoryRepository categoryRepository;
  private final AnswerRepository answerRepository;

    public QuestionController(QuestionRepository questionRepository, CategoryRepository categoryRepository, AnswerRepository answerRepository) {
        this.questionRepository = questionRepository;
        this.categoryRepository = categoryRepository;
        this.answerRepository = answerRepository;
    }

    @CrossOrigin(origins = "http://localhost:3000")
  @PostMapping(path="/{cat_id}") // Map ONLY POST Requests
  public String addNewQuestion(@PathVariable(value="cat_id") Integer catId, @RequestBody Question question) {

	Optional<Category> cat = categoryRepository.findById(catId);
    if (cat.isEmpty()) {
        return "Category not found";
    }

	question.setCategory(cat.get());
	// we need to store nested Answer-Objects seperately
	List<Answer> answers = List.copyOf(question.getAnswers());
	
	question.setAnswers(null);
	questionRepository.save(question);
	
	// we need to store nested Answer-Objects seperately
	answers.forEach(a -> {
		a.setQuestion(question);
		answerRepository.save(a);
	});
	
	return "Saved";
  }

  @CrossOrigin(origins = "http://localhost:3000")
  @GetMapping(path="")
  public Iterable<Question> getAllQuestions() {
    return questionRepository.findAll();
  } 
    
}