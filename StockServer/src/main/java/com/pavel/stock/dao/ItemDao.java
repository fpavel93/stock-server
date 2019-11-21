package com.pavel.stock.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pavel.stock.model.Item;

public interface ItemDao extends JpaRepository<Item, Integer>{

	Optional<Item> findByInventoryCode(int inventoryCode);
}
