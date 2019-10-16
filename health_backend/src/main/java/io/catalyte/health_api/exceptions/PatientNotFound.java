package io.catalyte.health_api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "The Patient with the given ID could not be found.")
public class PatientNotFound extends RuntimeException {

}

