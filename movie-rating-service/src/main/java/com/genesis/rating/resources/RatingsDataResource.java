package com.genesis.rating.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.genesis.rating.model.Rating;
import com.genesis.rating.model.UserRatings;

@RestController
@RequestMapping("/ratings")
public class RatingsDataResource {

	@GetMapping("/{movieId}")
	public Rating getRating(@PathVariable("movieId") String id) {
		return new Rating(id, 5);
	}

	@GetMapping("/users/{userId}")
	public UserRatings getUserRatings(@PathVariable("userId") String userId) {
		List<Rating> ratings = Arrays.asList(new Rating("1234", 4), new Rating("4567", 5));
		UserRatings userRatings = new UserRatings();
		userRatings.setRatings(ratings);
		return userRatings;
	}

}
