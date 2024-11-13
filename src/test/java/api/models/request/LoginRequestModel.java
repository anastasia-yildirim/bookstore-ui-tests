package api.models.request;

import lombok.Data;

@Data
public class LoginRequestModel {
    String userName, password;
}
