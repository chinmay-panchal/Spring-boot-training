package com.example.UserService.services.impl;

import com.example.UserService.entity.Hotel;
import com.example.UserService.entity.Rating;
import com.example.UserService.entity.User;
import com.example.UserService.exceptions.ResourceNotFoundException;
import com.example.UserService.external.services.HotelService;
import com.example.UserService.repository.UserRepostiory;
import com.example.UserService.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RestTemplate restTemplate;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private HotelService hotelService;

    @Autowired
    private UserRepostiory userRepostiory;

    @Override
    public User saveUser(User user) {
        user.setUserId(UUID.randomUUID().toString());
        return userRepostiory.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        // implementing rating service call: Using RestTemplate
        return userRepostiory.findAll();
    }

    @Override
    public User getUserById(String userId) {
        logger.info("Fetching user with ID: {}", userId);

        // fetch user from DB
        User user = userRepostiory.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        try {
            // Call Rating Service using service name instead of localhost:8083
            Rating[] ratingsOfUsers = restTemplate.getForObject(
                    "http://RATINGSERVICE/ratings/users/" + userId,
                    Rating[].class
            );

            logger.info("{}", ratingsOfUsers);

            List<Rating> ratings = Arrays.stream(ratingsOfUsers).toList();

            List<Rating> ratingList = ratings.stream().map(rating -> {

                // Call Hotel Service using service name instead of localhost:8082
                // without fiegn client
//            ResponseEntity<Hotel> forEntity = restTemplate.getForEntity(
//                    "http://HOTELSERVICE/hotels/" + rating.getHotelId(),
//                    Hotel.class
//            );
//            Hotel hotel = forEntity.getBody();
//            logger.info("response status code: {}", forEntity.getStatusCode());

                // with feign client - FIXED VERSION
                try {
                    Hotel hotel = hotelService.getHotelById(rating.getHotelId());
                    if (hotel != null) {

                        rating.setHotel(hotel);
                        logger.info("Successfully fetched hotel with id: {}", rating.getHotelId());
                    } else {
                        logger.error("Hotel not found with id: {}", rating.getHotelId());
                    }
                } catch (Exception e) {
                    logger.error("Failed to fetch hotel with id: {} - Error: {}", rating.getHotelId(), e.getMessage());
                    throw e;
                }

                return rating;
            }).collect(Collectors.toList());

            user.setRatings(ratingList);

        } catch (Exception e) {
            logger.error("Failed to fetch ratings from RATING-SERVICE", e);
            throw e;
        }

        return user;
    }

    @Override
    public void deleteUser(String userId) {
        if (!userRepostiory.existsById(userId)) {
            throw new ResourceNotFoundException("User not found with id: " + userId);
        }
        userRepostiory.deleteById(userId);
    }

    @Override
    public User updateUser(User user, String userId) {
        return userRepostiory.findById(userId)
                .map(existingUser -> {
                    existingUser.setName(user.getName());
                    existingUser.setEmail(user.getEmail());
                    existingUser.setAbout(user.getAbout());
                    return userRepostiory.save(existingUser);
                })
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
    }
}
