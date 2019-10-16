package io.catalyte.health_api.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import io.catalyte.health_api.domain.User;



public interface UserRepository extends MongoRepository<User , String> {
	Optional<User> findByEmail(String email);
	Optional<User> findByName(String name);
	
}





