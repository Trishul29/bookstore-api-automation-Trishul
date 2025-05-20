package PojoClasses.create;

import PojoClasses.BaseResponse;
import io.qameta.allure.internal.shadowed.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor

public class LoginResponse  extends BaseResponse {

    private String access_token;
    private String token_type;
}
