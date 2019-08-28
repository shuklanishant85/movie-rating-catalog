package com.genesis.catalog.model;
public class Rating {

	private String movieId;
	private int movieRating;

	public Rating() {

	}

	public Rating(String movieId, int rating) {
		super();
		this.movieId = movieId;
		this.movieRating = rating;
	}

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public int getMovieRating() {
		return movieRating;
	}

	public void setMovieRating(int rating) {
		this.movieRating = rating;
	}


}
