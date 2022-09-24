package ru.otus.asamofalov;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import ru.otus.asamofalov.service.ApplicationRunner;
import ru.otus.asamofalov.service.IOService;
import ru.otus.asamofalov.service.IOServiceStreams;

@ComponentScan
@PropertySource("classpath:application.properties")
public class Main {

    public static void main(String[] args) {

        IOService ioService = new IOServiceStreams(System.out, System.in);

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        context.getBean(ApplicationRunner.class).run(ioService);
    }
}
