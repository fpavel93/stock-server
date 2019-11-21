package com.pavel.stock.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.pavel.stock.dao.ItemDao;

import com.pavel.stock.model.Item;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
public class ItemController {

	@Autowired
	ItemDao dao;

	@GetMapping("/items")
	@ApiOperation(value = "Find all items", notes = "Returning all the items existing in the stock")
	public List<Item> getAllItems() {
		List<Item> itemList = dao.findAll();
		if (itemList.isEmpty()) {
			return null;
		} else {
			return itemList;
		}
	}

	@GetMapping("/item/{itemNo}")
	@ApiOperation(value = "Find item by item number", notes = "Provide an item number to look up specific item in the stock", response = Item.class)
	public Optional<Item> getItem(@ApiParam(value = "Item number in the stoke of the item you need find", required = true) @PathVariable int itemNo) {
		return dao.findById(itemNo);
	}

	@PostMapping(path = "/item", consumes = { "application/json" })
	@ApiOperation(value = "Add item", notes = "Provide an item details in json format to add to the stock")
	public String addItem(@ApiParam(value = "Item (in json format) you need to add to the stoke", required = true) @RequestBody Item item) {
		Item existedItem = dao.findByInventoryCode(item.getInventoryCode()).orElse(null);
		if (existedItem != null) {
			return "Item Inventory Code is already exists in the DataBase";
		} else {
			if (dao.findById(item.getItemNo()).orElse(null) != null) {
				return "Item number is already exists in the DataBase";
			}
			dao.save(item);
			return "Add successfully";
		}
	}

	@DeleteMapping("/item/{itemNo}")
	@ApiOperation(value = "Delete item by item number", notes = "Provide an item number to delete specific item from the stock")
	public String deleteItem(@ApiParam(value = "Item number in the stoke of the item you need to delete", required = true) @PathVariable int itemNo) {
		Item item = dao.findById(itemNo).orElse(null);
		if (item == null) {
			return "Item not exist";
		} else {
			dao.delete(item);
			return "Deleted successfully";
		}
	}

	@PutMapping(path = "/itemAddQuantity")
	@ApiOperation(value = "Deposit quantity of item to the stock", notes = "Provide an item number and the amount which will deposit to the stock")
	public String depositQuantityItem(@ApiParam(value = "Item number in the stoke of the item you need to deposit", required = true) @RequestParam(value = "itemNo") int itemNo,
			@ApiParam(value = "Quantity of the items of this type you need to deposit", required = true) @RequestParam(value = "amount") int amount) {
		Item item = dao.findById(itemNo).orElse(null);
		if (item == null) {
			return "Item not exist";
		} else {
			item.setAmount(item.getAmount() + amount);
			dao.save(item);
			return "Successfully quantity deposit";
		}
	}

	@PutMapping(path = "/itemDecreaseQuantity")
	@ApiOperation(value = "Withdrawal quantity of item from the stock", notes = "Provide an item number and the amount which will Withdrawal from the stock")
	public String withdrawalQuantityItem(@ApiParam(value = "Item number in the stoke of the item you need to withdrawal", required = true) @RequestParam(value = "itemNo") int itemNo,
			@ApiParam(value = "Quantity of the items of this type you need to withdrawal", required = true) @RequestParam(value = "amount") int amount) {
		Item item = dao.findById(itemNo).orElse(null);
		if (item == null) {
			return "Item not exist";
		} else {
			int newAmount = item.getAmount() - amount;
			if (newAmount < 0) {
				return "New amount is less than zero";
			} else {
				item.setAmount(newAmount);
				dao.save(item);
				return "Successfully quantity withdrawal";
			}
		}
	}
}
