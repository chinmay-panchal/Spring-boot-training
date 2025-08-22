package com.example.UserService.external.services;

import com.example.UserService.entity.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Service
@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

    // GET


    // POST
    @PostMapping("/ratings")
    Rating createRating(Rating rating);

    // PUT
    @PutMapping("/ratings/{ratingId}")
    Rating updateRating(@PathVariable("ratingId") String ratingId, Rating updatedRating);

    // DELETE
    @DeleteMapping("/ratings/{ratingId}")
    void deleteRating(@PathVariable("ratingId") String ratingId);
}
