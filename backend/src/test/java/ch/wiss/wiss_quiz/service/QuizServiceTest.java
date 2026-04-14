package ch.wiss.wiss_quiz.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import ch.wiss.wiss_quiz.model.Answer;
import ch.wiss.wiss_quiz.model.Question;

class QuizServiceTest {

    private QuizService service;

    @BeforeEach
    void setUp() {
        service = new QuizService(null, null);
    }

    // Station 1 – Standardfälle (Happy Path)
    @Test
    void returnsAllIfLessOrEqualMax() {
        List<Question> input = List.of(new Question(), new Question());

        List<Question> result = service.pickQuizQuestions(input, 3);

        assertEquals(2, result.size());
    }

    // Station 1 – Standardfälle (Happy Path)
    @Test
    void limitsToMaxIfMoreThanMax() {
        List<Question> input = List.of(
                new Question(), new Question(), new Question(), new Question(), new Question()
        );

        List<Question> result = service.pickQuizQuestions(input, 3);

        assertEquals(3, result.size());
    }

    // Station 1 – Standardfälle (Happy Path)
    @Test
    void doesNotModifyOriginalList() {
        List<Question> input = new ArrayList<>(List.of(
                new Question(), new Question(), new Question(), new Question()
        ));

        service.pickQuizQuestions(input, 3);

        assertEquals(4, input.size());
    }

    // Station 1 – Standardfälle (Happy Path)
    @Test
    void calculateScorePercent_returns60_when3Of5Correct() {
        double result = service.calculateScorePercent(3, 5);

        assertEquals(60.0, result);
    }

    // Station 1 – Standardfälle (Happy Path)
    @Test
    void isQuizPassed_returnsTrue_whenPercentIs60() {
        boolean result = service.isQuizPassed(60.0);

        assertTrue(result);
    }

    // Station 1 – Standardfälle (Happy Path)
    @Test
    void isQuizPassed_returnsFalse_whenPercentIs59() {
        boolean result = service.isQuizPassed(59.0);

        assertFalse(result);
    }

    // Station 2 – Randfälle (Edge Cases)
    @Test
    void handlesBadInputs() {
        assertTrue(service.pickQuizQuestions(null, 3).isEmpty());
        assertTrue(service.pickQuizQuestions(List.of(), 3).isEmpty());
        assertTrue(service.pickQuizQuestions(List.of(new Question()), 0).isEmpty());
    }

    // Station 2 – Randfälle (Edge Cases)
    @Test
    void pickQuizQuestions_returnsEmptyList_whenMaxQuestionsIsNegative() {
        List<Question> input = List.of(new Question(), new Question());

        List<Question> result = service.pickQuizQuestions(input, -1);

        assertTrue(result.isEmpty());
    }

    // Station 2 – Randfälle (Edge Cases)
    @Test
    void calculateScorePercent_returnsZero_whenTotalIsZero() {
        double result = service.calculateScorePercent(3, 0);

        assertEquals(0.0, result);
    }

    // Station 2 – Randfälle (Edge Cases)
    @Test
    void calculateScorePercent_returnsZero_whenTotalIsNegative() {
        double result = service.calculateScorePercent(3, -5);

        assertEquals(0.0, result);
    }

    // Station 2 – Randfälle (Edge Cases)
    @Test
    void hasEnoughQuestions_returnsTrue_whenListHasAtLeastThreeQuestions() {
        List<Question> input = List.of(new Question(), new Question(), new Question());

        boolean result = service.hasEnoughQuestions(input);

        assertTrue(result);
    }

    // Station 2 – Randfälle (Edge Cases)
    @Test
    void hasEnoughQuestions_returnsFalse_whenListHasLessThanThreeQuestions() {
        List<Question> input = List.of(new Question(), new Question());

        boolean result = service.hasEnoughQuestions(input);

        assertFalse(result);
    }

    // Station 3 – Validierung von Quizfragen
    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidQuestionsProvider")
    void validateQuestionForQuiz_returnsFalse_forInvalidQuestions(String description, Question question) {
        boolean result = service.validateQuestionForQuiz(question);
        assertFalse(result);
    }

    static Stream<Arguments> invalidQuestionsProvider() {
        Question qTextNull = new Question();
        qTextNull.setQuestionText(null);
        qTextNull.setAnswers(createValidAnswers());

        Question qTextBlank = new Question();
        qTextBlank.setQuestionText("   ");
        qTextBlank.setAnswers(createValidAnswers());

        Question qAnswersNull = new Question();
        qAnswersNull.setQuestionText("Frage?");

        Question qOneAnswer = new Question();
        qOneAnswer.setQuestionText("Frage?");
        qOneAnswer.setAnswers(List.of(createAnswer("Nur eine", true)));

        Question qNullAnswer = new Question();
        qNullAnswer.setQuestionText("Frage?");
        List<Answer> answersWithNull = new ArrayList<>();
        answersWithNull.add(createAnswer("A", true));
        answersWithNull.add(null);
        qNullAnswer.setAnswers(answersWithNull);

        Question qBlankAnswer = new Question();
        qBlankAnswer.setQuestionText("Frage?");
        qBlankAnswer.setAnswers(List.of(createAnswer(" ", true), createAnswer("B", false)));

        Question qNoCorrect = new Question();
        qNoCorrect.setQuestionText("Frage?");
        qNoCorrect.setAnswers(List.of(createAnswer("A", false), createAnswer("B", false)));

        Question qMultipleCorrect = new Question();
        qMultipleCorrect.setQuestionText("Frage?");
        qMultipleCorrect.setAnswers(List.of(createAnswer("A", true), createAnswer("B", true)));

        return Stream.of(
                Arguments.of("whenQuestionIsNull", null),
                Arguments.of("whenQuestionTextIsNull", qTextNull),
                Arguments.of("whenQuestionTextIsBlank", qTextBlank),
                Arguments.of("whenAnswersAreNull", qAnswersNull),
                Arguments.of("whenLessThanTwoAnswersExist", qOneAnswer),
                Arguments.of("whenAnswerIsNull", qNullAnswer),
                Arguments.of("whenAnswerTextIsBlank", qBlankAnswer),
                Arguments.of("whenNoCorrectAnswerExists", qNoCorrect),
                Arguments.of("whenMultipleCorrectAnswersExist", qMultipleCorrect)
        );
    }

    // Station 3 – Validierung von Quizfragen
    @Test
    void validateQuestionForQuiz_returnsTrue_whenExactlyOneCorrectAnswerExists() {
        Question question = new Question();
        question.setQuestionText("Frage?");
        question.setAnswers(createValidAnswers());

        boolean result = service.validateQuestionForQuiz(question);

        assertTrue(result);
    }

    // Station 3 – Validierung von Quizfragen
    @Test
    void countValidQuestions_returnsZero_whenQuestionsIsNull() {
        int result = service.countValidQuestions(null);

        assertEquals(0, result);
    }

    // Station 3 – Validierung von Quizfragen
    @Test
    void countValidQuestions_returnsZero_whenQuestionsIsEmpty() {
        int result = service.countValidQuestions(List.of());

        assertEquals(0, result);
    }

    // Station 3 – Validierung von Quizfragen
    @Test
    void countValidQuestions_countsOnlyValidQuestions() {
        Question valid1 = new Question();
        valid1.setQuestionText("Q1");
        valid1.setAnswers(createValidAnswers());

        Question valid2 = new Question();
        valid2.setQuestionText("Q2");
        valid2.setAnswers(createValidAnswers());

        Question invalid = new Question();
        invalid.setQuestionText("Q3");
        invalid.setAnswers(List.of(createAnswer("Only one", true)));

        int result = service.countValidQuestions(List.of(valid1, invalid, valid2));

        assertEquals(2, result);
    }

    private static List<Answer> createValidAnswers() {
        return List.of(
                createAnswer("A", true),
                createAnswer("B", false)
        );
    }

    private static Answer createAnswer(String text, boolean correct) {
        Answer answer = new Answer();
        answer.setQuestionAnswer(text);
        answer.setCorrect(correct);
        return answer;
    }
}
