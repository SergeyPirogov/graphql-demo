package rest_assured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;

public class TestGetFilm {

    @Test
    public void testCanGetFilm() {
        String req = "{\"query\":\" { film (filmID: 1){ id title director }}\"}";

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(req)
                .when()
                .post("http://localhost:44935")
                .then().log().all()
                .body("data.film.title", equalTo("A New Hope"))
                .body("data.film.director", equalTo("George Lucas"));
    }


    @Test
    public void testCanGetFilmById() {
        String req = "{\"query\":\"query film($id: ID) { film (filmID: $id){ id title director }}\"," +
                "\"variables\": {\"id\": \"1\"}}";

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(req)
                .when()
                .post("http://localhost:44935")
                .then().log().all()
                .body("data.film.title", equalTo("A New Hope"))
                .body("data.film.director", equalTo("George Lucas"));
    }
}
