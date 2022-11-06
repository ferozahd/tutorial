package com.tutorial.springgraph.controllers;

import com.tutorial.springgraph.entities.Movie;
import com.tutorial.springgraph.exceptions.ResourceNotFoundException;
import com.tutorial.springgraph.repositories.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movie")
@RequiredArgsConstructor
public class MovieController {
    private final MovieRepository movieRepository;

    @GetMapping()
    ResponseEntity<?> getAll(){
        return ResponseEntity.ok(movieRepository.findAll());
    }
    @PostMapping()
    ResponseEntity<?> create(){
        Movie movie =new Movie();
        movie.setName("Dui prithibi ");
        movie.setProducer("Raj Chakrabarty");
        movieRepository.save(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(movie);
    }
    @GetMapping("/{id}")
    ResponseEntity<Movie> findById(@PathVariable Long id){
        Movie data = this.movieRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Movie not not found "));
        return  ResponseEntity.ok(data);
    }
    @DeleteMapping()
    ResponseEntity<Void> deleteAll(){
        this.movieRepository.deleteAll();
        return ResponseEntity.status(HttpStatus.GONE).build();
    }
}
