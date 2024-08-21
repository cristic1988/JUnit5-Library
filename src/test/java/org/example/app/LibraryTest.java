package org.example.app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

class LibraryTest {

    private static Library library;

    @BeforeAll
    public static void setup() {
        library = new Library();
    }

    @AfterEach
    public void init() {
        library.getBooks().clear();
    }

    @Test
    @DisplayName("Test if product is really added.")
    void whenAddBookThenBookIsAdded() {
        Book book = new Book("Misery", "Stiven King");
        int expectedBookCount = 1;
        library.addBook(book);
        assertEquals(expectedBookCount, library.getBookCount(),
                "Shop should have at least 1 book!");
    }

    @Test
    @DisplayName("When null book is adding then exception can be thrown.")
    public void whenAddNullBookThenThrowException() {
        Book invalidBook = null;
        String expectedExceptionMessage =
                "Parameter [book] must not be null!";
        NullPointerException exception =
                assertThrows(NullPointerException.class, () ->
                        library.addBook(invalidBook));
        assertEquals(expectedExceptionMessage, exception.getMessage(),
                "Incorrect exception message!");
    }

    @Test
    @DisplayName("Test if book is really removed.")
    void whenRemoveExistingBookThenBookIsRemoved() {
        Book book = new Book("Misery", "Stiven King");
        int expectedBookCount = 0;
        library.addBook(book);
        boolean isBookRemoved = library.removeBook(book);
        assertTrue(isBookRemoved, "Result should be 'true'!");
        assertEquals(expectedBookCount, library.getBookCount(),
                "Shop shouldn't have any books!");
    }

    @Test
    @DisplayName("Test removing non-existing book.")
    public void whenRemoveNonExistingBookThenBookIsNotRemoved() {
        Book book = new Book("Misery", "Stiven King");
        Book toRemove = new Book("Harry Potter", "Joanne Rowling");
        int expectedBookCount = 1;
        library.addBook(book);
        boolean isBookRemoved = library.removeBook(toRemove);
        assertFalse(isBookRemoved, "Result should be 'false'!");
        assertEquals(expectedBookCount, library.getBookCount(),
                "Shop should have at least 1 book!");
    }

    @Test
    @DisplayName("When null book is removing then exception can be thrown.")
    public void whenRemoveNullBookThenThrowException() {
        Book toRemove = null;
        String expectedExceptionMessage =
                "Parameter [book] must not be null!";
        NullPointerException exception =
                assertThrows(NullPointerException.class,
                        () -> library.removeBook(toRemove));
        assertEquals(expectedExceptionMessage, exception.getMessage(),
                "Incorrect exception message!");
    }
}

