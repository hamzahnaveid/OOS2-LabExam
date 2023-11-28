/* C22428264 - Hamzah Naveid */
public class Item {
	protected String name;
	protected double price;
	
	public Item() {
		this.name = "";
		this.price = 0.0;
	}
	
	public Item(String n, double p) {
		this.name = n;
		this.price = p;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public String toString() {
		return this.name + " | " + this.price;
	}

}
