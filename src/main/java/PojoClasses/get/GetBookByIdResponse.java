package PojoClasses.get;

import PojoClasses.BaseResponse;
import io.qameta.allure.internal.shadowed.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetBookByIdResponse extends BaseResponse {
    private int id;
    private String name;
    private String author;
    private int published_year;
    private String book_summary;
    private  String detail;

}
