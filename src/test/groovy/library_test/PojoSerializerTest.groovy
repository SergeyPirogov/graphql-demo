package library_test

import com.graphql.demo.client.GraphqlProperty
import com.graphql.demo.client.QueryGenerator
import spock.lang.Specification


class PojoSerializerTest extends Specification {

    def "test can serialize java class"() {
        expect:
        QueryGenerator.toQuery(User) == "user { name adress { houseNumber street }}"
    }

}

class User{
    String name
    @GraphqlProperty
    Adress adress
}

class Adress{
    String houseNumber
    String street
}

