package com.cakefactory;

import com.cakefactory.catalog.ItemEntity;
import com.cakefactory.catalog.ItemRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

@SpringBootTest
@AutoConfigureMockMvc
class CatalogControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ItemRepository itemRepository;

	@BeforeEach
	void setUp() {
		itemRepository.deleteAll();
		itemRepository.save(new ItemEntity("Red Velvet", new BigDecimal("3.99")));
		itemRepository.save(new ItemEntity("Chocolate", new BigDecimal("4.49")));
	}

	@Test
	@DisplayName("index page returns the landing page")
	void returnsLandingPage() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("Cake Factory")));
	}

	@Test
	@DisplayName("index page displays catalog items")
	void displaysCatalogItems() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("Red Velvet")))
		.andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("Chocolate")));
	}

}
