package com.lcwd.user.service.service.impl;

import com.lcwd.user.service.entities.Hotel;
import com.lcwd.user.service.entities.Rating;
import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.exception.ResourceNotFoundException;
import com.lcwd.user.service.external.services.HotelService;
import com.lcwd.user.service.repositories.UserRepository;
import com.lcwd.user.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private HotelService hotelService;


    @Override
    public User saveUser(User user) {
        //generate unique userid
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getallUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        //get user from database with the help of user repository
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with give id is not found on server !! : " + userId));
        //fetch rating of the above user from Rating Service
        // localhost:8083/ratings/users/37ced5da-30b1-47e1-b61b-9e35baefb670
        Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(),Rating[].class);
        System.out.println("LIST" + ratingsOfUser);

        List <Rating> ratings=Arrays.stream(ratingsOfUser).toList();

//        List<Rating> ratingList =ratings.stream().map(rating -> {
//            //api call to hotel service to get the hotel
//            //http://localhost:8082/hotels/b1157c22-a96e-4d95-9f01-418acb8f02c4
//            ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class);
//            Hotel hotel = forEntity.getBody();
//            System.out.println("Response Status Code " + forEntity.getStatusCode());
//            //set the hotel rating
//            rating.setHotel(hotel);
//            //return the rating
//
//            return rating;
//        }).collect(Collectors.toList());

        List<Rating> ratingList =ratings.stream().map(rating -> {
            //api call to hotel service to get the hotel
            //http://localhost:8082/hotels/b1157c22-a96e-4d95-9f01-418acb8f02c4
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
//            System.out.println("Response Status Code " + forEntity.getStatusCode());
            //set the hotel rating
            rating.setHotel(hotel);
            //return the rating

            return rating;
        }).collect(Collectors.toList());

        user.setRatings(ratingList);


        return user;
    }
}
