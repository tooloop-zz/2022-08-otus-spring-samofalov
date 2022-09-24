package ru.otus.asamofalov.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.otus.asamofalov.domain.Question;
import ru.otus.asamofalov.domain.Student;
import ru.otus.asamofalov.exception.IOServiceCheckException;
import ru.otus.asamofalov.exception.QuestionServiceException;

@Component
public class ApplicationRunner {

    private final QuestionService questionService;
    private final int rightAnswersLimit;
    private IOService ioService;

    public ApplicationRunner(QuestionService questionService, @Value("${rightAnswersLimit}") int rightAnswersLimit) {
        this.questionService = questionService;
        this.rightAnswersLimit = rightAnswersLimit;
    }

    public void run(IOService ioService) {

        try {
            if (ioService == null) throw new IOServiceCheckException();
            this.ioService = ioService;

            Student student = getStudent();
            var questions = questionService.getAll();
            ioService.outputString("answer these questions:");
            int counter = 1;
            int rightAnswersCount = 0;
            for (Question question : questions) {
                question.setUserAnswer(ioService.readStringWithPrompt(counter + ". " + question.asString() + ":"));
                if (question.isAnswerRight()) rightAnswersCount++;
                counter++;
            }
            student.setPassed(rightAnswersCount >= rightAnswersLimit);

            ioService.outputFormattedString("%s %s, you answered %d questions correctly (must be %d), your test is %s",
                    new Object[]{
                            student.getFirstName(),
                            student.getLastName(),
                            rightAnswersCount,
                            rightAnswersLimit,
                            student.isPassed() ? "passed" : "not passed"
                    });


        } catch (QuestionServiceException e) {
            System.out.println("Datasource error: " + e.getMessage());
        } catch (IOServiceCheckException e) {
            System.out.println("ioService must be defined");
        } catch (Exception e) {
            System.out.println("Application execute error: " + e.getMessage());
        }
    }


    private Student getStudent() {

        Student result = new Student();

        result.setFirstName(ioService.readStringWithPrompt("Enter your first name: "));
        result.setLastName(ioService.readStringWithPrompt("Enter your last name: "));

        return result;
    }
}
