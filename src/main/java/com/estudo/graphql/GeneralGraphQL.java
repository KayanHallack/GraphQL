package com.estudo.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

@Component
public class GeneralGraphQL implements GraphQLQueryResolver {

    public String helloWorld(){
        return "Hello World!";
    }
}
