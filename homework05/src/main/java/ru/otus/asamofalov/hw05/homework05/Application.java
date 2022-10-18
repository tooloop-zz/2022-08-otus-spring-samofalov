package ru.otus.asamofalov.hw05.homework05;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.h2.tools.Console;

import java.sql.SQLException;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		try {
			Console.main(args);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
