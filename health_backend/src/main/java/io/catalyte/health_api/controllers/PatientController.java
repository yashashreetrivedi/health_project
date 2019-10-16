package io.catalyte.health_api.controllers;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.catalyte.health_api.domain.Patient;
import io.catalyte.health_api.domain.PatientInfo;
import io.catalyte.health_api.domain.Address;
import io.catalyte.health_api.domain.Encounters;
import io.catalyte.health_api.repositories.EncountersRepository;
import io.catalyte.health_api.repositories.PatientRepository;
import io.catalyte.health_api.validation.ValidationAllControllers;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/patient")
public class PatientController {

	
	@Autowired
	PatientRepository patientRepo;
		
	private @Autowired AutowireCapableBeanFactory beanFactory;


	private Logger logger = LoggerFactory.getLogger(PatientController.class);
	ValidationAllControllers validation = new ValidationAllControllers();
	
	/**
	 * Get all patient record only admin can see all information about user
	 * @return
	 */
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")  
	@ApiOperation("Get all customers in the system.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = Patient.class) })
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Patient>> getPatient() {
		logger.debug(" The Current patients: " + patientRepo.findAll());
		List<Patient> patients = new ArrayList<Patient>();
		patients = patientRepo.findAll();
		
		if (patients != null && patients.size() > 0)
		{
		logger.debug("Retrieved the all patients");
		return new ResponseEntity<List<Patient>>(patients, HttpStatus.OK);
		}
		logger.debug("There is no patient as of now");
		return new ResponseEntity<List<Patient>>(HttpStatus.NOT_FOUND);
	}

	
		/**
		 * Get only those specific filed both user and admin can see information but some specific field
		 * @return
		 */
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")  // user and admin both can access GET method
	@ApiOperation("Get all customers with spicific field in the system.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = Patient.class) })
	@RequestMapping(value = "/info",method = RequestMethod.GET)
	public ResponseEntity<List<PatientInfo>> getPatientInfo() {
		logger.debug(" The Current patients: " + patientRepo.findAll());
		List<Patient> patients = new ArrayList<Patient>();
		patients = patientRepo.findAll();
		List<PatientInfo> patientsInfo = new ArrayList<PatientInfo>();
		
		 for (Patient patient : patients) 
	        {
			 PatientInfo p = new PatientInfo ();
			 p.set_id(patient.get_id());
			 p.setFirstname(patient.getFirstname());
			 p.setLastname(patient.getLastname());
			 p.setAge(patient.getAge());
			 p.setGender(patient.getGender());
			 			 
			 patientsInfo.add(p);  
	        }
		 if (patients != null && patients.size() > 0)
			{
		
			 logger.debug("Retrieved the all patients");
			 return new ResponseEntity<List<PatientInfo>>(patientsInfo, HttpStatus.OK);
			}
		 logger.debug("There is no patient as of now");
			return new ResponseEntity<List<PatientInfo>>(HttpStatus.NOT_FOUND);
	}
	
	/**
	 * 
	 * @param patient
	 * @return patient record 
	 */
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@ApiOperation(" Create Patient in the system.")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created", response = Patient.class) })
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Patient> createCustomer(@RequestBody Patient patient) {
		boolean isValid = validatePatient(patient);
		if (isValid) {
			Patient createdPatient = null;
			createdPatient = patientRepo.insert(patient);
			if(createdPatient == null)
			{
				return new ResponseEntity<Patient>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<Patient>(patient, HttpStatus.CREATED);
		} else
			return new ResponseEntity<Patient>(HttpStatus.BAD_REQUEST);
	}
	

	/**
	 * 
	 * @param Id
	 * @return Get record of patient related to that id
	 */
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@ApiOperation("Get patient by Id in the system.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = Patient.class) })
	@RequestMapping(value = "/{Id}", method = RequestMethod.GET)
	public ResponseEntity<Patient> getPatientById(@PathVariable String Id) {
		List<Patient> tmp = patientRepo.findAll();
		Iterator<Patient> it = tmp.iterator();
		while (it.hasNext()) {
			Patient patient = it.next();
			if (patient.get_id().equalsIgnoreCase(Id)) {
				return new ResponseEntity<>(patient, HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	/**
	 * 
	 * @param Id
	 * @return delete record of that id 
	 */
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@ApiOperation("Delete patient by Id in the system.")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "No Content", response = Patient.class) })
	@RequestMapping(value = "/{Id}", method = RequestMethod.DELETE)
	public ResponseEntity deletePatientById(@PathVariable String Id) {
		List<Patient> tmp = patientRepo.findAll();
		Iterator<Patient> it = tmp.iterator();
		while (it.hasNext()) {
			Patient patient = it.next();
			if (patient.get_id().equalsIgnoreCase(Id)) {
				logger.info("Patient with ID of " + patient.get_id());
				List<Encounters> patEncounters = new ArrayList<Encounters>();
			
				EncountersController encountersController= new EncountersController();
				 beanFactory.autowireBean(encountersController);
				 
				 patEncounters = encountersController.getPatientsEncounter(Id);
				if(patEncounters.size() > 0)
				{
					return new ResponseEntity<Patient>(HttpStatus.NOT_ACCEPTABLE);
				}
				patientRepo.deleteById(Id);
				return new ResponseEntity<Patient>(HttpStatus.NO_CONTENT);
			}

		}
		return new ResponseEntity<Patient>(HttpStatus.NOT_FOUND);
}


	/**
	 * 
	 * @param Id
	 * @param patient1
	 * @return update a patient record relate to that id
	 */
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@ApiOperation("Update Patient by Id in the system.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "ok",response = Patient.class) })
	@RequestMapping(value = "/{Id}", method = RequestMethod.PUT)
	public ResponseEntity<Patient> updateCustomer(@PathVariable String Id, @RequestBody Patient patient1) {

		List<Patient> patient = patientRepo.findAll();
		boolean isValid = validatePatient(patient1);
		if (isValid) {
			Iterator<Patient> it = patient.iterator();
			while (it.hasNext()) {
				Patient p = it.next();
				if (p.get_id().equalsIgnoreCase(Id)) {
						p.setFirstname(patient1.getFirstname());
						p.setLastname(patient1.getLastname());
						p.setSsn(patient1.getSsn());
						p.setAge(patient1.getAge());
						p.setGender(patient1.getGender());
						p.setHeight(patient1.getHeight());
						p.setWeight(patient1.getWeight());
						p.setInsurance(patient1.getInsurance());
						Address existingAddress  = p.getAddress();
						existingAddress.setStreet(patient1.getAddress().getStreet());
						existingAddress.setCity(patient1.getAddress().getCity());
						existingAddress.setState(patient1.getAddress().getState());
						existingAddress.setPostal(patient1.getAddress().getPostal());
						p.setAddress(existingAddress);
				    	Patient createdPatient = null;
				    	createdPatient = patientRepo.save(p);
				    	if(createdPatient == null)
						{
							return new ResponseEntity<Patient>(HttpStatus.INTERNAL_SERVER_ERROR);
						}
						
						return new ResponseEntity<Patient>(p, HttpStatus.OK);

				}
			}
			
		}
		return new ResponseEntity<Patient>(HttpStatus.BAD_REQUEST);
	}
	

	/**
	 * 
	 * @param patient
	 * @return bool value of validate patient
	 */
	
	private boolean validatePatient(Patient patient) {
		if ( patient != null && validation.validateNotNullPatient(patient.get_id(), patient.getFirstname(), patient.getLastname(),patient.getGender(), patient.getAddress().getCity(), patient.getAddress().getStreet(),patient.getHeight(), patient.getWeight(), patient.getAge() ) && 
				validation.validatePostal(patient.getAddress().getPostal()) && 
				validation.validateSSN(patient.getSsn()) && validation.validateState(patient.getAddress().getState()) && validation.ValidateGender(patient.getGender()))  {
			return true;
		} else {
			return false;
		}

		}
}
	
	
	


