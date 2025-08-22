package com.example.RatingService.services;

import com.example.RatingService.entities.Rating;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RatingService {
    // create
    Rating createRating(Rating rating);

    // get all by rating
    List<Rating>getRatings();

    // get by userId
    List<Rating> getRatingsByUserId(String userId);

    // get all by hotelId
    List<Rating> getRatingsByHotelId(String hotelId);
}
