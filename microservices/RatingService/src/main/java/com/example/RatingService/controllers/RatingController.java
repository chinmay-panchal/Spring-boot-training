package com.example.RatingService.controllers;

import com.example.RatingService.entities.Rating;
import com.example.RatingService.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ratings")
public class RatingController {
    @Autowired
    private RatingService ratingService;

    //create rating
    @PostMapping
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.createRating(rating));
    }

    //get all ratings
    @GetMapping
    public ResponseEntity<Iterable<Rating>> getRatings() {
        return ResponseEntity.ok(ratingService.getRatings());
    }

    //get ratings by userId
    @GetMapping("/users/{userId}")
    public ResponseEntity<Iterable<Rating>> getRatingsByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(ratingService.getRatingsByUserId(userId));
    }

    //get ratings by hotelId
    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<Iterable<Rating>> getRatingsByHotelId(@PathVariable String hotelId) {
        return ResponseEntity.ok(ratingService.getRatingsByHotelId(hotelId));
    }

}
