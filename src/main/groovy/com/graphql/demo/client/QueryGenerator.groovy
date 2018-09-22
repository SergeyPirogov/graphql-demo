package com.graphql.demo.client

import groovy.json.JsonOutput

import java.lang.reflect.Field

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

    static Query generateQuery(Class<?> aClass) {
        return new Query("query { ${toQuery(aClass)} }")
    }


    static String toQuery(Class<?> tClass) {
        def fields = tClass.declaredFields.findAll { !it.synthetic }

        def query = getQueryRoot(tClass)

        for (Field f : fields) {
            def annotation = f.getAnnotation(GraphqlProperty)
            if (annotation != null) {
                query += toQuery(f.type)
            } else {
                query += "${f.name} "
            }
        }

        query += "}"

        return query
    }


    private static String getQueryRoot(Class<?> tClass) {
        def grapqlArguments = tClass.getAnnotation(GrapqlArguments)
        if (grapqlArguments == null) {
            return "${tClass.simpleName.uncapitalize()} { "
        }

        def args = []

        for (Argument argument in grapqlArguments.value()) {
            args.add("${argument.name()}:${argument.val()}")
        }

        return "${tClass.simpleName.uncapitalize()} (${args.join(",")}) { "
    }

    static def generateMutation(String operationName, Object input, List<String> returnFields) {
        return "mutation ${operationName} { ${operationName} (input:${JsonOutput.toJson(input)}) { ${returnFields.join(" ")} }}"
    }

}