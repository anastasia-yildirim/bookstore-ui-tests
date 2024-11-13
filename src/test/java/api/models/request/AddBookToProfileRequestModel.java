package api.models.request;

import lombok.Data;
import api.models.BookModel;

import java.util.List;

@Data
public class AddBookToProfileRequestModel {

    String userId;
    List<BookModel> collectionOfIsbns;
}
