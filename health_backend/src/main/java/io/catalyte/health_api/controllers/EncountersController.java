package io.catalyte.health_api.controllers;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import io.catalyte.health_api.domain.Encounters;
import io.catalyte.health_api.repositories.EncountersRepository;
import io.catalyte.health_api.validation.ValidationAllControllers;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/encounters")
public class EncountersController {
	
	/**
	 * Get all encounter in the system .Only admin can access 
	 */

	@Autowired
	EncountersRepository encounterRepo;
	private Logger logger = LoggerFactory.getLogger(EncountersController.class);
	ValidationAllControllers validation = new ValidationAllControllers();
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")  // user and admin both can access GET method
	@ApiOperation("Get all Encounters in the system.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = Encounters.class) })
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Encounters>> getEncounters() {
		logger.debug("The Encounter is : "+ encounterRepo.findAll());
		List<Encounters>encounter = new ArrayList<Encounters>();
		encounter = encounterRepo.findAll();
		if (encounter != null && encounter.size() > 0)
		{
		logger.debug("Retrived all encounters");
		return new ResponseEntity<List<Encounters>>(encounter, HttpStatus.OK);
		}
		logger.debug("There is no customer as of now");
		return new ResponseEntity<List<Encounters>>(HttpStatus.NOT_FOUND);
	}
	
	/**
	 * 
	 * @param encounter
	 * take input value
	 * @return created record of encounter
	 */
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@ApiOperation(" Create New encounter  in the system.")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created", response = Encounters.class) })
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Encounters> createCustomer(@RequestBody Encounters encounter) {
		boolean isValid = validateEncounters(encounter);
		if (isValid) {
			Encounters createdEncounter = null;
			createdEncounter = encounterRepo.insert(encounter);
			if(createdEncounter == null)
			{
				return new ResponseEntity<Encounters>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<Encounters>(encounter, HttpStatus.CREATED);
		} else
			return new ResponseEntity<Encounters>(HttpStatus.BAD_REQUEST);
	}
	
	
	/**
	 * 
	 * @param Id
	 * @return record related to given Id
	 */
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@ApiOperation("Get Encounters by Id in the system.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = Encounters.class) })
	@RequestMapping(value = "/{Id}", method = RequestMethod.GET)
	public ResponseEntity<Encounters> getEncounterById(@PathVariable String Id) {
		List<Encounters> tmp = encounterRepo.findAll();
		Iterator<Encounters> it = tmp.iterator();
		while (it.hasNext()) {
			Encounters encounter = it.next();
			if (encounter.get_id().equalsIgnoreCase(Id)) {
				return new ResponseEntity<>(encounter, HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	/**
	 * 
	 * @param patId
	 * @return patient related to given Id
	 */
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@RequestMapping(value = "patient/{patId}", method = RequestMethod.GET)
	public List<Encounters> getPatientsEncounter(@PathVariable String patId) {
		List<Encounters> patEncounters = new ArrayList<Encounters>();
		for (Encounters e : encounterRepo.findAll()) {
			if (e.getPatientid().equalsIgnoreCase(patId)){ 
				patEncounters.add(e);
				
			}
			
		}
		
		return patEncounters;
	}

	/**
	 * 
	 * @param Id
	 * @param encounter
	 * @return Updated record related to given ID
	 */
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@ApiOperation("Update Encounters by Id in the system.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "ok", response = Encounters.class) })
	@RequestMapping(value = "/{Id}", method = RequestMethod.PUT)
	public ResponseEntity<Encounters>updateEncounter(@PathVariable String Id, @RequestBody Encounters encounter  ) {
		List<Encounters>encounterList= encounterRepo.findAll();
		boolean isvalid = validateEncounters(encounter);
		if(isvalid) {
			Iterator<Encounters> it =encounterList.iterator();
		while(it.hasNext()) {
			Encounters updatedEncounter = it.next();
			if(updatedEncounter.get_id().equals(Id)) 
           {
				updatedEncounter.setBillingcode(encounter.getBillingcode());
				updatedEncounter.setChiefcomplaint(encounter.getChiefcomplaint());
				updatedEncounter.setCopay(encounter.getCopay());
				updatedEncounter.setDate(encounter.getDate());
				updatedEncounter.setIcd10(encounter.getIcd10());
				updatedEncounter.setPatientid(encounter.getPatientid());
				updatedEncounter.setNotes(encounter.getNotes());
				updatedEncounter.setPluse(encounter.getPluse());
				updatedEncounter.setSystolic(encounter.getSystolic());
				updatedEncounter.setTotalcost(encounter.getTotalcost());
				updatedEncounter.setProvider(encounter.getProvider());
				updatedEncounter.setVisitcode(encounter.getVisitcode());
				updatedEncounter.setDiastolic(encounter.getDiastolic());
				Encounters createdEncounter = null;
				createdEncounter = encounterRepo.save(updatedEncounter);
				if(createdEncounter== null)
				{
					return new ResponseEntity<Encounters>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
				return new ResponseEntity<Encounters>(updatedEncounter , HttpStatus.OK);
				
				}
			
			
			
			}
		}
	
		return new ResponseEntity<Encounters>(HttpStatus.BAD_REQUEST);
		}

		
	
	/**
	 * 
	 * @param Id
	 * @return delete record of that record
	 */
	
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@ApiOperation("Delete Encounter by Id in the system.")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "No Content", response = Encounters.class) })
	@RequestMapping(value = "/{Id}", method = RequestMethod.DELETE)
	public ResponseEntity deleteEncounterById(@PathVariable String Id) {
		List<Encounters> tmp = encounterRepo.findAll();
		Iterator<Encounters> it = tmp.iterator();
		while (it.hasNext()) {
			Encounters encounter = it.next();
			if (encounter.get_id().equalsIgnoreCase(Id)) {
				logger.info("Encounter with ID of " + encounter.get_id());
				encounterRepo.deleteById(Id);
				return new ResponseEntity<Encounters>(HttpStatus.NO_CONTENT);
			}	
		}
		return new ResponseEntity<Encounters>(HttpStatus.NOT_FOUND);

	}
	
	
/**
 * 
 * @param encounter
 * @return bool value if encounter is validate or not
 */
	private boolean validateEncounters(Encounters encounter) {
		if (  validation.validateNotNullEncounters( encounter.get_id(),encounter.getVisitcode(), encounter.getVisitcode(), 
			  encounter.getProvider(),encounter.getBillingcode(),encounter.getIcd10(),encounter.getTotalcost(),encounter.getCopay(),
			  encounter.getPluse(), encounter.getSystolic(),encounter.getDiastolic(),encounter.getDate()) && 
				validation.validateIcd(encounter.getIcd10()) && validation.validateVisiteCode(encounter.getVisitcode()) && 
				validation.validatebillingcode(encounter.getBillingcode())) {
			return true;
		} else {
			return false;
		}

		}
	}

