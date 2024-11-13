package tests;

import helpers.LoginExtension;
import helpers.WithLogin;
import io.qameta.allure.*;
import models.Session;
import models.bookstore.BookModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.ProfilePage;
import steps.api.BookStoreApiSteps;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.sleep;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Epic("Книжный магазин DemoQA")
@Feature("Действия пользователя в профиле")
@Story("Добавление книг в профиль и удаление книг из профиля")
public class ProfileTests extends TestBase {

    private final BookStoreApiSteps bookStoreApiSteps = new BookStoreApiSteps();
    private final ProfilePage profilePage = new ProfilePage();

    @Test
    @WithLogin
    @DisplayName("Удаление книги из профиля")
    @Owner("@anastasiayildirim")
    @Severity(SeverityLevel.CRITICAL)
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
        collectionOfIsbns = bookStoreApiSteps.addBookToIsbnCollection(isbn, collectionOfIsbns);
        books = bookStoreApiSteps.addBookToProfile(collectionOfIsbns, session);
        assertEquals(collectionOfIsbns, books);
        profilePage.openProfilePage();
        profilePage.deleteBookFromProfile(isbn);
        sleep(1000);
        //Assert
        Allure.step("Убедиться, что книга не отображается в интерфейсе");
        assertThat(profilePage.getBookInProfile(isbn).isDisplayed()).isFalse();
        books = bookStoreApiSteps.getBooksFromProfile(session);
        Allure.step("Убедиться, что список книг пустой");
        assertTrue(books.isEmpty());
    }
}