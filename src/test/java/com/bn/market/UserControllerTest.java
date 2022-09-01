package com.bn.market;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithUserDetails("test@gmail.com")
public class UserControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void correctUserLoginTest() throws Exception {
		this.mockMvc.perform(get("http://localhost/user/products"))
				.andDo(print())
				.andExpect(authenticated())
				.andExpect(xpath("//span[@id='username']").string("test@gmail.com"));
	}

	@Test
	@WithUserDetails("bilyk.nazar00@gmail.com")
	public void incorrectUserLoginTest() throws Exception {
		this.mockMvc.perform(get("http://localhost/user/products"))
				.andDo(print())
				.andExpect(status().isForbidden());
	}
}
