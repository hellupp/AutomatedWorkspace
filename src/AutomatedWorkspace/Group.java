package AutomatedWorkspace;
/* Group class with all useful methods, getters and setters, constructors 
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Group {
	
	private String name;
	private String description;
	protected List<Goods> goods;

	public Group(String name) {
		this.name = name;
		this.description = "";
		goods = new ArrayList<>();
	}
	
	public Group(String name, String description) {
		this.name = name;
		this.description = description;
		goods = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Goods> getGoods() {
		Collections.sort(goods, (p1, p2) -> p1.getName().compareTo(p2.getName()));
		return goods;
	}

	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	public void addGood(Goods good) {
		this.goods.add(good);
	}
	
	public void addGood(String goodName) {
		this.goods.add(new Goods(goodName));
	}
	
	public double getCostOfGroup() {
		double cost = 0;
		for (Goods g : getGoods()) {
			cost += (g.getPriceForOne() * g.getAmount());
		}
		return cost;
	}
	

	public String toString() {
		String res = this.getName() + "\n\n";
		for (int i = 0; i < goods.size(); i++) {
			res += (i+1) + ") " + goods.get(i).toString() + "\n";
		}
		return res;
	}
}
