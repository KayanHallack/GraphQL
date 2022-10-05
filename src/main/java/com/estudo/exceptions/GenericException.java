package com.estudo.exceptions;

import com.fasterxml.jackson.annotation.JsonIgnore;
import graphql.servlet.GenericGraphQLError;

import java.util.List;
import java.util.Map;

class GenericException extends GenericGraphQLError {

    GenericException(String message) {
        super(message);
    }

    @Override
    @JsonIgnore
    public List<Object> getPath() {
        return null;
    }

    @Override
    @JsonIgnore
    public Map<String, Object> getExtensions() {
        return null;
    }
}
