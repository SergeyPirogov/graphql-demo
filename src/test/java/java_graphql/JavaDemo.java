package java_graphql;

import com.graphql.demo.client.GraphqlClient;
import com.graphql.demo.client.Query;
import com.graphql.demo.client.QueryGenerator;
import org.hamcrest.Matchers;
import org.junit.Test;
import rest_assured.model.Planet;

import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;

public class JavaDemo {

    private GraphqlClient graphqlClient = new GraphqlClient("http://localhost:46767");

    @Test
    public void testCanGetPlanet() {
        Map<String, String> args = new HashMap<>();
        args.put("planetID", "8");

        Query query = QueryGenerator.generateQuery("planet", args, singletonList("name"));

        Planet planet = graphqlClient.executeQuery(query).asPojo(Planet.class);

        assertThat(planet.getName(), Matchers.equalTo("Naboo"));
    }
}
