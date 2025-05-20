package PojoClasses.get;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Book {
    private String book_summary;
    private int id;
    private String name;
    private String author;
    private int published_year;

}
