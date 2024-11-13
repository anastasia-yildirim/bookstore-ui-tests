package api.models.response;

import lombok.Data;
import api.models.BookModel;

import java.util.List;

@Data
public class GetBooksFromStoreResponseModel {
    List<BookModel> books;
}