package AdvancedReplacement;

public class Item {

	Integer itemCode;
	String itemName;
	String itemDescription;
	Integer itemPrice;
	
	public Item(Integer itemCode,  String itemName, String itemDescription, Integer itemPrice){
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.itemDescription = itemDescription;
		this.itemPrice = itemPrice;
	}

	public Integer getItemCode() {
		return itemCode;
	}

	public String getItemName() {
		return itemName;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public Integer getItemPrice() {
		return itemPrice;
	}

}