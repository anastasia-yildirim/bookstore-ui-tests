package api.models.response;

import api.models.BookModel;
import lombok.Data;

import java.util.List;

@Data
public class AddBookToProfileResponseModel {

    List<BookModel> books;
}
