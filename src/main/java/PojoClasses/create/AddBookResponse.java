package PojoClasses.create;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import PojoClasses.BaseResponse;


@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddBookResponse extends BaseResponse {

    private int id;
   private String name;
   private String author;
   private int published_year;
   private String book_summary;
   private  String detail;

}
