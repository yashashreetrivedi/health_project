package io.catalyte.health_api.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import io.catalyte.health_api.domain.Encounters;



public interface EncountersRepository extends  MongoRepository<Encounters, String> {

}




