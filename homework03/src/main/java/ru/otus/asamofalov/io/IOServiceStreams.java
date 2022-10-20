package ru.otus.asamofalov.io;

import org.springframework.stereotype.Service;

import java.io.PrintStream;
import java.util.Scanner;

@Service
public class IOServiceStreams implements IOService {

    private final PrintStream output;
    private final Scanner input;

    public IOServiceStreams() {
        output = System.out;
        input = new Scanner(System.in);
    }

    @Override
    public void outputString(String s) {
        output.print(s);
    }

    @Override
    public String readStringWithPrompt(String prompt) {
        outputString(prompt);
        return input.nextLine();
    }

    @Override
    public String readStringWithFormattedPrompt(String format, Object... args) {
        outputString(String.format(format, args));
        return input.nextLine();
    }

    @Override
    public void outputFormattedString(String format, Object... args) {
        outputString(String.format(format, args));
    }

    @Override
    public void outputStringLn(String s) {
        output.println(s);
    }
}
