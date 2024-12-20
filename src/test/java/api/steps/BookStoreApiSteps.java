package api.steps;

import api.models.BookModel;
import api.models.Session;
import api.models.addbooks.AddBookToProfileRequestModel;
import api.models.addbooks.AddBookToProfileResponseModel;
import api.models.getbooks.GetBooksFromProfileResponseModel;
import api.models.getbooks.GetBooksFromStoreResponseModel;
import io.qameta.allure.Step;

import java.util.List;
import java.util.Random;

import static api.specs.BookStoreSpec.*;
import static io.restassured.RestAssured.given;

public class BookStoreApiSteps {

    @Step("Удалить все книги из профиля")
    public void deleteAllBooksFromProfile(Session session) {
        given(bookStoreRequestSpec)
                .header("Authorization", "Bearer " + session.getToken())
                .queryParam("UserId", session.getUserId())
                .when()
                .delete("/BookStore/v1/Books")
                .then()
                .spec(bookStoreResponseSpec204);
    }

    @Step("Получить список книг, доступных в магазине")
    public List<BookModel> getBooksFromStore() {
        GetBooksFromStoreResponseModel response =
                given(bookStoreRequestSpec)
                        .when()
                        .get("/BookStore/v1/books")
                        .then()
                        .spec(bookStoreResponseSpec200)
                        .extract().as(GetBooksFromStoreResponseModel.class);

        return response.getBooks();
    }

    @Step("Выбрать любую книгу")
    public String selectRandomBook(List<BookModel> books) {
        Random random = new Random();
        int randomIndex = random.nextInt(books.size());
        BookModel randomBook = books.get(randomIndex);

        return randomBook.getIsbn();
    }

    @Step("Добавить книгу в список книг")
    public List<BookModel> addBookToIsbnCollection(String isbn, List<BookModel> collectionOfIsbns) {
        BookModel selectedBook = new BookModel();
        selectedBook.setIsbn(isbn);
        collectionOfIsbns.add(selectedBook);

        return collectionOfIsbns;
    }

    @Step("Добавить выбранную книгу в профиль")
    public List<BookModel> addBookToProfile(List<BookModel> collectionOfIsbns, Session session) {

        AddBookToProfileRequestModel bodyData = new AddBookToProfileRequestModel();
        bodyData.setCollectionOfIsbns(collectionOfIsbns);
        bodyData.setUserId(session.getUserId());
        AddBookToProfileResponseModel response =
                given(bookStoreRequestSpec)
                        .header("Authorization", "Bearer " + session.getToken())
                        .body(bodyData)
                        .when()
                        .post("/BookStore/v1/Books")
                        .then()
                        .spec(bookStoreResponseSpec201)
                        .extract().as(AddBookToProfileResponseModel.class);

        return response.getBooks();
    }

    @Step("Получить список книг из профиля по API")
    public List<BookModel> getBooksFromProfile(Session session) {
        GetBooksFromProfileResponseModel response =
                given(bookStoreRequestSpec)
                        .when()
                        .header("Authorization", "Bearer " + session.getToken())
                        .get("/Account/v1/User/" + session.getUserId())
                        .then()
                        .spec(bookStoreResponseSpec200)
                        .extract().as(GetBooksFromProfileResponseModel.class);

        return response.getBooks();
    }
}