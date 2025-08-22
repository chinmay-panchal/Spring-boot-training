package com.example.HotelService.services;

import com.example.HotelService.entities.Hotel;
import org.springframework.stereotype.Service;

import java.util.List;

public interface HotelService {
    //create hotel
    Hotel createHotel(Hotel hotel);

    //get all hotels
    List<Hotel> getAllHotels();

    //get hotel by id
    Hotel getHotelById(String hotelId);
}
