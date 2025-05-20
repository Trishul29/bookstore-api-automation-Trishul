package PojoClasses.update;

import PojoClasses.BaseResponse;
import io.qameta.allure.internal.shadowed.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpDateBookResponseBody extends BaseResponse {

    private String name;
    private String author;
    private int published_year;
    private String book_summary;
    private  int id;
    private String detail;
}
