package client

import groovy.json.JsonSlurper
import io.restassured.RestAssured
import io.restassured.http.ContentType

class GraphqlClient {

    def <T> T executeQuery(String query, Class<T> aClass) {


        return RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(new Query(query))
                .when()
                .post("http://localhost:44935")
                .then().log().all().extract()
                .response()
                .jsonPath().getObject("data.${aClass.simpleName.toLowerCase()}", aClass)
    }


}
