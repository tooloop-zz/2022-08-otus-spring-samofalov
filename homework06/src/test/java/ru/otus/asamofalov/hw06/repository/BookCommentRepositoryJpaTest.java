package ru.otus.asamofalov.hw06.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.asamofalov.hw06.domain.BookComment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayName("BookCommentRepositoryJpa must..")
@DataJpaTest
@Import({BookCommentRepositoryJpa.class, BookRepositoryJpa.class, AuthorRepositoryJpa.class, GenreRepositoryJpa.class})
class BookCommentRepositoryJpaTest {

    @Autowired
    BookCommentRepository bookCommentRepository;

    @Autowired
    TestEntityManager testEntityManager;

    @DisplayName("..return comment by id")
    @Test
    void shouldReturnComment() {
        assertNotNull(bookCommentRepository.getById(1));
    }

    @DisplayName("..append new comment to book")
    @Test
    void shouldAppendNewCommentToBook() {
        var comment = new BookComment("testComment", 1L);
        bookCommentRepository.append(comment);
        var found = testEntityManager.find(BookComment.class, comment.getId());
        assertEquals("testComment", found.getText());
    }

    @DisplayName("..update comment")
    @Test
    void shouldUpdateCommentById() {
        var comment = bookCommentRepository.getById(1);
        var oldLength = comment.getText().length();
        comment.setText("_" + comment.getText());
        bookCommentRepository.update(comment);
        var found = testEntityManager.find(BookComment.class, comment.getId());
        assertThat(found.getText().length()).isGreaterThan(oldLength);
    }

    @DisplayName("..delete comment")
    @Test
    void shouldDeleteCommentById() {
        var comment = bookCommentRepository.getById(1);
        bookCommentRepository.delete(comment);
        assertNull(bookCommentRepository.getById(comment.getId()));
    }
}