package api.models.auth;

import lombok.Data;

@Data
public class GenerateTokenResponseModel {
    String token, status, result, expires;
}
