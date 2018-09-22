package rest_assured

import com.graphql.demo.client.Argument
import com.graphql.demo.client.Config
import com.graphql.demo.client.GraphqlClient
import com.graphql.demo.client.GraphqlProperty
import com.graphql.demo.client.GrapqlArguments
import com.graphql.demo.client.QueryGenerator
import rest_assured.model.Film
import rest_assured.model.Person
import spock.lang.Specification

class TestPeople extends Specification {

    private GraphqlClient graphqlClient = new GraphqlClient(Config.URL)


    def "test can get person"() {
        given:

        def query = """
            {
              person(personID: 1){
                name
                eyeColor
                gender
                height
              }
            }
        """

        when:
        def person = graphqlClient.execute(query).asPojo(Person)

        then:
        person.name == 'Luke Skywalker'
    }

    def "test can get film"() {
        given:
        def query = QueryGenerator.generateQuery("film", ["filmID": "2"], ["title", "director"])

        when:
        def film = graphqlClient.execute(query).asPojo(Film)

        then:
        film.director == "Irvin Kershner"
        film.title == "The Empire Strikes Back"
    }

    def "test can get all films"() {
        given:
        def query = QueryGenerator.generateQuery(AllFilms)

        when:
        def string = graphqlClient.execute(query).response.extract().body().asString()

        then:
        string == "{\"data\":{\"allFilms\":{\"totalCount\":7,\"films\":[{\"title\":\"A New Hope\"}],\"pageInfo\":{\"hasNextPage\":true}}}}"
    }
}

@GrapqlArguments(@Argument(name = "first", val = "1"))
class AllFilms {
    String totalCount
    @GraphqlProperty
    Films films
    @GraphqlProperty
    PageInfo pageInfo
}

class Films {
    String title
    String director
}

class PageInfo {
    Boolean hasNextPage
}
