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
@WithUserDetails("bilyk.nazar00@gmail.com")
public class AdminControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void correctAdminLoginTest() throws Exception {
		this.mockMvc.perform(get("http://localhost/admin/users"))
				.andDo(print())
				.andExpect(authenticated())
				.andExpect(xpath("//span[@id='username']").string("bilyk.nazar00@gmail.com"));
	}

	@Test
	@WithUserDetails("test@gmail.com")
	public void incorrectAdminLoginTest() throws Exception {
		this.mockMvc.perform(get("http://localhost/admin/users"))
				.andDo(print())
				.andExpect(status().isForbidden());
	}
}
