package ru.otus.asamofalov;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import ru.otus.asamofalov.service.ApplicationRunner;

@ComponentScan
@PropertySource("classpath:application.properties")
public class Main {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        context.getBean(ApplicationRunner.class).run();
    }
}
