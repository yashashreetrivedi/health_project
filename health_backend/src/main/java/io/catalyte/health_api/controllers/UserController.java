package io.catalyte.health_api.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import io.catalyte.health_api.domain.User;
import io.catalyte.health_api.repositories.UserRepository;
import io.catalyte.health_api.validation.ValidationAllControllers;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	UserRepository userRepo;
	
	@Autowired
    PasswordEncoder passwordEncoder;
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	ValidationAllControllers validation = new ValidationAllControllers();
	
	/**
	 * Get all user in the system ,Only admin have access 
	 * @return
	 */
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	
	@ApiOperation("Get all users in the system.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = User.class) })
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<User>> getUser() {
		logger.debug("The Current users:" + userRepo.findAll());
		List<User> user = new ArrayList<User>();
		user = userRepo.findAll();
		if (user != null && user.size() > 0) 
		{
		logger.debug("Retrived the current user");
		return new ResponseEntity<List<User>>(user , HttpStatus.OK);
		}
		logger.debug("There is no patient as of now");
		return new ResponseEntity<List<User>>(HttpStatus.NOT_FOUND);
	}
	
	
	/**
	 * 
	 * @param user
	 * @return new user record, only admin can access this method
	 */
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@ApiOperation(" Create User in the system.")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created", response = User.class) })
	@RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<User> createUser(@RequestBody User user) {
		boolean isValid = validateUser(user);
		Optional<User> newUser = userRepo.findByEmail(user.getEmail());
		if( newUser.isPresent()) {
			return new ResponseEntity<User>( HttpStatus.BAD_REQUEST);
        } 
		 user.setPassword(passwordEncoder.encode(user.getPassword())); 
         if(isValid) {
        User result = userRepo.save(user);
        return new ResponseEntity<User>(user , HttpStatus.CREATED);
         }
         else
 			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);

	}
	
	/**
	 * 
	 * @param Id
	 * @return user  record related to given Id
	 */
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@ApiOperation("Get user by Id in the system.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = User.class) })
	@RequestMapping(value = "/{Id}", method = RequestMethod.GET)
	public ResponseEntity<User> getPatientById(@PathVariable String Id) {
		List<User> usertmp = userRepo.findAll();
		Iterator<User> it = usertmp.iterator();
		while (it.hasNext()) {
			User user = it.next();
			if (user.get_id().equalsIgnoreCase(Id)) {
				return new ResponseEntity<>(user, HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	/**
	 * 
	 * @param Id
	 * @return delete user with given ID
	 */
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@ApiOperation("Delete user by Id in the system.")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "No Content", response = User.class) })
	@RequestMapping(value = "/{Id}", method = RequestMethod.DELETE)
	public ResponseEntity deletePatientById(@PathVariable String Id) {
		List<User> usertmp = userRepo.findAll();
		Iterator<User> it = usertmp.iterator();
		while (it.hasNext()) {
			User user = it.next();
			if (user.get_id().equalsIgnoreCase(Id)) {
				logger.info("User with ID of " + user.get_id());
				userRepo.deleteById(Id);
				return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
			}

		}
		return new ResponseEntity<User>(HttpStatus.NOT_FOUND);

}
	

	/**
	 * 
	 * @param Id
	 * @param user
	 * @return updated user record of given Id
	 */
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@ApiOperation("Update user by Id in the system.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "ok", response = User.class) })
	@RequestMapping(value = "/{Id}", method = RequestMethod.PUT)
	public ResponseEntity<User>UpdateUser(@PathVariable String Id,  @RequestBody User user) {
		List<User> usersList = userRepo.findAll();
		boolean isValid = validateUser(user);
		if(isValid) {
			Iterator<User> it = usersList.iterator();
			while (it.hasNext()) {
				User updateduser = it.next();
				if(updateduser.get_id().equals(Id));
				if(validateUser(user)) {
					updateduser.setName(user.getName());
					updateduser.setEmail(user.getEmail());
					 user.setPassword(passwordEncoder.encode(user.getPassword())); 
					updateduser.setTitle(user.getTitle());
					updateduser.setRoles(user.getRoles());
					User creatdUser= null;
					creatdUser = userRepo.save(user);
					
					if(creatdUser == null)
					{
						return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
					}
					return new ResponseEntity<User>(user, HttpStatus.OK);
			}
		}
		}
		
		return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
	}
	
	
	/**
	 * 
	 * @param user
	 * @return boolen value to check enter user record is valid or not
	 */
	private boolean validateUser(User user) {
		if ( user != null   && validation.validateNotNullUser(user.getName(), user.getPassword(), user.getTitle()) && validation.validateEmail(user.getEmail())) {
			return true;
		} else {
			return false;
		}

		}
}
	

	
	
	
	
	
	
	
	
	
	
	


