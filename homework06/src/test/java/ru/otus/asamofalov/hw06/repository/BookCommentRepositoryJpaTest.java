package ru.otus.asamofalov.hw06.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.asamofalov.hw06.domain.BookComment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayName("BookCommentRepositoryJpa must..")
@DataJpaTest
@Import({BookCommentRepositoryJpa.class, BookRepositoryJpa.class, AuthorRepositoryJpa.class, GenreRepositoryJpa.class})
class BookCommentRepositoryJpaTest {

    @Autowired
    BookCommentRepository bookCommentRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    TestEntityManager testEntityManager;

    @DisplayName("..return all comments for book")
    @Test
    void shouldReturnAllCommentsForBook() {
        assertNotEquals(0, bookCommentRepository.getByBookId(1).size());
    }

    @DisplayName("..return comment by id")
    @Test
    void shouldReturnComment() {
        assertNotNull(bookCommentRepository.getById(1));
    }

    @DisplayName("..append new comment to book")
    @Test
    void shouldAppendNewCommentToBook() {
        var book = bookRepository.getAll().get(0);
        var comment = new BookComment("testComment", book);
        bookCommentRepository.appendComment(comment);
        var found = testEntityManager.find(BookComment.class, comment.getId());
        assertThat(found).isNotNull().usingRecursiveComparison().isEqualTo(comment);
    }

    @DisplayName("..update comment")
    @Test
    void shouldUpdateCommentById() {
        var comment = bookCommentRepository.getByBookId(1L).get(0);
        comment.setText("_" + comment.getText());
        bookCommentRepository.updateComment(comment);
        var found = testEntityManager.find(BookComment.class, comment.getId());
        assertThat(found).isNotNull().usingRecursiveComparison().isEqualTo(comment);
    }

    @DisplayName("..delete comment")
    @Test
    void shouldDeleteCommentById() {
        var comment = bookCommentRepository.getByBookId(1L).get(0);
        bookCommentRepository.deleteComment(comment);
        assertNull(bookCommentRepository.getById(comment.getId()));
    }
}