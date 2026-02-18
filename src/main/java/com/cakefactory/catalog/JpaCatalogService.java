package com.cakefactory.catalog;

import com.cakefactory.catalog.model.Item;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JpaCatalogService implements CatalogService {

    private final ItemRepository itemRepository;

    public JpaCatalogService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<Item> getItems() {
        return itemRepository.findAll().stream()
                .map(entity -> new Item(entity.getName(), entity.getPrice()))
                .toList();
    }
}