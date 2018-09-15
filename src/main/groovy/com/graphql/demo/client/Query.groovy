package com.graphql.demo.client

import groovy.transform.builder.Builder

@Builder
class Query {
    String query

    Query(String query) {
        this.query = query
    }
}
