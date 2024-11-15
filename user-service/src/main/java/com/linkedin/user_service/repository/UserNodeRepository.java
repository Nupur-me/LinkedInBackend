package com.linkedin.user_service.repository;


import com.linkedin.user_service.entity.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface UserNodeRepository extends Neo4jRepository<Person, Long> {}
