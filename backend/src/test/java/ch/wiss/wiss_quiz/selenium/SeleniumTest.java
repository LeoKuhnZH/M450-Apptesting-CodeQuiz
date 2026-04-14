package ch.wiss.wiss_quiz.selenium;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

class SeleniumTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // Helper Methoden (dürfen verwendet werden)
    private void openQuizPage() {
        driver.get("http://localhost:3000/quiz");
    }

    private void openHomePage() {
        driver.get("http://localhost:3000/");
    }

    private WebElement questionText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("[data-testid='question-text']")));
    }

    private List<WebElement> answerButtons() {
        return driver.findElements(By.cssSelector("[data-testid^='answer-button-']"));
    }

    private WebElement nextButton() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("[data-testid='next-button']")));
    }

    // ============================
    // 🟢 Beispieltests (gegeben)
    // ============================

    @Test
    void quizPage_showsQuestionText() {
        openQuizPage();

        String text = questionText().getText();

        assertFalse(text.isBlank(), "Der Fragetext sollte sichtbar sein.");
    }

    @Test
    void quizPage_showsAnswerButtons() {
        openQuizPage();

        wait.until(webDriver -> !answerButtons().isEmpty());

        List<WebElement> buttons = answerButtons();

        assertFalse(buttons.isEmpty(), "Antwort-Buttons sollten sichtbar sein.");
        assertEquals(4, buttons.size(), "Es sollten 4 Antworten vorhanden sein.");
    }

    // ============================
    // 🟡 Aufgabe 1
    // ============================

    @Test
    void clickingAnswer_changesPageState() {
        openQuizPage();

        // 1. Warte auf Antwort-Buttons
        wait.until(webDriver -> !answerButtons().isEmpty());
        // 2. Klicke auf eine Antwort
        answerButtons().get(0).click();
        // 3. Hole den Next-Button
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("[data-testid='next-button']")
            ));

        // 4. Prüfe, dass er sichtbar ist
        String bodyText = driver.findElement(By.tagName("body")).getText();

        assertTrue(bodyText.contains("Next"));
    }

    // ============================
    // 🟡 Aufgabe 2
    // ============================

    @Test
    void nextButton_isVisible_afterAnswer() {
        openQuizPage();

        // 1. Warte auf Antwort-Buttons
        wait.until(webDriver -> !answerButtons().isEmpty());
        // 2. Klicke auf eine Antwort
        answerButtons().get(0).click();
        // 3. Hole den Next-Button
        WebElement next = nextButton();
        // 4. Prüfe, dass er sichtbar ist
        assertTrue(next.isDisplayed());
    }

    // ============================
    // 🔴 Aufgabe 3
    // ============================

    @Test
    void nextButton_loadsNextQuestion() {
        openQuizPage();

        // 1. Speichere erste Frage
        String questionBefore = questionText().getText();
        // 2. Klicke Antwort
        answerButtons().get(0).click();
        // 3. Klicke Next
        nextButton().click();
        // 4. Prüfe, dass neue Frage angezeigt wird
        String questionAfter = questionText().getText();
        assertNotEquals(questionBefore, questionAfter);
    }

    // ============================
    // 🔴 Aufgabe 4 (schwer)
    // ============================

    @Test
    void correctAnswer_increasesScore() {
        openQuizPage();

        // 1. Klicke die richtige Antwort (Hinweis: index beachten!)
        answerButtons().get(1).click();
        // 2. Warte auf Score-Anzeige
        // 3. Prüfe, dass Score = 100 ist
        assertTrue(wait.until(ExpectedConditions.textToBe(
                By.cssSelector("[data-testid='score-text']"),
                "Question: 1 / 3 Score: 100"
        )));
    }

    // ============================
    // 🔴 Aufgabe 5 (Bonus)
    // ============================

    @Test
    void quiz_can_progress_through_multiple_questions() {
        openQuizPage();

        // 1. Mehrere Fragen nacheinander beantworten
        /* first question */
        String firstQuestion = questionText().getText();
        answerButtons().get(0).click();
        nextButton().click();

        /* second question */
        String secondQuestion = questionText().getText();
        answerButtons().get(0).click();
        nextButton().click();

        /* third question */
        String thirdQuestion = questionText().getText();

        // 2. Assert
        assertNotEquals(firstQuestion, secondQuestion, thirdQuestion);
    }

    // ============================
    // 🔴 Aufgabe 6 (Bonus)
    // ============================
    @Test
    void selectCategory_and_answerFirstQuestion_correctly() {
        // 1. Öffne die Seite (Startseite, nicht /quiz!)
        openHomePage();

        // 2. Finde die Kategorie-Combobox
        driver.findElement(By.linkText("Quiz")).click();

        // 3. Wähle "Applikationen testen"
        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("category-select")));
        Select categorySelect = new Select(dropdown);
        categorySelect.selectByVisibleText("Applikationen testen");

        // 4. Klicke auf "Quiz starten"
        driver.findElement(By.xpath("//button[text()='Quiz starten']")).click();

        // 5. Warte bis die Frage erscheint
        questionText();

        // 6. Klicke die richtige Antwort
        wait.until(webDriver -> !answerButtons().isEmpty());
        answerButtons().get(2).click();

        // 7. Prüfe, dass der Score 100 ist
        assertTrue(wait.until(ExpectedConditions.textToBe(
                By.cssSelector("[data-testid='score-text']"),
                "Question: 1 / 3 Score: 100"
        )));
    }
}