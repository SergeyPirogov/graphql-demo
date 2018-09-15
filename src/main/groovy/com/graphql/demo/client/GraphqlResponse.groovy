package com.graphql.demo.client

import groovy.transform.TupleConstructor
import io.restassured.response.ValidatableResponse

@TupleConstructor
class GraphqlResponse {

    ValidatableResponse response

    def <T> T asPojo(Class<T> tClass) {
        return response.log()
                .all()
                .extract()
                .body()
                .jsonPath()
                .getObject("data.${tClass.simpleName.toLowerCase()}", tClass)
    }
}
