package todo

import com.graphql.demo.client.Argument
import com.graphql.demo.client.GraphqlClient
import com.graphql.demo.client.GrapqlArguments
import com.graphql.demo.client.QueryGenerator
import spock.lang.Specification
import todo.model.Response

class ToDoGraphQLTest extends Specification{

    private GraphqlClient graphqlClient = new GraphqlClient("https://graffiti-todo.herokuapp.com/graphql")

    def "test can get all todos"() {
        given:
        def query = QueryGenerator.generateQuery(Todos)

        when:
        def resp = graphqlClient.executeQuery(query).asPojo(GetToDosResponse)

        then:
        resp.data.todos.size() == 7

    }
}

@GrapqlArguments(
    @Argument(name = "complete", val = "true")
)
class Todos {
    String text
    Boolean complete
}

class Data {
    List<Todos> todos
}

class GetToDosResponse {
    Data data
}