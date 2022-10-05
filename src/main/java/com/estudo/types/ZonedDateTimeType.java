package com.estudo.types;


import com.estudo.utils.DateUtils;
import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;
import graphql.schema.GraphQLScalarType;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Objects;

@Component
public class ZonedDateTimeType extends GraphQLScalarType {
    private static final String ZONED_DATE_TIME = "ZonedDateTime";
    private static final String EXCEPTION_MESSAGE = "Invalid Date: ";

    public ZonedDateTimeType() {
        super(ZONED_DATE_TIME, "Zoned Date Time", new Coercing<ZonedDateTime, String>() {

            @Override
            public String serialize(Object object) throws CoercingSerializeException {
                if (object instanceof ZonedDateTime) {
                    return DateUtils.parseDate((ZonedDateTime) object);
                }
                throw new CoercingSerializeException(EXCEPTION_MESSAGE + object);
            }

            @Override
            public ZonedDateTime parseValue(Object object) throws CoercingParseValueException {
                if (object instanceof String) {
                    ZonedDateTime date = DateUtils.parseString((String) object);

                    if(Objects.nonNull(date)) return date;
                }
                throw new CoercingParseValueException(EXCEPTION_MESSAGE + object);
            }

            @Override
            public ZonedDateTime parseLiteral(Object object) throws CoercingParseLiteralException {
                if (!(object instanceof StringValue)) return null;

                String string = ((StringValue) object).getValue();
                ZonedDateTime date = DateUtils.parseString(string);

                if(Objects.nonNull(date)) return date;

                throw new CoercingParseLiteralException(EXCEPTION_MESSAGE + object);
            }
        });
    }
}
