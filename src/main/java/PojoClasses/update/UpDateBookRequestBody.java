package PojoClasses.update;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpDateBookRequestBody {
    private String name;
    private String author;
    private int published_year;
    private String book_summary;
}
