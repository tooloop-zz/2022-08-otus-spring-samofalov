package ru.otus.asamofalov;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.asamofalov.domain.Question;
import ru.otus.asamofalov.service.QuestionService;

public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");

        QuestionService questionService = context.getBean(QuestionService.class);

        System.out.println("Questions list:");
        int counter = 1;
        for (Question question : questionService.getAll()) {
            System.out.println(counter + ". " + question.asString() + ":");
            counter++;
        }

        context.close();
    }
}
