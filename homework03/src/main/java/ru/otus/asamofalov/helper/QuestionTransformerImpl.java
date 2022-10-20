package ru.otus.asamofalov.helper;

import org.springframework.stereotype.Component;
import ru.otus.asamofalov.domain.ChosenAnswerQuestion;
import ru.otus.asamofalov.domain.FreeAnswerQuestion;
import ru.otus.asamofalov.domain.Question;
import ru.otus.asamofalov.exception.QuestionTransformerException;
import ru.otus.asamofalov.service.AppMessageSource;

@Component
public class QuestionTransformerImpl implements QuestionTransformer {

    private final AppMessageSource appMessageSource;

    public QuestionTransformerImpl(AppMessageSource appMessageSource) {
        this.appMessageSource = appMessageSource;
    }

    @Override
    public String asString(Question question) {
        if (question instanceof FreeAnswerQuestion) {
            return appMessageSource.getMessage("freeFormAnswerTip", question.getText());
        } else if (question instanceof ChosenAnswerQuestion) {
            return appMessageSource.getMessage("chosenAnswerTip", question.getText(),
                    String.join(", ", ((ChosenAnswerQuestion) question).getAnswers()));
        } else throw new QuestionTransformerException();
    }
}
