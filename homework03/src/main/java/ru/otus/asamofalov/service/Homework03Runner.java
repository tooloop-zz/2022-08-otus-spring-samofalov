package ru.otus.asamofalov.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.otus.asamofalov.domain.Question;
import ru.otus.asamofalov.domain.Student;
import ru.otus.asamofalov.exception.IOServiceCheckException;
import ru.otus.asamofalov.exception.QuestionServiceException;
import ru.otus.asamofalov.io.IOService;

@Slf4j
@Component
public class Homework03Runner implements CommandLineRunner {

    private final QuestionService questionService;
    private final IOService ioService;
    private final int rightAnswersLimit;
    private final AppMessageSourceImpl appMessageSourceImpl;

    public Homework03Runner(QuestionService questionService,
                            IOService ioService,
                            @Value("${rightAnswersLimit}") int rightAnswersLimit, AppMessageSourceImpl appMessageSourceImpl) {
        this.questionService = questionService;
        this.ioService = ioService;
        this.rightAnswersLimit = rightAnswersLimit;
        this.appMessageSourceImpl = appMessageSourceImpl;
    }

    @Override
    public void run(String... args) {

        try {
            Student student = getStudent();
            var questions = questionService.ask();
            var rightAnswersCount = questions.stream().filter(Question::isAnswerRight).count();
            student.setPassed(rightAnswersCount >= rightAnswersLimit);

            ioService.outputFormattedString(appMessageSourceImpl.getMessage("resultTemplate",
                    student.getFirstName(),
                    student.getLastName(),
                    rightAnswersCount,
                    rightAnswersLimit,
                    student.isPassed() ? appMessageSourceImpl.getMessage("resultPassed") : appMessageSourceImpl.getMessage("resultNotPassed")));
        } catch (QuestionServiceException e) {
            log.error("Datasource error: " + e.getMessage());
        } catch (IOServiceCheckException e) {
            log.error("ioService must be defined");
        } catch (Exception e) {
            log.error("Application execute error: " + e.getMessage());
        }
    }

    private Student getStudent() {
        Student result = new Student();
        result.setLastName(ioService.readStringWithPrompt(appMessageSourceImpl.getMessage("studentLastName")));
        result.setFirstName(ioService.readStringWithPrompt(appMessageSourceImpl.getMessage("studentFirstName")));
        return result;
    }
}