package com.cakefactory.catalog;

import com.cakefactory.catalog.model.Item;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class InMemoryCatalog implements Catalog {

    @Override
    public List<Item> getItems() {
        return List.of(
                new Item("Red Velvet", new BigDecimal("3.99")),
                new Item("Chocolate", new BigDecimal("4.49")),
                new Item("Carrot Cake", new BigDecimal("3.49"))
        );
    }
}