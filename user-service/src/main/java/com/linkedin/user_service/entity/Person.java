package com.linkedin.user_service.entity;


import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
public class Person {
    @Id
    private Long userId;

    private String name;

    public Person() {}

    public Person(Long userID, String name) {
        this.userId = userID;
        this.name = name;
    }
}
