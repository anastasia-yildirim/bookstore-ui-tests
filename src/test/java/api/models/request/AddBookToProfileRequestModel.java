package api.models.request;

import api.models.BookModel;
import lombok.Data;

import java.util.List;

@Data
public class AddBookToProfileRequestModel {

    String userId;
    List<BookModel> collectionOfIsbns;
}
