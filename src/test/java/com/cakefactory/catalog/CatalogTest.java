package com.cakefactory.catalog;

import com.cakefactory.catalog.model.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CatalogTest {

	@Autowired
	CatalogService catalog;

	@Test
	@DisplayName("returns a non-empty list of items")
	void returnsItems() {
		List<Item> items = catalog.getItems();

		assertThat(items).isNotEmpty();
	}

	@Test
	@DisplayName("each item has a name and a price")
	void itemsHaveNameAndPrice() {
		List<Item> items = catalog.getItems();

		assertThat(items).allSatisfy(item -> {
			assertThat(item.getName()).isNotBlank();
			assertThat(item.getPrice()).isNotNull();
		});
	}
}