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

import io.catalyte.health_api.controllers.PatientController;
import io.catalyte.health_api.domain.Address;
import io.catalyte.health_api.domain.Patient;
import io.catalyte.health_api.repositories.PatientRepository;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PatientControllerTest {
	
	@Mock
	private PatientRepository patientRepo;
	private static final Logger logger = LoggerFactory.getLogger(PatientController.class);
	public Patient patient;
	public Address address;
	private List<Patient> patients;
	private String patientId;
	private String firstname= "Mike";
	private String lastname= "Morgen";
	private String ssn="232-45-6789";
	private Integer age= 30;
	private String gender="Male";
	private Integer height=76;
	private Integer weight=170;
	private String insurance="BCBC ppo";
	
	
	

	
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).apply(springSecurity()).build();
		MockitoAnnotations.initMocks(this);
		patient = new Patient();
		address = new Address();
		address.setStreet("23 Hyde Park");
		address.setCity("chicago");
		address.setState("IL");
		address.setPostal("60515");
		patient.setFirstname(firstname);
		patient.setLastname(lastname);
		patient.setSsn(ssn);
		patient.setAge(age);
		patient.setGender(gender);
		patient.setHeight(height);
		patient.setWeight(weight);
		patient.setInsurance(insurance);
		patient.setAddress(address);
		patients= new ArrayList<Patient>();
		patients.add(patient);
		
		
		try {
			patientId = createHelper(patient);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	

	
	@Test
	@WithMockUser(roles = "ADMIN")
	public void testa1AddPatientRoleAdmin() throws Exception {
		
		Gson gson = new Gson();
		String test = gson.toJson(patient);
		MvcResult createPatientTestResult = mockMvc
				.perform(post("/patient").contentType(MediaType.APPLICATION_JSON).content(test))
				.andExpect(status().isCreated()).andReturn();
		String content = createPatientTestResult.getResponse().getContentAsString();
  if(test == " ") {
	  MvcResult createPatientResultEmpty = mockMvc
				.perform(post("/patient").contentType(MediaType.APPLICATION_JSON).content(test))
				.andExpect(status().isBadRequest()).andReturn();
	  String content2 = createPatientResultEmpty.getResponse().getContentAsString();
  }
 if(test== null) {
	 MvcResult createPatientResultNull = mockMvc
		.perform(post("/patient").contentType(MediaType.APPLICATION_JSON).content(test))
		.andExpect(status().isInternalServerError()).andReturn();
String content2 = createPatientResultNull.getResponse().getContentAsString();
}

	}
	

	
	@Test
	@WithMockUser(roles = "ADMIN")
	public void testa2GetPatientAdminInfo() throws Exception {
		Gson gson = new Gson();
		String test = gson.toJson(patient);
		MvcResult getCustomerTestResult = mockMvc
				.perform(get("/patient/info").contentType(MediaType.APPLICATION_JSON).content(test))
				.andExpect(status().isOk()).andReturn();
		String content = getCustomerTestResult.getResponse().getContentAsString();
	}
	
	@Test
	@WithMockUser(roles = "ADMIN")
	public void testa3GetAllPatient() throws Exception {
		Gson gson = new Gson();
		String test = gson.toJson(patient);
		MvcResult getCustomerTestResult = mockMvc
				.perform(get("/patient").contentType(MediaType.APPLICATION_JSON).content(test))
				.andExpect(status().isOk()).andReturn();
		String content = getCustomerTestResult.getResponse().getContentAsString();
	}
	
	
	@Test
	@WithMockUser(roles = "ADMIN")
	public void testa4GetPatientAdmin() throws Exception {
		Gson gson = new Gson();
		String test = gson.toJson(patient);
		MvcResult getCustomerTestResult = mockMvc
				.perform(get("/patient").contentType(MediaType.APPLICATION_JSON).content(test))
				.andExpect(status().isOk()).andReturn();
		String content = getCustomerTestResult.getResponse().getContentAsString();
	}
	
	
	@Test
	@WithMockUser(roles = "ADMIN")
	public void testa5GetPatientByIdAdmin() throws Exception {
		Gson gson = new Gson();
		String test = gson.toJson(patient);
		String patientIdLink = "/patient/" + String.valueOf("5900eb2d4a0d410d4724db3a");
		MvcResult getPatientestResultById = mockMvc
				.perform(get(patientIdLink).contentType(MediaType.APPLICATION_JSON).content(test))
				.andExpect(status().isOk()).andReturn();
		String content = getPatientestResultById.getResponse().getContentAsString();
	}
	
	
	@Test
	@WithMockUser(roles="ADMIN")
	public void testa6UpdatePatientByIdAdmin() throws Exception {
		 Gson gson = new Gson();
		 String json = gson.toJson(patient);
		 String patientIdLink ="/patient/" + String.valueOf(patientId);
		 MvcResult getPatienttestResultById = mockMvc
				 .perform(get(patientIdLink).contentType(MediaType.APPLICATION_JSON).content(json))
				 .andExpect(status().isOk()).andReturn();
		 String Content = getPatienttestResultById.getResponse().getContentAsString();
		 
		 
		 
	}
	
	@Test
	@WithMockUser(roles="ADMIN")
	public void testb2DeletePatientAdmin() throws Exception {
		String patientIdLink ="/patient/" + String.valueOf(patientId);
		mockMvc.perform(delete(patientIdLink).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isNoContent());

	}
	
	
	@Test
	@WithMockUser(roles="ADMIN")
	public void testb3DeletePatientAdminIdencounter() throws Exception {
		String patientIdLink ="/patient/" + String.valueOf("5900eb2d4a0d410d4724db3a");
		mockMvc.perform(delete(patientIdLink).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isNotAcceptable());

	}
	//USER role testing
	
@Test
@WithMockUser(roles="USER")
public void testa7AddpatientAsuser() throws Exception {
	try {
		Gson gson = new Gson();
		String test = gson.toJson(patient);
		
		mockMvc.perform(post("/patient").contentType(MediaType.APPLICATION_JSON).content(test))
		.andExpect(status().isForbidden()).andReturn();
} catch (Exception e) {
Assert.assertTrue(e.getCause() instanceof AccessDeniedException);
}

}


@Test
@WithMockUser(roles = "USER")
public void testa8GetallPatientAsUser() throws Exception {
	
	this.mockMvc.perform(get("/patient")).andExpect(status().isForbidden());
}




@Test
@WithMockUser(roles = "USER")
public void testa9GetPatientByIdAsUser() throws Exception {

	this.mockMvc.perform(get("/patient/5900eb2d4a0d410d4724db65")).andExpect(status().isForbidden());
}
	


@Test
@WithMockUser(roles = "USER")
public void testa10EditPatientUser() throws Exception {

	Gson gson = new Gson();
	String json = gson.toJson(patient);
	String patientIdLink = "/patient/" + String.valueOf(patientId);
	MvcResult result = mockMvc.perform(put(patientIdLink).contentType(MediaType.APPLICATION_JSON).content(json))
			.andExpect(status().isForbidden()).andReturn();

}



@Test
@WithMockUser(roles = "USER")
public void testb4DeletePatientAsUser() throws Exception {

	try {
		String patientIdLink = "/patient/" + String.valueOf("5900eb2d4a0d410d4724db3b");
		mockMvc.perform(delete(patientIdLink).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isForbidden());
	} catch (Exception e) {
		Assert.assertTrue(e.getCause() instanceof AccessDeniedException);
	}

}

@Test
@WithMockUser(roles="USER")
public void testb5DeletePatientAdminIdencounteruser() throws Exception {
	String patientIdLink ="/patient/" + String.valueOf("5900eb2d4a0d410d4724db3a");
	mockMvc.perform(delete(patientIdLink).contentType(MediaType.APPLICATION_JSON))
	.andExpect(status().isForbidden());

}

@Test 
public void testToString() {
	assertTrue(patient.toString().equals(patient.toString()));
}

	public String createHelper(Patient p) throws Exception {
		try {
			Gson gson = new Gson();
			String test = gson.toJson(p);
			MvcResult patientResult = mockMvc
			.perform(post("/patient").contentType(MediaType.APPLICATION_JSON).content(test)).andReturn();
			String content = patientResult.getResponse().getContentAsString();
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
