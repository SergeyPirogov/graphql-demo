package library_test

import com.graphql.demo.client.Query
import com.graphql.demo.client.QueryGenerator
import com.sun.org.apache.xpath.internal.operations.Bool
import groovy.transform.TupleConstructor
import spock.lang.Specification

class QuerySerializerTest extends Specification {

    def "test simple query generator"() {
        expect:
        assert QueryGenerator.generateQuery("allFilms", ["totalCount"]).query == "query allFilms { allFilms { totalCount } }"
    }

    def "test generator with arguments"(){
        expect:
        assert QueryGenerator.generateQuery("person", ["personID":"1"], ["name"]).query == "query person { person(personID:1) { name }}"
    }

    def "test can generate mutation"(){
        expect:
        assert  QueryGenerator.generateMutation("addToDo",
                new AddToDoInput("demo", false, "1"),
                ["clientMutationId"]) == "mutation addToDo { addToDo (input:{\"clientMutationId\":\"1\",\"complete\":false,\"text\":\"demo\"}) { clientMutationId }"
    }
}

@TupleConstructor
class AddToDoInput{
    String text
    Boolean complete
    String clientMutationId
}