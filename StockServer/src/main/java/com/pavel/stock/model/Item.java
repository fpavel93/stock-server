package com.pavel.stock.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@ApiModel(description = "Item details")
public class Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "The unique item number, which is the primary key in the database")
	private int itemNo;
	
	@ApiModelProperty(notes = "The name of the item")
	private String name;
	
	@ApiModelProperty(notes = "The amount of the item")
	private int amount;
	
	@Column(unique = true)
	@ApiModelProperty(notes = "The unique inventory code of the item")
	private int inventoryCode;
	
	public int getItemNo() {
		return itemNo;
	}
	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getInventoryCode() {
		return inventoryCode;
	}
	public void setInventoryCode(int inventoryCode) {
		this.inventoryCode = inventoryCode;
	}
	@Override
	public String toString() {
		return "Item [itemNo=" + itemNo + ", name=" + name + ", amount=" + amount + ", inventoryCode=" + inventoryCode
				+ "]";
	}
	
	

}
