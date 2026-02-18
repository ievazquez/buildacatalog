package com.cakefactory.catalog;

import com.cakefactory.catalog.model.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(JpaCatalogService.class)
class CatalogTest {

	@Autowired
	ItemRepository itemRepository;

	@Autowired
	CatalogService catalog;

	@Test
	@DisplayName("returns items from the database")
	void returnsItems() {
		itemRepository.save(new ItemEntity("Red Velvet", new BigDecimal("3.99")));

		List<Item> items = catalog.getItems();

		assertThat(items).hasSize(1);
		assertThat(items.get(0).getName()).isEqualTo("Red Velvet");
		assertThat(items.get(0).getPrice()).isEqualByComparingTo(new BigDecimal("3.99"));
	}

	@Test
	@DisplayName("maps entities to domain items with name and price")
	void mapsEntitiesToDomainItems() {
		itemRepository.save(new ItemEntity("Chocolate", new BigDecimal("4.49")));
		itemRepository.save(new ItemEntity("Carrot Cake", new BigDecimal("3.49")));

		List<Item> items = catalog.getItems();

		assertThat(items).hasSize(2);
		assertThat(items).allSatisfy(item -> {
			assertThat(item.getName()).isNotBlank();
			assertThat(item.getPrice()).isNotNull();
		});
	}
}