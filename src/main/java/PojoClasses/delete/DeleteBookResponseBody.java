package PojoClasses.delete;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import PojoClasses.BaseResponse;
@Data
@JsonIgnoreProperties(ignoreUnknown = true)

public class DeleteBookResponseBody  extends BaseResponse {
    @JsonProperty("message")
    private String message;
    private  String detail;

}
