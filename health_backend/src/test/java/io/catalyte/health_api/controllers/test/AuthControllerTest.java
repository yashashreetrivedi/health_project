package io.catalyte.health_api.controllers.test;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;

import io.catalyte.health_api.payload.LoginRequest;



@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AuthControllerTest {
	private static final Logger logger = LoggerFactory.getLogger(AuthControllerTest.class);
	private LoginRequest user;
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired 
	private WebApplicationContext wac;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).apply(springSecurity()).build();
		MockitoAnnotations.initMocks(this);
		user = new LoginRequest();
		
		user.setEmail("g@gmail.com");
		user.setPassword("12345");
		
	}
	
	@Test
	public void testA1Login() throws Exception {
		Gson gson = new Gson();
		String json = gson.toJson(user);
		MvcResult result = mockMvc.perform(post("/auth/signin")
			.contentType(MediaType.APPLICATION_JSON)
			.content(json))
			.andExpect(status()
			.isOk())
			.andReturn();
	}
	
	@Test
	public void testA2Login() throws Exception {
		user.setEmail("g@gmail.com");
		user.setPassword("123");
		Gson gson = new Gson();
		String json = gson.toJson(user);
		MvcResult result = mockMvc.perform(post("/auth/signin")
			.contentType(MediaType.APPLICATION_JSON)
			.content(json))
			.andExpect(status()
			.isUnauthorized())
			.andReturn();
	}
	
	@Test
	public void testA3Login() throws Exception {
		user.setEmail("testing@gmail.com");
		user.setPassword("test123");
		Gson gson = new Gson();
		String json = gson.toJson(user);
		MvcResult result = mockMvc.perform(post("/auth/signin")
			.contentType(MediaType.APPLICATION_JSON)
			.content(json))
			.andExpect(status()
			.isUnauthorized())
			.andReturn();
	}
	
	
	
}
