package com.example.UserService.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rating {
    private String userId;
    private String hotelId;
    private String ratingId;
    private int rating;
    private String feedback;

    private Hotel hotel;
}
