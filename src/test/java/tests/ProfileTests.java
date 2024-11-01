package tests;

import helpers.LoginExtension;
import helpers.WithLogin;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import models.Session;
import models.bookstore.BookModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import steps.api.BookStoreApiSteps;
import steps.ui.BookStoreUiSteps;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.*;

@Tag("bookstore")
@Epic("")
@Story("")
@Feature("")
public class ProfileTests extends TestBase {

    BookStoreApiSteps bookStoreApiSteps = new BookStoreApiSteps();
    BookStoreUiSteps bookStoreUiSteps = new BookStoreUiSteps();

    @WithLogin
    @DisplayName("Удаление книги из профиля")
    @Owner("@anastasiayildirim")
    @Test
    void deleteBookFromProfileTest() {
        //Arrange
        Session session = LoginExtension.getSession();
        List<BookModel> books;
        String isbn;
        List<BookModel> collectionOfIsbns = new ArrayList<>();

        //Act
        bookStoreApiSteps.deleteAllBooksFromProfile(session);
        books = bookStoreApiSteps.getBooksFromStore();
        assertNotEquals(null, books);

        isbn = bookStoreApiSteps.selectRandomBook(books);
        bookStoreApiSteps.addBookToIsbnCollection(isbn, collectionOfIsbns);
        books = bookStoreApiSteps.addBookToProfile(collectionOfIsbns, session);
        assertEquals(collectionOfIsbns, books);

        bookStoreUiSteps.openProfile();
        bookStoreUiSteps.deleteBookFromProfile(isbn);

        //Assert
        $(".ReactTable").$("a[href='/profile?book=" + isbn + "']").shouldNot(exist);
        books = bookStoreApiSteps.getBooksFromProfile(session);
        assertTrue(books.isEmpty());
    }
}