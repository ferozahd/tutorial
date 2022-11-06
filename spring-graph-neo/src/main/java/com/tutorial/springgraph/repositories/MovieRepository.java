package com.tutorial.springgraph.repositories;

import com.tutorial.springgraph.entities.Movie;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface MovieRepository extends Neo4jRepository<Movie,Long> {
}
