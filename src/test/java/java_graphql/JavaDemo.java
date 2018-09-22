package java_graphql;

import com.graphql.demo.client.Config;
import com.graphql.demo.client.GraphqlClient;
import com.graphql.demo.client.Query;
import com.graphql.demo.client.QueryGenerator;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;

public class JavaDemo {

    private GraphqlClient graphqlClient = new GraphqlClient(Config.URL);

    @Test
    public void testCanGetPlanet() {
        Map<String, String> args = new HashMap<>();
        args.put("planetID", "1");

        Query query = QueryGenerator.generateQuery("planet", args, singletonList("name"));

        graphqlClient.execute(query)
                .assertThat("data.planet.name", Matchers.equalTo("Tatooine"));
    }
}
