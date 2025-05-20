package PojoClasses.create;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddBookRequestBody {
    private String name;
    private String author;
    private int published_year;
    private String book_summary;
    private  Integer id;
}
