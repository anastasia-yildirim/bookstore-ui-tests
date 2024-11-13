package api.models.addbooks;

import api.models.BookModel;
import lombok.Data;

import java.util.List;

@Data
public class AddBookToProfileResponseModel {

    List<BookModel> books;
}
