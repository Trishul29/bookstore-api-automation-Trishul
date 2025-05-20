package PojoClasses.create;

import PojoClasses.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginErrorReponse extends BaseResponse {
    String detail;
}
