package AutomatedWorkspace;
/* Goods class with all useful methods, getters and setters, constructors 
 */

public class Goods {
	
	private String name;
	private String description;
	private String manufacturer;
	private int amount;
	private double priceForOne;

	public Goods(String goodName) {
		this.name = goodName;
		this.description = "";
		this.manufacturer = "";
		this.priceForOne = 0.0;
		this.amount = 0;
	}
	
	
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public void setAmount(String amount) {
		this.amount = Integer.parseInt(amount);
	}

	public double getPriceForOne() {
		return priceForOne;
	}

	public void setPriceForOne(int priceForOne) {
		this.priceForOne = priceForOne;
	}

	public void setPriceForOne(String price) {
		this.priceForOne = Double.parseDouble(price);
	}
	
	
	public String toString() {
		String res = "";
		res = "Назва товару: " + this.name + "\nОпис: " + this.description + "\nВиробник: " + this.manufacturer +
				"\nЦіна за одиницю: " + this.priceForOne + "\nКількість одиниць:" + this.amount + "\n";
		return res;
	}

}
