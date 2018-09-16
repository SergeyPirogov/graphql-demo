package library_test

import com.graphql.demo.client.QueryGenerator
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
}