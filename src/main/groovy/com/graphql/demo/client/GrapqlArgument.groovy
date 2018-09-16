package com.graphql.demo.client

import java.lang.annotation.*

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface GrapqlArguments {
      Argument[] value()
}

@Retention(RetentionPolicy.RUNTIME)
@Repeatable( value = GrapqlArguments.class )
@interface Argument {
    String name()
    String val()
}