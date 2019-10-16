package io.catalyte.health_api.controllers.test;
import static org.junit.Assert.assertTrue;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;

import io.catalyte.health_api.controllers.EncountersController;
import io.catalyte.health_api.controllers.PatientController;
import io.catalyte.health_api.domain.Encounters;
import io.catalyte.health_api.domain.Patient;
import io.catalyte.health_api.repositories.EncountersRepository;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EncountersControllerTest {
	@Mock
	private EncountersRepository encounterRepo;
	private static final Logger logger = LoggerFactory.getLogger(EncountersController.class);
	public Encounters encounter;
	private List<Encounters> encounters;
	private String encounterId;
	private String patientId= "5900eb2d4a0d410d4724db6b";
	private String notes="Oc obalbi besajbog tines nikiwo rewhuza re idu laed ekoluvone gomocoga..";
	private String visitcode="A9H 5Q8";
	private String provider="Mrs Shah";
	private String billingcode="162.517.132-32";
	private String icd10="E59";
	private Double totalcost=1500.00;
	private Double copay=10.00;
	private String chiefcomplaint="something";
	private Integer pulse=122;
	private Integer systolic=123;
	private Integer diastolic=64;
	private Long date=1449223880882l;
	
	@Autowired
	private MockMvc mockMvc;
	
	
	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).apply(springSecurity()).build();
		MockitoAnnotations.initMocks(this);
		encounter= new Encounters();
		encounter.setPatientid(patientId);;
		encounter.setNotes(notes);
		
		encounter.setVisitcode(visitcode);
		encounter.setProvider(provider);
		encounter.setBillingcode(billingcode);
		encounter.setIcd10(icd10);
		encounter.setTotalcost(totalcost);
		encounter.setCopay(copay);
		encounter.setChiefcomplaint(chiefcomplaint);
		encounter.setPluse(pulse);
		encounter.setSystolic(systolic);
		encounter.setDiastolic(diastolic);
		encounter.setDate(date);
		encounters= new ArrayList<Encounters>();
		encounters.add(encounter);
		

		try {
			encounterId = createHelper(encounter);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	@WithMockUser(roles = "ADMIN")
	public void testa1AddEncounterRoleAdmin() throws Exception {
		Gson gson = new Gson();
		String test = gson.toJson(encounter);
		int foundIndex = 0;
		for (int i = 0 ; i<test.length() ; i++) {
		        if (test.charAt(i) == '}')
		        {
		        	foundIndex = i;
		        	break;
		        }
		}	
	
		String newStr = test.substring(0,13) + " \"5900eb2d4a0d410d4724db6b\" " + test.substring(foundIndex +1, test.length());				
		MvcResult createPatientTestResult = mockMvc
				.perform(post("/encounters").contentType(MediaType.APPLICATION_JSON).content(newStr))
				.andExpect(status().isCreated()).andReturn();
		String content = createPatientTestResult.getResponse().getContentAsString();
		
	}


	@Test
	@WithMockUser(roles = "ADMIN")
	public void testa2GetEncountersAdmin() throws Exception {
		Gson gson = new Gson();
		String test = gson.toJson(encounter);
		MvcResult getEncounterTestResult = mockMvc
				.perform(get("/encounters").contentType(MediaType.APPLICATION_JSON).content(test))
				.andExpect(status().isOk()).andReturn();
		String content = getEncounterTestResult.getResponse().getContentAsString();
	}



	@Test
	@WithMockUser(roles = "ADMIN")
	public void testa3GetEncounterByIdAdmin() throws Exception {
		Gson gson = new Gson();
		String test = gson.toJson(encounter);	
		String encounterIdLink = "/encounters/" + String.valueOf("5900eb2d4a0d410d4724dcbe");
		MvcResult getEncountertestResultById = mockMvc
				.perform(get(encounterIdLink).contentType(MediaType.APPLICATION_JSON).content(test))
				.andExpect(status().isOk()).andReturn();
		String content = getEncountertestResultById.getResponse().getContentAsString();
	}
	
	@Test
	@WithMockUser(roles = "ADMIN")
	public void testa4GetEncounterByPtientIdAdmin() throws Exception {
		Gson gson = new Gson();
		String test = gson.toJson(encounter);	
		
		String encounterIdLink = "/encounters/patient" + String.valueOf("5900eb2d4a0d410d4724db37");
		MvcResult getEncountertestResultById = mockMvc
				.perform(get(encounterIdLink).contentType(MediaType.APPLICATION_JSON).content(test))
				.andExpect(status().isOk()).andReturn();
		String content = getEncountertestResultById.getResponse().getContentAsString();
	}

@Test
@WithMockUser(roles="ADMIN")
public void testa5UpdateEncounterByIdAdmin() throws Exception {
	Encounters e= new Encounters();
	
	e.setNotes("Update notes");
	e.setVisitcode("A232");
	e.setProvider("mrs mansi");
	

	
	 Gson gson = new Gson();
	 String json = gson.toJson(e);
	 String encounterIdLink ="/encounters/" + String.valueOf(encounterId);
	 MvcResult getEncountertestResultById = mockMvc
			 .perform(get(encounterIdLink).contentType(MediaType.APPLICATION_JSON).content(json))
			 .andExpect(status().isOk()).andReturn();
	 String Content = getEncountertestResultById.getResponse().getContentAsString();
	 
	 
	 
}


@Test
@WithMockUser(roles="ADMIN")
public void testb2DeleteEncounterAdmin() throws Exception {
	String encounterIdLink ="/encounters/" + String.valueOf(encounterId);
	mockMvc.perform(delete(encounterIdLink).contentType(MediaType.APPLICATION_JSON))
	.andExpect(status().isNoContent());

}


////USER role testing

@Test
@WithMockUser(roles="USER")
public void testa6AddEncounterAsuser() throws Exception {
	try {
		Gson gson = new Gson();
		String test = gson.toJson(encounter);
		int foundIndex = 0;
		for (int i = 0 ; i<test.length() ; i++) {
		        if (test.charAt(i) == '}')
		        {
		        	foundIndex = i;
		        	break;
		        }
		}	
	
		String newStr = test.substring(0,13) + " \"5900eb2d4a0d410d4724db6b\" " + test.substring(foundIndex +1, test.length());	
		mockMvc.perform(post("/encounters").contentType(MediaType.APPLICATION_JSON).content(newStr))
		.andExpect(status().isForbidden()).andReturn();
  } catch (Exception e) {
Assert.assertTrue(e.getCause() instanceof AccessDeniedException);
}
}
	
	@Test
	@WithMockUser(roles = "USER")
	public void testa7GeEncoounterAsUser() throws Exception {
		this.mockMvc.perform(get("/encounters")).andExpect(status().isForbidden());
		
	}
	

@Test
@WithMockUser(roles = "USER")
public void testa8GetEncounterByIdAsUser() throws Exception {
	
	this.mockMvc.perform(get("/encounters/encounterId")).andExpect(status().isForbidden());
}


@Test
@WithMockUser(roles = "USER")
public void testa9EditEncounterUser() throws Exception {

	Gson gson = new Gson();
	String test = gson.toJson(encounter);
	int foundIndex = 0;
	for (int i = 0 ; i<test.length() ; i++) {
	        if (test.charAt(i) == '}')
	        {
	        	foundIndex = i;
	        	break;
	        }
	}	

	String newStr = test.substring(0,13) + " \"5900eb2d4a0d410d4724db4c\" " + test.substring(foundIndex +1, test.length());	
	String encounterIdLink = "/encounters/" + String.valueOf(encounterId);
	MvcResult result = mockMvc.perform(put(encounterIdLink).contentType(MediaType.APPLICATION_JSON).content(newStr))
			.andExpect(status().isForbidden()).andReturn();

}



@Test
@WithMockUser(roles = "USER")
public void testb3DeleteEncounterAsUser() throws Exception {

	try {
		String encounterIdLink = "/patient/" + String.valueOf("encounterId");
		mockMvc.perform(delete(encounterIdLink).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isForbidden());
	} catch (Exception e) {
		Assert.assertTrue(e.getCause() instanceof AccessDeniedException);
	}

}


@Test 
public void testToString() {
	assertTrue(encounter.toString().equals(encounter.toString()));
}

public String createHelper(Encounters e) throws Exception {
	try {
		Gson gson = new Gson();
		String test = gson.toJson(e);
		int foundIndex = 0;
		for (int i = 0 ; i<test.length() ; i++) {
		        if (test.charAt(i) == '}')
		        {
		        	foundIndex = i;
		        	break;
		        }
		}	
	
		String newStr = test.substring(0,13) + " \"5900eb2d4a0d410d4724db6b\" " + test.substring(foundIndex +1, test.length());	
		MvcResult encounterResult = mockMvc
		.perform(post("/encounters").contentType(MediaType.APPLICATION_JSON).content(newStr)).andReturn();
		String content = encounterResult.getResponse().getContentAsString();
		String[] contentList = content.split(",");
		Map<String, String> actualResult = new HashMap<String, String>();
		for (String str : contentList) {
		String[] temp = str.split(":");
		temp[0].replace("\"", "");
		String t = temp[0].replaceAll("[\\{\\}\\(\\)]", "");
		Boolean checkId = t.startsWith("\"_id");
		if (checkId) {	
			return temp[1].replace("\"", "");
			}
		}

		return "id not found";
	} catch (Exception ex) {
		throw new Exception(ex);
	}
}
}




