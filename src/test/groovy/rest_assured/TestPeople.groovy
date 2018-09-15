package rest_assured

import client.GraphqlClient
import client.model.Person
import spock.lang.Specification

class TestPeople extends Specification{

    private GraphqlClient graphqlClient = new GraphqlClient();

    def "test can get person"(){
        given:

        def query = """
            query getLuke {
              person(personID: 1){
                name
                eyeColor
                gender
                height
              }
            }
        """

        when:
        def response = graphqlClient.executeQuery(query, Person)

        then:
        response.name == 'Luke Skywalker'
    }
}
