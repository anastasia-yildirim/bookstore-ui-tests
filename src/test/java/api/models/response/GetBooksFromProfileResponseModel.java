package api.models.response;

import lombok.Data;
import api.models.BookModel;

import java.util.List;

@Data
public class GetBooksFromProfileResponseModel {
    String userId, username;
    List<BookModel> books;
}
