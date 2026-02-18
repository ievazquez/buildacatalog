package com.cakefactory.catalog;

import com.cakefactory.catalog.model.Item;
import com.cakefactory.catalog.persistence.ItemEntity;
import com.cakefactory.catalog.persistence.ItemRepository;
import com.cakefactory.catalog.persistence.JpaCatalogService;
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
class JpaCatalogServiceTest {

	@Autowired
	ItemRepository itemRepository;

	@Autowired
	JpaCatalogService jpaCatalogService;

	@Test
	@DisplayName("maps entities to domain items with correct name and price")
	void mapsEntitiesToDomainItems() {
		itemRepository.save(new ItemEntity("Red Velvet", new BigDecimal("3.99")));
		itemRepository.save(new ItemEntity("Chocolate", new BigDecimal("4.49")));

		List<Item> items = jpaCatalogService.getItems();

		assertThat(items).hasSize(2);
		assertThat(items).extracting(Item::getName)
				.containsExactlyInAnyOrder("Red Velvet", "Chocolate");
		assertThat(items).extracting(Item::getPrice)
				.usingComparatorForType(BigDecimal::compareTo, BigDecimal.class)
				.containsExactlyInAnyOrder(new BigDecimal("3.99"), new BigDecimal("4.49"));
	}

	@Test
	@DisplayName("returns empty list when no entities exist")
	void returnsEmptyListWhenNoEntities() {
		List<Item> items = jpaCatalogService.getItems();

		assertThat(items).isEmpty();
	}
}