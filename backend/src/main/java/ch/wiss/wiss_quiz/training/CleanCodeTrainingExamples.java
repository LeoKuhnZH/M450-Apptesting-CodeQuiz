package ch.wiss.wiss_quiz.training;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class CleanCodeTrainingExamples {

    private static final Logger log = LoggerFactory.getLogger(CleanCodeTrainingExamples.class);

    // Schlechter Methodenname + unnötige Variable + verschachtelte ifs
    public String doStuff(String name, int points, boolean active) {
        String result;

        if (active) {
            if (points > 80) {
                result = "User " + name + " passed";
            } else {
                result = "User " + name + " failed";
            }
        } else {
            result = "User inactive";
        }

        return result;
    }

    // Duplikation (DRY verletzt)
    public void printAdminMessage(String name) {
        for (String s : Arrays.asList("Hello " + name, "Welcome to WissQuiz", "Role: Admin")) {
            log.info(s);
        }
    }

    public void printTeacherMessage(String name) {
        for (String s : Arrays.asList("Hello " + name, "Welcome to WissQuiz", "Role: Teacher")) {
            log.info(s);
        }
    }

    // Magic Number
    public boolean isQuizPassed(int scorePercent) {
        return scorePercent >= 60;
    }

    public boolean getsCertificate(int scorePercent) {
        return scorePercent >= 60;
    }

    // Zu viele Verantwortlichkeiten + schlechte Lesbarkeit
    public int calculateSomething(List<Integer> numbers) {
        int result = 0;

        for (int n : numbers) {
            if (n > 0) {
                if (n % 2 == 0) {
                    result = result + n * 2;
                } else {
                    result = result + n;
                }
            }
        }

        return result;
    }
    
    // Lange Methode + mehrere Verantwortlichkeiten + schlechte Lesbarkeit
    public String processUserData(String name, int age, boolean isMember, int points) {
        String result;

        if (name != null && !name.isEmpty()) {
            if (age > 18) {
                if (isMember) {
                    if (points > 100) {
                        result = "Premium Member: " + name;
                    } else {
                        result = "Standard Member: " + name;
                    }
                } else {
                    result = "Guest: " + name;
                }
            } else {
                result = "Too young";
            }
        } else {
            result = "Invalid name";
        }

        return result;
    }
    
    // Schlechte Parameter-Namen + unnötige Abkürzungen
    public boolean chk(int a, int b) {
        return a > b;
    }
    
 // Schlechte Fehlerbehandlung + unklare Logik
    public int divide(int a, int b) {
        int result = 0;

        try {
            result = a / b;
        } catch (Exception e) {
            log.info("Error");
        }

        return result;
    }
    
 // Unnötige Komplexität + schlechte Lesbarkeit
    public boolean isEvenAndPositive(int number) {
        if (number > 0) {
            return number % 2 == 0;
        } else {
            return false;
        }
    }
}