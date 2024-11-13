package api.models.getbooks;

import api.models.BookModel;
import lombok.Data;

import java.util.List;

@Data
public class GetBooksFromProfileResponseModel {
    String userId, username;
    List<BookModel> books;
}
