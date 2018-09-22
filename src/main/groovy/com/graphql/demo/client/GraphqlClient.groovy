package com.graphql.demo.client

import groovy.transform.TupleConstructor
import io.restassured.RestAssured
import io.restassured.http.ContentType

@TupleConstructor
class GraphqlClient {

    String url

    GraphqlResponse execute(String query) {
        return execute(new Query(query))
    }

    GraphqlResponse execute(Query query){
        return new GraphqlResponse(RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(query)
                .when()
                .post(url)
                .then())
    }
}
