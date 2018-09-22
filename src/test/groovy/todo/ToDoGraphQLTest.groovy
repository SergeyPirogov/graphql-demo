package todo

import com.graphql.demo.client.Argument
import com.graphql.demo.client.GraphqlClient
import com.graphql.demo.client.GraphqlResponse
import com.graphql.demo.client.GrapqlArguments
import com.graphql.demo.client.QueryGenerator
import spock.lang.Specification

import static org.hamcrest.Matchers.equalTo
import static org.hamcrest.Matchers.hasSize

class ToDoGraphQLTest extends Specification{

    private GraphqlClient graphqlClient = new GraphqlClient("https://graffiti-todo.herokuapp.com/graphql")

    def "test can get all todos using raw query"(){
        given:

        def query = """
              query { 
                  todos (complete:false) { 
                    text 
                    complete 
                  } 
              }
        """
        when:
        def response = graphqlClient.execute(query)

        then:
        response.assertThat("data.todos", hasSize(3))
    }


    def "test can get all todos"() {
        given:
        def query = QueryGenerator.generateQuery(Todos)

        when:
        def resp = graphqlClient.execute(query).asPojo(GetToDosResponse)

        then:
        resp.data.todos.size() == 3
    }

    def "test can add todo"(){
        given:
        def mutation = """
            mutation addToDo { 
                addTodo(input:{
                    text: "todo",
                    complete: false,
                    clientMutationId: "1"
            }) {
                clientMutationId
            }
        }
        """

        when:
        def response = graphqlClient.execute(mutation)

        then:
        response.assertThat("data.addTodo.clientMutationId", equalTo("1"))
    }
}

@GrapqlArguments(
    @Argument(name = "complete", val = "false")
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