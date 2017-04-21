package AdvancedReplacement;

public class Item {

	Integer itemCode;
	String itemName;
	String itemDescription;
	Integer itemPrice;
	
	public Item(){
		
	}
	
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

	public void setItemCode(Integer itemCode) {
		this.itemCode = itemCode;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public void setItemPrice(Integer itemPrice) {
		this.itemPrice = itemPrice;
	}
	
	

}
