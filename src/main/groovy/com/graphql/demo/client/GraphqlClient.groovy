package com.graphql.demo.client

import groovy.transform.TupleConstructor
import io.restassured.RestAssured
import io.restassured.http.ContentType

@TupleConstructor
class GraphqlClient {

    String url

    GraphqlResponse executeQuery(String query) {
        return new GraphqlResponse(RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(new Query(query))
                .when()
                .post(url)
                .then())
    }

    GraphqlResponse executeQuery(Query query){
        return new GraphqlResponse(RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(query)
                .when()
                .post(url)
                .then())
    }
}
