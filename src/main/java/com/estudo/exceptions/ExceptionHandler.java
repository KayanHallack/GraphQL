package com.estudo.exceptions;

import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.servlet.GraphQLErrorHandler;
import graphql.validation.ValidationError;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ExceptionHandler implements GraphQLErrorHandler {

    private static final String GENERIC_MESSAGE = "Unable to Process the Request";

    @Override
    public List<GraphQLError> processErrors(List<GraphQLError> exceptions) {
        return exceptions.stream()
                .map(exception -> {
                    if (exception instanceof ExceptionWhileDataFetching){
                        ExceptionWhileDataFetching fetchingDataException = (ExceptionWhileDataFetching) exception;
                        return new GenericException(fetchingDataException.getException().getMessage());
                    }

                    if (exception instanceof ValidationError){
                        ValidationError fetchingDataException = (ValidationError) exception;
                        return new GenericException(fetchingDataException.getDescription());
                    }

                    return new GenericException(GENERIC_MESSAGE);
                })
                .collect(Collectors.toList());
    }
}
