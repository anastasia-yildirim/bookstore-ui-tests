package api.models.response;

import api.models.BookModel;
import lombok.Data;

import java.util.List;

@Data
public class GetBooksFromStoreResponseModel {
    List<BookModel> books;
}