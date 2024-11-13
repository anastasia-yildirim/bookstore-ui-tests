package api.models.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GenerateTokenRequestModel {
    String userName, password;
}
