package com.estudo;

import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTest;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

@GraphQLTest
class ClientGraphQLTest {

    @Autowired
    GraphQLTestTemplate graphQLTestTemplate;

    //WIP
    @Test
    void getClients_shouldReturnAllFields() throws IOException {
        GraphQLResponse response = graphQLTestTemplate.perform("graphql/client.graphql", "getClients");

        Assertions.assertNotNull(response);
    }
}
