package io.catalyte.health_api.repositories;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import io.catalyte.health_api.domain.Patient;

public interface  PatientRepository extends  MongoRepository<Patient, String>  {
	
}
