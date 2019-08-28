package com.genesis.catalog.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.genesis.catalog.model.CatalogItem;
import com.genesis.catalog.model.Movie;
import com.genesis.catalog.model.UserRatings;

@RestController
@RequestMapping("/catalog")
public class CatalogResource {

	private static final Logger LOGGER = LoggerFactory.getLogger(CatalogItem.class);

	@Autowired
	private DiscoveryClient discoveryClient;

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping(value = "/{userId}", produces = "application/json")
	public List<CatalogItem> getCatalogForUserId(@PathVariable("userId") String id) {

		List<ServiceInstance> services = discoveryClient.getInstances("movie-catalog-service");
		services.stream().forEach(service -> LOGGER.info(service.getUri().toString()));
		// get all rated movie IDs
		UserRatings ratings = restTemplate.getForObject("http://movie-rating-service/ratings/users/" + id,
				UserRatings.class);
		return ratings.getRatings().stream().map(rating -> {
			// for each id call rating service and get rating
			Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(),
					Movie.class);
			return new CatalogItem(movie.getName(), "Desc", rating.getMovieRating());
		}).collect(Collectors.toList());

	}
}
