package app.ichat.config.mongo;

import com.fasterxml.jackson.databind.util.Converter;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.expression.BeanFactoryAccessor;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.servlet.view.tiles3.SpringBeanPreparerFactory;

/**
 * @author Steve Zou
 */
@Configuration
@EnableMongoRepositories("app.ichat.repository")
public class MongoConfig extends AbstractMongoClientConfiguration {
    @Value("${spring.data.mongodb.database}")
    private String database;
    @Autowired
    ZonedDateTimeReadConverter zonedDateTimeReadConverter;
    @Autowired
    ZonedDateTimeWriteConverter zonedDateTimeWriteConverter;


    @Override
    protected String getDatabaseName() {
        return database;
    }

    @Override
    public MongoCustomConversions customConversions() {
        ArrayList<Object> converters = new ArrayList<>();
        converters.add(zonedDateTimeReadConverter);
        converters.add(zonedDateTimeWriteConverter);
        return new MongoCustomConversions(converters);
    }
}
