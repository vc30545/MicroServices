package com.lcwd.ratinG.controllers;

import com.lcwd.ratinG.entities.Rating;
import com.lcwd.ratinG.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {
    @Autowired
    private RatingService ratingService;

    //create rating
    @PostMapping
    public ResponseEntity<Rating> create(@RequestBody Rating rating) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.create(rating));
    }

    //get all
    @GetMapping
    public ResponseEntity<List<Rating>> getRatings(){
        return  ResponseEntity.ok(ratingService.getAllRating());
    }

    //get rating by userId
//    @GetMapping("/hotels/{userId}")
    @RequestMapping(value = "users/{userId}", method = RequestMethod.GET)
    public ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable String userId){
        return  ResponseEntity.ok(ratingService.getRatingByUserId(userId));
    }
    //get rating by hotelId
    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingsByHotelId(@PathVariable String hotelId){
        return  ResponseEntity.ok(ratingService.getRatingByHotelId(hotelId));
    }


}
