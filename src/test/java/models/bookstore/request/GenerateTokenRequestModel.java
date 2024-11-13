package models.bookstore.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GenerateTokenRequestModel {
    String userName, password;
}
