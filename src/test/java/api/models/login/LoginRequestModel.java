package api.models.login;

import lombok.Data;

@Data
public class LoginRequestModel {
    String userName, password;
}
