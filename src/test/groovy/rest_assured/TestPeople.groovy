package rest_assured

import com.graphql.demo.client.GraphqlClient
import com.graphql.demo.client.QueryGenerator
import rest_assured.model.Film
import rest_assured.model.Person
import spock.lang.Specification

class TestPeople extends Specification {

    private GraphqlClient graphqlClient = new GraphqlClient("http://localhost:46767")


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
        def person = graphqlClient.executeQuery(query).asPojo(Person)

        then:
        person.name == 'Luke Skywalker'
    }

    def "test can get film"() {
        given:
        def query = QueryGenerator.generateQuery("film", ["filmID": "2"], ["title", "director"])

        when:
        def film = graphqlClient.executeQuery(query).asPojo(Film)

        then:
        film.director == "Irvin Kershner"
        film.title == "The Empire Strikes Back"
    }
}
