package io.catalyte.health_api.controllers.test;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.UUID;

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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;


import io.catalyte.health_api.controllers.PatientController;
import io.catalyte.health_api.controllers.UserController;
import io.catalyte.health_api.domain.Patient;
import io.catalyte.health_api.domain.User;
import io.catalyte.health_api.repositories.UserRepository;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserControllerTest {

@Mock
private UserRepository userRepo;
private static final Logger logger = LoggerFactory.getLogger(UserController.class);
public User user;
private List<User> users;
private String userId;
private String name="nametest";
private String title="testtitle1";
private String password="testing123";
String[] testroles = new String[2];{
testroles[0]="ROLE_USER";
testroles[1]="ROLE_ADMIN";
}



@Autowired
private MockMvc mockMvc;

@Autowired
private WebApplicationContext wac;



@Before
public void setUp() {
	PasswordEncoder passwordEncoder = null;
	mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).apply(springSecurity()).build();
	MockitoAnnotations.initMocks(this);
	
	user=new User();
	user.setName(name);
	user.setTitle(title);
	user.setPassword(password);
	user.setEmail("d1@gmail.com");
	user.setRoles(testroles);

}




@Test
@WithMockUser(roles = "ADMIN")
public void testa1AddUserRoleAdmin() throws Exception {
	
	Gson gson = new Gson();
	String test = gson.toJson(user);
	MvcResult createUserTestResult = mockMvc
			.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(test))
			.andExpect(status().isCreated()).andReturn();
	String content = createUserTestResult.getResponse().getContentAsString();

}

@Test
@WithMockUser(roles = "ADMIN")
public void testa1AddUserRoleAdminsamevalue() throws Exception {
	
	Gson gson = new Gson();
	String test = gson.toJson(user);
	MvcResult createUserTestResult = mockMvc
			.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(test))
			.andExpect(status().isBadRequest()).andReturn();
	String content = createUserTestResult.getResponse().getContentAsString();

}

@Test
@WithMockUser(roles = "ADMIN")
public void testa2GetUserAdmin() throws Exception {
	Gson gson = new Gson();
	String test = gson.toJson(user);
	MvcResult getUserTestResult = mockMvc
			.perform(get("/users").contentType(MediaType.APPLICATION_JSON).content(test))
			.andExpect(status().isOk()).andReturn();
	String content = getUserTestResult.getResponse().getContentAsString();
}

@Test
@WithMockUser(roles = "ADMIN")
public void testa3GetUserByIdAdmin() throws Exception {
	Gson gson = new Gson();
	String test = gson.toJson(user);
	String userIdLink = "/users/" + String.valueOf("5900eb2d4a0d410d4724db33");
	MvcResult getUserResultById = mockMvc
			.perform(get(userIdLink).contentType(MediaType.APPLICATION_JSON).content(test))
			.andExpect(status().isOk()).andReturn();
	String content = getUserResultById.getResponse().getContentAsString();
}


@Test
@WithMockUser(roles="ADMIN")
public void testa4UpdateUserByIdAdmin() throws Exception {
	User user1=new User();
	user1.setName("update");
	user1.setTitle("updatetitile");
	user1.setEmail("d2@gmail.com");
	user1.setPassword("test2");
	user1.setRoles(testroles);
	Gson gson = new Gson();
	 String json = gson.toJson(user1);
	 String userIdLink ="/users/" + String.valueOf("5900eb2d4a0d410d4724db2f");
	 MvcResult getUsertestResultById = mockMvc
			 .perform(get(userIdLink).contentType(MediaType.APPLICATION_JSON).content(json))
			 .andExpect(status().isOk()).andReturn();
	 String Content = getUsertestResultById.getResponse().getContentAsString();
	 
}


@Test
@WithMockUser(roles="ADMIN")
public void testb2DeleteUserAdmin() throws Exception {
	String userIdLink ="/users/" + String.valueOf("5b85ae7581b2305eec9bb0f7");
	mockMvc.perform(delete(userIdLink).contentType(MediaType.APPLICATION_JSON))
	.andExpect(status().isNoContent());

}
	 
//USER role testing

@Test
@WithMockUser(roles="USER")
public void testa5AddUserAsuser() throws Exception {
	User user2=new User();
	user2.setName("update");
	user2.setTitle("updatetitile");
	user2.setEmail("d3@gmail.com");
	user2.setPassword("test2");
	user2.setRoles(testroles);
	try {
		Gson gson = new Gson();
		String test = gson.toJson(user2);
		
		mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(test))
		.andExpect(status().isForbidden()).andReturn();
} catch (Exception e) {
Assert.assertTrue(e.getCause() instanceof AccessDeniedException);
}

}

@Test
@WithMockUser(roles = "USER")
public void testa6GeUserAsUser() throws Exception {
	
	this.mockMvc.perform(get("/users")).andExpect(status().isForbidden());
}

@Test
@WithMockUser(roles = "USER")
public void testa7GetUserByIdAsUser() throws Exception {

	this.mockMvc.perform(get("/users/5b8426def84980177c6a0afc")).andExpect(status().isForbidden());
}
	


@Test
@WithMockUser(roles = "USER")
public void testa8EditUserAsUser() throws Exception {
	User user3=new User();
	user3.setName("update");
	user3.setTitle("updatetitile");
	user3.setEmail("d4@gmail.com");
	user3.setPassword("test2");
	user3.setRoles(testroles);
	Gson gson = new Gson();
	String json = gson.toJson(user);
	String userIdLink = "/users/" + String.valueOf("5b8371d10e4b32585cab88ee");
	MvcResult result = mockMvc.perform(put(userIdLink).contentType(MediaType.APPLICATION_JSON).content(json))
			.andExpect(status().isForbidden()).andReturn();

}



@Test
@WithMockUser(roles = "USER")
public void testb3DeleteUsertAsUser() throws Exception {

	try {
		String patientIdLink = "/users/" + String.valueOf("5b8426def84980177c6a0afc");
		mockMvc.perform(delete(patientIdLink).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isForbidden());
	} catch (Exception e) {
		Assert.assertTrue(e.getCause() instanceof AccessDeniedException);
	}

}

public String createHelper(String [] testroles) throws Exception {
	try {
		
		user=new User();
		user.setName(name);
		user.setTitle(title);
		user.setPassword(password);
		user.setEmail(UUID.randomUUID().toString() + "@test.com");
		user.setRoles(testroles);
		
		Gson gson = new Gson();
		
		String test = gson.toJson(user);
		MvcResult userResult = mockMvc
		.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(test)).andReturn();
		String content = userResult.getResponse().getContentAsString();
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