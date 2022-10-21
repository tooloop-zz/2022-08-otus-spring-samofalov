package ru.otus.asamofalov.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import ru.otus.asamofalov.domain.Question;
import ru.otus.asamofalov.domain.Student;
import ru.otus.asamofalov.exception.IOServiceCheckException;
import ru.otus.asamofalov.exception.QuestionServiceException;
import ru.otus.asamofalov.io.IOService;

import java.util.Locale;

@Slf4j
@ShellComponent
public class Homework03Runner {

    private final QuestionService questionService;
    private final IOService ioService;
    private final int rightAnswersLimit;
    private final AppMessageSourceImpl appMessageSourceImpl;
    private Student student;

    public Homework03Runner(QuestionService questionService,
                            IOService ioService,
                            @Value("${rightAnswersLimit}") int rightAnswersLimit, AppMessageSourceImpl appMessageSourceImpl) {
        this.questionService = questionService;
        this.ioService = ioService;
        this.rightAnswersLimit = rightAnswersLimit;
        this.appMessageSourceImpl = appMessageSourceImpl;
    }

    @ShellMethod(key = {"r", "run"})
    @ShellMethodAvailability(value = "isRunCommandAvailable")
    public void run() {

        try {
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

    @ShellMethod(key = {"l", "login"})
    public String login() {
        student = new Student();
        student.setLastName(ioService.readStringWithPrompt(appMessageSourceImpl.getMessage("studentLastName")));
        student.setFirstName(ioService.readStringWithPrompt(appMessageSourceImpl.getMessage("studentFirstName")));
        return appMessageSourceImpl.getMessage("runTestPrompt");
    }

    private Availability isRunCommandAvailable() {
        return student == null ?
                Availability.unavailable(appMessageSourceImpl.getMessage("loginPleasePrompt")) :
                Availability.available();
    }

    @ShellMethod(key = {"c", "change-locale"})
    public void changeLocale(String locale) {
        appMessageSourceImpl.setNewLocale(new Locale(locale));
    }
}