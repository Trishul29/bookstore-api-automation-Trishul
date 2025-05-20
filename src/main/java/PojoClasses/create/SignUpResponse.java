package PojoClasses.create;

import PojoClasses.BaseResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data


public class SignUpResponse  extends BaseResponse {

//String message;
    @JsonProperty("detail")
String detail;

    @JsonProperty("message")
    String message;
}
