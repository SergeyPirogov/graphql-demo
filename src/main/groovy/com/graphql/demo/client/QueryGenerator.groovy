package com.graphql.demo.client

class QueryGenerator {

    static Query generateQuery(String operationName, List<String> fields) {
        return new Query("query ${operationName} { ${operationName} { ${fields.join(" ")} } }")
    }

    static Query generateQuery(String operationName, Map<String, ?> arguments, List<String> fields) {
        if (arguments?.size() == 0) {
            throw new IllegalArgumentException("Arguments can't be empty in this case")
        }

        def array = []

        for (e in arguments) {
            array.add("${e.key}:${e.value}")
        }

        def args = array.join(",")

        return new Query("query ${operationName} { ${operationName}(${args}) { ${fields.join(" ")} }}")
    }

}