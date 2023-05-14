package app.ichat.domain.article;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author Steve Zou
 */

@Document("my_collection")
public class MyCollection {
    @Field("name")
    public String name;
    @Field("age")
    public Integer age;
}
