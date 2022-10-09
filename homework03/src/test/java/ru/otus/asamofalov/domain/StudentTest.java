package ru.otus.asamofalov.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Student class")
class StudentTest {

    @DisplayName("constructed correctly")
    @Test
    void shouldHaveCorrectConstructor() {

        Student student = new Student();
        student.setFirstName("firstName");
        student.setLastName("lastName");
        student.setPassed(true);

        assertAll(
                () -> assertEquals("firstName", student.getFirstName()),
                () -> assertEquals("lastName", student.getLastName()),
                () -> assertTrue(student.isPassed())
        );
    }


}