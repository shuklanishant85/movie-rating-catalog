# movie-rating-catalog
A spring boot microservices based implemetation 

## Contents:
This project contains three microservices:
1. movie-catalog-service
2. movie-rating-service
3. movie-info-service

The services are registered in Eureka Server which runs on port 8761 (default).

The movie-catalog-service internally makes calls to movie-rating-service\
and movie-info-service and returns a list of movies as response

movie-catalog-service is served at end-point: http://localhost:8081/catalog/id
