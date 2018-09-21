package com.graphql.demo.client

import groovy.transform.TupleConstructor
import io.restassured.response.ValidatableResponse
import org.hamcrest.Matcher

@TupleConstructor
class GraphqlResponse {

    ValidatableResponse response

    def <T> T asPojo(String path, Class<T> tClass) {
        return response.log()
                .all()
                .extract()
                .body()
                .jsonPath()
                .getObject(path, tClass)
    }

    def <T> T asPojo(Class<T> tClass) {
        return response.log()
                .all()
                .extract()
                .body()
                .as(tClass)
    }

    def assertThat(String path, Matcher matcher){
        response.log().all().body(path, matcher)
    }
}
